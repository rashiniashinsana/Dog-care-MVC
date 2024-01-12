package lk.ijse.dogCareClinic.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dogCareClinic.dto.AppointmentDto;
import lk.ijse.dogCareClinic.dto.OwnerDto;
import lk.ijse.dogCareClinic.dto.tm.OwnerTm;
import lk.ijse.dogCareClinic.model.AppointmentModel;
import lk.ijse.dogCareClinic.model.OwnerModel;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

public class OwnerFormController {

        @FXML
        private AnchorPane ownerPane;

        @FXML
        private JFXButton btn;

        @FXML
        private JFXButton btn1;

        @FXML
        private JFXButton btn11;

        @FXML
        private JFXButton btn111;

        @FXML
        private TableView<OwnerTm> tblOwner;

        @FXML
        private TableColumn<?, ?> colContacts;

        @FXML
        private TableColumn<?, ?> colOwnerID;

        @FXML
        private TableColumn<?, ?> colOwnerName;

        @FXML
        private TextField txtContacts;

        @FXML
        private TextField txtOwnerID;

        @FXML
        private TextField txtOwnerName;

    public void initialize() {
        setCellValueFactory();
        loadAllOwner();
    }

    private void setCellValueFactory() {
        colOwnerID.setCellValueFactory(new PropertyValueFactory<>("OwnerId"));
        colOwnerName.setCellValueFactory(new PropertyValueFactory<>("OwnerName"));
        colContacts.setCellValueFactory(new PropertyValueFactory<>("Contacts"));
    }


    private void loadAllOwner() {
        var model = new OwnerModel();

        ObservableList<OwnerTm> obList = FXCollections.observableArrayList();

        try {
            List<OwnerDto> dtoList = model.getAllOwner();

            for(OwnerDto dto : dtoList) {
                obList.add(
                        new OwnerTm(
                                dto.getOwnerId(),
                                dto.getOwnerName(),
                                dto.getContacts()
                        )
                );
            }

            tblOwner.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean validateOwner() {
        String idText = txtOwnerID.getText();
        boolean isIdValid = idText.matches("[O][0-9]{3,}");
        if (!isIdValid) {
            new Alert((Alert.AlertType.ERROR), "Invalid ID").show();
            return false;
        }
        String nameText = txtOwnerName.getText();
        boolean isNameValid = Pattern.compile("[A-Za-z].{2,}").matcher(nameText).matches();
        if (!isNameValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid Owner Name").show();
            return false;
        }
        String contactText = this.txtContacts.getText();
        boolean isContactValid = Pattern.compile("^[0-9]{10}$").matcher(contactText).matches();
        if (!isContactValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid Contact").show();
            return false;
        }
        return true;
    }

        @FXML
        void btnAddOnAction(ActionEvent event) {
            boolean isOwnerValid = validateOwner();
            if (isOwnerValid) {
                String id = txtOwnerID.getText();
                String name = txtOwnerName.getText();
                String contact = txtContacts.getText();

                var dto = new OwnerDto(id, name, contact);

                var model = new OwnerModel();
                try {
                    boolean isSaved = model.saveOwner(dto);
                    if (isSaved) {
                        loadAllOwner();
                        new Alert(Alert.AlertType.CONFIRMATION, "Owner saved!").show();
                        clearFields();
                    }
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            }
        }

        private void clearFields() {
            txtOwnerID.setText("");
            txtOwnerName.setText("");
            txtContacts.setText("");
        }

        @FXML
        void btnAppointmentOnAction(ActionEvent event) throws IOException {
            ownerPane.getChildren().clear();
            ownerPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/appointment-form.fxml")));
        }

        @FXML
        void btnBackOnAction(ActionEvent event) throws IOException {
            ownerPane.getChildren().clear();
            ownerPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/dashboard-form.fxml")));

        }

        @FXML
        void btnClearOnAction(ActionEvent event) {
            clearFields();
        }

        @FXML
        void btnCommunityOnAction(ActionEvent event) throws IOException {
            ownerPane.getChildren().clear();
            ownerPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/communitypro-form.fxml")));
        }

        @FXML
        void btnDashboardOnAction(ActionEvent event) throws IOException {
            ownerPane.getChildren().clear();
            ownerPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/dashboard-form.fxml")));

        }

        @FXML
        void btnDeleteOnAction(ActionEvent event) {
            String id = txtOwnerID.getText();

            var ownerModel = new OwnerModel();
            try {
                boolean isDeleted = ownerModel.deleteOwner(id);

                if(isDeleted) {
                    loadAllOwner();
//                tblCustomer.refresh();
                    new Alert(Alert.AlertType.CONFIRMATION, "Owner Deleted!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

        @FXML
        void btnDogOnAction(ActionEvent event) throws IOException {
            ownerPane.getChildren().clear();
            ownerPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/dog-form.fxml")));
        }

        @FXML
        void btnEmployeeOnAction(ActionEvent event) throws IOException {
            ownerPane.getChildren().clear();
            ownerPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/employee-form.fxml")));
        }

        @FXML
        void btnInventoryOnAction(ActionEvent event) throws IOException {
            ownerPane.getChildren().clear();
            ownerPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/inventory-form.fxml")));
        }

        @FXML
        void btnOwnerOnAction(ActionEvent event) throws IOException {
            ownerPane.getChildren().clear();
            ownerPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/owner-form.fxml")));
        }

        @FXML
        void btnPaymentOnAction(ActionEvent event) throws IOException {
            ownerPane.getChildren().clear();
            ownerPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/payment-form.fxml")));
        }


        @FXML
        void btnReportOnAction(ActionEvent event) throws IOException {
            ownerPane.getChildren().clear();
            ownerPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/reportForm.fxml")));
        }
        @FXML
        void btnRecordOnAction(ActionEvent event) throws IOException {
            ownerPane.getChildren().clear();
            ownerPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/record-form.fxml")));
        }

        @FXML
        void btnSearchOnAction(ActionEvent event) {
            String id = txtOwnerID.getText();

            var model = new OwnerModel();
            try {
                OwnerDto dto = model.searchOwner(id);

                if(dto != null) {
                    fillFields(dto);
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "Owner  not found!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

    private void fillFields(OwnerDto dto) {
        txtOwnerID.setText(dto.getOwnerId());
        txtOwnerName.setText(dto.getOwnerName());
        txtContacts.setText(dto.getContacts());
    }

        @FXML
        void btnUpdateOnAction(ActionEvent event) {
            boolean isOwnerValid = validateOwner();
            if (isOwnerValid) {
            String id = txtOwnerID.getText();
            String name = txtOwnerName.getText();
            String contact = txtContacts.getText();

            var dto = new OwnerDto(id, name, contact);

            var model = new OwnerModel();
            try {
                boolean isUpdated = model.updateOwner(dto);
                if(isUpdated) {
                    loadAllOwner();
                    new Alert(Alert.AlertType.CONFIRMATION, "Owner Updated!").show();
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

        public TableColumn<?, ?> getColContacts() {
            return colContacts;
        }

        public void setColContacts(TableColumn<?, ?> colContacts) {
            this.colContacts = colContacts;
        }

        public TableColumn<?, ?> getColOwnerID() {
            return colOwnerID;
        }

        public void setColOwnerID(TableColumn<?, ?> colOwnerID) {
            this.colOwnerID = colOwnerID;
        }

        public TableColumn<?, ?> getColOwnerName() {
            return colOwnerName;
        }

        public void setColOwnerName(TableColumn<?, ?> colOwnerName) {
            this.colOwnerName = colOwnerName;
        }

        public TextField getTxtContacts() {
            return txtContacts;
        }

        public void setTxtContacts(TextField txtContacts) {
            this.txtContacts = txtContacts;
        }

        public TextField getTxtOwnerID() {
            return txtOwnerID;
        }

        public void setTxtOwnerID(TextField txtOwnerID) {
            this.txtOwnerID = txtOwnerID;
        }

        public TextField getTxtOwnerName() {
            return txtOwnerName;
        }

        public void setTxtOwnerName(TextField txtOwnerName) {
            this.txtOwnerName = txtOwnerName;
        }

        public AnchorPane getOwnerPane() {
        return ownerPane;
    }

        public void setOwnerPane(AnchorPane ownerPane) {
        this.ownerPane = ownerPane;
    }





}

