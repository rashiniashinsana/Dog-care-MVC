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
import lk.ijse.dogCareClinic.dto.InventoryDto;
import lk.ijse.dogCareClinic.dto.RecordDto;
import lk.ijse.dogCareClinic.dto.tm.RecordTm;
import lk.ijse.dogCareClinic.model.AppointmentModel;
import lk.ijse.dogCareClinic.model.InventoryModel;
import lk.ijse.dogCareClinic.model.RecordModel;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class RecordFormController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private JFXButton btn;

    @FXML
    private JFXButton btn1;

    @FXML
    private JFXButton btn11;

    @FXML
    private JFXButton btn111;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colRecordID;

    @FXML
    private TableView<RecordTm> tblRecord;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtRecordID;
    @FXML
    private DatePicker dateView;


    public void initialize() {
        setCellValueFactory();
        loadAllRecord();
    }

    private void setCellValueFactory() {
        colRecordID.setCellValueFactory(new PropertyValueFactory<>("recordId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    private void loadAllRecord() {
        var model = new RecordModel();

        ObservableList<RecordTm> obList = FXCollections.observableArrayList();

        try {
            List<RecordDto> dtoList = model.getAllRecords();

            for(RecordDto dto : dtoList) {
                obList.add(
                        new RecordTm(
                                dto.getRecordId(),
                                dto.getDescription(),
                                dto.getDate()
                        )
                );
            }

            tblRecord.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private boolean validateRecord() {
        String idText = txtRecordID.getText();
        boolean isIdValid = idText.matches("[R][0-9]{3,}");
        if (!isIdValid) {
            new Alert((Alert.AlertType.ERROR), "Invalid Record ID").show();
            return false;
        }
        return true;
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        boolean isRecordValid = validateRecord();
        if (isRecordValid) {
            String id = txtRecordID.getText();
            String description = txtDescription.getText();
            String date = String.valueOf(dateView.getValue());

            var dto = new RecordDto(id, description, date);

            var model = new RecordModel();
            try {
                boolean isSaved = model.saveRecord(dto);
                if (isSaved) {
                    loadAllRecord();
                    new Alert(Alert.AlertType.CONFIRMATION, "Record saved!").show();
                    clearFields();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    private void clearFields() {
        txtRecordID.setText("");
        txtDescription.setText("");
        dateView.setValue(LocalDate.now());
    }

    @FXML
    void btnAppointmentOnAction(ActionEvent event) throws IOException {
        rootPane.getChildren().clear();
        rootPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/appointment-form.fxml")));

    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        rootPane.getChildren().clear();
        rootPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/dashboard-form.fxml")));

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnCommunityOnAction(ActionEvent event) throws IOException {
        rootPane.getChildren().clear();
        rootPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/communitypro-form.fxml"))));
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        rootPane.getChildren().clear();
        rootPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/dashboard-form.fxml")));
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtRecordID.getText();

        var recordModel = new RecordModel();
        try {
            boolean isDeleted = recordModel.deleteRecord(id);

            if(isDeleted) {
                loadAllRecord();
//                tblCustomer.refresh();
                new Alert(Alert.AlertType.CONFIRMATION, "Record Deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnDogOnAction(ActionEvent event) throws IOException {
        rootPane.getChildren().clear();
        rootPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/dog-form.fxml"))));

    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException {
        rootPane.getChildren().clear();
        rootPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/employee-form.fxml"))));

    }

    @FXML
    void btnInventoryOnAction(ActionEvent event) throws IOException {
        rootPane.getChildren().clear();
        rootPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/inventory-form.fxml"))));

    }

    @FXML
    void btnOwnerOnAction(ActionEvent event) throws IOException {
        rootPane.getChildren().clear();
        rootPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/owner-form.fxml"))));

    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) throws IOException {
        rootPane.getChildren().clear();
        rootPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/payment-form.fxml"))));

    }

    @FXML
    void btnReportOnAction(ActionEvent event) throws IOException {
        rootPane.getChildren().clear();
        rootPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/reportForm.fxml"))));
    }
    @FXML
    void btnRecordOnAction(ActionEvent event) throws IOException {
        rootPane.getChildren().clear();
        rootPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/record-form.fxml"))));

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String id = txtRecordID.getText();

        var model = new RecordModel();
        try {
          RecordDto dto = model.searchRecord(id);

            if(dto != null) {
                fillFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Record not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    private void fillFields(RecordDto dto) {
        txtRecordID.setText(dto.getRecordId());
        txtDescription.setText(dto.getDescription());
        dateView.setValue(LocalDate.now());
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtRecordID.getText();
        String description = txtDescription.getText();
        String date = String.valueOf(dateView.getValue());

        var dto = new RecordDto(id, description, date);

        var model = new RecordModel();
        try {
            boolean isUpdated = model.updateRecord(dto);
            if(isUpdated) {
                loadAllRecord();
                new Alert(Alert.AlertType.CONFIRMATION, "Record Updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public AnchorPane getRootPane() {
        return rootPane;
    }

    public void setRootPane(AnchorPane rootPane) {
        this.rootPane = rootPane;
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

    public JFXButton getBtn1() {
        return btn1;
    }

    public void setBtn1(JFXButton btn1) {
        this.btn1 = btn1;
    }

    public JFXButton getBtn() {
        return btn;
    }

    public void setBtn(JFXButton btn) {
        this.btn = btn;
    }
}