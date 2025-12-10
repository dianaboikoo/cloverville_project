package com.example.cloverville_project;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditController {

    @FXML private TextField nameField;
    @FXML private TextField personalPointsField;
    @FXML private TextField greenPointsField;

    // Temporary storage of the resident being edited
    public static Resident selectedResident;

    private ClovervilleFacade facade =  ClovervilleFacade.getInstance();

    @FXML
    public void initialize() {
        if (selectedResident == null) return;

        nameField.setText(selectedResident.getName());
        personalPointsField.setText(String.valueOf(selectedResident.getPersonalPoints()));
        greenPointsField.setText(String.valueOf(selectedResident.getGreenPoints()));
    }

    @FXML
    private void handleSave() {

        if (selectedResident == null) return;

        selectedResident.setName(nameField.getText());
        selectedResident.setPersonalPoints(Integer.parseInt(personalPointsField.getText()));
        selectedResident.setGreenPoints(Integer.parseInt(greenPointsField.getText()));

        // No need to tell the facade — it updates the object directly

        SceneManager.switchTo("list-view.fxml");
    }

    @FXML
    private void handleBack() {
        SceneManager.switchTo("list-view.fxml");
    }
}
