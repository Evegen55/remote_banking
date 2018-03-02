package com.remote.banking.configuration;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class PropertyConfig {

    @Bean(name = "global_properties")
    //use it with @Resource(name = "global_properties") private Properties globalProperties;
    public static PropertiesFactoryBean global() {
        final PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setLocation(new ClassPathResource("application.properties"));
        return bean;
    }

    @Bean(name = "db_properties")
    public static PropertiesFactoryBean dbconnections() {
        final PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setLocation(new ClassPathResource("db_connections.properties"));
        return bean;
    }

    @Bean(name = "jpaProperties")
    public static PropertiesFactoryBean jpaProperties() {
        final PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setLocation(new ClassPathResource("jpa.properties"));
        return bean;
    }

    @Bean(name="validator")
    public static LocalValidatorFactoryBean validatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }

}
