package com.example.cloverville_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TaskListController {

    @FXML private ListView<CommunalTask> taskListView;
    @FXML private TextField nameField;
    @FXML private TextArea descriptionField;
    @FXML private TextField personalPointsField;

    private final ClovervilleFacade facade = ClovervilleFacade.getInstance();
    private final ObservableList<CommunalTask> tasksObs = FXCollections.observableArrayList();

    public static CommunalTask selectedTask;

    @FXML
    public void initialize() {
        tasksObs.setAll(facade.getAllTasks());
        taskListView.setItems(tasksObs);
    }

    @FXML
    private void handleAdd() {
        String name = nameField.getText();
        String desc = descriptionField.getText();
        int points = Integer.parseInt(personalPointsField.getText());

        CommunalTask t = facade.createTask(name, desc, points);
        tasksObs.add(t);

        facade.exportAllData("cloverville_export.json"); // AUTO-SAVE

        nameField.clear();
        descriptionField.clear();
        personalPointsField.clear();
    }

    @FXML
    private void handleDelete() {
        CommunalTask selected = taskListView.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        facade.deleteTask(selected);
        tasksObs.remove(selected);

        facade.exportAllData("cloverville_export.json"); // AUTO-SAVE
    }

    @FXML
    private void handleEdit() {
        selectedTask = taskListView.getSelectionModel().getSelectedItem();
        if (selectedTask == null) return;

        SceneManager.switchTo("task-edit.fxml");
    }

    @FXML
    private void handleBack() {
        SceneManager.switchTo("hello-view.fxml");
    }
}
