module com.test {
    requires javafx.controls;
    requires javafx.fxml;



    exports com.test.view;
    opens com.test.view to javafx.fxml;
    exports com.test.controller;
    opens com.test.controller to javafx.fxml;
    exports com.test.model;
    opens com.test.model to javafx.fxml;
    exports com.test;
    opens com.test to javafx.fxml;
}