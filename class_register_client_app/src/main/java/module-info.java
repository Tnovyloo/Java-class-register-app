module com.client_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;

    opens com.client_app to javafx.fxml;
    exports com.client_app;
    exports com.client_app.fetchedData;
}
