package lk.ijse.dogCareClinic.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dogCareClinic.dto.AppointmentDto;
import lk.ijse.dogCareClinic.dto.OwnerDto;
import lk.ijse.dogCareClinic.dto.tm.AppointmentTm;
import lk.ijse.dogCareClinic.model.AppointmentModel;
import lk.ijse.dogCareClinic.model.RecordModel;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class AppointmentFormController {

        @FXML
        private AnchorPane appointment;

        @FXML
        private JFXButton btn;

        @FXML
        private JFXButton btn1;

        @FXML
        private JFXButton btn11;

        @FXML
        private JFXButton btn111;

        @FXML
        private TableColumn<?, ?> colAppID;

        @FXML
        private TableColumn<?, ?> colEmpID;

        @FXML
        private TableColumn<?, ?> colOwnerID;

        @FXML
        private TableColumn<?, ?> colDate;

        @FXML
        private TableColumn<?, ?> colPurpose;

        @FXML
        private TableColumn<?, ?> colTime;

        @FXML
        private DatePicker dateView;

        @FXML
        private TableView<AppointmentTm> tblAppointment;

        @FXML
        private TextField txtAppEmployeeID;

        @FXML
        private TextField txtAppId;

        @FXML
        private TextField txtAppOwnerID;

        @FXML
        private TextField txtAppTime;

        @FXML
        private TextField txtApppurpose;


        private ActionEvent event;

        public void initialize() throws SQLException {
                setCellValueFactory();
                loadAllAppointment();
        }


        private void setCellValueFactory() {
                colAppID.setCellValueFactory(new PropertyValueFactory<>("AppointmentId"));
                colOwnerID.setCellValueFactory(new PropertyValueFactory<>("OwnerID"));
                colEmpID.setCellValueFactory(new PropertyValueFactory<>("EmployeeID"));
                colDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
                colTime.setCellValueFactory(new PropertyValueFactory<>("Time"));
                colPurpose.setCellValueFactory(new PropertyValueFactory<>("Purpose"));
        }

        private void loadAllAppointment() throws SQLException {
                var model = new AppointmentModel();

                ObservableList<AppointmentTm> obList = FXCollections.observableArrayList();

                try {
                        List<AppointmentDto> dtoList = model.getAllAppointment();

                        for(AppointmentDto dto : dtoList) {
                                obList.add(
                                        new AppointmentTm(
                                                dto.getAppointmentID(),
                                                dto.getOwnerID(),
                                                dto.getEmployeeID(),
                                                dto.getDate(),
                                                dto.getTime(),
                                                dto.getPurpose()
                                        )
                                );
                        }

                        tblAppointment.setItems(obList);
                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }



        }

        private boolean validateAppointment() {
                String appidText = txtAppId.getText();
                boolean isIdValid = appidText.matches("[A][0-9]{3,}");
                if (!isIdValid) {
                        new Alert((Alert.AlertType.ERROR), "Invalid Appointment ID").show();
                        return false;
                }
                String owneridText = txtAppOwnerID.getText();
                boolean isownerIdValid = Pattern.compile("[O][0-9]{3,}").matcher(owneridText).matches();
                if (!isownerIdValid) {
                        new Alert(Alert.AlertType.ERROR, "Invalid Owner ID").show();
                        return false;
                }
                String employeeidText = txtAppEmployeeID.getText();
                boolean isemployeeIdValid = Pattern.compile("[E][0-9]{3,}").matcher(employeeidText).matches();
                if (!isemployeeIdValid) {
                        new Alert(Alert.AlertType.ERROR, "Invalid Employee ID").show();
                        return false;
                }
                return true;
        }

        @FXML
        void btnAddOnAction(ActionEvent event) throws SQLException {
                String id = txtAppId.getText();
                boolean isAppointmentValid = validateAppointment();
                if (isAppointmentValid) {
                        String AppointmentID = txtAppId.getText();
                        String OwnerID = txtAppOwnerID.getText();
                        String EmployeeID = txtAppEmployeeID.getText();
                        String date = String.valueOf(dateView.getValue());
                        String time = txtAppTime.getText();
                        String purpose = txtApppurpose.getText();


                        var dto = new AppointmentDto(AppointmentID, OwnerID, EmployeeID, date, time, purpose);

                        var model = new AppointmentModel();
                        try {
                                boolean isSaved = model.saveAppointment(dto);
                                if (isSaved) {
                                        loadAllAppointment();
                                        new Alert(Alert.AlertType.CONFIRMATION, "Appointment saved!").show();
                                        clearFields();
                                }
                        } catch (SQLException e) {
                                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                        }
                }
        }

        private void clearFields() {
                txtAppId.setText("");
                txtAppOwnerID.setText("");
                txtAppEmployeeID.setText("");
                dateView.setValue(LocalDate.now());
                txtAppTime.setText("");
                txtApppurpose.setText("");
        }

        @FXML
        void btnAppointmentOnAction(ActionEvent event) throws IOException {
                appointment.getChildren().clear();
                appointment.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/appointment-form.fxml"))));
        }

        @FXML
        void btnBackOnAction(ActionEvent event) throws IOException {
                appointment.getChildren().clear();
                appointment.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/dashboard-form.fxml"))));

        }

        @FXML
        void btnClearOnAction(ActionEvent event) {
                clearFields();
        }

        @FXML
        void btnCommunityOnAction(ActionEvent event) throws IOException {
                appointment.getChildren().clear();
                appointment.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/communitypro-form.fxml"))));


        }

        @FXML
        void btnDashboardOnAction(ActionEvent event) throws IOException {
                appointment.getChildren().clear();
                appointment.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/dashboard-form.fxml"))));

        }

        @FXML
        void btnDeleteOnAction(ActionEvent event) {
                String id = txtAppId.getText();

                var appointmentModel = new AppointmentModel();
                try {
                        boolean isDeleted = appointmentModel.deleteAppointment(id);

                        if(isDeleted) {
                                loadAllAppointment();
                                tblAppointment.refresh();
                                new Alert(Alert.AlertType.CONFIRMATION, "Appointment deleted!").show();
                        }
                } catch (SQLException e) {
                        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }

        }

        @FXML
        void btnDogOnAction(ActionEvent event) throws IOException {
                appointment.getChildren().clear();
                appointment.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/dog-form.fxml"))));

        }

        @FXML
        void btnEmployeeOnAction(ActionEvent event) throws IOException {
                appointment.getChildren().clear();
                appointment.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/employee-form.fxml"))));

        }

        @FXML
        void btnInventoryOnAction(ActionEvent event) throws IOException {
                appointment.getChildren().clear();
                appointment.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/inventory-form.fxml"))));

        }

        @FXML
        void btnOwnerOnAction(ActionEvent event) throws IOException {
                appointment.getChildren().clear();
                appointment.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/owner-form.fxml"))));

        }

        @FXML
        void btnPaymentOnAction(ActionEvent event) throws IOException {
                appointment.getChildren().clear();
                appointment.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/payment-form.fxml"))));

        }

        @FXML
        void btnReportOnAction(ActionEvent event) throws IOException {
                appointment.getChildren().clear();
                appointment.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/reportForm.fxml"))));

        }

        @FXML
        void btnRecordOnAction(ActionEvent event) throws IOException {
                appointment.getChildren().clear();
                appointment.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/record-form.fxml"))));

        }

        @FXML
        void btnSearchOnAction(ActionEvent event) {
                        String id = txtAppId.getText();

                        var model = new AppointmentModel();
                try {
                      AppointmentDto dto = model.searchAppointment(id);

                        if(dto != null) {
                                fillFields(dto);
                        } else {
                                new Alert(Alert.AlertType.INFORMATION, "Appointment not found!").show();
                        }
                } catch (SQLException e) {
                        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
                }

                private void fillFields(AppointmentDto dto) {
                        txtAppId.setText(dto.getAppointmentID());
                        txtAppEmployeeID.setText(dto.getEmployeeID());
                        txtAppOwnerID.setText(dto.getOwnerID());
                        dateView.setValue(LocalDate.now());
                        txtAppTime.setText(dto.getTime());
                        txtApppurpose.setText(dto.getPurpose());
                }



        @FXML
        void btnUpdateOnAction(ActionEvent event) throws SQLException {
                String id = txtAppId.getText();
                boolean isAppointmentid = validateAppointment();
                if (isAppointmentid) {
                        String AppointmentID = txtAppId.getText();
                        String OwnerID = txtAppOwnerID.getText();
                        String EmployeeID = txtAppEmployeeID.getText();
                        String date = String.valueOf(dateView.getValue());
                        String time = txtAppTime.getText();
                        String purpose = txtApppurpose.getText();


                        var dto = new AppointmentDto(AppointmentID, OwnerID, EmployeeID, date, time, purpose);

                        var model = new AppointmentModel();
                        try {
                                boolean isUpdated = model.updateAppointment(dto);
                                if (isUpdated) {
                                        loadAllAppointment();
                                        new Alert(Alert.AlertType.CONFIRMATION, "Record updated!").show();
                                }
                        } catch (SQLException e) {
                                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                        }
                }
        }

        public TextField getTxtApppurpose() {
                return txtApppurpose;
        }

        public void setTxtApppurpose(TextField txtApppurpose) {
                this.txtApppurpose = txtApppurpose;
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

        public TableColumn<?, ?> getColAppID() {
                return colAppID;
        }

        public void setColAppID(TableColumn<?, ?> colAppID) {
                this.colAppID = colAppID;
        }

        public TableColumn<?, ?> getColEmpID() {
                return colEmpID;
        }

        public void setColEmpID(TableColumn<?, ?> colEmpID) {
                this.colEmpID = colEmpID;
        }

        public TableColumn<?, ?> getColOwnerID() {
                return colOwnerID;
        }

        public void setColOwnerID(TableColumn<?, ?> colOwnerID) {
                this.colOwnerID = colOwnerID;
        }


        public void setDate(DatePicker date) {
                this.dateView = date;
        }

        public TableView<AppointmentTm> getTblAppointment() {
                return tblAppointment;
        }

        public void setTblAppointment(TableView<AppointmentTm> tblAppointment) {
                this.tblAppointment = tblAppointment;
        }


        public ActionEvent getEvent() {
                return event;
        }

        public void setEvent(ActionEvent event) {
                this.event = event;
        }

        public TextField getTxtAppEmployeeID() {
                return txtAppEmployeeID;
        }

        public void setTxtAppEmployeeID(TextField txtAppEmployeeID) {
                this.txtAppEmployeeID = txtAppEmployeeID;
        }

        public TextField getTxtAppOwnerID() {
                return txtAppOwnerID;
        }

        public void setTxtAppOwnerID(TextField txtAppOwnerID) {
                this.txtAppOwnerID = txtAppOwnerID;
        }

        public void setDateView(DatePicker dateView) {
                this.dateView = dateView;
        }

        public TextField getTxtAppTime() {
                return txtAppTime;
        }

        public void setTxtAppTime(TextField txtAppTime) {
                this.txtAppTime = txtAppTime;
        }

        public TableColumn<?, ?> getColDate() {
                return colDate;
        }

        public void setColDate(TableColumn<?, ?> colDate) {
                this.colDate = colDate;
        }

        public TableColumn<?, ?> getColPurpose() {
                return colPurpose;
        }

        public void setColPurpose(TableColumn<?, ?> colPurpose) {
                this.colPurpose = colPurpose;
        }

        public TableColumn<?, ?> getColTime() {
                return colTime;
        }

        public void setColTime(TableColumn<?, ?> colTime) {
                this.colTime = colTime;
        }
}


