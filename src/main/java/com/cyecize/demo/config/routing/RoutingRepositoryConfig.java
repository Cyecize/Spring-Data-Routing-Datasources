package com.cyecize.demo.config.routing;

import com.cyecize.demo.api.account.AccountRepository;
import com.cyecize.demo.config.db.DataSourceType;
import com.cyecize.demo.config.db.RoutingDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Proxy;

@Slf4j
@Configuration
public class RoutingRepositoryConfig {

    private final AccountRepository accountRepository;

    private final TransactionalHelper transactionalHelper;

    public RoutingRepositoryConfig(@Qualifier("accountRepositoryNonConfig") AccountRepository accountRepository,
                                   TransactionalHelper transactionalHelper) {
        this.accountRepository = accountRepository;
        this.transactionalHelper = transactionalHelper;
    }

    @Bean
    @Primary
    public AccountRepository accountRepository() {
        return this.createProxyInstance(this.accountRepository);
    }

    private <E, ID, T extends JpaRepository<E, ID>> T createProxyInstance(T repository) {
        Class<?> repositoryType = repository.getClass().getInterfaces()[0];
        DataSourceType dataSourceType = repositoryType.getAnnotation(WithDatabase.class).value();

        Object instance;
        try {
            instance = Proxy.newProxyInstance(
                    Thread.currentThread().getContextClassLoader(),
                    new Class[]{repositoryType},
                    (proxy, method, args) -> this.transactionalHelper.runWithTransaction(() -> {
                        try {
                            RoutingDataSource.setCtx(dataSourceType);
                            return method.invoke(repository, args);
                        } finally {
                            RoutingDataSource.restoreCtx();
                        }
                    })
            );
        } catch (Exception ex) {
            log.error("Error while creating proxy for class {}", repository, ex);
            throw new RuntimeException(ex);
        }

        return (T) instance;
    }
}
