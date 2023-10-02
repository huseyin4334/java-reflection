package com.java.reflection.example.models;

import com.java.reflection.example.interfaces.Drawable;

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
