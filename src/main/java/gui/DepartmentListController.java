package gui;

import app.AppView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

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
        protected void onNewButtonClick() {
            System.out.println("onNewButtonClick()");
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
