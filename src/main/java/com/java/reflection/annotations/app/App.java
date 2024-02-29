package com.java.reflection.annotations.app;

import com.java.reflection.annotations.models.annotations.ScanPackages;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;

@ScanPackages({"", "http", "databases", "configs"})
public class App {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException, URISyntaxException, InterruptedException {

        ScanPackages p = App.class.getAnnotation(ScanPackages.class);

        if (p == null || p.value().length == 0)
            throw new RuntimeException("No packages to scan");

        InitializersOperations.initialize(p.value());

    }

    /*
       Annotation is a tag that represents the metadata i.e. attached with class, interface,
       methods or fields to indicate some additional information which can be used by java compiler and JVM.

       Annotations are used for providing information to the compiler.
       Annotations are used at compile time and at runtime.

       @Interface is used to create an annotation.

       Meta Annotations:
         1. @Target: It specifies where the annotation is applicable.
            For example, you can apply an annotation to a method, a class, a field, or a parameter.
            ElementType is an enum type that has the following constants: ANNOTATION_TYPE, CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, PARAMETER, TYPE.
            ANNOTATION_TYPE: It is used to annotate another annotation. (So, You can create an annotation and apply it to another annotation.)
            CONSTRUCTOR: It is used to annotate a constructor.
            FIELD: It is used to annotate a field.
            LOCAL_VARIABLE: It is used to annotate a local variable. (Local variable is a variable that is declared within the body of a method.)
            METHOD: It is used to annotate a method.
            PACKAGE: It is used to annotate a package.
            PARAMETER: It is used to annotate a parameter.
            TYPE: It is used to annotate a class or interface.
         2. @Retention: It specifies whether the annotation is available at runtime.
            For example, you can use the @Retention annotation to specify whether an annotation is included in the class file.
            RetentionPolicy is an enum type that has three constants: SOURCE, CLASS, and RUNTIME.
            SOURCE: It is retained only in the source file and is discarded during compilation.
            -> We can use SOURCE for the annotations that are used to provide information to the compiler.
            CLASS: It is retained in the .class file but not available at runtime.
            -> We can use CLASS for the annotations that are used by the compiler but not required at runtime.
            RUNTIME: It is retained in the .class file and available at runtime.
            -> We can use RUNTIME for the annotations that are available at runtime.
        3. @Documented: It specifies whether the annotation is included in the javadoc.
            JavaDoc is a documentation generator created by Sun Microsystems for the Java language.
            Javadoc creates a set of HTML pages that describe the public and protected classes, interfaces, constructors, methods, and fields.
            When you create an annotation and apply it to a class, the annotation is included in the javadoc.
        4. @Inherited: It specifies whether the annotation is inherited by subclasses.
           For example, if you create an annotation and apply it to a class, the annotation is inherited by the subclass of that class.
        5. @Repeatable: It specifies whether the annotation can be applied more than once to the same declaration.
            For example, you give an annotation to a method and you want to give the same annotation to the same method again.

     */
}
