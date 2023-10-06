package com.java.reflection.fields.apps;

import com.java.reflection.fields.enums.exp1.Category;
import com.java.reflection.fields.models.exp1.Movie;

import java.lang.reflect.Field;

public class Exp2 {

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        Movie movie = new Movie("The Matrix", 1999, 15.99, true, Category.ADVENTURE);

        declaredFieldsInfo(movie.getClass(), movie);

        fieldDiffs();
    }

    public static <T> void declaredFieldsInfo(Class<? extends T> clazz, T instance) throws IllegalAccessException {

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            System.out.printf("Field name : %s type : %s%n",
                    field.getName(),
                    field.getType().getName());

            System.out.printf("Field is isSynthetic : %s%n", field.isSynthetic());
            field.setAccessible(true);
            System.out.println("Field value is: " + field.get(instance));

            System.out.println();
        }
    }

    public static void fieldDiffs() throws NoSuchFieldException, IllegalAccessException {
        Field field = Movie.class.getDeclaredField("MINIMUM_PRICE");
        Field field2 = Movie.class.getDeclaredField("category");

        System.out.println("Field value is : " + field.get(null));
        /*
            That's working because field is static.
            This field not need an object for get value.
         */

        try {
            System.out.println("Field value is : " + field2.get(null));
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
