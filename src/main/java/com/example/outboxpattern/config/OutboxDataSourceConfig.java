/*package com.example.outboxpattern.config;

import com.example.outboxpattern.domain.model.event.Event;
import com.zaxxer.hikari.HikariDataSource;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.outboxpattern.repository.Outbox",
        entityManagerFactoryRef = "outboxEntityManagerFactory",
        transactionManagerRef= "outboxTransactionManager")
public class OutboxDataSourceConfig {
    @Bean
    @ConfigurationProperties("spring.second-datasource")
    public DataSourceProperties outboxDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.second-datasource.configuration")
    public DataSource outboxDataSource() {
        return outboxDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean(name = "outboxEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean outboxEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(outboxDataSource())
                .packages(Event.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager outboxTransactionManager(
            final @Qualifier("outboxEntityManagerFactory") LocalContainerEntityManagerFactoryBean outboxEntityManagerFactory) {
        return new JpaTransactionManager(outboxEntityManagerFactory.getObject());
    }
}*/
