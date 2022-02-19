package com.cyecize.demo.config.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Slf4j
public class RoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        final DataSourceType sourceType = DatabaseContextHolder.peekDataSource().orElse(null);

        if (sourceType != null) {
            log.info("Chose {} data source for the next transaction.", sourceType);
        }

        return sourceType;
    }
}
