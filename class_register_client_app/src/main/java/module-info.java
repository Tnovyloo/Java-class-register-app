module com.client_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;

    opens com.client_app to javafx.fxml;
    exports com.client_app;
}
