package com.java.reflection.method.example;

public class Application {

    public static void main(String[] args) throws Throwable {
        TestingFramework testingFramework = new TestingFramework();

        testingFramework.runTestSuite(PaymentServiceTest.class);
    }
}
