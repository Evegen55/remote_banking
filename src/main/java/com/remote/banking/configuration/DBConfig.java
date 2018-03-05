package com.remote.banking.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DBConfig {

    @Resource(name = "db_properties")
    private Properties globalProperties;
    @Resource(name = "jpaProperties")
    private Properties jpaProperties;

    @Primary
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        final String datasource_url = globalProperties.getProperty("datasource.url");
        final String driver_class_name = globalProperties.getProperty("datasource.driver-class-name");
        final String datasource_username = globalProperties.getProperty("datasource.username");
        final String datasource_password = globalProperties.getProperty("datasource.password");

        final DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create()
                .url(datasource_url)
                .driverClassName(driver_class_name)
                .password(datasource_password)
                .username(datasource_username);

        return dataSourceBuilder.build();
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("dataSource") DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = builder
                .dataSource(dataSource)
                .packages("com.remote.banking.models.for_rdbms")
                .persistenceUnit("remote_banking_db_JPAUnit")
                .build();
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        localContainerEntityManagerFactoryBean.setJpaProperties(jpaProperties);

        return localContainerEntityManagerFactoryBean;
    }

    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory
                    entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
