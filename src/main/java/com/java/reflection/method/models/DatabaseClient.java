package com.java.reflection.method.models;

public class DatabaseClient {

    public boolean storeData(String data) {
        System.out.printf("Data: %s stored in database%n", data);
        return true;
    }
}
