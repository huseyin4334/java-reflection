package com.java.reflection.fields.apps;

import java.lang.reflect.Array;

public class Exp4 {

    public static void main(String[] args) {
        int[] intArray = {1, 2, 3};
        inspectArray(intArray);
        inspectValues(intArray);
        System.out.println();

        double[][] doubleArray = {{1.0, 2.0}, {3.0, 4.0}};
        inspectArray(doubleArray);
        inspectValues(doubleArray);
        System.out.println();
    }

    public static void inspectArray(Object array) {
        Class<?> clazz = array.getClass();

        System.out.printf("Is array: %s%n", clazz.isArray());

        Class<?> componentType = clazz.getComponentType();

        System.out.printf("Component type: %s%n", componentType.getTypeName());
    }

    public static void inspectValues(Object array) {
        int length = Array.getLength(array);

        System.out.print("{");
        for (int i = 0; i < length; i++) {
            Object value = Array.get(array, i);
            if (value.getClass().isArray()) {
                inspectValues(value);
            }
            else
                System.out.print(value);

            if (i < length - 1)
                System.out.print(", ");
        }
        System.out.print("}");
    }
}
