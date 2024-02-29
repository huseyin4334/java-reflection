package com.java.reflection.annotations.repetable;

import com.java.reflection.annotations.models.annotations.ScanPackages;
import com.java.reflection.annotations.models.annotations.ScheduleAnnotations.*;
import com.java.reflection.annotations.models.databases.Cache;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class ScheduleApp {

    public static void main(String[] args) {

        List<Method> methods = getScheduledMethods(List.of(Cache.class));


        for (Method method : methods) {
            schedule(method);
        }
    }

    public static void schedule(Method method) {
        Schedule[] schedules = method.getAnnotationsByType(Schedule.class);

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

        for (Schedule s : schedules) {
            service.scheduleAtFixedRate(
                    () -> runMethod(method, s),
                    s.delaySeconds(),
                    s.periodSeconds(),
                    TimeUnit.SECONDS
            );
        }
    }

    private static void runMethod(Method method, Schedule schedule) {
        Date curr = new Date();
        SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");

        System.out.println("Running " + method.getName() + " at " + f.format(curr) + " with name " + schedule.name());

        try {
            method.invoke(null);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static List<Method> getScheduledMethods(List<Class<?>> classes) {
        List<Method> scheduledMethods = new ArrayList<>();

        for (Class<?> clazz : classes) {
            if (!clazz.isAnnotationPresent(ScheduledExecutorClass.class))
                continue;

            for (Method method : clazz.getDeclaredMethods()) {
                if (method.getAnnotationsByType(Schedule.class).length != 0) {
                    scheduledMethods.add(method);
                }
            }
        }

        return scheduledMethods;
    }
}
