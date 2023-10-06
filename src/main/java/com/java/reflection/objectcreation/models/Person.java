package com.java.reflection.objectcreation.models;

public class Person {
    private String name;
    private int age;
    private Address address;

    private Person() {
        name = "Anonymous";
        age = 30;
        address = null;
    }

    public Person(String name) {
        this.name = name;
        age = 30;
        address = null;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        address = null;
    }

    public Person(String name, Integer age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                '}';
    }
}
