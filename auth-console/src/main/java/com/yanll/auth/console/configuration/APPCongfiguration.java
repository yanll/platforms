package com.yanll.auth.console.configuration;

import com.yanll.auth.console.SwaggerConfig;
import com.yanll.auth.console.manager.AuthRealm;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tk.techforge.patron.SecurityManager;
import tk.techforge.patron.interceptors.DefaultRequestMappingInterceptor;
import tk.techforge.patron.interceptors.DefaultResourceInterceptor;
import tk.techforge.patron.interceptors.PatronInterceptor;
import tk.techforge.patron.subject.DefaultSubjectFactory;
import tk.techforge.patron.subject.SubjectFactory;

@Configuration
@ComponentScan(basePackages = {"com.yanll.framework.web", "com.yanll.auth.service"})
@ComponentScan(basePackages = {"com.yanll.auth.console.manager", "com.yanll.auth.console.controller"})
@ComponentScan(basePackageClasses = {SwaggerConfig.class})
@MapperScan(basePackages = "com.yanll.auth.service.dao")
public class APPCongfiguration implements WebMvcConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(APPCongfiguration.class);


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        logger.info("addCorsMappings...");
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowedHeaders("*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.info("******Registry DefaultRequestMappingInterceptor.*******");
        //自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(defaultResourceInterceptor())
                .excludePathPatterns("/static")
                .addPathPatterns("/**");
        registry.addInterceptor(requestMappingPermissionInterceptor())
                .excludePathPatterns("/static")
                .addPathPatterns("/**");
    }

    @Bean
    public PatronInterceptor requestMappingPermissionInterceptor() {
        PatronInterceptor patronInterceptor = new DefaultRequestMappingInterceptor();
        patronInterceptor.setSecurityManager(securityManager());
        return patronInterceptor;
    }

    @Bean
    public PatronInterceptor defaultResourceInterceptor() {
        PatronInterceptor patronInterceptor = new DefaultResourceInterceptor();
        patronInterceptor.setSecurityManager(securityManager());
        return patronInterceptor;
    }


    @Bean
    public SecurityManager securityManager() {
        SecurityManager securityManager = new AuthRealm();
        return securityManager;
    }

    @Bean
    public SubjectFactory subjectFactory() {
        SubjectFactory factory = new DefaultSubjectFactory();
        return factory;
    }
}
