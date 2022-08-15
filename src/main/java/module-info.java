module app.javadesktopapp {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens app.javadesktopapp to javafx.fxml;
    exports app.javadesktopapp;
}