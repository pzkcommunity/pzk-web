package org.pzk.web.test;

import org.pzk.web.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 在web容器启动的时候创建对象，而且在整个创建对象的过程中，会调用相应方法来初始化容器以及前端控制器
 * 编写好该类之后，就相当于是在以前我们配置好了web.xml文件
 */
public class QuickStart extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * 获取包扫描配置类，相当于xml配置中的spring配置文件
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    /**
     * 获取包扫描配置类，相当于xml配置中的springmvc.xml
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppConfig.class};
    }
}
