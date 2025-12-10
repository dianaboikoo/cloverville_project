package com.example.cloverville_project;

import java.util.List;

public class ClovervilleFacade {

    // -------------------------
    // SINGLETON (IMPORTANT!)
    // -------------------------
    private static ClovervilleFacade instance;

    public static ClovervilleFacade getInstance() {
        if (instance == null) instance = new ClovervilleFacade();
        return instance;
    }

    // Private constructor so no one else can create instances
    private ClovervilleFacade() {
        this.residentList = new ResidentList();
        this.tradeOfferList = new TradeOfferList();
        this.taskList = new CommunalTaskList();
        this.greenActionList = new GreenActionList();
    }

    // -------------------------
    // INTERNAL DATA LISTS
    // -------------------------
    private ResidentList residentList;
    private TradeOfferList tradeOfferList;
    private CommunalTaskList taskList;
    private GreenActionList greenActionList;


    // ============================================================
    // RESIDENT ACCESS
    // ============================================================

    public Resident createResident(String name, int personalPoints, int greenPoints) {
        Resident r = new Resident(name, personalPoints, greenPoints);
        residentList.addResident(r);
        return r;
    }

    public Resident getResident(String name) {
        return residentList.findByName(name);
    }

    public List<Resident> getAllResidents() {
        return residentList.getAllResidents();
    }

    public ResidentList getResidentList() {
        return residentList;
    }


    // ============================================================
    // TRADE OFFER ACCESS
    // ============================================================

    public List<TradeOffer> getAllTradeOffers() {
        return tradeOfferList.getAllOffers();
    }

    public TradeOffer createTradeOffer(String ownerName, String offer, String price,
                                       String status, Integer pointCost) {

        TradeOffer t = new TradeOffer(ownerName, offer, price, status, pointCost);

        // Add to main list
        tradeOfferList.addOffer(t);

        // Attach to the owner
        Resident owner = residentList.findByName(ownerName);
        if (owner != null) {
            owner.getOwnedTradeOffers().add(t);
        }

        return t;
    }

    public void deleteTradeOffer(TradeOffer t) {

        // Remove from master list
        tradeOfferList.removeOffer(t);

        // Remove from all residents
        for (Resident r : residentList.getAllResidents()) {
            r.getAssignedTradeOffers().remove(t);
            r.getOwnedTradeOffers().remove(t);
        }
    }

    public void editTradeOffer(TradeOffer offer,
                               String newOffer,
                               String newPrice,
                               String newStatus) {

        offer.setTradeOffer(newOffer);
        offer.setPriceOrService(newPrice);
        offer.setStatus(newStatus);
    }


    // ============================================================
    // ASSIGN TRADE OFFER (POINT TRANSFER)
    // ============================================================

    public void assignTradeOffer(String assignedResidentName, TradeOffer offer) {

        Resident assigned = residentList.findByName(assignedResidentName);
        Resident owner = residentList.findByName(offer.getOwner());

        if (assigned == null || owner == null) return;

        Integer cost = offer.getPointCost();

        if (cost != null && cost > 0) {

            if (assigned.getPersonalPoints() < cost) return;

            assigned.setPersonalPoints(assigned.getPersonalPoints() - cost);
            owner.setPersonalPoints(owner.getPersonalPoints() + cost);
        }

        offer.setStatus("Assigned");

        if (!assigned.getAssignedTradeOffers().contains(offer)) {
            assigned.getAssignedTradeOffers().add(offer);
        }
    }


    // ============================================================
    // COMMUNAL TASKS
    // ============================================================

    public List<CommunalTask> getAllTasks() {
        return taskList.getAllTasks();
    }

    public CommunalTask createTask(String name, String desc, int points) {
        CommunalTask t = new CommunalTask(name, desc, points);
        taskList.addTask(t);
        return t;
    }

    public void deleteTask(CommunalTask t) {
        taskList.removeTask(t);
    }
    public void editTask(CommunalTask task, String newName, String newDescription, int newPoints) {
        task.setName(newName);
        task.setDescription(newDescription);
        task.setPersonalPoints(newPoints);
    }


    public TaskLogEntry completeTask(String residentName, String taskName) {

        Resident r = residentList.findByName(residentName);
        CommunalTask t = taskList.findByName(taskName);

        if (r == null || t == null) return null;

        // Add personal points
        r.setPersonalPoints(r.getPersonalPoints() + t.getPersonalPoints());

        // Create log entry
        TaskLogEntry entry = new TaskLogEntry(r, t);
        TaskLog.log.add(entry);

        return entry;
    }


    // ============================================================
    // GREEN ACTIONS
    // ============================================================

    public GreenAction createGreenAction(String name, int points) {
        GreenAction a = new GreenAction(name, points);
        greenActionList.addAction(a);
        return a;
    }

    public List<GreenAction> getAllGreenActions() {
        return greenActionList.getAllActions();
    }

    public void performGreenAction(String residentName, String actionName) {
        Resident r = residentList.findByName(residentName);
        GreenAction a = greenActionList.findByName(actionName);

        if (r != null && a != null) {
            r.setGreenPoints(r.getGreenPoints() + a.getGreenPoints());
        }
    }
}
