package com.java.reflection.objectcreation.models;

import lombok.Getter;

import java.net.InetSocketAddress;

@Getter
public class ServerConfiguration {

    private static ServerConfiguration instance;

    private final InetSocketAddress address;

    private final String greetingMessage;

    private ServerConfiguration(int port, String greetingMessage) {
        this.address = new InetSocketAddress("localhost", port);
        this.greetingMessage = greetingMessage;

        if (instance == null) {
            instance = this;
        }
    }

    public static ServerConfiguration getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "ServerConfiguration{" +
                "address=" + address +
                ", greetingMessage='" + greetingMessage + '\'' +
                '}';
    }

    protected Object readResolve() {
        return instance;
    }
}
