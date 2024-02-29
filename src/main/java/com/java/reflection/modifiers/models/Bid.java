package com.java.reflection.modifiers.models;

import lombok.Getter;

import java.io.Serializable;

@Getter
public abstract class Bid implements Serializable {

    protected int price;

    protected String bidderName;


    @Override
    public String toString() {
        return "Bid{" +
                "price=" + price +
                ", bidderName='" + bidderName + '\'' +
                '}';
    }

    public static class Builder {
        private int price;
        private String bidderName;

        public Builder price(int price) {
            this.price = price;
            return this;
        }

        public Builder bidderName(String bidderName) {
            this.bidderName = bidderName;
            return this;
        }

        public Bid build() {
            return new Builder.BidImpl();
        }

        private class BidImpl extends Bid {
            private BidImpl() {
                this.price = Builder.this.price;
                this.bidderName = Builder.this.bidderName;
            }
        }
    }
}
