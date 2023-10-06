package com.java.reflection.introduction.getClass;

import com.java.reflection.introduction.getClass.models.Car;

import java.util.HashMap;
import java.util.Map;

public class App {

    private static void nonPrimitiveTypeClass() {
        // Get classes for non primitive types
        String className = "com.java.reflection.introduction.getClass.models.Car";

        Car car = new Car();

        Map<String, Integer> map = new HashMap<String, Integer>();

        Class<? extends String> stringClass = className.getClass();
        Class<String> stringClass2 = String.class;

        Class<Car> carClass = (Class<Car>) car.getClass();

        Class<?> mapClass = map.getClass();

        System.out.println(stringClass.getSimpleName());
        System.out.println(carClass.getSimpleName());
        System.out.println(mapClass.getSimpleName());
    }

    private static void primitiveTypeClass() {
        // Primitives not have getClass() method
        Class<?> booleanT = boolean.class;
        System.out.println(booleanT.getSimpleName());
    }

    private static void classForName() {
        try {
            // find a class
            Class<?> carClass = Class.forName("com.java.reflection.introduction.getClass.models.Car");
            System.out.println(carClass.getSimpleName());

            Class<String> stringClass = (Class<String>) Class.forName("java.lang.String");
            System.out.println(stringClass.getSimpleName());

            // innerclass
            Class<Car.Engine> engineClass = (Class<Car.Engine>) Class.forName("com.java.reflection.introduction.getClass.models.Car$Engine");
            System.out.println(engineClass.getSimpleName());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Class<?> booleanT = Class.forName("boolean");
        } catch (Exception ex) {
            System.out.print("Exception: Primitive types not have forName() method. -> ");
            System.out.println(ex.getClass().getSimpleName());
        }
    }

    public static void main(String[] args) {
        // Get classes for non primitive types
        System.out.println("Get classes for non primitive types");
        nonPrimitiveTypeClass();
        System.out.println();

        // Primitives classes
        System.out.println("Primitives classes");
        primitiveTypeClass();
        System.out.println();

        // Class.forName()
        System.out.println("Class.forName()");
        classForName();
        System.out.println();

    }
}
