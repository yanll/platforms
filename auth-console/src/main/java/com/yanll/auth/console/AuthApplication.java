package com.yanll.auth.console;

import com.yanll.framework.auth.permission.config.ConsolePermissionCongfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by breez on 2016/03/30.
 */
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
//@EnableAutoConfiguration(exclude={
//        DataSourceAutoConfiguration.class,
//        DataSourceTransactionManagerAutoConfiguration.class,
//})
@ComponentScan(
        basePackages = {"com.yanll.framework.web", "com.yanll.auth.service", "com.yanll.auth.console"},
        basePackageClasses = {/*ImportConfiguration.class,*/ ConsolePermissionCongfiguration.class}
)
@MapperScan(basePackages = "com.yanll.auth.service.dao")
public class AuthApplication extends WebMvcConfigurerAdapter {


    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}