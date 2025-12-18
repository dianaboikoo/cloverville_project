package com.example.cloverville_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

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
    @FXML
    private void handleResetPersonalPoints() {
        facade.resetAllPersonalPoints();

        // AUTO-SAVE to website JSON
        facade.exportAllData("cloverville_export.json");

        // Update UI (community pool might have changed)

    }
    @FXML
    private void handleGoToCompleteTask(){
        SceneManager.switchTo("complete-task.fxml");

    }
    @FXML
    private void handleGoToTaskPage(){
        SceneManager.switchTo("task-list.fxml");

    }



}
