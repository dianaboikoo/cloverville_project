package com.example.cloverville_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

public class CompleteTaskController {

    @FXML private ComboBox<Resident> residentCombo;
    @FXML private ComboBox<CommunalTask> taskCombo;
    @FXML private ListView<TaskLogEntry> logList;

    private ObservableList<Resident> residentsObs = FXCollections.observableArrayList();
    private ObservableList<CommunalTask> tasksObs = FXCollections.observableArrayList();

    private ClovervilleFacade facade = ClovervilleFacade.getInstance();

    @FXML
    public void initialize() {

        // Load data from facade
        residentsObs.setAll(facade.getAllResidents());
        tasksObs.setAll(facade.getAllTasks());

        residentCombo.setItems(residentsObs);
        taskCombo.setItems(tasksObs);

        // Log list stays global for now
        logList.setItems(TaskLog.log);
    }

    @FXML
    private void handleComplete() {

        Resident r = residentCombo.getValue();
        CommunalTask t = taskCombo.getValue();

        if (r == null || t == null) return;

        // Let the facade handle ALL logic
        TaskLogEntry entry = facade.completeTask(r.getName(), t.getName());

        if (entry != null) {
            logList.refresh();
        }
    }

    @FXML
    private void handleBack() {
        SceneManager.switchTo("list-view.fxml");
    }
}
