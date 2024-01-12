package lk.ijse.dogCareClinic;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/create-account-form.fxml")))));
        stage.setTitle("Dog Care Clinic");
        stage.centerOnScreen();

        stage.setResizable(false);
        stage.show();
    }
}
