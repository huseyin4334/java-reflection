package com.java.reflection.annotations.models.databases;


import com.java.reflection.annotations.models.annotations.ScheduleAnnotations.*;

@ScheduledExecutorClass
public class Cache {


    @Schedule(
            delaySeconds = 2,
            periodSeconds = 10
    )
    @Schedule(
            name = "reloadCache",
            delaySeconds = 4,
            periodSeconds = 20
    )
    public static void reloadCache() {
        System.out.println("Reloading cache...");
    }
}
