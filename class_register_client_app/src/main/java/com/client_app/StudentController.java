package com.client_app;

import java.io.IOException;
import javafx.fxml.FXML;

public class StudentController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}