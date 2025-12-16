package com.example.cloverville_project;

import java.util.ArrayList;
import java.util.List;

public class Resident {

    private String name;
    private int personalPoints;
    private int greenPoints;
    private List<TradeOffer> assignedTradeOffers = new ArrayList<>();
    private List<TradeOffer> ownedTradeOffers = new ArrayList<>();

    // NEW: percentage boost applied to each task
    private int participationBoostPercent = 0;

    public Resident() { }

    public Resident(String name, int personalPoints, int greenPoints) {
        this.name = name;
        this.personalPoints = personalPoints;
        this.greenPoints = greenPoints;
    }

    // --- existing getters/setters ---

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getPersonalPoints() { return personalPoints; }
    public void setPersonalPoints(int personalPoints) { this.personalPoints = personalPoints; }

    public int getGreenPoints() { return greenPoints; }
    public void setGreenPoints(int greenPoints) { this.greenPoints = greenPoints; }

    public List<TradeOffer> getAssignedTradeOffers() { return assignedTradeOffers; }
    public List<TradeOffer> getOwnedTradeOffers() { return ownedTradeOffers; }

    // --- NEW boost accessors ---

    public int getParticipationBoostPercent() {
        return participationBoostPercent;
    }

    public void setParticipationBoostPercent(int participationBoostPercent) {
        this.participationBoostPercent = participationBoostPercent;
    }

    @Override
    public String toString() {
        return name;
    }
}
