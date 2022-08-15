package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AppView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppView.class.getResource("mainView.fxml"));

        // Setting ScrollPane:

            ScrollPane scrollPane = fxmlLoader.load();
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);

        Scene scene = new Scene(scrollPane);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}