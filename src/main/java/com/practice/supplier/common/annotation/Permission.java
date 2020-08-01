package com.practice.supplier.common.annotation;

import com.practice.supplier.common.domain.Const;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {
    String[] roles() default {Const.ADMIN, Const.USER};
}
