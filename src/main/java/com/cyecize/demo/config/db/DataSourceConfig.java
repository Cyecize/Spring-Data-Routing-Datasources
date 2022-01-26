package com.cyecize.demo.config.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class DataSourceConfig {

    private final Environment env;

    @Bean
    public DataSource getDataSource() {
        final Map<Object, Object> dataSources = this.buildDataSources();

        final RoutingDataSource routingDataSource = new RoutingDataSource();
        routingDataSource.setTargetDataSources(dataSources);
        routingDataSource.setDefaultTargetDataSource(dataSources.get(DataSourceType.PRIMARY));

        return routingDataSource;
    }

    private Map<Object, Object> buildDataSources() {
        final Map<Object, Object> result = new HashMap<>();

        for (DataSourceType sourceType : DataSourceType.values()) {
            result.put(sourceType, this.buildDataSource(sourceType));
        }

        return result;
    }

    private DataSource buildDataSource(DataSourceType sourceType) {
        final HikariConfig config = new HikariConfig();

        config.setJdbcUrl(this.env.getProperty(String.format("datasource.%s.url", sourceType.getName())));
        config.setUsername(this.env.getProperty(String.format("datasource.%s.username", sourceType.getName())));
        config.setPassword(this.env.getProperty(String.format("datasource.%s.password", sourceType.getName())));
        config.setDriverClassName(this.env.getProperty(String.format(
                "datasource.%s.driver-class-name", sourceType.getName()
        )));

        config.setAutoCommit(false);

        return new HikariDataSource(config);
    }
}
