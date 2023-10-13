package com.java.reflection.fields.apps;

import java.lang.reflect.*;

public class ObjectSizeCalculator {
    private static final long HEADER_SIZE = 12;
    private static final long REFERENCE_SIZE = 4;

    public long sizeOfObject(Object input) throws IllegalAccessException {
        long size = HEADER_SIZE + REFERENCE_SIZE;

        Field[] fields = input.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            if (field.isSynthetic()) continue;

            if (field.getType().isPrimitive()) {
                size += sizeOfPrimitiveType(field.getType());
            } else if (field.getType().equals(String.class))
                size += sizeOfString(field.get(input).toString());
            else {
                size += sizeOfObject(field);
            }
        }
        return size;
    }


    /*************** Helper methods ********************************/
    private long sizeOfPrimitiveType(Class<?> primitiveType) {
        if (primitiveType.equals(int.class)) {
            return 4;
        } else if (primitiveType.equals(long.class)) {
            return 8;
        } else if (primitiveType.equals(float.class)) {
            return 4;
        } else if (primitiveType.equals(double.class)) {
            return 8;
        } else if (primitiveType.equals(byte.class)) {
            return 1;
        } else if (primitiveType.equals(short.class)) {
            return 2;
        }
        throw new IllegalArgumentException(String.format("Type: %s is not supported", primitiveType));
    }

    private long sizeOfString(String inputString) {
        int stringBytesSize = inputString.getBytes().length;
        return HEADER_SIZE + REFERENCE_SIZE + stringBytesSize;
    }

    public static void main(String[] args) {
        AccountSummary accountSummary = new AccountSummary("John", "Doe", (short) 30, 100000);
        ObjectSizeCalculator objectSizeCalculator = new ObjectSizeCalculator();
        try {
            System.out.println("Object size is: " + objectSizeCalculator.sizeOfObject(accountSummary));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}


class AccountSummary {
    private final String firstName;
    private final String lastName;
    private final short age;
    private final int salary;

    public AccountSummary(String firstName, String lastName, short age, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
    }
}
