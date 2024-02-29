package com.java.reflection.annotations.models.annotations;

import java.lang.annotation.*;

public class ScheduleAnnotations {

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ScheduledExecutorClass {
    }


    @Repeatable(Schedules.class)
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Schedule {
        int delaySeconds() default 0;
        int periodSeconds();

        String name() default "default";
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Schedules {
        Schedule[] value();
    }
}
