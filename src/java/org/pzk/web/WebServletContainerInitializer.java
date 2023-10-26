package org.pzk.web;

import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Set;

/**
 * web ioc 初始化
 *
 * tomcat中留有拓展点ServletContainerInitializer。
 *
 * ServletContainerInitializer的子类由SPI进行加载
 *
 * @HandlesTypes(WebApplicationInitializer.class) 会将WebApplicationInitializer子类传入onStartup方法中
 *
 * 因此可以由用户继承ioc相关类，配置相关包扫描类，初始化ioc容器
 */
@HandlesTypes(WebApplicationInitializer.class)
public class WebServletContainerInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> webApplications, ServletContext ctx) throws ServletException {

        if (!ObjectUtils.isEmpty(webApplications)){
            final ArrayList<WebApplicationInitializer> initializers = new ArrayList<>(webApplications.size());

            //排除接口和抽象类
            for (Class<?> webApplication : webApplications) {
                if (!webApplication.isInterface() && !Modifier.isAbstract(webApplication.getModifiers())
                        && WebApplicationInitializer.class.isAssignableFrom(webApplication)){//是WebApplicationInitializer的子
                    try {
                        //spring 工具类实例化
                        initializers.add((WebApplicationInitializer) ReflectionUtils.accessibleConstructor(webApplication).newInstance());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            // 统一调用onStartUp()方法
            if (!ObjectUtils.isEmpty(initializers)){
                for (WebApplicationInitializer initializer : initializers) {
                    initializer.onStartUp(ctx);
                }
            }
        }

    }
}
