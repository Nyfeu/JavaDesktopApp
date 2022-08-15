package gui;

import app.AppView;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;

public class DepartmentListController implements Initializable {

        private DepartmentService service;

        public void setDepartmentService(DepartmentService service) {
                this.service = service;
        }

        private ObservableList<Department> observableList;

        public void updateTableView() {
                if(service == null) {
                        throw new IllegalStateException("Service was null!");
                }
                List<Department> list = service.findAll();
                observableList = FXCollections.observableArrayList(list);
                tableViewDepartment.setItems(observableList);

        }

        private void createDialogForm(String resource,Stage parentStage) {

                try {

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resource));
                        Pane pane = fxmlLoader.load();

                        Stage dialogStage = new Stage();
                        dialogStage.setTitle("Enter Department data");
                        dialogStage.setScene(new Scene(pane));
                        dialogStage.setResizable(false);
                        dialogStage.initOwner(parentStage);
                        dialogStage.initModality(Modality.WINDOW_MODAL);
                        dialogStage.showAndWait();

                } catch (IOException e) {
                        Alerts.showAlerts("IOException", "Error loading view", e.getMessage(), AlertType.ERROR);
                }

        }

    // Buttons:

        @FXML
        private Button newBtn;

    // Table:

        @FXML
        private TableView<Department> tableViewDepartment;

        @FXML
        private TableColumn<Department,Integer> tableColumnId;

        @FXML
        private TableColumn<Department,String> tableColumnName;

    // Event Handling methods:

        @FXML
        protected void onNewButtonClick(ActionEvent event) {
            Stage parentStage = Utils.currentStage(event);
            createDialogForm("departmentForm.fxml", parentStage);
        }

    // Initializable:

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                initializeNodes();
        }

        private void initializeNodes() {

                tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
                tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));

                Stage stage = (Stage) AppView.getScene().getWindow();
                tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());

        }
}
