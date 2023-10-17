package com.java.reflection.fieldmodification.apps;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Exp1 {

    public static void main(String[] args) {
        String[] result = concat(String.class, new String[]{"a", "b"}, "c", new String[] {"d", "e"});
        System.out.println(Arrays.toString(result));

        Integer [] result2 = concat2(Integer.class, 1,2,3,4,5);
        System.out.println(Arrays.toString(result2));
    }

    public static  <T> T concat(Class<?> type, Object... arguments) {

        if (arguments.length == 0) {
            return null;
        }

        List<Object> list = new ArrayList<>();

        for (Object argument : arguments) {
            if (argument.getClass().equals(type)) {
                list.add(argument);
            }else {
                list.addAll(Arrays.asList(Objects.requireNonNull(concat(type, Arrays.stream((Object[]) argument).toArray()))));
            }
        }

        Object flattenedArray = Array.newInstance(type, list.size());

        for (int i = 0; i < list.size() ; i++) {
            Array.set(flattenedArray, i, list.get(i));
        }

        return (T) flattenedArray;
    }

    public static <T> T concat2(Class<?> type, Object... arguments) {
        if (arguments.length == 0) {
            return null;
        }

        List<Object> elements = new ArrayList<>();
        for (Object argument : arguments) {
            if (argument.getClass().isArray()) {
                int length = Array.getLength(argument);

                for (int i = 0 ; i < length ; i++) {
                    elements.add(Array.get(argument, i));
                }
            } else {
                elements.add(argument);
            }
        }

        Object flattenedArray = Array.newInstance(type, elements.size());

        for (int i = 0; i <elements.size() ; i++) {
            Array.set(flattenedArray, i, elements.get(i));
        }

        return (T)flattenedArray;
    }
}
