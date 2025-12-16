package com.example.cloverville_project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditController {

    @FXML private TextField nameField;
    @FXML private TextField personalPointsField;
    @FXML private TextField greenPointsField;
    @FXML private TextField boostField;

    @FXML private Label participationCountLabel;
    @FXML private Label currentBoostLabel;

    private final ClovervilleFacade facade = ClovervilleFacade.getInstance();
    private Resident resident;

    @FXML
    public void initialize() {
        resident = facade.getSelectedResident();
        if (resident == null) return;

        nameField.setText(resident.getName());
        personalPointsField.setText(String.valueOf(resident.getPersonalPoints()));
        greenPointsField.setText(String.valueOf(resident.getGreenPoints()));
        boostField.setText(String.valueOf(resident.getParticipationBoostPercent()));

        // UPDATE THE LABELS
        updateParticipationInfo();
    }

    private void updateParticipationInfo() {
        int count = facade.getParticipationCount(resident);
        int boost = resident.getParticipationBoostPercent();

        participationCountLabel.setText("Completed Tasks: " + count);
        currentBoostLabel.setText("Boost: " + boost + "%");
    }

    @FXML
    private void handleSave() {
        resident.setName(nameField.getText());
        resident.setPersonalPoints(Integer.parseInt(personalPointsField.getText()));
        resident.setGreenPoints(Integer.parseInt(greenPointsField.getText()));

        facade.exportAllData("cloverville_export.json");

        SceneManager.switchTo("list-view.fxml");
    }

    @FXML
    private void handleApplyBoost() {
        if (boostField.getText().isEmpty()) return;

        resident.setParticipationBoostPercent(Integer.parseInt(boostField.getText()));

        // UPDATE LABELS WHEN BOOST CHANGES
        updateParticipationInfo();

        facade.exportAllData("cloverville_export.json");
    }

    @FXML
    private void handleBack() {
        SceneManager.switchTo("list-view.fxml");
    }
}
