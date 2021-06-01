package com.eis.gateway.config;

import com.eis.gateway.interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePath = new ArrayList<>();
        excludePath.add("/trader/login");
        registry.addInterceptor(new AuthenticationInterceptor()).addPathPatterns("/trader/**")
                .excludePathPatterns(excludePath);
    }
}
