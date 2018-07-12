package com.yanll.auth.console.configuration;

import com.yanll.auth.console.SwaggerConfig;
import com.yanll.auth.service.domain.PermissionBeanDTO;
import com.yanll.auth.service.domain.UserBeanDTO;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tk.techforge.patron.AuthHandler;
import tk.techforge.patron.interceptors.DefaultRequestMappingInterceptor;
import tk.techforge.patron.interceptors.PatronContextInterceptor;
import tk.techforge.patron.interceptors.PatronInterceptor;


@Configuration
@ComponentScan(basePackages = {"com.yanll.framework.web", "com.yanll.auth.service"})
@ComponentScan(basePackages = {"com.yanll.auth.console.manager", "com.yanll.auth.console.controller"})
@ComponentScan(basePackageClasses = {SwaggerConfig.class})
@MapperScan(basePackages = "com.yanll.auth.service.dao")
public class APPCongfiguration implements WebMvcConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(APPCongfiguration.class);

    @Autowired
    AuthHandler<UserBeanDTO, PermissionBeanDTO> authHandler;

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
        /*自定义拦截器，添加拦截路径和排除拦截路径*/
        logger.info("******Registry patronContextInterceptor.*******");
        registry.addInterceptor(patronContextInterceptor()).excludePathPatterns("/static").addPathPatterns("/**");
        logger.info("******Registry defaultRequestMappingInterceptor.*******");
        registry.addInterceptor(defaultRequestMappingInterceptor()).excludePathPatterns("/static").addPathPatterns("/**");
    }

    @Bean
    public PatronInterceptor patronContextInterceptor() {
        PatronInterceptor patronInterceptor = new PatronContextInterceptor();
        patronInterceptor.setAuthHandler(authHandler);
        patronInterceptor.setIgnoredExpression(new String[]{"/swagger-resources"});
        return patronInterceptor;
    }

    @Bean
    public PatronInterceptor defaultRequestMappingInterceptor() {
        PatronInterceptor patronInterceptor = new DefaultRequestMappingInterceptor();
        patronInterceptor.setAuthHandler(authHandler);
        patronInterceptor.setIgnoredExpression(new String[]{"/swagger-resources"});
        return patronInterceptor;
    }

}
