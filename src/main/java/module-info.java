module classes {
    requires javafx.controls;
    requires javafx.fxml;

    opens classes to javafx.fxml;
    exports classes;
    exports classes.controllers;
    opens classes.controllers to javafx.fxml;
}