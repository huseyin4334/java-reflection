package com.java.reflection.annotations.models.annotations;

import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RetryOperation {

    Class<? extends Throwable>[] retryOnException() default {IOException.class};
    long durationBetweenRetries() default 1000;
    String failMessage() default "Operation failed";
    int retryCount() default 3;

}
