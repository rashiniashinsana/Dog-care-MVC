package lk.ijse.dogCareClinic.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Navigation {

    public static void Navigation (AnchorPane Pane, String path) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("/view/"+path));
        AnchorPane newScene = fxmlLoader.load();
        Pane.getChildren().clear();
        Pane.getChildren().setAll(newScene);
    }
    public static void changePane(Pane pane, String path) throws IOException {
        Parent parent = FXMLLoader.load(Navigation.class.getResource("/view/"+path));
        pane.getChildren().clear();
        pane.getChildren().add(parent);

    }
}
