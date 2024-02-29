package com.javarush.jira;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import javax.sql.DataSource;

@Configuration
@Profile("test")
@Sql(scripts = {"classpath:db/changelog-test.sql", "classpath:data-pg.sql"}, config = @SqlConfig(encoding = "UTF-8"))
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
