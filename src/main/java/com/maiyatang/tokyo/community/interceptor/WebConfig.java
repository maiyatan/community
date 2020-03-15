package com.maiyatang.tokyo.community.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebMvc 这个标签会拦截js css文件
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截所有请求
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**");
        // 不拦截的
//        registry.addInterceptor(new ThemeInterceptor()).excludePathPatterns("/admin/**");

//        registry.addInterceptor(new LocaleInterceptor());
//        registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/secure/*");
    }
}