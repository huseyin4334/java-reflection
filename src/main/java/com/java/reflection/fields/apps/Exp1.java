package com.java.reflection.fields.apps;

import com.java.reflection.fields.enums.exp1.Category;
import com.java.reflection.fields.models.exp1.Movie;

import java.lang.reflect.Field;

public class Exp1 {

    public static void main(String[] args) {
        System.out.println("Movie.class");
        declaredFieldsInfo(Movie.class);

        System.out.println("\n\nMovie.MovieStats.class");
        declaredFieldsInfo(Movie.MovieStats.class);

        System.out.println("\n\nCategory.class");
        declaredFieldsInfo(Category.class);
    }

    public static void declaredFieldsInfo(Class<?> clazz) {

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            System.out.printf("Field name : %s type : %s%n",
                    field.getName(),
                    field.getType().getName());

            System.out.printf("Field is isSynthetic : %s%n", field.isSynthetic());

            System.out.println();
        }
    }
}
