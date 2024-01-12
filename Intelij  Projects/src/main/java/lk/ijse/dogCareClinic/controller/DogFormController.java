package lk.ijse.dogCareClinic.controller;

        import com.jfoenix.controls.JFXButton;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Scene;
        import javafx.scene.control.*;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.scene.layout.AnchorPane;
        import javafx.stage.Stage;
        import lk.ijse.dogCareClinic.dto.DogDto;
        import lk.ijse.dogCareClinic.dto.communityDto;
        import lk.ijse.dogCareClinic.dto.tm.DogTm;
        import lk.ijse.dogCareClinic.model.CommunityModel;
        import lk.ijse.dogCareClinic.model.DogModel;
        import java.io.IOException;
        import java.sql.SQLException;
        import java.time.LocalDate;
        import java.util.List;
        import java.util.Objects;
        import java.util.regex.Pattern;

public class DogFormController {
    @FXML
    private AnchorPane dogPane;

    @FXML
    private JFXButton btn;

    @FXML
    private JFXButton btn1;

    @FXML
    private JFXButton btn11;

    @FXML
    private JFXButton btn111;

    @FXML
    private TableColumn<?, ?> colAge;

    @FXML
    private TableColumn<?, ?> colBreed;

    @FXML
    private TableColumn<?, ?> colDogID;

    @FXML
    private TableColumn<?, ?> colDogName;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TableColumn<?, ?> colOwnerID;

    @FXML
    private TableView<DogTm> tblDog;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtBreed;

    @FXML
    private TextField txtDogID;

    @FXML
    private TextField txtDogName;

    @FXML
    private TextField txtGender;

    @FXML
    private TextField txtOwnerID;

    @FXML
    private DatePicker dateView;

    public void initialize() {
        setCellValueFactory();
        loadAllDog();
    }

    private void setCellValueFactory() {
        colDogID.setCellValueFactory(new PropertyValueFactory<>("D_ID"));
        colDogName.setCellValueFactory(new PropertyValueFactory<>("D_Name"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        colBreed.setCellValueFactory(new PropertyValueFactory<>("Breed"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("Age"));
        colOwnerID.setCellValueFactory(new PropertyValueFactory<>("O_ID"));
    }

    private void loadAllDog() {
        var model = new DogModel();

        ObservableList<DogTm> obList = FXCollections.observableArrayList();

        try {
            List<DogDto> dtoList = model.getAllDog();

            for (DogDto dto : dtoList) {
                obList.add(
                        new DogTm(
                                dto.getD_ID(),
                                dto.getD_Name(),
                                dto.getGender(),
                                dto.getBreed(),
                                dto.getAge(),
                                dto.getO_ID()
                        )
                );
            }

            tblDog.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean validateDog(){
        String idText = txtOwnerID.getText();
        boolean isIdValid = idText.matches("[O][0-9]{3,}");
        if (!isIdValid) {
            new Alert((Alert.AlertType.ERROR), "Invalid ID").show();
            return false;
        }
        String nameText = txtDogName.getText();
        boolean isNameValid = Pattern.compile("[A-Za-z]{2,}").matcher(nameText).matches();
        if (!isNameValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid Dog Name").show();
            return false;
        }

        String ageText = this.txtAge.getText();
        boolean isAgeValid = Pattern.compile("[0-9]").matcher(ageText).matches();
        if (!isAgeValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid Age").show();
            return false;
        }
        return true;
    }

    @FXML
        void btnAddOnAction(ActionEvent event) {
        boolean isDogValid = validateDog();
        if (isDogValid) {
            String D_ID = txtDogID.getText();
            String D_Name = txtDogName.getText();
            String Gender = txtGender.getText();
            String Breed = txtBreed.getText();
            String Age = txtAge.getText();
            String O_ID = txtOwnerID.getText();


            var dto = new DogDto(D_ID, D_Name, Gender, Breed, Age, O_ID);

            var model = new DogModel();
            try {
                boolean isSaved = model.saveDog(dto);
                if (isSaved) {
                    loadAllDog();
                    new Alert(Alert.AlertType.CONFIRMATION, "Record saved!").show();
                    clearFields();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

        private void clearFields() {
                txtDogID.setText("");
                txtDogName.setText("");
                txtGender.setText("");
                txtBreed.setText("");
                txtAge.setText("");
                txtOwnerID.setText("");
        }


        @FXML
        void btnAppointmentOnAction(ActionEvent event) throws IOException {
                dogPane.getChildren().clear();
                dogPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/appointment-form.fxml")));

        }

        @FXML
        void btnBackOnAction(ActionEvent event) throws IOException {
                dogPane.getChildren().clear();
                dogPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/dashboard-form.fxml")));

        }

        @FXML
        void btnClearOnAction(ActionEvent event) {
            clearFields();
        }

        @FXML
        void btnCommunityOnAction(ActionEvent event) throws IOException {
                dogPane.getChildren().clear();
                dogPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/communitypro-form.fxml")));

        }

        @FXML
        void btnDashboardOnAction(ActionEvent event) throws IOException {
                dogPane.getChildren().clear();
                dogPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/dashboard-form.fxml")));

        }

        @FXML
        void btnDeleteOnAction(ActionEvent event) {
                String id = txtDogID.getText();

                var DogModel = new DogModel();
                try {
                        boolean isDeleted = DogModel.deleteDog(id);

                        if(isDeleted) {
                                loadAllDog();
//                tblCustomer.refresh();
                                new Alert(Alert.AlertType.CONFIRMATION, "Dog Deleted!").show();
                        }
                } catch (SQLException e) {
                        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
        }

        @FXML
        void btnDogOnAction(ActionEvent event) throws IOException {
                dogPane.getChildren().clear();
                dogPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/dog-form.fxml")));

        }

        @FXML
        void btnEmployeeOnAction(ActionEvent event) throws IOException {
                dogPane.getChildren().clear();
                dogPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/employee-form.fxml")));

        }

        @FXML
        void btnInventoryOnAction(ActionEvent event) throws IOException {
                dogPane.getChildren().clear();
                dogPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/inventory-form.fxml")));

        }

        @FXML
        void btnOwnerOnAction(ActionEvent event) throws IOException {
                dogPane.getChildren().clear();
                dogPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/owner-form.fxml")));

        }

        @FXML
        void btnPaymentOnAction(ActionEvent event) throws IOException {
                dogPane.getChildren().clear();
                dogPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/payment-form.fxml")));

        }

        @FXML
        void btnReportOnAction(ActionEvent event) throws IOException {
                dogPane.getChildren().clear();
                dogPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/reportForm.fxml")));
        }

         @FXML
        void btnRecordOnAction(ActionEvent event) throws IOException {
                dogPane.getChildren().clear();
                dogPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/record-form.fxml")));

        }

        @FXML
        void btnSearchOnAction(ActionEvent event) throws SQLException {

                String id = txtDogID.getText();

                var model = new DogModel();
                DogDto dto = model.searchDog(id);

                if(dto != null) {
                    fillFields(dto);
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "Dog not found!").show();
                }
            }

            private void fillFields(DogDto dto) {
                txtDogID.setText(dto.getD_ID());
                txtDogName.setText(dto.getD_Name());
                txtGender.setText(dto.getGender());
                txtBreed.setText(dto.getBreed());
                txtAge.setText(dto.getAge());
                txtOwnerID.setText(dto.getO_ID());
            }



        @FXML
        void btnTreatmentOnAction(ActionEvent event) throws IOException {
            Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/treatment-form.fxml"))));
            Stage stage1 = new Stage();
            stage1.setScene(scene);
            stage1.show();
        }
        @FXML
       void btnUpdateOnAction(ActionEvent event) {
            boolean isDogValid = validateDog();
            if (isDogValid) {
                String D_ID = txtDogID.getText();
                String D_Name = txtDogName.getText();
                String Gender = txtGender.getText();
                String Breed = txtBreed.getText();
                String Age = txtAge.getText();
                String O_ID = txtOwnerID.getText();


                var dto = new DogDto(D_ID, D_Name, Gender, Breed, Age, O_ID);

                var model = new DogModel();
                try {
                    boolean isUpdated = model.updateDog(dto);
                    if (isUpdated) {
                        loadAllDog();
                        new Alert(Alert.AlertType.CONFIRMATION, "Record updated!").show();
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

        public TableColumn<?, ?> getColAge() { return colAge; }

        public void setColAge(TableColumn<?, ?> colAge) { this.colAge = colAge; }

        public TableColumn<?, ?> getColBreed() { return colBreed; }

        public void setColBreed(TableColumn<?, ?> colBreed) { this.colBreed = colBreed; }

        public TableColumn<?, ?> getColDogID() { return colDogID; }

        public void setColDogID(TableColumn<?, ?> colDogID) { this.colDogID = colDogID; }

        public TableColumn<?, ?> getColDogName() { return colDogName; }

        public void setColDogName(TableColumn<?, ?> colDogName) { this.colDogName = colDogName; }

        public TableColumn<?, ?> getColGender() { return colGender; }

        public void setColGender(TableColumn<?, ?> colGender) { this.colGender = colGender; }

        public TableColumn<?, ?> getColOwnerID() { return colOwnerID; }

        public void setColOwnerID(TableColumn<?, ?> colOwnerID) { this.colOwnerID = colOwnerID; }

        public TableView<DogTm> getTblDog() { return tblDog; }

        public void setTblDog(TableView<DogTm> tblDog) { this.tblDog = tblDog; }

        public TextField getTxtAge() { return txtAge; }

        public void setTxtAge(TextField txtAge) { this.txtAge = txtAge; }

        public TextField getTxtBreed() { return txtBreed; }

        public void setTxtBreed(TextField txtBreed) { this.txtBreed = txtBreed; }

        public TextField getTxtDogID() { return txtDogID; }

        public void setTxtDogID(TextField txtDogID) { this.txtDogID = txtDogID; }

        public TextField getTxtDogName() { return txtDogName; }

        public void setTxtDogName(TextField txtDogName) { this.txtDogName = txtDogName; }

        public TextField getTxtGender() { return txtGender; }

        public void setTxtGender(TextField txtGender) { this.txtGender = txtGender; }

        public TextField getTxtOwnerID() { return txtOwnerID; }

        public void setTxtOwnerID(TextField txtOwnerID) { this.txtOwnerID = txtOwnerID; }

        public DatePicker getTxtDateView(){ return dateView;}
        public void setDateView(DatePicker dateView) { this.dateView = dateView; }

        public AnchorPane getDogPane() { return dogPane; }

        public void setDogPane(AnchorPane dogPane) { this.dogPane = dogPane; }
}
