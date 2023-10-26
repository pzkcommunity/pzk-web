package org.pzk.web.resolver;

import org.pzk.web.excpetion.ConvertCastException;
import org.pzk.web.excpetion.HttpRequestMethodNotSupport;
import org.pzk.web.excpetion.NotFoundException;
import org.pzk.web.handler.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *默认异常解析器，尽可能的枚举所有上层发生的异常进行处理
 */
public class DefaultHandlerExceptionResolver implements HandlerExceptionResolver {

    private int order;

    @Override
    public Boolean resolveException(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler, Exception ex) throws IOException {

        final Class<? extends Exception> type = ex.getClass();
        if (type == ConvertCastException.class) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,ex.getMessage());
            return true;
        }else if (type == HttpRequestMethodNotSupport.class){
            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED,ex.getMessage());
            return true;
        }else if (type == NotFoundException.class){
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,ex.getMessage());
            return true;
        }
        return false;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
