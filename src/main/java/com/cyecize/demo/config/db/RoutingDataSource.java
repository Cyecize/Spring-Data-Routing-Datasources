package com.cyecize.demo.config.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Stack;

@Slf4j
public class RoutingDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<Stack<DataSourceType>> ctx = new ThreadLocal<>();

    public static void setCtx(DataSourceType dataSourceType) {
        getCtx().push(dataSourceType);
    }

    public static void restoreCtx() {
        Stack<DataSourceType> ctx = getCtx();
        if (!ctx.isEmpty()) {
            ctx.pop();
        }
    }

    @Override
    protected Object determineCurrentLookupKey() {
        Stack<DataSourceType> ctx = getCtx();

        if (ctx.isEmpty()) {
            return null;
        }

        DataSourceType sourceType = ctx.peek();
        log.info("Chose {} data source for the next transaction.", sourceType);

        return sourceType;
    }

    private static Stack<DataSourceType> getCtx() {
        if (ctx.get() == null) {
            ctx.set(new Stack<>());
        }

        return ctx.get();
    }
}
