package com.java.reflection.annotations.models.configs;

import com.java.reflection.annotations.models.annotations.InitializerClass;
import com.java.reflection.annotations.models.annotations.InitializerMethod;

@InitializerClass
public class ConfigsLoader {

    @InitializerMethod
    public void loadAllConfigs() {
        System.out.println("Loading all configuration files");
    }
}
