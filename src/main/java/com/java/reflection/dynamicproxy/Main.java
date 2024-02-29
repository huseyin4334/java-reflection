package com.java.reflection.dynamicproxy;

import com.java.reflection.dynamicproxy.external.DatabaseReader;
import com.java.reflection.dynamicproxy.external.HttpClient;
import com.java.reflection.dynamicproxy.external.impl.DatabaseReaderImpl;
import com.java.reflection.dynamicproxy.external.impl.HttpClientImpl;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Normal execution -----------------");
        HttpClient httpClient = new HttpClientImpl();
        useHttpClient(httpClient);

        DatabaseReader reader = new DatabaseReaderImpl();
        useDatabaseReader(reader);

        System.out.println("Dynamic Proxy execution -----------------");
        HttpClient httpClientProxy = createProxy(new HttpClientImpl());
        useHttpClient(httpClientProxy);

        DatabaseReader readerProxy = createProxy(new DatabaseReaderImpl());
        useDatabaseReader(readerProxy);
    }

    @SuppressWarnings("unchecked")
    public static <T> T createProxy(Object instance) {
        Class<?>[] classes = instance.getClass().getInterfaces();

        TimeMeasureHandlerProxy proxy = new TimeMeasureHandlerProxy(instance);

        return (T) Proxy.newProxyInstance(
                instance.getClass().getClassLoader(), // class loader is used to load the proxy class. It is the same class loader that loaded the original object.
                classes,
                proxy
        );
    }


    public static void useHttpClient(HttpClient httpClient) {
        httpClient.initialize();
        String response = httpClient.sendRequest("some request");

        System.out.println(String.format("Http response is : %s", response));
    }

    public static void useDatabaseReader(DatabaseReader databaseReader) throws InterruptedException {
        int rowsInGamesTable = 0;
        try {
            rowsInGamesTable = databaseReader.countRowsInTable("GamesTable");
        } catch (IOException e) {
            System.out.println("Catching exception " + e);
            return;
        }

        System.out.println(String.format("There are %s rows in GamesTable", rowsInGamesTable));

        String[] data = databaseReader.readRow("SELECT * from GamesTable");

        System.out.println(String.format("Received %s", String.join(" , ", data)));
    }


    static class TimeMeasureHandlerProxy implements InvocationHandler {

        /*
        * The InvocationHandler interface is used to create dynamic proxies.
        * It contains only one method, invoke(), which is called when a method is invoked on the dynamic proxy.
        * proxy - proxy object in the invoke() method is the object that we are creating a proxy for. It is the object that we are intercepting method calls for.
        * We can use the proxy object to invoke the original method on the original object.
        * ((Proxy) proxy).h is the InvocationHandler that is associated with the proxy object.
         */

        private final Object origin;

        public TimeMeasureHandlerProxy(Object origin) {
            this.origin = origin;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(String.format("Measuring Proxy - Before Executing method : %s()", method.getName()));

            long startTime = System.nanoTime();

            Object result;
            try {
                result = method.invoke(origin, args);
            } catch (InvocationTargetException e) {
                throw e.getTargetException();
            }

            System.out.println();
            System.out.println(String.format("Measuring Proxy - Execution of %s() took %dns \n", method.getName(), System.nanoTime() - startTime));

            return result;
        }
    }
}
