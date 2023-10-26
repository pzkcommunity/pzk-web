package org.pzk.web.handler;

import javax.servlet.http.HttpServletRequest;

/**
 * 映射器接口，根据请求路径获取对应的HandlerExecutionChain
 *
 * HandlerExecutionChain中包含拦截器链
 */
public interface HandlerMapping {
    HandlerExecutionChain getHandler(HttpServletRequest request) throws HttpRequestMethodNotSupportedException, Exception;
}
