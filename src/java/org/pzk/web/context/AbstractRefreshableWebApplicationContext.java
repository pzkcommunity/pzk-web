package org.pzk.web.context;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.AbstractRefreshableConfigApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

/**
 * 用于容器刷新接入点
 *
 */
public abstract class AbstractRefreshableWebApplicationContext extends AbstractRefreshableConfigApplicationContext
        implements ConfigurableWebApplicationContext{

    private ServletContext servletContext;

    private ServletConfig servletConfig;


    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public void setServletConfig(ServletConfig servletConfig) {
        this.servletConfig = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return this.servletConfig;
    }

    @Override
    public ServletContext getServletContext(){
        return this.servletContext;
    }


    @Override
    protected void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        // 注册ServletContext后置处理器
        beanFactory.addBeanPostProcessor(new ServletContextAwareProcessor(this.servletContext,this.servletConfig));

        /**
         * A类中有test方法，B类实现A类，且有一个test属性。也就是说当前会有一个set方法和一个set注入。
         * 在特定场景下会干扰。因此忽略set注入
         */
        beanFactory.ignoreDependencyInterface(ServletContextAware.class);
        beanFactory.ignoreDependencyInterface(ServletConfig.class);
    }

}
