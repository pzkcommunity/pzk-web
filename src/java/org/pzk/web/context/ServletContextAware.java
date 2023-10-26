package org.pzk.web.context;

import org.springframework.beans.factory.Aware;

import javax.servlet.ServletContext;

/**
 * 用户可以通过实现这个接口获取到ServletContext
 */
public interface ServletContextAware extends Aware {
    void setServletContext(ServletContext servletContext);
}
