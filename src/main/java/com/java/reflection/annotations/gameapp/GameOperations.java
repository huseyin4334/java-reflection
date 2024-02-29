package com.java.reflection.annotations.gameapp;

import static com.java.reflection.annotations.models.annotations.GameAnnotations.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameOperations {


    public static <T> T execute(Object instance) throws InvocationTargetException, IllegalAccessException {
        Class<?> clazz = instance.getClass();

        Map<String, Method> operations = getOperations(clazz);
        Map<String, Field> inputFields = getInputFields(clazz);

        Method finalResult = getFinalResult(clazz);

        return executeWithDependencies(instance, finalResult, operations, inputFields);
    }

    private static <T> T executeWithDependencies(Object instance, Method currentMethod, Map<String, Method> operations, Map<String, Field> inputFields) throws IllegalAccessException, InvocationTargetException {
        List<Object> params = new ArrayList<>(currentMethod.getParameterCount());

        for (Parameter p : currentMethod.getParameters()) {
            DependsOn d = p.getAnnotation(DependsOn.class);
            Input input = p.getAnnotation(Input.class);

            if (d == null && input == null)
                throw new IllegalAccessException("Empty dependency");

            if (d != null) {
                Method method = operations.get(d.value());

                if (method == null)
                    throw new IllegalAccessException(String.format("Dependency %s not found", d.value()));

                params.add(executeWithDependencies(instance, method, operations, inputFields));
            } else {
                Field field = inputFields.get(input.value());

                if (field == null)
                    throw new IllegalAccessException(String.format("Field %s not found", input.value()));

                field.setAccessible(true);
                params.add(field.get(instance));
            }
        }

        return (T) currentMethod.invoke(instance, params.toArray());


    }


    private static Map<String, Method> getOperations(Class<?> clazz) {
        Map<String, Method> operations = new HashMap<>();

        for (Method method : clazz.getDeclaredMethods()) {
            Operation o = method.getAnnotation(Operation.class);
            if (o != null)
                operations.put(o.value(), method);
        }

        return operations;
    }

    private static Map<String, Field> getInputFields(Class<?> clazz) {
        Map<String, Field> operations = new HashMap<>();

        for (Field field : clazz.getDeclaredFields()) {
            Input o = field.getAnnotation(Input.class);
            if (o != null)
                operations.put(o.value(), field);
        }

        return operations;
    }

    private static Method getFinalResult(Class<?> clazz) throws IllegalAccessException {
        for (Method method : clazz.getDeclaredMethods())
            if (method.isAnnotationPresent(FinalResult.class))
                return method;

        throw new IllegalAccessException("FinalResult function not found");
    }
}
