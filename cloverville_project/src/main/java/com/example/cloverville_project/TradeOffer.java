package com.example.cloverville_project;

public class TradeOffer {

    private String owner;            // Owner name (string reference)
    private String tradeOffer;       // Description/title of the offer
    private String priceOrService;   // Either a service description OR number string
    private String status;           // "Unassigned", "Assigned", etc.
    private Integer pointCost;       // Parsed cost if numeric, otherwise null

    // Needed for JSON export
    public TradeOffer() {}

    public TradeOffer(String owner, String tradeOffer, String priceOrService,
                      String status, Integer pointCost) {
        this.owner = owner;
        this.tradeOffer = tradeOffer;
        this.priceOrService = priceOrService;
        this.status = status;
        this.pointCost = pointCost;
    }

    // ---------------------------
    // Getters
    // ---------------------------
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

    // ---------------------------
    // Setters
    // ---------------------------
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

    // ---------------------------
    // Useful for ComboBox and debugging
    // ---------------------------
    @Override
    public String toString() {
        return tradeOffer + " (Owner: " + owner + ")";
    }
}
