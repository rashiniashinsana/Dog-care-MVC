package lk.ijse.dogCareClinic.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dogCareClinic.dto.InventoryDto;
import lk.ijse.dogCareClinic.dto.tm.InventoryTm;
import lk.ijse.dogCareClinic.model.InventoryModel;
import lk.ijse.dogCareClinic.model.PlaceInventoryModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class InventoryFormController {

    @FXML
    private JFXButton btn;

    @FXML
    private JFXButton btn1;

    @FXML
    private JFXButton btn11;

    @FXML
    private JFXButton btn111;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemID;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colUnitPrice;


    @FXML
    private TableView<InventoryTm> tblInventory;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtItemID;

    @FXML
    private TextField txtItemName;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    private   AnchorPane inventoryPane;

    public void initialize() {
        setCellValueFactory();
        loadAllInventory();
    }

    private void setCellValueFactory() {
        colItemID.setCellValueFactory(new PropertyValueFactory<>("Item_ID"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("Item_Name"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("Unit_Price"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));

    }

    private void loadAllInventory() {
        var model = new InventoryModel();

        ObservableList<InventoryTm> obList = FXCollections.observableArrayList();

        try {
            List<InventoryDto> dtoList = model.getAllInventory();

            for(InventoryDto dto : dtoList) {
                obList.add(
                        new InventoryTm(
                                dto.getItem_ID(),
                                dto.getItem_Name(),
                                dto.getDescription(),
                                dto.getUnit_Price(),
                                dto.getQuantity()
                        )
                );
            }

            tblInventory.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean validateInventory() {
        String idText = txtItemID.getText();
        boolean isIdValid = idText.matches("[I][0-9]{3,}");
        if (!isIdValid) {
            new Alert((Alert.AlertType.ERROR), "Invalid ID").show();
            return false;
        }
        String nameText = txtItemName.getText();
        boolean isNameValid = Pattern.compile("[A-Za-z]{3,}").matcher(nameText).matches();
        if (!isNameValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid Inventory Name").show();
            return false;
        }

        String nameUnitPrice = txtUnitPrice.getText();
        boolean isUnitPriceValid = Pattern.compile("^[1-9][0-9]*(.[0-9]{2})?$").matcher(nameUnitPrice).matches();
        if (!isUnitPriceValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid Inventory UnitPrice").show();
            return false;
        }

        String nameQuantity = txtQuantity.getText();
        boolean isUnitQuantityValid = Pattern.compile("^[1-9][0-9]*$").matcher(nameQuantity).matches();
        if (!isUnitQuantityValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid Inventory Quantity").show();
            return false;
        }
        return true;
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

        boolean isInventoryValid = validateInventory();
        if (isInventoryValid) {
            String id = txtItemID.getText();
            String name = txtItemName.getText();
            String description = txtDescription.getText();
            String unitprice = txtUnitPrice.getText();
            String quantity = txtQuantity.getText();


            var dto = new InventoryDto(id, name, description, unitprice, quantity);

            var model = new PlaceInventoryModel();
            try {
                boolean isSaved = model.PlaceCustomerOrder(dto);
                if (isSaved) {
                    loadAllInventory();
                    new Alert(Alert.AlertType.CONFIRMATION, "Inventory Saved!").show();
                    clearFields();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    private void clearFields() {
        txtItemID.setText("");
        txtItemName.setText("");
        txtDescription.setText("");
        txtUnitPrice.setText("");
        txtQuantity.setText("");
    }


    @FXML
    void btnAppointmentOnAction(ActionEvent event) throws IOException {
        inventoryPane.getChildren().clear();
        inventoryPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/appointment-form.fxml")));

    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        inventoryPane.getChildren().clear();
        inventoryPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/dashboard-form.fxml")));

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnCommunityOnAction(ActionEvent event) throws IOException {
        inventoryPane.getChildren().clear();
        inventoryPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/communitypro-form.fxml"))));
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        inventoryPane.getChildren().clear();
        inventoryPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/dashboard-form.fxml")));
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtItemID.getText();

        var inventoryModel = new InventoryModel();
        try {
            boolean isDeleted = inventoryModel.deleteInventory(id);

            if(isDeleted) {
                loadAllInventory();
//                tblCustomer.refresh();
                new Alert(Alert.AlertType.CONFIRMATION, "Inventory Deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnDogOnAction(ActionEvent event) throws IOException {
        inventoryPane.getChildren().clear();
        inventoryPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/dog-form.fxml"))));
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException {
        inventoryPane.getChildren().clear();
        inventoryPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/employee-form.fxml"))));

    }

    @FXML
    void btnInventoryOnAction(ActionEvent event) throws IOException {
        inventoryPane.getChildren().clear();
        inventoryPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/inventory-form.fxml"))));
    }

    @FXML
    void btnOwnerOnAction(ActionEvent event) throws IOException {
        inventoryPane.getChildren().clear();
        inventoryPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/owner-form.fxml"))));
    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) throws IOException {
        inventoryPane.getChildren().clear();
        inventoryPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/payment-form.fxml"))));
    }

    @FXML
    void btnReportOnAction(ActionEvent event) throws IOException {
        inventoryPane.getChildren().clear();
        inventoryPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/reportForm.fxml"))));
    }
    @FXML
    void btnRecordOnAction(ActionEvent event) throws IOException {
        inventoryPane.getChildren().clear();
        inventoryPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/record-form.fxml"))));

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) throws SQLException {

        String id = txtItemID.getText();

        var model = new InventoryModel();
        InventoryDto dto = model.searchInventory(id);

        if(dto != null) {
            fillFields(dto);
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Inventory  not found!").show();
        }
    }

    private void fillFields(InventoryDto dto) {
        txtItemID.setText(dto.getItem_ID());
        txtItemName.setText(dto.getItem_Name());
        txtDescription.setText(dto.getDescription());
        txtUnitPrice.setText(dto.getUnit_Price());
        txtQuantity.setText(String.valueOf(dto.getQuantity()));
    }


    @FXML
    void btnSupplierOnAction(ActionEvent event) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/supplier-form.fxml"))));
        Stage stage1 = new Stage();
        stage1.setScene(scene);
        stage1.show();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        boolean isInventoryValid = validateInventory();
        if (isInventoryValid) {
            String id = txtItemID.getText();
            String name = txtItemName.getText();
            String description = txtDescription.getText();
            String unitprice = txtUnitPrice.getText();
            String quantity = txtQuantity.getText();

            var dto = new InventoryDto(id, name, description, unitprice, quantity);

            var model = new InventoryModel();
            try {
                boolean isUpdated = model.updateInventory(dto);
                if (isUpdated) {
                    loadAllInventory();
                    new Alert(Alert.AlertType.CONFIRMATION, "Inventory Updated!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    public JFXButton getBtn() {
        return btn;
    }

    public void setBtn(JFXButton btn) {
        this.btn = btn;
    }

    public JFXButton getBtn1() {
        return btn1;
    }

    public void setBtn1(JFXButton btn1) {
        this.btn1 = btn1;
    }

    public JFXButton getBtn11() {
        return btn11;
    }

    public void setBtn11(JFXButton btn11) {
        this.btn11 = btn11;
    }

    public JFXButton getBtn111() {
        return btn111;
    }

    public void setBtn111(JFXButton btn111) {
        this.btn111 = btn111;
    }

    public TableView<InventoryTm> getTblInventory() {
        return tblInventory;
    }

    public void setTblInventory(TableView<InventoryTm> tblInventory) {
        this.tblInventory = tblInventory;
    }

    public TextField getTxtDescription() {
        return txtDescription;
    }

    public void setTxtDescription(TextField txtDescription) {
        this.txtDescription = txtDescription;
    }

    public TextField getTxtItemID() {
        return txtItemID;
    }

    public void setTxtItemID(TextField txtItemID) {
        this.txtItemID = txtItemID;
    }

    public TextField getTxtItemName() {
        return txtItemName;
    }

    public void setTxtItemName(TextField txtItemName) {
        this.txtItemName = txtItemName;
    }

    public TextField getTxtQuantity() {
        return txtQuantity;
    }

    public void setTxtQuantity(TextField txtQuantity) {
        this.txtQuantity = txtQuantity;
    }

    public TextField getTxtSearch() {
        return txtSearch;
    }

    public void setTxtSearch(TextField txtSearch) {
        this.txtSearch = txtSearch;
    }

    public AnchorPane getInventoryPane() {
        return inventoryPane;
    }

    public void setInventoryPane(AnchorPane inventoryPane) {
        this.inventoryPane = inventoryPane;
    }

    public TableColumn<?, ?> getColItemID() {
        return colItemID;
    }

    public void setColItemID(TableColumn<?, ?> colItemID) {
        this.colItemID = colItemID;
    }

    public TableColumn<?, ?> getColItemName() {
        return colItemName;
    }

    public void setColItemName(TableColumn<?, ?> colItemName) {
        this.colItemName = colItemName;
    }

    public TableColumn<?, ?> getColQuantity() {
        return colQuantity;
    }

    public void setColQuantity(TableColumn<?, ?> colQuantity) {
        this.colQuantity = colQuantity;
    }

    public TableColumn<?, ?> getColUnitPrice() {
        return colUnitPrice;
    }

    public void setColUnitPrice(TableColumn<?, ?> colUnitPrice) {
        this.colUnitPrice = colUnitPrice;
    }

    public TextField getTxtUnitPrice() {
        return txtUnitPrice;
    }

    public void setTxtUnitPrice(TextField txtUnitPrice) {
        this.txtUnitPrice = txtUnitPrice;
    }
}