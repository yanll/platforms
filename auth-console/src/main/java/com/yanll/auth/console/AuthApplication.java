package com.yanll.auth.console;

import com.yanll.auth.console.configuration.APPCongfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by breez on 2016/03/30.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = {APPCongfiguration.class})
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}