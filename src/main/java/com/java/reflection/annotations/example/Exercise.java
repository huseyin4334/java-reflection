package com.java.reflection.annotations.example;

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

public class Exercise {

    /**
     * Complete your code here if necessary
     */
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface OpenResources {
        /**
         * Complete your code here if necessary
         */
    }

    public Set<Method> getAllAnnotatedMethods(Object input) {
        Set<Method> annotatedMethods = new HashSet<>();

        for (Method method : input.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(OpenResources.class)) {
                annotatedMethods.add(method);
            }
        }

        /**
         * Complete your code here
         */
        return annotatedMethods;
    }
}