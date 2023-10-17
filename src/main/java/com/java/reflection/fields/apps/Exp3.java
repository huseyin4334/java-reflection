package com.java.reflection.fields.apps;

import com.java.reflection.fields.models.exp3.Address;
import com.java.reflection.fields.models.exp3.Company;
import com.java.reflection.fields.models.exp3.Person;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

public class Exp3 {

    public static void main(String[] args) {
        Person person = new Person(
                "John",
                true,
                25,
                175,
                new Address(
                        "Street 1",
                        (short) 1
                ),
                new Company(
                        "Company 1",
                        "123456789",
                        new Address(
                                "Street 3",
                                (short) 588
                        )
                ),
                new Object[]{
                        "Hobby 1",
                        "Hobby 2",
                        new String[] {
                                "Hobby 3",
                                "Hobby 4"
                        },
                        new Address(
                                "Inner Street 1",
                                (short) 23
                        ),
                }
        );

        System.out.println(objectToJson(person, 0));
    }

    public static String objectToJson(Object instance, int level) {
        Field[] fields = instance.getClass().getDeclaredFields();

        StringBuilder json = new StringBuilder(indent(Math.min(level, 1)))
                .append("{\n");

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            try {
                field.setAccessible(true);

                if (field.isSynthetic()) continue;

                json.append(indent(level + 1))
                        .append(formatStringValue(field.getName()))
                        .append(":");

                if (field.getType().isPrimitive()) {
                    json.append(formatPrimitive(field, instance));
                }
                else if (field.getType().equals(String.class))
                    json.append(formatStringValue(field.get(instance).toString()));
                else if (field.getType().isArray())
                    json.append(arrayToJson(field.get(instance), level + 1));
                else {
                    json.append(objectToJson(field.get(instance), level + 1));
                }

                if (i < fields.length - 1)
                    json.append(", ");

                json.append("\n");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return json.append(indent(level))
                .append("}")
                .toString();
    }

    public static String arrayToJson(Object instance, int level) {
        int length = Array.getLength(instance);

        StringBuilder json = new StringBuilder(indent(level))
                .append("[\n");

        for (int i = 0; i < length; i++) {
            Object element = Array.get(instance, i);

            if (element.getClass().isPrimitive()) {
                json.append(indent(level + 1)).append(element.toString());
            }
            else if (element.getClass().equals(String.class))
                json.append(indent(level + 1)).append(formatStringValue(element.toString()));
            else if (element.getClass().isArray())
                json.append(arrayToJson(element, level + 1));
            else
                json.append(objectToJson(element, level + 1));

            if (i < length - 1)
                json.append(", ");

            json.append("\n");
        }

        return json.append(indent(level))
                .append("]")
                .toString();
    }

    public static String indent(int level) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indent.append("\t");
        }
        return indent.toString();
    }

    private static String formatPrimitive(Field field, Object parent) throws IllegalAccessException {
        if (
                field.getType().getName().equals("int") ||
                        field.getType().getName().equals("long") ||
                        field.getType().getName().equals("short") ||
                        field.getType().getName().equals("byte") ||
                        field.getType().getName().equals("boolean")
        )
            return field.get(parent).toString();

        else if (
                field.getType().getName().equals("double") ||
                field.getType().getName().equals("float")
        )
            return String.format("%.02f", field.get(parent));


        throw new RuntimeException(String.format("Type %s not supported", field.getType().getName()));
    }

    private static String formatStringValue(String value) {
        return String.format("'%s'", value);
    }
}
