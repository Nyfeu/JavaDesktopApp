module app.javadesktopapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens app to javafx.fxml;
    exports app;
    exports gui;
    opens gui to javafx.fxml;
    opens model.entities to javafx.graphics, javafx.fxml, javafx.base;
}