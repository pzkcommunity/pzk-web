package org.pzk.web.context;

import org.springframework.beans.factory.Aware;

import javax.servlet.ServletConfig;

/**
 * 用户可以通过实现这个接口获取到ServletConfig
 */
public interface ServletConfigAware extends Aware {
    void setServletConfig(ServletConfig servletConfig);
}
