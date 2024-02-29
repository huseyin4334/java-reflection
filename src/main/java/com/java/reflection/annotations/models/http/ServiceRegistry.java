package com.java.reflection.annotations.models.http;

import com.java.reflection.annotations.models.annotations.InitializerClass;
import com.java.reflection.annotations.models.annotations.InitializerMethod;

@InitializerClass
public class ServiceRegistry {

    @InitializerMethod
    public void registerService() {
        System.out.println("Service successfully registered");
    }
}
