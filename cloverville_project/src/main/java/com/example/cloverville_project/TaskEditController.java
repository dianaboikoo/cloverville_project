package com.example.cloverville_project;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TaskEditController {

    @FXML private TextField nameField;
    @FXML private TextArea descriptionField;
    @FXML private TextField personalPointsField;

    private final ClovervilleFacade facade = ClovervilleFacade.getInstance();

    @FXML
    public void initialize() {
        CommunalTask t = TaskListController.selectedTask;

        nameField.setText(t.getName());
        descriptionField.setText(t.getDescription());
        personalPointsField.setText(String.valueOf(t.getPersonalPoints()));
    }

    @FXML
    private void handleSave() {
        CommunalTask t = TaskListController.selectedTask;

        facade.editTask(t,
                nameField.getText(),
                descriptionField.getText(),
                Integer.parseInt(personalPointsField.getText()));

        facade.exportAllData("cloverville_export.json"); // AUTO-SAVE

        SceneManager.switchTo("task-list.fxml");
    }

    @FXML
    private void handleBack() {
        SceneManager.switchTo("task-list.fxml");
    }
}
