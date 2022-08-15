package gui;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class DepartmentFormController implements Initializable {

    // TextFields:

        @FXML
        private TextField txtId;

        @FXML
        private TextField txtName;

    // Labels:

        @FXML
        private Label labelError;

    // Buttons:

        @FXML
        private Button saveBtn;

        @FXML
        private Button cancelBtn;

    // Event Handling methods:

        public void onSaveButtonAction() {
            System.out.println("onSaveButtonAction");
        }

        public void onCancelButtonAction() {
            System.out.println("onCancelButtonAction");
        }

    // Initializable:

        @Override
        public void initialize(URL url, ResourceBundle rb) {
                initializeNode();
        }

        private void initializeNode() {
                Constraints.setTextFieldInteger(txtId);
                Constraints.setTextFieldMaxLength(txtName,30);
        }

}
