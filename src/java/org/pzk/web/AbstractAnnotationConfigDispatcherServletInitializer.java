package org.pzk.web;

import org.pzk.web.context.AnnotationConfigWebApplicationContext;
import org.pzk.web.context.WebApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.ObjectUtils;

import javax.servlet.Filter;

/**
 * 实现创建父容器和子容器，扫描父子容器配置类由子类实现
 */
public abstract class AbstractAnnotationConfigDispatcherServletInitializer extends AbstractDispatcherServletInitializer{

    @Override
    protected AnnotationConfigApplicationContext createRootApplicationContext() {

        //子类需要实现这个方法，扫描的父容器配置类
        //获取包扫描配置类，相当于xml配置中的applicationContext.xml
        final Class<?>[] rootConfigClasses = getRootConfigClasses();
        if (!ObjectUtils.isEmpty(rootConfigClasses)){
            //创建root 父容器
            final AnnotationConfigApplicationContext rootContext = new AnnotationConfigApplicationContext();
            //加载配置类
            rootContext.register(rootConfigClasses);
            return rootContext;
        }
        return null;
    }


    @Override
    protected WebApplicationContext createServletApplicationContext() {

        //子类需要实现这个方法，扫描的子容器配置类
        //获取包扫描配置类，相当于xml配置中的springmvc.xml
        final Class<?>[] webConfigClasses = getServletConfigClasses();
        if (!ObjectUtils.isEmpty(webConfigClasses)){
            //创建子容器
            final AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
            //加载配置类
            webContext.register(webConfigClasses);
            return webContext;
        }
        return null;
    }

    @Override
    protected Filter[] getFilters() {
        return new Filter[0];
    }

}