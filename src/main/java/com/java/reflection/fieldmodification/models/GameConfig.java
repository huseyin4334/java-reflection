package com.java.reflection.fieldmodification.models;

import lombok.Getter;
import lombok.Setter;

@Getter
public class GameConfig {
    private int releaseYear;
    private String gameName;
    private double price;

    @Override
    public String toString() {
        return "GameConfig{" +
                "releaseYear=" + releaseYear +
                ", gameName='" + gameName + '\'' +
                ", price=" + price +
                '}';
    }
}
