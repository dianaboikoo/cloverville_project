package com.example.cloverville_project;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class GreenActionsController {

    @FXML private TextField nameField;
    @FXML private TextField pointsField;
    @FXML private ComboBox<Resident> residentCombo;
    @FXML private ListView<String> actionList;

    public static javafx.collections.ObservableList<String> assignedActions =
            FXCollections.observableArrayList();

    private final ClovervilleFacade facade = ClovervilleFacade.getInstance();

    @FXML
    public void initialize() {
        residentCombo.setItems(FXCollections.observableArrayList(facade.getAllResidents()));
        actionList.setItems(assignedActions);
    }

    @FXML
    private void handleAdd() {

        String name = nameField.getText();
        String pointsText = pointsField.getText();
        Resident resident = residentCombo.getValue();

        if (name.isEmpty() || pointsText.isEmpty() || resident == null) return;

        int points = Integer.parseInt(pointsText);

        //  STORE the green action in the system
        facade.createGreenAction(name, points);

        // APPLY it to the resident
        facade.performGreenAction(resident.getName(), name);

        // UI log
        assignedActions.add(resident.getName() + " received +" + points + " points for " + name);

        // EXPORT (auto-save)
        facade.exportAllData("cloverville_export.json");

        // Clear input
        nameField.clear();
        pointsField.clear();
        residentCombo.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleBack() {
        SceneManager.switchTo("hello-view.fxml");
    }
}
