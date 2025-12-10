package com.example.cloverville_project;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TaskEditController {

    @FXML private TextField nameField;
    @FXML private TextArea descriptionField;
    @FXML private TextField personalPointsField;

    private ClovervilleFacade facade = ClovervilleFacade.getInstance();

    @FXML
    public void initialize() {

        CommunalTask task = TaskListController.selectedTask;
        if (task == null) return;

        nameField.setText(task.getName());
        descriptionField.setText(task.getDescription());
        personalPointsField.setText(String.valueOf(task.getPersonalPoints()));
    }

    @FXML
    private void handleSave() {

        CommunalTask task = TaskListController.selectedTask;
        if (task == null) return;

        String newName = nameField.getText();
        String newDesc = descriptionField.getText();
        int newPoints = personalPointsField.getText().isEmpty()
                ? 0
                : Integer.parseInt(personalPointsField.getText());

        // Update task via facade
        facade.editTask(task, newName, newDesc, newPoints);

        // Return to the task list page
        SceneManager.switchTo("task-list.fxml");
    }

    @FXML
    private void handleBack() {
        SceneManager.switchTo("task-list.fxml");
    }
}
