package com.java.reflection.annotations.models;

import com.java.reflection.annotations.models.annotations.InitializerClass;
import com.java.reflection.annotations.models.annotations.InitializerMethod;

@InitializerClass
public class AutoSaver {

    @InitializerMethod
    public void startAutoSavingThreads() {
        System.out.println("Start automatic data saving to disk");
    }
}
