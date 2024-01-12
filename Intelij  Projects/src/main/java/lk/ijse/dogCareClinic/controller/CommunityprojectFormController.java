package lk.ijse.dogCareClinic.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dogCareClinic.dto.AppointmentDto;
import lk.ijse.dogCareClinic.dto.communityDto;
import lk.ijse.dogCareClinic.dto.tm.CommunityTm;
import lk.ijse.dogCareClinic.model.AppointmentModel;
import lk.ijse.dogCareClinic.model.CommunityModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class CommunityprojectFormController implements Initializable {

        @FXML
        private TableColumn<?, ?> colDate;

        @FXML
        private TableColumn<?, ?> colLocation;

        @FXML
        private TableColumn<?, ?> colProjectId;

        @FXML
        private TableColumn<?, ?> colProjectname;

        @FXML
        private DatePicker date;

        @FXML
        private TableView<CommunityTm> tblCommunity;

        @FXML
        private TextField txtLocation;

        @FXML
        private TextField txtProjectId;

        @FXML
        private TextField txtProjectName;

        @FXML
        private AnchorPane communityPane;


        private boolean validateCommunityPro() {
                String idText = txtProjectId.getText();
                boolean isIdValid = idText.matches("[P][0-9]{3,}");
                if (!isIdValid) {
                        new Alert((Alert.AlertType.ERROR), "Invalid Project ID").show();
                        return false;
                }
                String nameText = txtProjectName.getText();
                boolean isNameValid = Pattern.compile("[A-Za-z].{2,}").matcher(nameText).matches();
                if (!isNameValid) {
                        new Alert(Alert.AlertType.ERROR, "Invalid Community Project").show();
                        return false;
                }
                return true;
        }

                @FXML
        void btnAddOnAction(ActionEvent event) throws SQLException {
                boolean isCommunityProValid = validateCommunityPro();
                if (isCommunityProValid) {
                        CommunityModel model = new CommunityModel();
                        boolean b = model.saveCustomer(new communityDto(txtProjectId.getText(), txtProjectName.getText(), String.valueOf(date.getValue()), txtLocation.getText()));
                        if (b) {
                                loadAllCommunity();
                                new Alert(Alert.AlertType.CONFIRMATION, "Project saved!").show();
                        }
                }
        }

        @FXML
        void btnAppointmentOnAction(ActionEvent event) throws IOException {
                communityPane.getChildren().clear();
                communityPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/appointment-form.fxml"))));

        }

        @FXML
        void btnBackOnAction(ActionEvent event) throws IOException {
                communityPane.getChildren().clear();
                communityPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/dashboard-form.fxml"))));

        }

        @FXML
        void btnClearOnAction(ActionEvent event) {
                txtProjectId.setText("");
                txtProjectName.setText("");
                date.setValue(LocalDate.now());
                txtLocation.setText("");
        }

        @FXML
        void btnCommunityOnAction(ActionEvent event)  {

        }

        @FXML
        void btnDashboardOnAction(ActionEvent event) throws IOException {
                communityPane.getChildren().clear();
                communityPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/dashboard-form.fxml"))));

        }

        @FXML
        void btnDeleteOnAction(ActionEvent event) {
                String id = txtProjectId.getText();

                var communityModel = new CommunityModel();
                try {
                        boolean isDeleted = communityModel.deleteCommunity(id);

                        if(isDeleted) {
                                loadAllCommunity();
//                                tblCustomer.refresh();
                                new Alert(Alert.AlertType.CONFIRMATION, "Community Project Deleted!").show();
                        }
                } catch (SQLException e) {
                        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
        }

        @FXML
        void btnDogOnAction(ActionEvent event) throws IOException {
                communityPane.getChildren().clear();
                communityPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/dog-form.fxml"))));

        }

        @FXML
        void btnEmployeeOnAction(ActionEvent event) throws IOException {
                communityPane.getChildren().clear();
                communityPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/employee-form.fxml"))));

        }

        @FXML
        void btnInventoryOnAction(ActionEvent event) throws IOException {
                communityPane.getChildren().clear();
                communityPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/inventory-form.fxml"))));

        }

        @FXML
        void btnOwnerOnAction(ActionEvent event) throws IOException {
                communityPane.getChildren().clear();
                communityPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/owner-form.fxml"))));

        }

        @FXML
        void btnPaymentOnAction(ActionEvent event) throws IOException {
                communityPane.getChildren().clear();
                communityPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/payment-form.fxml"))));

        }

        @FXML
        void btnReportOnAction(ActionEvent event) throws IOException {
                communityPane.getChildren().clear();
                communityPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/reportForm.fxml"))));

        }
        @FXML
        void btnRecordOnAction(ActionEvent event) throws IOException {
                communityPane.getChildren().clear();
                communityPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/record-form.fxml"))));

        }

        @FXML
        void btnSearchOnAction(ActionEvent event) throws SQLException {
                String id = txtProjectId.getText();

                var model = new CommunityModel();
                communityDto dto = model.searchCommunity(id);

                if(dto != null) {
                        fillFields(dto);
                } else {
                        new Alert(Alert.AlertType.INFORMATION, "Project not found!").show();
                }
        }

        private void fillFields(communityDto dto) {
                txtProjectId.setText(dto.getProjectId());
                txtProjectName.setText(dto.getName());
                date.setValue(LocalDate.now());
                txtLocation.setText(dto.getLocation());
        }



        @FXML
        void btnUpdateOnAction(ActionEvent event) throws SQLException {
                String id = txtProjectId.getText();
                boolean isCommunityProValid = validateCommunityPro();
                if (isCommunityProValid) {
                        CommunityModel model = new CommunityModel();
                        try {
                                boolean isUpdated = model.updateProject(new communityDto(txtProjectId.getText(), txtProjectName.getText(), String.valueOf(date.getValue()), txtLocation.getText()));
                                if (isUpdated) {
                                        loadAllCommunity();
                                        new Alert(Alert.AlertType.CONFIRMATION, "Community updated!").show();
                                }
                        } catch (SQLException e) {
                                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                        }
                }
        }
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                setCellValueFactory();
                loadAllCommunity();
        }

        private void setCellValueFactory() {
                colProjectId.setCellValueFactory(new PropertyValueFactory<>("projectId"));
                colProjectname.setCellValueFactory(new PropertyValueFactory<>("Name"));
                colDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
                colLocation.setCellValueFactory(new PropertyValueFactory<>("Location"));

        }

        private void loadAllCommunity() {
                var model = new CommunityModel();

                ObservableList<CommunityTm> obList = FXCollections.observableArrayList();

                try {
                        List<communityDto> dtoList = model.getAllCustomers();

                        for(communityDto dto : dtoList) {
                                obList.add(
                                        new CommunityTm(
                                                dto.getProjectId(),
                                                dto.getName(),
                                                dto.getDate(),
                                                dto.getLocation()
                                        )
                                );
                        }

                        tblCommunity.setItems(obList);
                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }
        }
}
