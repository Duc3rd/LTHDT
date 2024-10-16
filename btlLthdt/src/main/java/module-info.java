module com.example.btllthdt {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.btllthdt to javafx.fxml;
    exports com.example.btllthdt;
}