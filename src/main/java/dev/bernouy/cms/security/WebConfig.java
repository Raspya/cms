package dev.bernouy.cms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private InterceptorAuth interceptorAuth;

    @Autowired
    public WebConfig( InterceptorAuth interceptorAuth ){
        this.interceptorAuth = interceptorAuth;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptorAuth);
    }
}