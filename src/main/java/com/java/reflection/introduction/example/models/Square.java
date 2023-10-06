package com.java.reflection.introduction.example.models;

import com.java.reflection.introduction.example.interfaces.Drawable;

public class Square implements Drawable {

    private Color color;

    @Override
    public int getNumberOfCorner() {
        return 4;
    }

    private enum Color {
        RED, GREEN, BLUE
    }
}
