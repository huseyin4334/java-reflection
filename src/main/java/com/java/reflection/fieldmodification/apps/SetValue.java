package com.java.reflection.fieldmodification.apps;

import com.java.reflection.fieldmodification.models.GameConfig;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.Scanner;

public class SetValue {

    public static void main(String[] args) {
        SetValue setValue = new SetValue();

        try {
            GameConfig gameConfig = setValue.createConfigObject(GameConfig.class, new File("src/main/resources/game-properties.cfg"));
            System.out.println(gameConfig);
        } catch (IOException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public <T> T createConfigObject(Class<T> clazz, File file)
            throws IOException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

        Scanner scanner = new Scanner(file);

        Constructor<T> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);

        T instance = constructor.newInstance();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String[] parts = line.split("=");

            String fieldName = parts[0];
            String fieldValue = parts[1];

            try {
                setFieldValue(instance, fieldName, fieldValue);
            } catch (NoSuchFieldException ex) {
                System.out.printf("Property %s is unsupported\n", fieldName);
            }
        }

        scanner.close();

        return instance;
    }

    public <T> void setFieldValue(T instance, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Class<T> clazz = (Class<T>) instance.getClass();

        Field field = clazz.getDeclaredField(fieldName);

        field.setAccessible(true);

        if (!field.getType().equals(value.getClass()))
            value = parseValue(field.getType(), value);

        field.set(instance, value);
    }

    public static Object parseValue(Class<?> type, Object value) {
        if (type.equals(int.class)) {
            return Integer.parseInt(value.toString());
        } else if (type.equals(double.class)) {
            return Double.parseDouble(value.toString());
        } else if (type.equals(float.class)) {
            return Float.parseFloat(value.toString());
        } else if (type.equals(long.class)) {
            return Long.parseLong(value.toString());
        } else if (type.equals(short.class)) {
            return Short.parseShort(value.toString());
        } else if (type.equals(byte.class)) {
            return Byte.parseByte(value.toString());
        } else if (type.equals(boolean.class)) {
            return Boolean.parseBoolean(value.toString());
        } else if (type.equals(char.class)) {
            return value.toString().charAt(0);
        } else if (type.equals(String.class)) {
            return value.toString();
        }
        return null;
    }
}
