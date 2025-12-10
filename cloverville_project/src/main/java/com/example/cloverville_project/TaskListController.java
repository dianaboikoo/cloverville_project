package com.example.cloverville_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TaskListController {

    @FXML private ListView<CommunalTask> taskListView;
    @FXML private TextField nameField;
    @FXML private TextArea descriptionField;
    @FXML private TextField personalPointsField;

    // Used to pass the selected task to the edit screen
    public static CommunalTask selectedTask;

    private ClovervilleFacade facade = ClovervilleFacade.getInstance();

    private ObservableList<CommunalTask> tasksObs = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        // Load tasks from facade
        tasksObs.setAll(facade.getAllTasks());

        taskListView.setItems(tasksObs);
    }

    @FXML
    private void handleAdd() {

        String name = nameField.getText();
        String desc = descriptionField.getText();
        int points = personalPointsField.getText().isEmpty()
                ? 0
                : Integer.parseInt(personalPointsField.getText());

        if (name.isEmpty()) return;

        // Create task via facade
        CommunalTask newTask = facade.createTask(name, desc, points);

        // Add to UI list
        tasksObs.add(newTask);

        nameField.clear();
        descriptionField.clear();
        personalPointsField.clear();
    }

    @FXML
    private void handleDelete() {
        CommunalTask selected = taskListView.getSelectionModel().getSelectedItem();
        if (selected != null) {

            // Delete from facade
            facade.deleteTask(selected);

            // Remove from UI list
            tasksObs.remove(selected);
        }
    }

    @FXML
    private void handleEdit() {
        selectedTask = taskListView.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            SceneManager.switchTo("task-edit.fxml");
        }
    }

    @FXML
    private void handleBack() {
        SceneManager.switchTo("list-view.fxml");
    }
}
