package com.java.reflection.example.models;

import java.util.Arrays;
import java.util.List;

public class ClassAnalyzer {
    private static final List<String> JDK_PACKAGE_PREFIXES =
            Arrays.asList("com.sun.", "java", "javax", "jdk", "org.w3c", "org.xml");

    public static PopupTypeInfo createPopupTypeInfoFromClass(Class<?> inputClass) {

        return PopupTypeInfo.builder()
                .isPrimitive(inputClass.isPrimitive())
                .isInterface(inputClass.isInterface())
                .isEnum(inputClass.isEnum())
                .name(inputClass.getSimpleName())
                .isJdk(isJdkClass(inputClass))
                .inheritedClassNames(getAllInheritedClassNames(inputClass))
                .build();
    }

    /*********** Helper Methods ***************/

    public static boolean isJdkClass(Class<?> inputClass) {
        /** Complete the code
         Hint: What does inputClass.getPackage() return when the class is a primitive type?
         **/

        return inputClass.isPrimitive() ||
                JDK_PACKAGE_PREFIXES.stream().anyMatch(prefix -> inputClass.getPackageName().startsWith(prefix));
    }

    public static String[] getAllInheritedClassNames(Class<?> inputClass) {
        /** Complete the code
         Hints: What does inputClass.getSuperclass() return when the inputClass doesn't inherit from any class?
         What does inputClass.getSuperclass() return when the inputClass is a primitve type?
         **/

        String[] inheritedClasses;
        if (inputClass.isInterface()) {
            inheritedClasses = Arrays.stream(inputClass.getInterfaces())
                    .map(Class::getSimpleName)
                    .toArray(String[]::new);
        } else {
            Class<?> inheritedClass = inputClass.getSuperclass();
            inheritedClasses = inheritedClass != null ?
                    new String[]{inputClass.getSuperclass().getSimpleName()}
                    : null;
        }
        return inheritedClasses;

    }
}
