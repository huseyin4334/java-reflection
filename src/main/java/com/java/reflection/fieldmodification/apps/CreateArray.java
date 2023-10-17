package com.java.reflection.fieldmodification.apps;

import java.lang.reflect.Array;

public class CreateArray {

    public static void main(String[] args) {

    }

    public static <T> T[] createArray(Class<T> clazz, int length) {
        return (T[]) Array.newInstance(clazz, length);
    }

    public static <T> T[][] createArray(Class<T> clazz, int length, int length2) {
        return (T[][]) Array.newInstance(clazz, length, length2);
    }

    public static <T> Object createArray(Class<T> clazz, int... lengths) {
        return Array.newInstance(clazz, lengths);
    }

    public static void setValue(Object array, int index, Object value) {
        Array.set(array, index, value);
    }
}
