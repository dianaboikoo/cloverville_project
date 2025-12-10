package com.example.cloverville_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ListController {

    @FXML private ListView<Resident> listView;
    @FXML private TextField nameField;

    private ClovervilleFacade facade = ClovervilleFacade.getInstance();

    private ObservableList<Resident> residentsObs = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        // Load residents from facade
        residentsObs.setAll(facade.getAllResidents());

        listView.setItems(residentsObs);
    }

    @FXML
    private void handleAdd() {
        if (nameField.getText().isEmpty()) return;

        Resident r = facade.createResident(nameField.getText(), 0, 0);

        residentsObs.add(r);

        nameField.clear();
    }

    @FXML
    private void handleDelete() {
        Resident selected = listView.getSelectionModel().getSelectedItem();
        if (selected != null) {

            // Remove from facade list
            facade.getResidentList().removeResident(selected);

            // Remove from UI list
            residentsObs.remove(selected);
        }
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
    private void handleEdit() {
        Resident selected = listView.getSelectionModel().getSelectedItem();
        if (selected != null) {

            // Pass resident to EditController
            EditController.selectedResident = selected;

            SceneManager.switchTo("edit-view.fxml");
        }
    }
}
