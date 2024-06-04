package com.client_app;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class TeacherController {

    @FXML
    private TextField email;
    
    @FXML
    private PasswordField password;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void loginAsTeacher() throws IOException {
        String emailValue = this.email.getText();       
        String passwordValue = this.password.getText();

        System.out.println(emailValue + " " + passwordValue);
    }
}
