package com.example.cloverville_project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {

    @FXML private Label communityPoolLabel;

    private final ClovervilleFacade facade = ClovervilleFacade.getInstance();

    @FXML
    public void initialize() {
        communityPoolLabel.setText("Community Pool: " + facade.getCommunityPool());
    }

    @FXML
    private void openResidents() {
        SceneManager.switchTo("list-view.fxml");
    }

    @FXML
    private void openTradeOffers() {
        SceneManager.switchTo("trade_offers.fxml");
    }

    @FXML
    private void openGreenActions() {
        SceneManager.switchTo("green-action.fxml");
    }
}
