package com.cyecize.demo.config.routing;

import com.cyecize.demo.config.db.DataSourceType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface WithDatabase {

    DataSourceType value() default DataSourceType.PRIMARY;
}
