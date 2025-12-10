package com.example.cloverville_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class GreenActionsController {

    @FXML private TextField nameField;
    @FXML private TextField pointsField;
    @FXML private ComboBox<Resident> residentCombo;
    @FXML private ListView<String> actionList;

    private ClovervilleFacade facade = ClovervilleFacade.getInstance();

    private ObservableList<Resident> residentsObs = FXCollections.observableArrayList();

    // A UI-friendly history list
    public static ObservableList<String> assignedActions = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        // Load residents from Facade instead of static global lists
        residentsObs.setAll(facade.getAllResidents());
        residentCombo.setItems(residentsObs);

        // Keep existing action history behavior
        actionList.setItems(assignedActions);
    }

    @FXML
    private void handleAdd() {

        String name = nameField.getText();
        String pointsText = pointsField.getText();
        Resident resident = residentCombo.getValue();

        if (name.isEmpty() || pointsText.isEmpty() || resident == null) {
            System.out.println("Missing fields!");
            return;
        }

        int points = Integer.parseInt(pointsText);

        // --- Create and apply the green action using the facade ---
        facade.performGreenAction(resident.getName(), name);

        // --- Add to history list for UI ---
        String record = resident.getName() + " received +" + points + " points for: " + name;
        assignedActions.add(record);

        // Clear UI
        nameField.clear();
        pointsField.clear();
        residentCombo.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleBack() {
        SceneManager.switchTo("main-menu.fxml");
    }
}
