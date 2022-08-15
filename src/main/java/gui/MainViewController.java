package gui;

import app.AppView;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;

import java.io.IOException;

public class MainViewController {


    // Menu Items:

        @FXML
        private MenuItem menuItemSeller;

        @FXML
        private MenuItem menuItemDepartment;

        @FXML
        private MenuItem menuItemAbout;


    // Event Handling methods:

        @FXML
        protected void onMenuSellerAction() {
            System.out.println("onMenuSellerAction");
        }

        @FXML
        protected void onMenuDepartmentAction() {
            loadView2("departmentList.fxml");
        }

        @FXML
        protected void onMenuAboutAction() {
            loadView("aboutView.fxml");
        }

    // Aux Functions

        private synchronized void loadView(String resource) {

                try {

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resource));

                        VBox vBox = fxmlLoader.load();

                        Scene scene = AppView.getScene();

                        VBox mainVBox = (VBox) ((ScrollPane) scene.getRoot()).getContent();

                        Node mainMenu = mainVBox.getChildren().get(0);

                        mainVBox.getChildren().clear();
                        mainVBox.getChildren().add(mainMenu);
                        mainVBox.getChildren().addAll(vBox);


                } catch (IOException e) {

                        Alerts.showAlerts("IOException","Error loading view: " + resource, e.getMessage(), AlertType.ERROR);

                }


        }

        private synchronized void loadView2(String resource) {

                try {

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resource));

                        VBox vBox = fxmlLoader.load();

                        Scene scene = AppView.getScene();

                        VBox mainVBox = (VBox) ((ScrollPane) scene.getRoot()).getContent();

                        Node mainMenu = mainVBox.getChildren().get(0);

                        mainVBox.getChildren().clear();
                        mainVBox.getChildren().add(mainMenu);
                        mainVBox.getChildren().addAll(vBox);

                        DepartmentListController controller = fxmlLoader.getController();
                        controller.setDepartmentService(new DepartmentService());
                        controller.updateTableView();


                } catch (IOException e) {

                        Alerts.showAlerts("IOException","Error loading view: " + resource, e.getMessage(), AlertType.ERROR);

                }


        }

}