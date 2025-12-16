package com.example.cloverville_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class TradeOffersController implements Initializable {

    @FXML private TableView<TradeOffer> tradeTable;
    @FXML private TableColumn<TradeOffer, String> ownerColumn;
    @FXML private TableColumn<TradeOffer, String> offerColumn;
    @FXML private TableColumn<TradeOffer, String> priceColumn;
    @FXML private TableColumn<TradeOffer, String> statusColumn;

    @FXML private TextField offerField;
    @FXML private TextField priceField;

    @FXML private ComboBox<Resident> ownerComboBox;
    @FXML private ComboBox<Resident> residentComboBox;
    @FXML private ComboBox<TradeOffer> taskComboBox;

    private final ClovervilleFacade facade = ClovervilleFacade.getInstance();

    private final ObservableList<Resident> residentsObs = FXCollections.observableArrayList();
    private final ObservableList<TradeOffer> offersObs = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        residentsObs.setAll(facade.getAllResidents());
        ownerComboBox.setItems(residentsObs);
        residentComboBox.setItems(residentsObs);

        offersObs.setAll(facade.getAllTradeOffers());
        tradeTable.setItems(offersObs);
        taskComboBox.setItems(offersObs);

        ownerColumn.setCellValueFactory(new PropertyValueFactory<>("owner"));
        offerColumn.setCellValueFactory(new PropertyValueFactory<>("tradeOffer"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("priceOrService"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        tradeTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                offerField.setText(newVal.getTradeOffer());
                priceField.setText(newVal.getPriceOrService());
            }
        });
    }

    @FXML
    private void handleAddOffer() {

        Resident owner = ownerComboBox.getValue();
        if (owner == null) return;

        String offer = offerField.getText();
        String price = priceField.getText();

        Integer cost = null;
        try { cost = Integer.parseInt(price); } catch (Exception ignored) {}

        facade.createTradeOffer(owner.getName(), offer, price, "Unassigned", cost);

        refresh();
        facade.exportAllData("cloverville_export.json"); // AUTO-SAVE

        offerField.clear();
        priceField.clear();
        ownerComboBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleEditOffer() {
        TradeOffer selected = tradeTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        facade.editTradeOffer(selected,
                offerField.getText(),
                priceField.getText(),
                selected.getStatus());

        refresh();
        facade.exportAllData("cloverville_export.json"); // AUTO-SAVE
    }

    @FXML
    private void handleAssignOffer() {
        TradeOffer offer = taskComboBox.getValue();
        Resident resident = residentComboBox.getValue();
        if (offer == null || resident == null) return;

        facade.assignTradeOffer(resident.getName(), offer);
        refresh();
        facade.exportAllData("cloverville_export.json"); // AUTO-SAVE
    }

    @FXML
    private void handleDeleteOffer() {

        TradeOffer selected = tradeTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        facade.deleteTradeOffer(selected);
        refresh();

        facade.exportAllData("cloverville_export.json"); // AUTO-SAVE
    }

    private void refresh() {
        offersObs.setAll(facade.getAllTradeOffers());
        residentsObs.setAll(facade.getAllResidents());
        tradeTable.refresh();
        taskComboBox.setItems(offersObs);
    }

    @FXML
    private void handleBackToMain() {
        SceneManager.switchTo("hello-view.fxml");
    }
}
