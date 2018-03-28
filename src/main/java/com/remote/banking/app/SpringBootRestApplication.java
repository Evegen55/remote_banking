package com.remote.banking.app;

import com.google.gson.GsonBuilder;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"com.remote.banking"})
public class SpringBootRestApplication extends SpringBootServletInitializer {

    public static final GsonBuilder PRETTY_PRINTING_BUILDER = new GsonBuilder().setPrettyPrinting();

    public static void main(final String[] args) {
        final SpringApplication application = new SpringApplication(SpringBootRestApplication.class);
        application.setBannerMode(Banner.Mode.CONSOLE);
        application.run(args); //ConfigurableApplicationContext configurableApplicationContext autowired
    }
}
