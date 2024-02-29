package com.java.reflection.dynamicproxy.external;

public interface HttpClient {

    void initialize();

    String sendRequest(String request);
}
