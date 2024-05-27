module com.client_app {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.client_app to javafx.fxml;
    exports com.client_app;
}
