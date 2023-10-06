package com.java.reflection.introduction.example.apps;

import com.java.reflection.introduction.example.interfaces.Drawable;
import com.java.reflection.introduction.example.models.Square;

import java.util.ArrayList;

public class App {

    private static void printClassInfo(Class<?>... classes) {

        for (Class<?> c : classes) {
            System.out.println(String.format("Class name: %s, class package name: %s", c.getSimpleName(), c.getPackageName()));
            System.out.println("Type of class: " + c.getTypeName());

            Class<?> [] interfaces = c.getInterfaces();

            for (Class<?> i : interfaces) {
                System.out.println(String.format("Class is: %s, Implemented Interface name: %s", c.getSimpleName(), i.getSimpleName()));
            }

            System.out.println("Is array class: " + c.isArray());
            System.out.println("Is enum class: " + c.isEnum());
            System.out.println("Is interface class: " + c.isInterface());
            System.out.println("Is primitive class: " + c.isPrimitive());
            System.out.println("Is annotation class: " + c.isAnnotation());
            System.out.println("Is anonymous class: " + c.isAnonymousClass());

            System.out.println();
            System.out.println();
        }

    }

    public static void main(String[] args) throws ClassNotFoundException {
        var circle = new Drawable() { // anonymous class
            @Override
            public int getNumberOfCorner() {
                return 0;
            }
        };


        printClassInfo(
                Square.class,
                Class.forName("com.java.reflection.introduction.example.models.Square$Color"),
                String.class,
                boolean.class,
                int[][].class,
                ArrayList.class,
                circle.getClass()
        );
    }
}
