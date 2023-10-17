package com.java.reflection.method.apps;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public class GeneralMethod {

    public static void main(String[] args) throws NoSuchMethodException {
        Method method = GeneralMethod.class.getDeclaredMethod("getArray", String.class, String.class);

        System.out.println("Method name is " + method.getName());
        System.out.println("Return type is " + method.getReturnType().getSimpleName());
        System.out.println("Parameter count is " + method.getParameterCount());

        System.out.println("Exception types are: " + Arrays.stream(method.getExceptionTypes()).map(Class::getSimpleName).collect(Collectors.joining(", ")));
    }

    public static String[] getArray(String param1, String param2) throws RuntimeException, NoSuchMethodException {
        return new String[] {param1, param2};
    }
}
