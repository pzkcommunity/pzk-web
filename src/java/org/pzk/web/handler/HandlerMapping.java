package org.pzk.web.handler;

import org.springframework.core.Ordered;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取映射器的接口，根据请求路径获取对应的HandlerExecutionChain
 *
 * HandlerExecutionChain中包含拦截器链
 */
public interface HandlerMapping extends Ordered {
//    HandlerExecutionChain getHandler(HttpServletRequest request) throws HttpRequestMethodNotSupportedException, Exception;
    HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception;

}
