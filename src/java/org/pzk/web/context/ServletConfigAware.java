package org.pzk.web.context;

import org.springframework.beans.factory.Aware;

import javax.servlet.ServletConfig;

public interface ServletConfigAware extends Aware {
    void setServletConfig(ServletConfig servletConfig);
}
