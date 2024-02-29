package com.java.reflection.method.apps;

import com.java.reflection.method.models.FileLogger;
import com.java.reflection.method.models.HttpClient;
import com.java.reflection.method.models.UdpClient;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Invocation {

    public static void main(String[] args) {

        List<Object> requestExecutors = Arrays.asList(
                new HttpClient("http://localhost:8080"),
                new HttpClient("http://localhost:8081"),
                new FileLogger(),
                new UdpClient()
        );

        List<Class<?>> methodParameters = List.of(
                String.class
        );

        Map<Object, Method> executors = groupExecutors(requestExecutors, methodParameters);

        try {
            execute(executors, "Request body");
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void execute(Map<Object, Method> executors, String requestBody) throws InvocationTargetException, IllegalAccessException {
        for (Map.Entry<Object, Method> entry : executors.entrySet()) {

            Boolean response = (Boolean) entry.getValue().invoke(
                    entry.getKey(),
                    requestBody
            );

            /*
            if (response == null || Boolean.FALSE.equals(response)) {
                System.out.println("Request failed. Aborting...");
                return;
            }
             */
        }
    }

    public static Map<Object, Method> groupExecutors(List<Object> requestExecutors, List<Class<?>> methodParameters) {
        Map<Object, Method> instanceToMethod = new HashMap<>();

        for (Object executor : requestExecutors) {
            for (Method method : executor.getClass().getDeclaredMethods()) {
                if (compareTypes(method.getParameterTypes(), methodParameters))
                    instanceToMethod.put(executor, method);
            }
        }

        return instanceToMethod;
    }

    public static boolean compareTypes(Class<?>[] methodParameters, List<Class<?>> expectedParameters) {
        if (methodParameters.length != expectedParameters.size()) return false;

        for (int i = 0; i < methodParameters.length; i++) {
            if (!methodParameters[i].equals(expectedParameters.get(i))) return false;
        }

        return true;
    }
}
