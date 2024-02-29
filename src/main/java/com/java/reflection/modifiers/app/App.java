package com.java.reflection.modifiers.app;

import com.java.reflection.modifiers.models.AnyThing;
import com.java.reflection.modifiers.models.Auction;
import com.java.reflection.modifiers.models.Bid;

import java.io.Serializable;
import java.lang.reflect.Modifier;

public class App {

    public static void main(String[] args) {
        runAuction();
        printClassModifiers(Serializable.class);
        printClassModifiers(Auction.class);
        printClassModifiers(Bid.class);
        printClassModifiers(Bid.Builder.class);
        printClassModifiers(AnyThing.class);
    }

    public static void printClassModifiers(Class<?> clazz) {
        System.out.println("====================================");
        System.out.printf(
                "Class: %s Modifier: %s%n",
                clazz.getName(),
                Modifier.toString(clazz.getModifiers())
        );

        printMethodModifiers(clazz);
        printFieldModifiers(clazz);
    }

    public static void printMethodModifiers(Class<?> clazz) {
        for (var method : clazz.getDeclaredMethods()) {
            System.out.printf(
                    "Method: %s Modifier: %s%n",
                    method.getName(),
                    Modifier.toString(method.getModifiers())
            );
        }
    }

    public static void printFieldModifiers(Class<?> clazz) {
        for (var field : clazz.getDeclaredFields()) {
            System.out.printf(
                    "Field: %s Modifier: %s%n",
                    field.getName(),
                    Modifier.toString(field.getModifiers())
            );
        }
    }



    public static void runAuction() {
        Auction auction = new Auction();
        auction.startAuction();

        Bid bid1 = new Bid.Builder()
                .price((int) (Math.random() * 100))
                .bidderName("John")
                .build();

        auction.addBid(bid1);

        Bid bid2 = new Bid.Builder()
                .price((int) (Math.random() * 100))
                .bidderName("Jane")
                .build();

        auction.addBid(bid2);

        auction.stopAuction();

        System.out.println(auction.getBids());
        System.out.println("Highest bid: " + auction.getHighestBid().get());
    }

    /**
     * Access Modifiers
     *  - public
     *  - protected
     *  - default
     *  - private
     *
     *  Non-Access Modifiers
     *  - final
     *  - abstract
     *  - static
     *  - synchronized
     *  - transient
     *  - volatile
     *  - native
     *  - strictfp
     *
     *   Bitmap Encoding
     *   - 0 - default -> binary 0000
     *   - 1 - public -> binary 0001
     *   - 2 - private -> binary 0010
     *   - 4 - protected -> binary 0100
     *   - 8 - static -> binary 1000
     *   - 16 - final -> binary 0001 0000
     *   - 32 - synchronized -> binary 0010 0000
     *   ....
     */
}
