package com.remote.banking.app;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan("com.remote.banking")
public class SpringBootRestApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringBootRestApplication.class);
        application.setBannerMode(Banner.Mode.CONSOLE);
        application.run(args);
    }
}
