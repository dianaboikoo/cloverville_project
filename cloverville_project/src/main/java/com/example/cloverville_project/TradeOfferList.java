package com.example.cloverville_project;

import java.util.ArrayList;
import java.util.List;

public class TradeOfferList {

    private List<TradeOffer> offers = new ArrayList<>();

    public TradeOfferList() {}

    public void addOffer(TradeOffer offer) {
        offers.add(offer);
    }

    public void removeOffer(TradeOffer offer) {
        offers.remove(offer);
    }

    public List<TradeOffer> getAllOffers() {
        return offers;
    }

    public List<TradeOffer> getOffersByOwner(String ownerName) {
        List<TradeOffer> result = new ArrayList<>();
        for (TradeOffer offer : offers) {
            if (offer.getOwner().equalsIgnoreCase(ownerName)) {
                result.add(offer);
            }
        }
        return result;
    }
    public void clear() {
        offers.clear();
    }


    public List<TradeOffer> getOpenOffers() {
        List<TradeOffer> result = new ArrayList<>();
        for (TradeOffer offer : offers) {
            if ("open".equalsIgnoreCase(offer.getStatus())) {
                result.add(offer);
            }
        }
        return result;
    }
}
