package com.java.reflection.method.example;

public class PaymentServiceTest {

    public static void beforeClass() {
        // Called in the beginning of the test suite only once
        // Used for all tests need to share computationally expensive setup
        System.out.println("before class");
    }

    public void setupTest() {
        // Called before every test
        // Used for setting up resource before every test
        System.out.println("setup test");
    }

    public void testCreditCardPayment() {
        // Test case 1
        System.out.println("test 1");
    }

    public void testWireTransfer() {
        // Test case 2
        System.out.println("test 2");
    }

    public void testInsufficientFunds() {
        // Test case 3
        System.out.println("test 3");
    }

    public static void afterClass() {
        // Called once in the end of the entire test suite
        // Used for closing and cleaning up common resources
        System.out.println("after class");
    }
}