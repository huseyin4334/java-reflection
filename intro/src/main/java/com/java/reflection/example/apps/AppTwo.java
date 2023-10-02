package com.java.reflection.example.apps;

import com.java.reflection.example.models.ClassAnalyzer;

public class AppTwo {

    public static void main(String[] args) {

        System.out.println(
                ClassAnalyzer.createPopupTypeInfoFromClass(int.class)
        );
    }
}

