package com.java.reflection.objectcreation.models;

public class Address {

    private String street;
    private int number;

    public Address(String street, int number) {
        super();
        this.street = street;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", number=" + number +
                '}';
    }
}
