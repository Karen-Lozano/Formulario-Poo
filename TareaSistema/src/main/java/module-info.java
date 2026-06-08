module com.example.tareasistema {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tareasistema to javafx.fxml;
    exports com.example.tareasistema;
}