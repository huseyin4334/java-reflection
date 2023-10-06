package com.java.reflection.objectcreation.web;

import com.java.reflection.objectcreation.models.ServerConfiguration;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;

public class WebServer {

    public void startServer() throws IOException {
        HttpServer server = HttpServer.create(ServerConfiguration.getInstance().getAddress(), 0);

        server.createContext("/greeting").setHandler(exchange -> {
            String response = ServerConfiguration.getInstance().getGreetingMessage();
            exchange.sendResponseHeaders(200, response.length());
            exchange.getResponseBody().write(response.getBytes());
            exchange.close();

            System.out.println("Request received");
        });

        System.out.printf(
                "Starting server on address %s:%d%n",
                ServerConfiguration.getInstance().getAddress().getHostName(),
                ServerConfiguration.getInstance().getAddress().getPort()
                );

        server.start();
    }
}
