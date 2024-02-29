package com.java.reflection.annotations.models.databases;

import com.java.reflection.annotations.models.annotations.InitializerClass;
import com.java.reflection.annotations.models.annotations.InitializerMethod;
import com.java.reflection.annotations.models.annotations.RetryOperation;

import java.io.IOException;

@InitializerClass
public class DatabaseConnection {
    private int failCounter = 5;


    @RetryOperation(
            retryOnException = {IOException.class, RuntimeException.class},
            durationBetweenRetries = 10,
            failMessage = "Connection failed in database 1",
            retryCount = 10
    )
    @InitializerMethod
    public void connectToDatabase1() throws IOException {
        System.out.println("Connecting to database 1");

        if (failCounter > 0) {
            failCounter--;
            throw new IOException("Connection failed");
        }

        System.out.println("Connected to database 1");
    }

    @InitializerMethod
    public void connectToDatabase2() {
        System.out.println("Connecting to database 2");
    }
}
