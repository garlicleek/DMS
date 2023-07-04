package com.lihuo.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\";
        String path = "/home/java/static/";
        registry.addResourceHandler("/**").addResourceLocations("file:" + path);
    }

}
