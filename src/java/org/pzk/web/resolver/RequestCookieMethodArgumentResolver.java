package org.pzk.web.resolver;

import org.springframework.core.MethodParameter;
import org.pzk.web.annotation.Cookie;
import org.pzk.web.convert.ConvertComposite;
import org.pzk.web.excpetion.NotFoundException;
import org.pzk.web.handler.HandlerMethod;
import org.pzk.web.support.WebServletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * 解析cookie当中的参数
 */
public class RequestCookieMethodArgumentResolver implements HandlerMethodArgumentResolver{
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Cookie.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, HandlerMethod handlerMethod, WebServletRequest webServletRequest, ConvertComposite convertComposite) throws Exception {

        final Cookie parameterAnnotation = parameter.getParameterAnnotation(Cookie.class);
        String name = "";
        name = parameterAnnotation.value().equals("") ? parameter.getParameterName() :parameterAnnotation.value();
        final HttpServletRequest request = webServletRequest.getRequest();
        // 获取所有cookie
        final javax.servlet.http.Cookie[] cookies = request.getCookies();
        // 遍历拿值
        for (javax.servlet.http.Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return convertComposite.convert(handlerMethod,parameter.getParameterType(),cookie.getValue());
            }
        }

        if (parameterAnnotation.require()){
            throw new NotFoundException(handlerMethod.getPath() +"cookie没有携带: "+ name);

        }

        return null;
    }
}
