package org.pzk.web.support;

import org.pzk.web.convert.Convert;
import org.pzk.web.intercpetor.InterceptorRegistry;

/**
 * 定义拓展点规范供子类实现,都是default
 */
public interface WebMvcConfigurer {

    default void addIntercept(InterceptorRegistry registry){}
}

