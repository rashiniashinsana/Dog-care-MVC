package lk.ijse.dogCareClinic.controller;
import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.dogCareClinic.dto.DogDto;
import lk.ijse.dogCareClinic.dto.tm.DogTm;
import lk.ijse.dogCareClinic.model.DogModel;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;
public class NewDashboardFormController {

    public Label lblDate;
    public Label lblTime;
    @FXML
    private TableColumn<?, ?> colAge;

    @FXML
    private TableColumn<?, ?> colBreed;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private AnchorPane dashboard;



    @FXML
    private TableView<DogTm> tblDogDetails;


    public void initialize(){
        setCellValueFactory();
        loadAllDogs();
        time();

    }
    /*-----DATE AND TIME GENERATE------*/
    public  String dateNow() {
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
        return format.format(new java.util.Date());
    }
    public  String timeNow() {
        SimpleDateFormat dateFormat=new SimpleDateFormat("hh:mm aa");
        return dateFormat.format(new java.util.Date()) ;
    }
    private void time() {
        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.ZERO, e -> {
            System.out.println(timeNow());
            lblTime.setText(timeNow());
            lblDate.setText(dateNow());
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);

        timeline.play();
    }


    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("D_ID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("D_Name"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        colBreed.setCellValueFactory(new PropertyValueFactory<>("Breed"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("Age"));
    }

    private void loadAllDogs() {
        try {
            List<DogDto> allDog = new DogModel().getAllDog();
            ObservableList<DogTm> list = FXCollections.observableArrayList();
            for (DogDto dto : allDog){
                list.add(new DogTm(dto.getD_ID(),dto.getD_Name(),dto.getGender(),dto.getBreed(), dto.getAge(), dto.getO_ID()));
            }
            tblDogDetails.setItems(list);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @FXML
    void btnReportOnAction(ActionEvent event) throws IOException {
        dashboard.getChildren().clear();
        dashboard.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/reportForm.fxml")));
    }

    @FXML
    void btnAppointmentOnAction(ActionEvent event) throws IOException {
        dashboard.getChildren().clear();
        dashboard.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/appointment-form.fxml")));
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        dashboard.getChildren().clear();
        dashboard.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/login-form.fxml")));

    }


    @FXML
    void btnCommunityOnAction(ActionEvent event) throws IOException {
        dashboard.getChildren().clear();
        dashboard.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/communitypro-form.fxml"))));

    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        dashboard.getChildren().clear();
        dashboard.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/dashboard-form.fxml"))));

    }

    @FXML
    void btnDogOnAction(ActionEvent event) throws IOException {
        dashboard.getChildren().clear();
        dashboard.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/dog-form.fxml"))));
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException {
       /* Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/employee-form.fxml"))));
        Stage stage1 = new Stage();
        stage1.setScene(scene);
        stage1.show();*/
        dashboard.getChildren().clear();
        dashboard.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/employee-form.fxml"))));
    }

    @FXML
    void btnInventoryOnAction(ActionEvent event) throws IOException {
        dashboard.getChildren().clear();
        dashboard.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/inventory-form.fxml"))));

    }

    @FXML
    void btnOwnerOnAction(ActionEvent event) throws IOException {
        dashboard.getChildren().clear();
        dashboard.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/owner-form.fxml"))));
    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) throws IOException {

        dashboard.getChildren().clear();
        dashboard.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/payment-form.fxml"))));
    }

    @FXML
    void btnRecordOnAction(ActionEvent event) throws IOException {
        dashboard.getChildren().clear();
        dashboard.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/record-form.fxml"))));
       /* Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/record-form.fxml"))));
        Stage stage1 = new Stage();
        stage1.setScene(scene);
        stage1.show();*/
    }



}
