package com.katia.spring.security.configs;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;

public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/api/user").setViewName("user");
        registry.addViewController("/api/login").setViewName("login");
        registry.addViewController("/api/admin").setViewName("admin");
    }


}
