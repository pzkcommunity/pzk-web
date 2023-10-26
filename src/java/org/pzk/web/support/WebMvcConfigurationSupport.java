package org.pzk.web.support;

import org.springframework.context.annotation.Bean;
import org.pzk.web.adapter.HandlerMethodAdapter;
import org.pzk.web.adapter.RequestMappingHandlerMethodAdapter;
import org.pzk.web.handler.HandlerMapping;
import org.pzk.web.handler.RequestMappingHandlerMapping;
import org.pzk.web.intercpetor.HandlerInterceptor;
import org.pzk.web.intercpetor.InterceptorRegistry;
import org.pzk.web.intercpetor.MappedInterceptor;
import org.pzk.web.resolver.DefaultHandlerExceptionResolver;
import org.pzk.web.resolver.ExceptionHandlerExceptionResolver;
import org.pzk.web.resolver.HandlerExceptionResolver;

import java.util.List;

/**
 * 初始化组件
 */
public abstract class WebMvcConfigurationSupport {

    // 初始化组件

    @Bean
    public HandlerMapping handlerMapping(){

        final RequestMappingHandlerMapping requestMappingHandlerMapping = new RequestMappingHandlerMapping();
        requestMappingHandlerMapping.setOrder(0);
        final InterceptorRegistry registry = new InterceptorRegistry();
        getIntercept(registry);
        // todo 通过 registry 获取 MappedInterceptor
        // 获取拦截器
        final List<MappedInterceptor> interceptors = registry.getInterceptors();
        requestMappingHandlerMapping.addHandlerInterceptors(interceptors);
        // 添加拦截器
        return requestMappingHandlerMapping;
    }

    protected abstract void getIntercept(InterceptorRegistry registry);

    @Bean
    public HandlerMethodAdapter handlerMethodAdapter(){
        final RequestMappingHandlerMethodAdapter requestMappingHandlerMethodAdapter = new RequestMappingHandlerMethodAdapter();
        requestMappingHandlerMethodAdapter.setOrder(0);
        return requestMappingHandlerMethodAdapter;
    }

    @Bean
    public HandlerExceptionResolver defaultHandlerExceptionResolver(){

        final DefaultHandlerExceptionResolver defaultHandlerExceptionResolver = new DefaultHandlerExceptionResolver();
        defaultHandlerExceptionResolver.setOrder(1);
        return defaultHandlerExceptionResolver;
    }

    @Bean
    public HandlerExceptionResolver exceptionHandlerExceptionResolver(){

        final ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver = new ExceptionHandlerExceptionResolver();
        exceptionHandlerExceptionResolver.setOrder(0);
        return exceptionHandlerExceptionResolver;
    }

}

