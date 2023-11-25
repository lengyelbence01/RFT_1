module com.example.autokereskedes {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.autokereskedes to javafx.fxml;
    exports com.example.autokereskedes;
}