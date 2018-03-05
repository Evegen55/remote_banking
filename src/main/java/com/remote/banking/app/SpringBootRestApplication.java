package com.remote.banking.app;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"com.remote.banking"})
public class SpringBootRestApplication extends SpringBootServletInitializer {

    public static void main(final String[] args) {
        final SpringApplication application = new SpringApplication(SpringBootRestApplication.class);
        application.setBannerMode(Banner.Mode.CONSOLE);
        application.run(args);
    }
}
