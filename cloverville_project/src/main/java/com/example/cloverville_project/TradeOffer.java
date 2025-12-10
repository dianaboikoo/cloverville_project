package com.example.cloverville_project;

public class TradeOffer {

    private String owner;           // Name of the resident who created the offer
    private String tradeOffer;      // Description of offer
    private String priceOrService;  // What the owner wants in return
    private String status;          // e.g. "Unassigned", "Assigned"
    private Integer pointCost;      // Optional transferable points

    // Needed by Gson
    public TradeOffer() {}

    public TradeOffer(String owner, String tradeOffer, String priceOrService,
                      String status, Integer pointCost) {
        this.owner = owner;
        this.tradeOffer = tradeOffer;
        this.priceOrService = priceOrService;
        this.status = status;
        this.pointCost = pointCost;
    }

    // ===========================
    // GETTERS
    // ===========================

    public String getOwner() {
        return owner;
    }

    public String getTradeOffer() {
        return tradeOffer;
    }

    public String getPriceOrService() {
        return priceOrService;
    }

    public String getStatus() {
        return status;
    }

    public Integer getPointCost() {
        return pointCost;
    }

    // ===========================
    // SETTERS
    // ===========================

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setTradeOffer(String tradeOffer) {
        this.tradeOffer = tradeOffer;
    }

    public void setPriceOrService(String priceOrService) {
        this.priceOrService = priceOrService;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPointCost(Integer pointCost) {
        this.pointCost = pointCost;
    }

    @Override
    public String toString() {
        // This is shown in ComboBoxes / Tables
        return tradeOffer + " (" + owner + ")";
    }
}
