package com.cyecize.demo.config.routing;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TransactionalHelper {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Object runWithTransaction(ThrowableSupplier supplier) throws Exception {
        return supplier.supply();
    }

    @FunctionalInterface
    public interface ThrowableSupplier {
        Object supply() throws Exception;
    }
}
