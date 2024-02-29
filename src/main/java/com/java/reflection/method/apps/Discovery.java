package com.java.reflection.method.apps;

import com.java.reflection.method.models.Product;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

public class Discovery {

    public static void main(String[] args) {
        controlGetters(Product.class);
        controlSetters(Product.class);
    }

    public static void controlSetters(Class<?> clazz) throws IllegalArgumentException, IllegalStateException {
        controlFunction(clazz, "set");
    }

    public static void controlGetters(Class<?> clazz) throws IllegalArgumentException, IllegalStateException{
        controlFunction(clazz, "get");
    }

    public static void controlFunction(Class<?> clazz, String prefix) throws IllegalArgumentException, IllegalStateException {
        Field[] fields = clazz.getDeclaredFields();
        Map<String, Method> methodMap = getMethodMap(clazz);

        for (Field field : fields) {
            String fName = prefix + firstCapitalName(field.getName());
            Method method = methodMap.get(fName);

            if (method == null) {
                throw new IllegalStateException(fName + " method is not found.");
            }

            if (prefix.equals("get") && !method.getReturnType().equals(field.getType())) {
                throw new IllegalArgumentException(fName + " method's return type is not compatible with " + field.getName());
            } else {
                try {
                    clazz.getDeclaredMethod(fName, field.getType());
                } catch (NoSuchMethodException e) {
                    throw new IllegalStateException(fName + " method is not found with " + field.getType() + " parameter.");
                }
            }
        }
    }

    private static String firstCapitalName(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public static Map<String, Method> getMethodMap(Class<?> clazz) {
        Map<String, Method> methodMap = new TreeMap<>();

        for (Method method : clazz.getDeclaredMethods()) {
            methodMap.put(method.getName(), method);
        }

        return methodMap;
    }
}
