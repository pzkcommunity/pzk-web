package org.pzk.web.resolver;

import org.springframework.core.MethodParameter;
import org.pzk.web.convert.ConvertComposite;
import org.pzk.web.handler.HandlerMethod;
import org.pzk.web.support.WebServletRequest;

/**
 * 参数解析器顶层接口
 */
public interface HandlerMethodArgumentResolver {

    // 当前参数是否支持当前的请求中携带的数据
    boolean supportsParameter(MethodParameter parameter);


    /**
     * 解析参数
     *
     * @param parameter          参数
     * @param handlerMethod      映射器
     * @param webServletRequest  请求
     * @param convertComposite   类型转换器
     * @return
     * @throws Exception
     */
    Object resolveArgument(MethodParameter parameter, HandlerMethod handlerMethod, WebServletRequest webServletRequest, ConvertComposite convertComposite) throws Exception;
}
