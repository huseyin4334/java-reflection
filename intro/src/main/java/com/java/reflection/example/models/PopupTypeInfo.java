package com.java.reflection.example.models;

import lombok.Builder;
import lombok.Setter;

import java.util.Arrays;

@Builder
public class PopupTypeInfo {

    private boolean isPrimitive;
    private boolean isInterface;
    private boolean isEnum;
    private String name;
    private boolean isJdk;
    private String[] inheritedClassNames;

    @Override
    public String toString() {
        return "PopupTypeInfo{" +
                "isPrimitive=" + isPrimitive +
                ", isInterface=" + isInterface +
                ", isEnum=" + isEnum +
                ", name='" + name + '\'' +
                ", isJdk=" + isJdk +
                ", inheritedClassNames=" + Arrays.toString(inheritedClassNames) +
                '}';
    }
}
