package org.pzk.web.handler;

import javax.servlet.http.HttpServletRequest;

/**
 * 不提供实现，只说明有这个场景
 * @author pzk
 */
public class BeanNameUrlHandlerMapping extends AbstractHandlerMapping{
    @Override
    protected org.xhy.web.handler.HandlerMethod getHandlerInternal(HttpServletRequest request) {
        return null;
    }

    @Override
    protected void detectHandlerMethod(String name) throws Exception {

    }

    @Override
    protected boolean isHandler(Class type) {
        return false;
    }

    @Override
    protected void setOrder(int order) {
        this.order = 2;
    }
}
