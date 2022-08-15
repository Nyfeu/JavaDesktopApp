package gui;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

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
            System.out.println("onMenuDepartmentAction");
        }

        @FXML
        protected void onMenuAboutAction() {
            System.out.println("onMenuAboutAction");
        }


}