package com.java.reflection.method.example;

import java.util.*;
import java.lang.reflect.*;

public class TestingFramework {
    public void runTestSuite(Class<?> testClass) throws Throwable {
        Method[] methods = testClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
        Object o = testClass.getDeclaredConstructor().newInstance();

        // Find beforeClass method
        runMethod(o, findMethodByName(methods, "beforeClass"));

        // find setupTest method
        Method setupMethod = findMethodByName(methods, "setupTest");

        // find all test methods
        for (Method m : findMethodsByPrefix(methods, "test")) {
            runMethod(o, setupMethod);
            runMethod(o, m);
        }

        // find afterClass method
        runMethod(o, findMethodByName(methods, "afterClass"));
    }

    private void runMethod(Object o, Method m) throws InvocationTargetException, IllegalAccessException {
        if (m != null && m.getParameterCount() == 0 && m.getReturnType() == void.class) {
            //System.out.println(m.getName());
            m.invoke(o);
        }
    }

    /**
     * Helper method to find a method by name
     * Returns null if a method with the given name does not exist
     */
    private Method findMethodByName(Method[] methods, String name) {
        for (Method method : methods) {
            if (method.getName().equals(name)) {
                return method;
            }
        }
        return null;
    }

    /**
     * Helper method to find all the methods that start with the given prefix
     */
    private List<Method> findMethodsByPrefix(Method[] methods, String prefix) {
        List<Method> result = new ArrayList<>();
        for (Method method : methods) {
            if (method.getName().startsWith(prefix)) {
                result.add(method);
            }
        }
        return result;
    }
}