package org.pzk.web.context;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.lang.Nullable;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

/**
 * 用于制定存放ServletContext和ServletConfig规范
 * ServletContext servlet中的上下文
 * ServletConfig 配置类
 *
 * @author pzk
 */
public interface ConfigurableWebApplicationContext extends WebApplicationContext, ConfigurableApplicationContext {
    void setServletContext(@Nullable ServletContext servletContext);


    void setServletConfig(@Nullable ServletConfig servletConfig);


    ServletConfig getServletConfig();


    ServletContext getServletContext();
}
