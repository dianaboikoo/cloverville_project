package com.example.cloverville_project;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

public class CompleteTaskController {

    @FXML private ComboBox<Resident> residentCombo;
    @FXML private ComboBox<CommunalTask> taskCombo;
    @FXML private ListView<TaskLogEntry> logList;

    private final ClovervilleFacade facade = ClovervilleFacade.getInstance();

    @FXML
    public void initialize() {
        residentCombo.setItems(FXCollections.observableArrayList(facade.getAllResidents()));
        taskCombo.setItems(FXCollections.observableArrayList(facade.getAllTasks()));
        logList.setItems(TaskLog.log);
    }

    @FXML
    private void handleComplete() {
        Resident r = residentCombo.getValue();
        CommunalTask t = taskCombo.getValue();

        if (r == null || t == null) return;

        // COMPLETE TASK
        facade.completeTask(r.getName(), t.getName());

        logList.refresh();

        // AUTO EXPORT
        facade.exportAllData("cloverville_export.json");
    }

    @FXML
    private void handleBack() {
        SceneManager.switchTo("hello-view.fxml");
    }
}
