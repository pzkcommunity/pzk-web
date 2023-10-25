package org.pzk.web;

import org.pzk.web.context.AnnotationConfigWebApplicationContext;
import org.pzk.web.context.WebApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.ObjectUtils;

import javax.servlet.Filter;

/**
 * 留给子类的配置类创建spring ioc以及web ioc
 */
public abstract class AbstractAnnotationConfigDispatcherServletInitializer extends AbstractDispatcherServletInitializer{

    @Override
    protected AnnotationConfigApplicationContext createRootApplicationContext() {

        final Class<?>[] rootConfigClasses = getRootConfigClasses();
        if (!ObjectUtils.isEmpty(rootConfigClasses)){
            final AnnotationConfigApplicationContext rootContext = new AnnotationConfigApplicationContext();
            rootContext.register(rootConfigClasses);
            return rootContext;
        }
        return null;
    }


    @Override
    protected WebApplicationContext createWebApplicationContext() {

        final Class<?>[] webConfigClasses = getWebConfigClasses();
        if (!ObjectUtils.isEmpty(webConfigClasses)){
            final AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
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