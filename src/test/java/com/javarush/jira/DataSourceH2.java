package com.javarush.jira;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import javax.sql.DataSource;

@Configuration
@Profile("h2")
@Sql(scripts = {"classpath:db/changelog-test.sql", "classpath:data-h2.sql"}, config = @SqlConfig(encoding = "UTF-8"))
public class DataSourceH2 {

    @Bean
    public DataSource getDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.h2.Driver")
                .url("jdbc:h2:mem:mydb")
                .username("sa")
                .password("password")
                .build();
    }

}
