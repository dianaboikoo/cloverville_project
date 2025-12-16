package com.example.cloverville_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ListController {

    @FXML private ListView<Resident> listView;
    @FXML private TextField nameField;

    private final ClovervilleFacade facade = ClovervilleFacade.getInstance();
    private final ObservableList<Resident> residentsObs = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        listView.setItems(residentsObs);
        refreshList(); // refresh every time this screen loads
    }

    private void refreshList() {
        residentsObs.setAll(facade.getAllResidents());
        listView.refresh();
    }

    @FXML
    private void handleAdd() {
        String name = nameField.getText();
        if (name.isEmpty()) return;

        facade.createResident(name, 0, 0);
        refreshList();

        // AUTO-SAVE
        facade.exportAllData("cloverville_export.json");

        nameField.clear();
    }

    @FXML
    private void handleDelete() {
        Resident selected = listView.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        facade.deleteResident(selected);
        refreshList();

        // AUTO-SAVE
        facade.exportAllData("cloverville_export.json");
    }

    @FXML
    private void handleEdit() {
        Resident selected = listView.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        facade.setSelectedResident(selected);
        SceneManager.switchTo("edit-view.fxml");
    }

    @FXML
    private void handleGoToCompleteTask() {
        SceneManager.switchTo("complete-task.fxml");
    }

    @FXML
    private void handleGoToTaskPage() {
        SceneManager.switchTo("task-list.fxml");
    }

    @FXML
    private void handleBackToMain() {
        SceneManager.switchTo("hello-view.fxml");
    }
}
