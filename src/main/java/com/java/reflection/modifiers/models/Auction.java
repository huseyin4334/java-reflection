package com.java.reflection.modifiers.models;

import java.io.Serializable;
import java.util.*;

public class Auction implements Serializable {
    private final List<Bid> bids = new ArrayList<>();

    private transient volatile boolean isAuctionStarted;
    // volatile keyword is used to prevent thread caching of variables

    public synchronized void addBid(Bid bid) {
        this.bids.add(bid);
    }

    public List<Bid> getBids() {
        return Collections.unmodifiableList(bids);
    }

    public synchronized Optional<Bid> getHighestBid() {
        return bids.stream().max(Comparator.comparing(Bid::getPrice));
    }

    public void startAuction() {
        isAuctionStarted = true;
    }

    public boolean isAuctionRunning() {
        return isAuctionStarted;
    }

    public void stopAuction() {
        isAuctionStarted = false;
    }
}
