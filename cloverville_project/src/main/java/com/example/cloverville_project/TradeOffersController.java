package com.example.cloverville_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class TradeOffersController implements Initializable {

    @FXML
    private TableView<TradeOffer> tradeTable;

    @FXML
    private TableColumn<TradeOffer, String> ownerColumn;

    @FXML
    private TableColumn<TradeOffer, String> offerColumn;

    @FXML
    private TableColumn<TradeOffer, String> priceColumn;

    @FXML
    private TableColumn<TradeOffer, String> statusColumn;

    @FXML
    private TextField offerField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField statusField;

    @FXML
    private TextField pointCostField;

    @FXML
    private ComboBox<Resident> ownerComboBox;

    @FXML
    private ComboBox<Resident> residentComboBox;

    private ObservableList<TradeOffer> offers = FXCollections.observableArrayList();
    private ObservableList<Resident> residents = FXCollections.observableArrayList();

    private ClovervilleFacade facade = ClovervilleFacade.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Table column bindings
        tradeTable.setItems(offers);
        ownerColumn.setCellValueFactory(new PropertyValueFactory<>("owner"));
        offerColumn.setCellValueFactory(new PropertyValueFactory<>("tradeOffer"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("priceOrService"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Load initial data into UI lists
        residents.setAll(facade.getAllResidents());
        offers.setAll(facade.getAllTradeOffers());

        // Bind ComboBoxes
        ownerComboBox.setItems(residents);
        residentComboBox.setItems(residents);

        // When selecting an offer, display details
        tradeTable.getSelectionModel().selectedItemProperty().addListener((obs, old, selected) -> {
            if (selected != null) {
                offerField.setText(selected.getTradeOffer());
                priceField.setText(selected.getPriceOrService());
                statusField.setText(selected.getStatus());
                pointCostField.setText(selected.getPointCost() == null ? "" : selected.getPointCost().toString());
            }
        });
    }

    @FXML
    private void handleAddOffer() {

        Resident owner = ownerComboBox.getSelectionModel().getSelectedItem();
        if (owner == null) return;

        String offerText = offerField.getText();
        String price = priceField.getText();
        String status = statusField.getText().isBlank() ? "Unassigned" : statusField.getText();

        Integer pointCost = null;
        if (!pointCostField.getText().isBlank()) {
            try { pointCost = Integer.parseInt(pointCostField.getText().trim()); }
            catch (NumberFormatException ignored) {}
        }

        TradeOffer offer = facade.createTradeOffer(
                owner.getName(),
                offerText,
                price,
                status,
                pointCost
        );

        offers.add(offer);
        clearFields();
    }

    @FXML
    private void handleDeleteOffer() {
        TradeOffer selected = tradeTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        facade.deleteTradeOffer(selected);
        offers.remove(selected);
    }

    @FXML
    private void handleEditOffer() {
        TradeOffer selected = tradeTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        facade.editTradeOffer(
                selected,
                offerField.getText(),
                priceField.getText(),
                statusField.getText()
        );

        tradeTable.refresh();
    }

    @FXML
    private void handleAssignOffer() {

        TradeOffer selectedOffer = tradeTable.getSelectionModel().getSelectedItem();
        Resident assigned = residentComboBox.getSelectionModel().getSelectedItem();

        if (selectedOffer == null || assigned == null) return;

        facade.assignTradeOffer(assigned.getName(), selectedOffer);

        tradeTable.refresh();
    }

    private void clearFields() {
        offerField.clear();
        priceField.clear();
        statusField.clear();
        pointCostField.clear();
        ownerComboBox.getSelectionModel().clearSelection();
    }
}
