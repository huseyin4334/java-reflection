package com.java.reflection.objectcreation.apps;

import com.java.reflection.objectcreation.models.Address;
import com.java.reflection.objectcreation.models.Person;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void printConstructors(Class<?> input) {
        Constructor<?>[] constructors = input.getDeclaredConstructors();

        System.out.println("Constructors of " + input.getName() + " class has " + constructors.length + " declared constructors.");

        for (Constructor<?> constructor : constructors) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();

            System.out.println(
                    Arrays.stream(parameterTypes)
                            .map(Class::getSimpleName)
                            .collect(Collectors.toList())
            );
        }
        System.out.println();
        System.out.println();
    }

    public static void main( String[] args ) {

        printConstructors(Person.class);
        printConstructors(Address.class);
        printConstructors(String.class);
    }

}
