package com.java.reflection.objectcreation.apps;

import com.java.reflection.objectcreation.models.ServerConfiguration;
import com.java.reflection.objectcreation.web.WebServer;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.NoSuchElementException;

public class CreatePrivateConstructor {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {

        initConfiguration();

        WebServer webServer = new WebServer();

        webServer.startServer();

    }

    public static void initConfiguration() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Constructor<ServerConfiguration> constructor = ServerConfiguration.class
                .getDeclaredConstructor(int.class, String.class);

        constructor.setAccessible(true);
        constructor.newInstance(8080, "Hello World");
    }
}
