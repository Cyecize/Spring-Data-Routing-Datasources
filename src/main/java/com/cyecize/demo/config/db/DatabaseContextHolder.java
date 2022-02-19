package com.cyecize.demo.config.db;

import java.util.Optional;
import java.util.Stack;

public final class DatabaseContextHolder {

    private static final ThreadLocal<Stack<DataSourceType>> ctx = new ThreadLocal<>();

    public static void setCtx(DataSourceType dataSourceType) {
        getCtx().push(dataSourceType);
    }

    public static void restoreCtx() {
        final Stack<DataSourceType> ctx = getCtx();
        if (!ctx.isEmpty()) {
            ctx.pop();
        }
    }

    public static Optional<DataSourceType> peekDataSource() {
        final Stack<DataSourceType> ctx = getCtx();

        if (ctx.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(ctx.peek());
    }

    private static Stack<DataSourceType> getCtx() {
        if (ctx.get() == null) {
            ctx.set(new Stack<>());
        }

        return ctx.get();
    }
}
