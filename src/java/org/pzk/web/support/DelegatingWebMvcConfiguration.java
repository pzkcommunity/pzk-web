package org.pzk.web.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.pzk.web.intercpetor.InterceptorRegistry;

import java.util.List;

public class DelegatingWebMvcConfiguration extends WebMvcConfigurationSupport{


    private WebMvcComposite webMvcComposite = new WebMvcComposite();

    @Autowired(required = false)
    public void setWebMvcComposite(List<WebMvcConfigurer> webMvcConfigurers){
        webMvcComposite.addWebMvcConfigurers(webMvcConfigurers);

    }

    @Override
    protected void getIntercept(InterceptorRegistry registry) {
        webMvcComposite.addIntercept(registry);
    }
}
