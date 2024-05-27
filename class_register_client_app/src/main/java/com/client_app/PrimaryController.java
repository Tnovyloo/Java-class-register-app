package com.client_app;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToStudent() throws IOException {
        App.setRoot("student");
    }

    @FXML
    private void switchToTeacher() throws IOException {
        App.setRoot("teacher");
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}
