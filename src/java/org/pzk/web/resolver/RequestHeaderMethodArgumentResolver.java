package org.pzk.web.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.util.ObjectUtils;
import org.pzk.web.annotation.RequestHeader;
import org.pzk.web.convert.ConvertComposite;
import org.pzk.web.excpetion.NotFoundException;
import org.pzk.web.handler.HandlerMethod;
import org.pzk.web.support.WebServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 获取请求头中的指定内容
 */
public class RequestHeaderMethodArgumentResolver implements HandlerMethodArgumentResolver{
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestHeader.class) && parameter.getParameterType() != Map.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, HandlerMethod handlerMethod, WebServletRequest webServletRequest, ConvertComposite convertComposite) throws Exception {

        String name = "";
        final RequestHeader parameterAnnotation = parameter.getParameterAnnotation(RequestHeader.class);
        name = parameterAnnotation.value().equals("") ? parameter.getParameterName() : parameterAnnotation.value();

        final HttpServletRequest request = webServletRequest.getRequest();
        if (parameterAnnotation.require() && ObjectUtils.isEmpty(request.getHeader(name))){
            throw new NotFoundException(handlerMethod.getPath() + "请求头没有携带: " + name);
        }
        return convertComposite.convert(handlerMethod,parameter.getParameterType(),request.getHeader(name));

    }
}
