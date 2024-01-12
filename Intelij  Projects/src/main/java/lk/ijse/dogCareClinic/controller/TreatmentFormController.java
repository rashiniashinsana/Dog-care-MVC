package lk.ijse.dogCareClinic.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dogCareClinic.dto.PaymentDto;
import lk.ijse.dogCareClinic.dto.TreatmentDto;
import lk.ijse.dogCareClinic.dto.communityDto;
import lk.ijse.dogCareClinic.dto.tm.TreatmentTm;
import lk.ijse.dogCareClinic.model.CommunityModel;
import lk.ijse.dogCareClinic.model.PaymentModel;
import lk.ijse.dogCareClinic.model.TreatmentModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

public class TreatmentFormController {

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
    private TableColumn<?, ?> colDiagnosis;

    @FXML
    private TableColumn<?, ?> colDogID;

    @FXML
    private TableColumn<?, ?> colMedication;

    @FXML
    private TableColumn<?, ?> colPaymentID;

    @FXML
    private TableColumn<?, ?> colTreatmentID;

    @FXML
    private DatePicker dateView;

    @FXML
    private TableView<TreatmentTm> tblTreatment;


    @FXML
    private AnchorPane treatmentPane;

    @FXML
    private TextField txtDiagnosis;

    @FXML
    private TextField txtDogID;

    @FXML
    private TextField txtMedication;

    @FXML
    private TextField txtPaymentID;

    @FXML
    private TextField txtTreatmentId;


    public void initialize() {
        setCellValueFactory();
        loadAllTreatment();
    }

    private void setCellValueFactory() {
        colTreatmentID.setCellValueFactory(new PropertyValueFactory<>("TreatmentID"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        colMedication.setCellValueFactory(new PropertyValueFactory<>("Medication"));
        colDiagnosis.setCellValueFactory(new PropertyValueFactory<>("Diagnosis") );
        colDogID.setCellValueFactory(new PropertyValueFactory<>("DogID"));
        colPaymentID.setCellValueFactory(new PropertyValueFactory<>("PaymentID"));
    }

    private void loadAllTreatment() {
        var model = new TreatmentModel();

        ObservableList<TreatmentTm> obList = FXCollections.observableArrayList();

        try {
            List<TreatmentDto> dtoList = model.getAllTreatment();

            for (TreatmentDto dto : dtoList) {
                obList.add(
                        new TreatmentTm(
                                dto.getTreatmentID(),
                                dto.getDate(),
                                dto.getMedication(),
                                dto.getDiagnosis(),
                                dto.getDogID(),
                                dto.getPaymentID()
                        )
                );
            }

            tblTreatment.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean validateTreatment() {
        String idText = txtTreatmentId.getText();
        boolean isIdValid = idText.matches("[T][0-9]{3,}");
        if (!isIdValid) {
            new Alert((Alert.AlertType.ERROR), "Invalid Treatment ID").show();
            return false;
        }
        String PIDText = txtPaymentID.getText();
        boolean isTIDValid = Pattern.compile("[P][0-9]{3,}").matcher(PIDText).matches();
        if (!isTIDValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid Treatment ID").show();
            return false;
        }
        String DIDText = txtDogID.getText();
        boolean isDIDValid = Pattern.compile("[D][0-9]{3,}").matcher(DIDText).matches();
        if (!isDIDValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid Dog ID").show();
            return false;
        }
        return true;
    }
    @FXML
    void btnAddOnAction(ActionEvent event) {
        boolean isTreatmentValid = validateTreatment();
        if (isTreatmentValid) {
            String id = txtTreatmentId.getText();
            String date = String.valueOf(dateView.getValue());
            String medication = txtMedication.getText();
            String diagnosis = txtDiagnosis.getText();
            String dogid = txtDogID.getText();
            String paymentid = txtPaymentID.getText();

            var dto = new TreatmentDto(id, date, medication, diagnosis, dogid, paymentid);

            var model = new TreatmentModel();
            try {
                boolean isSaved = model.saveTreatment(dto);
                if (isSaved) {
                    loadAllTreatment();
                    new Alert(Alert.AlertType.CONFIRMATION, "Treatment Saved!").show();
                    clearFields();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    private void clearFields() {
        txtTreatmentId.setText("");
        dateView.setValue(LocalDate.now());
        txtMedication.setText("");
        txtDiagnosis.setText("");
        txtDogID.setText("");
        txtPaymentID.setText("");
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtTreatmentId.getText();

        var treatmentModel = new TreatmentModel();
        try {
            boolean isDeleted = TreatmentModel.deleteTreatment(id);

            if(isDeleted) {
                loadAllTreatment();
//                tblCustomer.refresh();
                new Alert(Alert.AlertType.CONFIRMATION, "Treatment Deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) throws SQLException {
        String id = txtTreatmentId.getText();

        var model = new TreatmentModel();
        TreatmentDto dto = model.searchTreatment(id);

        if(dto != null) {
            fillFields(dto);
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Treatment not found!").show();
        }
    }

    private void fillFields(TreatmentDto dto) {
        txtTreatmentId.setText(dto.getTreatmentID());
        dateView.setValue(LocalDate.now());
        txtMedication.setText(dto.getMedication());
        txtDiagnosis.setText(dto.getDiagnosis());
        txtPaymentID.setText(dto.getPaymentID());
        txtDogID.setText(dto.getDogID());
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        boolean isTreatmentValid = validateTreatment();
        if (isTreatmentValid) {
            String id = txtPaymentID.getText();
            String date = String.valueOf(dateView.getValue());
            String medication = txtMedication.getText();
            String diagnosis = txtDiagnosis.getText();
            String dogid = txtDogID.getText();
            String paymentid = txtPaymentID.getText();

            var dto = new TreatmentDto(id, date, medication, diagnosis, dogid, paymentid);

            var model = new TreatmentModel();
            try {
                boolean isUpdated = model.updateTreatment(dto);
                if (isUpdated) {
                    loadAllTreatment();
                    new Alert(Alert.AlertType.CONFIRMATION, "Treatment Updated!").show();
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

    public TableColumn<?, ?> getColDate() {
        return colDate;
    }

    public void setColDate(TableColumn<?, ?> colDate) {
        this.colDate = colDate;
    }

    public TableColumn<?, ?> getColDiagnosis() {
        return colDiagnosis;
    }

    public void setColDiagnosis(TableColumn<?, ?> colDiagnosis) {
        this.colDiagnosis = colDiagnosis;
    }

    public TableColumn<?, ?> getColDogID() {
        return colDogID;
    }

    public void setColDogID(TableColumn<?, ?> colDogID) {
        this.colDogID = colDogID;
    }

    public TableColumn<?, ?> getColMedication() {
        return colMedication;
    }

    public void setColMedication(TableColumn<?, ?> colMedication) {
        this.colMedication = colMedication;
    }

    public TableColumn<?, ?> getColPaymentID() {
        return colPaymentID;
    }

    public void setColPaymentID(TableColumn<?, ?> colPaymentID) {
        this.colPaymentID = colPaymentID;
    }

    public TableColumn<?, ?> getColTreatmentID() {
        return colTreatmentID;
    }

    public void setColTreatmentID(TableColumn<?, ?> colTreatmentID) {
        this.colTreatmentID = colTreatmentID;
    }

    public DatePicker getDateView() {
        return dateView;
    }

    public void setDateView(DatePicker dateView) {
        this.dateView = dateView;
    }

    public AnchorPane getTreatmentPane() {
        return treatmentPane;
    }

    public void setTreatmentPane(AnchorPane treatmentPane) {
        this.treatmentPane = treatmentPane;
    }

    public TextField getTxtDiagnosis() {
        return txtDiagnosis;
    }

    public void setTxtDiagnosis(TextField txtDiagnosis) {
        this.txtDiagnosis = txtDiagnosis;
    }

    public TextField getTxtDogID() {
        return txtDogID;
    }

    public void setTxtDogID(TextField txtDogID) {
        this.txtDogID = txtDogID;
    }

    public TextField getTxtMedication() {
        return txtMedication;
    }

    public void setTxtMedication(TextField txtMedication) {
        this.txtMedication = txtMedication;
    }

    public TextField getTxtPaymentID() {
        return txtPaymentID;
    }

    public void setTxtPaymentID(TextField txtPaymentID) {
        this.txtPaymentID = txtPaymentID;
    }

    public TextField getTxtTreatmentId() {
        return txtTreatmentId;
    }

    public void setTxtTreatmentId(TextField txtTreatmentId) {
        this.txtTreatmentId = txtTreatmentId;
    }


    public TableView<TreatmentTm> getTblTreatment() {
        return tblTreatment;
    }

    public void setTblTreatment(TableView<TreatmentTm> tblTreatment) {
        this.tblTreatment = tblTreatment;
    }
}