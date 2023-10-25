package org.pzk.web.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

/**
 * servlet上下文后置处理器
 */
public class ServletContextAwareProcessor implements BeanPostProcessor {

    private ServletContext servletContext;

    private ServletConfig servletConfig;


    public ServletContextAwareProcessor(ServletContext servletContext,ServletConfig servletConfig){
        this.servletConfig = servletConfig;
        this.servletContext = servletContext;
    }
    public ServletContextAwareProcessor(ServletContext servletContext){
        this(servletContext,null);
    }
    public ServletContextAwareProcessor(ServletConfig servletConfig){
        this(null,servletConfig);
    }

    public ServletConfig getServletConfig() {
        return servletConfig;
    }

    public ServletContext getServletContext() {
        return servletContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (getServletContext() != null && bean instanceof ServletContextAware) {
            ((ServletContextAware) bean).setServletContext(getServletContext());
        }
        if (getServletConfig() != null && bean instanceof ServletConfigAware) {
            ((ServletConfigAware) bean).setServletConfig(getServletConfig());
        }
        return bean;
    }
}
