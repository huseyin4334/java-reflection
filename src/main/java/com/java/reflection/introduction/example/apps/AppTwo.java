package com.java.reflection.introduction.example.apps;

import com.java.reflection.introduction.example.models.ClassAnalyzer;

public class AppTwo {

    public static void main(String[] args) {

        System.out.println(
                ClassAnalyzer.createPopupTypeInfoFromClass(int.class)
        );
    }
}

