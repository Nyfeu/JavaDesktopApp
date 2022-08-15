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
import java.util.function.Consumer;

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
            loadView("departmentList.fxml", (DepartmentListController controller) -> {
                    controller.setDepartmentService(new DepartmentService());
                    controller.updateTableView();
            });
        }

        @FXML
        protected void onMenuAboutAction() {
            loadView("aboutView.fxml", x -> {});
        }

    // Aux Functions

        private synchronized <T> void loadView(String resource, Consumer<T> initializingAction) {

                try {

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resource));

                        VBox vBox = fxmlLoader.load();

                        Scene scene = AppView.getScene();

                        VBox mainVBox = (VBox) ((ScrollPane) scene.getRoot()).getContent();

                        Node mainMenu = mainVBox.getChildren().get(0);

                        mainVBox.getChildren().clear();
                        mainVBox.getChildren().add(mainMenu);
                        mainVBox.getChildren().addAll(vBox);

                        T controller = fxmlLoader.getController();
                        initializingAction.accept(controller);

                } catch (IOException e) {

                        Alerts.showAlerts("IOException","Error loading view: " + resource, e.getMessage(), AlertType.ERROR);

                }


        }

}