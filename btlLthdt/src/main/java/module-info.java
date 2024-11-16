module com.example.btllthdt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.btllthdt to javafx.fxml;
    exports com.example.btllthdt;
    exports com.example.btllthdt.controller;
    opens com.example.btllthdt.controller to javafx.fxml;
    opens com.example.btllthdt.model to javafx.graphics, javafx.fxml, javafx.base;
}