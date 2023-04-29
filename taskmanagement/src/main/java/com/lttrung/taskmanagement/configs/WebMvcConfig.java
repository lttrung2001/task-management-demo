package com.lttrung.taskmanagement.configs;

import com.lttrung.taskmanagement.interceptors.TokenInterceptor;
import com.lttrung.taskmanagement.utils.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new TokenInterceptor(jwtHelper)).addPathPatterns("/api/change-password");
    }
}
