package com.java.reflection.fields.models.exp1;

import com.java.reflection.fields.enums.exp1.Category;

public class Movie extends Product {
    public static final double MINIMUM_PRICE = 10.99;

    private boolean isReleased;
    private Category category;
    private double actualPrice;

    public Movie(String name, int year, double price, boolean isReleased, Category category) {
        super(name, year);
        this.isReleased = isReleased;
        this.category = category;
        this.actualPrice = Math.max(price, MINIMUM_PRICE);
    }

    // Nested class
    public class MovieStats {
        private double timesWatched;

        public MovieStats(double timesWatched) {
            this.timesWatched = timesWatched;
        }

        public double getRevenue() {
            return timesWatched * actualPrice;
        }
    }
}
