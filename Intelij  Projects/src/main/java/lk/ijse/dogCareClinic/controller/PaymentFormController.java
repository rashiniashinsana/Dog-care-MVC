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
import lk.ijse.dogCareClinic.dto.OwnerDto;
import lk.ijse.dogCareClinic.dto.PaymentDto;
import lk.ijse.dogCareClinic.dto.tm.PaymentTm;
import lk.ijse.dogCareClinic.model.OwnerModel;
import lk.ijse.dogCareClinic.model.PaymentModel;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class PaymentFormController {

    @FXML
    private JFXButton btn;

    @FXML
    private JFXButton btn1;

    @FXML
    private JFXButton btn11;

    @FXML
    private JFXButton btn111;

    @FXML
    private TableView<PaymentTm> tblPayment;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtPaymentID;

    @FXML
    private DatePicker dateView;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colPaymentID;

    @FXML
    private AnchorPane paymentPane;

    public void initialize() {
        setCellValueFactory();
        loadAllPayment();
    }

    private void setCellValueFactory() {
        colPaymentID.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
    }

    private void loadAllPayment() {
        var model = new PaymentModel();

        ObservableList<PaymentTm> obList = FXCollections.observableArrayList();

        try {
            List<PaymentDto> dtoList = model.getAllPayment();

            for(PaymentDto dto : dtoList) {
                obList.add(
                        new PaymentTm(
                                dto.getPaymentId(),
                                dto.getAmount(),
                                dto.getDate()

                        )
                );
            }

            tblPayment.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean validatePayment() {
        String idText = txtPaymentID.getText();
        boolean isIdValid = idText.matches("[P][0-9]{3,}");
        if (!isIdValid) {
            new Alert((Alert.AlertType.ERROR), "Invalid Payment ID").show();
            return false;
        }
        String amountText = txtAmount.getText();
        boolean isamountValid = Pattern.compile("^[1-9][0-9]*(.[0-9]{2})?$").matcher(amountText).matches();
        if (!isamountValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid Amount").show();
            return false;
        }

        return true;
    }


    @FXML
    void btnAddOnAction(ActionEvent event) {
        boolean isPaymentValid = validatePayment();
        if (isPaymentValid) {
            String id = txtPaymentID.getText();
            String amount = txtAmount.getText();
            String date = String.valueOf(dateView.getValue());

            var dto = new PaymentDto(id, amount, date);

            var model = new PaymentModel();
            try {
                boolean isSaved = model.savePayment(dto);
                if (isSaved) {
                    loadAllPayment();
                    new Alert(Alert.AlertType.CONFIRMATION, "Payment saved!").show();
                    clearFields();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    private void clearFields() {
        txtPaymentID.setText("");
        txtAmount.setText("");
        dateView.setValue(LocalDate.now());
    }

    @FXML
    void btnAppointmentOnAction(ActionEvent event) throws IOException {
        paymentPane.getChildren().clear();
        paymentPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/appointment-form.fxml")));

    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        paymentPane.getChildren().clear();
        paymentPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/dashboard-form.fxml")));

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnCommunityOnAction(ActionEvent event) throws IOException {
        paymentPane.getChildren().clear();
        paymentPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/communitypro-form.fxml"))));
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        paymentPane.getChildren().clear();
        paymentPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/dashboard-form.fxml")));
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtPaymentID.getText();

        var paymentModel = new PaymentModel();
        try {
            boolean isDeleted = paymentModel.deletePayment(id);

            if(isDeleted) {
                loadAllPayment();
//                tblCustomer.refresh();
                new Alert(Alert.AlertType.CONFIRMATION, "Payment Deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnDogOnAction(ActionEvent event) throws IOException {
        paymentPane.getChildren().clear();
        paymentPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/dog-form.fxml"))));

    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException {
        paymentPane.getChildren().clear();
        paymentPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/employee-form.fxml"))));

    }

    @FXML
    void btnInventoryOnAction(ActionEvent event) throws IOException {
        paymentPane.getChildren().clear();
        paymentPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/inventory-form.fxml"))));

    }

    @FXML
    void btnOwnerOnAction(ActionEvent event) throws IOException {
        paymentPane.getChildren().clear();
        paymentPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/owner-form.fxml"))));

    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) throws IOException {
        paymentPane.getChildren().clear();
        paymentPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/payment-form.fxml"))));
    }

    @FXML
    void btnReportOnAction(ActionEvent event) throws IOException {
        paymentPane.getChildren().clear();
        paymentPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/reportForm.fxml"))));
    }
    @FXML
    void btnRecordOnAction(ActionEvent event) throws IOException {
        paymentPane.getChildren().clear();
        paymentPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/record-form.fxml"))));
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
            String id = txtPaymentID.getText();

            var model = new PaymentModel();
            try {
                PaymentModel dto = model.searchPayment(id);

                if(dto != null) {
                    fillFields((PaymentDto) dto);
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "Payment not found!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

    private void fillFields(PaymentDto dto) {
        txtPaymentID.setText(dto.getPaymentId());
        txtAmount.setText(dto.getAmount());
        dateView.setValue(LocalDate.parse(dto.getDate()));
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        boolean isPaymentValid = validatePayment();
        if (isPaymentValid) {
            String id = txtPaymentID.getText();
            String amount = txtAmount.getText();
            String date = String.valueOf(dateView.getValue());

            var dto = new PaymentDto(id, amount, date);

            var model = new PaymentModel();
            try {
                boolean isUpdated = model.updatePayment(dto);
                if (isUpdated) {
                    loadAllPayment();
                    new Alert(Alert.AlertType.CONFIRMATION, "Payment Updated!").show();
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

    public TableView<PaymentTm> getTblPayment() {
        return tblPayment;
    }

    public void setTblPayment(TableView<PaymentTm> tblPayment) {
        this.tblPayment = tblPayment;
    }

    public TextField getTxtAmount() {
        return txtAmount;
    }

    public void setTxtAmount(TextField txtAmount) {
        this.txtAmount = txtAmount;
    }

    public TextField getTxtPaymentID() {
        return txtPaymentID;
    }

    public void setTxtPaymentID(TextField txtPaymentID) {
        this.txtPaymentID = txtPaymentID;
    }

    public DatePicker getDateView() {
        return dateView;
    }

    public void setDateView(DatePicker dateView) {
        this.dateView = dateView;
    }

    public TableColumn<?, ?> getColAmount() {
        return colAmount;
    }

    public void setColAmount(TableColumn<?, ?> colAmount) {
        this.colAmount = colAmount;
    }

    public TableColumn<?, ?> getColPaymentID() {
        return colPaymentID;
    }

    public void setColPaymentID(TableColumn<?, ?> colPaymentID) {
        this.colPaymentID = colPaymentID;
    }
}