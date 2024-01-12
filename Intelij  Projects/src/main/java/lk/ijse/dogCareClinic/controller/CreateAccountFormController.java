package lk.ijse.dogCareClinic.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dogCareClinic.dto.UserDto;
import lk.ijse.dogCareClinic.model.UserModel;

import java.io.IOException;
import java.sql.SQLException;

public class CreateAccountFormController {
        @FXML
        private AnchorPane SignupPane;

        @FXML
        private PasswordField pwdPassword;

        @FXML
        private TextField textUserName;

        @FXML
        private TextField txtUsername1;

        @FXML
        private PasswordField txtUsername111;

        @FXML
        private Hyperlink txtLogin;


        @FXML
        void btnSignOnAction(ActionEvent event) throws IOException {
                String username = textUserName.getText();
                String password = pwdPassword.getText();
                String confirmPassword = txtUsername111.getText();
                String email = txtUsername1.getText();

                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() ||  email.isEmpty() ) {
                        new Alert(Alert.AlertType.ERROR, "Please fill in all fields").show();
                        return;
                }

                if (!password.equals(confirmPassword)) {
                        new Alert(Alert.AlertType.ERROR, "Passwords do not match").show();
                        return;
                }

                UserModel model = new UserModel();
                try {
                        if (model.searchUser(username) != null) {
                                new Alert(Alert.AlertType.ERROR, "Username already exists").show();
                                return;
                        }

                        UserDto newUser = new UserDto(username, password, confirmPassword,email);
                        model.saveUser(newUser);

                        new Alert(Alert.AlertType.INFORMATION, "User registered successfully").show();


                } catch (SQLException throwables) {
                        new Alert(Alert.AlertType.ERROR, throwables.getMessage()).show();
                }

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/create-account-form.fxml"));
                Parent root = loader.load();

                LoginFormController LoginFormController = loader.getController();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

                Stage currentStage = (Stage) SignupPane.getScene().getWindow();
                currentStage.close();
        }



        @FXML
        void txtLoginOnAction(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login-form.fxml"));
                Parent root = loader.load();

                LoginFormController LoginFormController = loader.getController();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

                Stage currentStage = (Stage) SignupPane.getScene().getWindow();
                currentStage.close();

        }
}


