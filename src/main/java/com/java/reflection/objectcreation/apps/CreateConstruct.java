package com.java.reflection.objectcreation.apps;

import com.java.reflection.objectcreation.models.Address;
import com.java.reflection.objectcreation.models.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class CreateConstruct
{

    public static Object createConstructor(Class<?> input, Object... args) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<?>[] constructors = input.getDeclaredConstructors();

        for (Constructor<?> constructor : constructors) {

            if (constructor.getParameterTypes().length == args.length) {
                return constructor.newInstance(args);
            }
        }

        return null;
    }

    public static <T> T constructorFactory(Class<T> inputClass, Object... args) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Class<?>[] paramClasses = Arrays.stream(args)
                .map(Object::getClass)
                .toArray(Class<?>[]::new);

        Constructor<T> constructor = inputClass.getDeclaredConstructor(paramClasses);

        return constructor.newInstance(args);
    }

    public static void main( String[] args ) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {

        System.out.println();
        System.out.println("Example 1");
        example1();

        System.out.println();
        System.out.println("Example 2");
        example2();
    }

    public static void example1() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Person person = (Person) createConstructor(Person.class, "John");

        System.out.println(person);

        person = (Person) createConstructor(Person.class, "John", 25, new Address("Main Street", 10));

        System.out.println(person);

        try {
            person = (Person) createConstructor(Person.class);
            System.out.println(person);
        } catch (IllegalAccessException ex) {
            System.out.println("Ops... Method is private!");
        }
    }

    public static void example2() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Person person = constructorFactory(Person.class, "John");

        System.out.println(person);

        person = constructorFactory(Person.class, "John", 25, new Address("Main Street", 10));

        /*
            It can take no such method error.
             Because string, Integer, Address are not the same type as the constructor.
             The constructor is expecting a primitive int, not an Integer.
         */

        System.out.println(person);

        try {
            person = constructorFactory(Person.class);
            System.out.println(person);
        } catch (IllegalAccessException ex) {
            System.out.println("Ops... Method is private!");
        }
    }

}
