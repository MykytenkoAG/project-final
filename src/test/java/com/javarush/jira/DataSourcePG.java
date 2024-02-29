package com.javarush.jira;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("test")
public class DataSourcePG {

    @Bean
    public DataSource getDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://localhost:5433/jira-test")
                .username("jira")
                .password("JiraRush")
                .build();
    }

}
