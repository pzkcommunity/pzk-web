package org.pzk.web.context;

import org.springframework.context.ApplicationContext;

/**
 * web ioc 容器根接口
 * @author pzk
 */
public interface WebApplicationContext extends ApplicationContext {
    String ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE = WebApplicationContext.class.getName() + ".ROOT";

}
