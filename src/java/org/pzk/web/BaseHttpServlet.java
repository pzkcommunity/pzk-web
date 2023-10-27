package org.pzk.web;

import org.pzk.web.context.AbstractRefreshableWebApplicationContext;
import org.pzk.web.context.WebApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.ObjectUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * 初始化ioc容器以及配置信息
 */
public abstract class BaseHttpServlet extends HttpServlet {

    protected ApplicationContext webApplicationContext;

    public BaseHttpServlet(ApplicationContext webApplicationContext){
        this.webApplicationContext = webApplicationContext;
    }

    // web ioc初始化以及配置
    @Override
    public void init() throws ServletException {
        //获取父容器
        final ServletContext servletContext = getServletContext();

        ApplicationContext rootContext = (ApplicationContext) servletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);

        // 在springboot场景下会根据当前存在类创建不同ioc,在boot下直接不管
        if (!ObjectUtils.isEmpty(webApplicationContext)){

            if (!(this.webApplicationContext instanceof AnnotationConfigApplicationContext)){
                // 需要转换
                AbstractRefreshableWebApplicationContext wac = (AbstractRefreshableWebApplicationContext) this.webApplicationContext;
                // 设置父子容器
                if (wac.getParent() == null){
                    wac.setParent(rootContext);
                }
                // 配置上下文
                wac.setServletContext(servletContext);
                wac.setServletConfig(getServletConfig());
                // web容器刷新
                wac.refresh();
            }
        }
        onRefresh(webApplicationContext);
    }

    //抽象方法，让子类实现
    protected abstract void onRefresh(ApplicationContext webApplicationContext);
}
