package app;

import gui.util.Alerts;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AppView extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(AppView.class.getResource("mainView.fxml"));

            // Setting ScrollPane:

            ScrollPane scrollPane = fxmlLoader.load();
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);

            scene = new Scene(scrollPane);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {

            Alerts.showAlerts("IOException","Error loading: MainView!", e.getMessage(), Alert.AlertType.ERROR);

        }

    }

    public static Scene getScene() {
        return scene;
    }

    public static void main(String[] args) {
        launch();
    }
}