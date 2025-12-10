package com.example.cloverville_project;

import com.example.cloverville_project.TradeOffer;

import java.util.ArrayList;
import java.util.List;

public class Resident {

    private String name;
    private int personalPoints;
    private int greenPoints;
    private List<TradeOffer> assignedTradeOffers = new ArrayList<>();
    private List<TradeOffer> ownedTradeOffers = new ArrayList<>();

    // Needed by Gson
    public Resident() {
    }

    public Resident(String name, int personalPoints, int greenPoints) {
        this.name = name;
        this.personalPoints = personalPoints;
        this.greenPoints = greenPoints;
    }

    // --- getters & setters ---

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPersonalPoints() {
        return personalPoints;
    }

    public void setPersonalPoints(int personalPoints) {
        this.personalPoints = personalPoints;
    }

    public int getGreenPoints() {
        return greenPoints;
    }

    public void setGreenPoints(int greenPoints) {
        this.greenPoints = greenPoints;
    }

    public List<TradeOffer> getAssignedTradeOffers() {
        return assignedTradeOffers;
    }

    public List<TradeOffer> getOwnedTradeOffers() {
        return ownedTradeOffers;
    }
    public void setAssignedTradeOffers(List<TradeOffer> assignedTradeOffers) {
        this.assignedTradeOffers = assignedTradeOffers;
    }



    @Override
    public String toString() {
        return name;
    }
}
