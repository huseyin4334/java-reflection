package com.java.reflection.method.models;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HttpClient {

    private String host;

    public boolean sendRequest(String data) {
        System.out.printf("Sending request to %s with data %s%n", host, data);
        return true;
    }
}
