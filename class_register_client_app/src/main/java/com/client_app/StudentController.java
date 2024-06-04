package com.client_app;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class StudentController {

    @FXML
    private TextField email;
    
    @FXML
    private TextField index;

    @FXML
    private PasswordField password;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void loginAsStudent() throws IOException {
        String emailValue = this.email.getText();       
        String indexValue = this.index.getText();
        String passwordValue = this.password.getText();

        System.out.println(emailValue + " " + indexValue + " " + passwordValue);
    }
}