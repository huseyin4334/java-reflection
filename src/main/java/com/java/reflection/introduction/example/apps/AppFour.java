package com.java.reflection.introduction.example.apps;

import java.util.HashSet;
import java.util.Set;

public class AppFour {

    public static void main(String[] args) {
        Set<Class<?>> allImplementedInterfaces = findAllImplementedInterfaces(String.class);
        System.out.println(allImplementedInterfaces);
    }

    /**
     * Returns all the interfaces that the current input class implements.
     * Note: If the input is an interface itself, the method returns all the interfaces the
     * input interface extends.
     */
    public static Set<Class<?>> findAllImplementedInterfaces(Class<?> input) {
        Set<Class<?>> allImplementedInterfaces = new HashSet<>();

        Class<?>[] inputInterfaces = input.getInterfaces();
        for (Class<?> currentInterface : inputInterfaces) {
            allImplementedInterfaces.add(currentInterface);
            allImplementedInterfaces.addAll(findAllImplementedInterfaces(currentInterface));
        }

        return allImplementedInterfaces;
    }
}
