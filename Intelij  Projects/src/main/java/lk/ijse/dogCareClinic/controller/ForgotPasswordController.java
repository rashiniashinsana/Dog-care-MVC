package lk.ijse.dogCareClinic.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import lk.ijse.dogCareClinic.email.SendText;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Objects;

public class ForgotPasswordController {
    @FXML
    private Rectangle forgotpasswordPane;

    @FXML
    private AnchorPane loginPane;

    @FXML
    private Pane pane;

    @FXML
    private TextField textUserName;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private PasswordField txtEmail;

    @FXML
    private Hyperlink txtLoginPage;

    @FXML
    private PasswordField txtOtp;

    @FXML
    private PasswordField txtPassword;


    @FXML
    void btnForgotOnAction(ActionEvent event) {


    }



    @FXML
    void txtLoginPageOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login-form.fxml"));
        Parent root = loader.load();

        LoginFormController LoginFormController = loader.getController();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

        Stage currentStage = (Stage) forgotpasswordPane.getScene().getWindow();
        currentStage.close();



    }

    @FXML
    void btnSendOtpOnAction(ActionEvent event) {
        int random = (int) (Math.random() * 100000);
        try {
            sendMail("Forgot Password", "Your Otp is " + random, txtEmail.getText());
            System.out.println("OTP sent successfully.");
        } catch (Exception e) {
            System.err.println("Error sending OTP: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void sendMail(String title, String message, String gmail) throws IOException, MessagingException, GeneralSecurityException {
        new SendText().sendMail(title, message, gmail);
    }
}


