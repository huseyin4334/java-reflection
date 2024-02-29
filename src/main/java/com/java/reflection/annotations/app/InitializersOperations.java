package com.java.reflection.annotations.app;

import com.java.reflection.annotations.models.AutoSaver;
import com.java.reflection.annotations.models.annotations.InitializerClass;
import com.java.reflection.annotations.models.annotations.InitializerMethod;
import com.java.reflection.annotations.models.annotations.RetryOperation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class InitializersOperations {


    public static void initialize(String... packages) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException, ClassNotFoundException, URISyntaxException, InterruptedException {
        List<Class<?>> scannedClasses = scanClasses(packages);

        for (Class<?> clazz : scannedClasses) {
            if (!clazz.isAnnotationPresent(InitializerClass.class))
                continue;

            List<Method> methods = getInitializerMethods(clazz);

            Object instance = clazz.getDeclaredConstructor().newInstance();

            for (Method method : methods)
                callMethod(instance, method);
        }
    }

    private static void callMethod(Object instance, Method method) throws InvocationTargetException, IllegalAccessException, InterruptedException {
        RetryOperation retryOperation = method.getAnnotation(RetryOperation.class);

        int retryCount = retryOperation != null ? retryOperation.retryCount() : 0;

        for (int i = 0; i <= retryCount; i++) {
            try {
                method.invoke(instance);
                break;
            } catch (InvocationTargetException e) {
                if (retryOperation != null && exceptionsControl(retryOperation.retryOnException(), e.getTargetException())) {
                    long duration = retryOperation.durationBetweenRetries();
                    System.out.println(retryOperation.failMessage() + ". Retrying in " + duration + "ms");
                    Thread.sleep(duration);
                }
                else
                    throw e;
            }
        }
    }

    private static boolean exceptionsControl(Class<? extends Throwable>[] exceptions, Throwable e) {
        for (Class<? extends Throwable> exception : exceptions) {
            if (exception.equals(e.getClass()))
                return true;
        }

        return false;
    }

    public static List<Method> getInitializerMethods(Class<?> clazz) {
        List<Method> methods = new ArrayList<>();

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(InitializerMethod.class))
                methods.add(method);
        }

        return methods;
    }

    private static List<Class<?>> scanClasses(String[] packages) throws IOException, ClassNotFoundException, URISyntaxException {
        List<Class<?>> classes = new ArrayList<>();

        for (String packageName : packages) {
            String packagePath = packageName.replace(".", "/");
            URI uri = Objects.requireNonNull(AutoSaver.class.getResource(packagePath)).toURI();

            if (uri.getScheme().equals("file")) {
                Path path = Paths.get(uri);
                classes.addAll(scanPackage(path, "com.java.reflection.annotations.models" + (packageName.isBlank() ? "" : "." + packageName)));
            }
        }

        return classes;
    }

    private static List<Class<?>> scanPackage(Path packagePath, String packageName) throws IOException, ClassNotFoundException {
        if (!Files.exists(packagePath)) {
            return Collections.emptyList();
        }

        List<Path> files = Files.list(packagePath)
                .filter(Files::isRegularFile) // this means that the file is not a directory or a symbolic link.
                .toList();

        List<Class<?>> classes = new ArrayList<>();

        for (Path path : files) {
            String fileName = path.getFileName().toString();

            if (fileName.endsWith(".class")) {
                // \\.class$ is a regular expression that matches the .class extension.
                // replaceFirst() is a method that replaces the first occurrence of a substring that matches a given regular expression with a given replacement.
                // $ is a special character in regular expressions that matches the end of a string.

                String fullName = packageName + "." + fileName.replaceFirst("\\.class$", "");
                Class<?> clazz = Class.forName(fullName);

                classes.add(clazz);
            }
        }

        return classes;
    }
}
