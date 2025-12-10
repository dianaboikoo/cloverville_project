package com.example.cloverville_project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to Cloverville!");
    }

    @FXML
    public void openTradeOffers() {
        SceneManager.switchTo("trade_offers.fxml");
    }

    @FXML
    public void openResidents() {
        SceneManager.switchTo("list-view.fxml");
    }

    @FXML
    public void openGreenActions() {
        SceneManager.switchTo("green-action.fxml");
    }
}
