module com.example.autokolcsonzo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.autokolcsonzo to javafx.fxml;
    exports com.example.autokolcsonzo;
}