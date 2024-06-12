package com.client_app;

import java.io.IOException;

import com.client_app.component.AlertPopUp;
import com.client_app.component.Client;
import com.client_app.component.EmailValidator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StudentController {

    private Client client;
    

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

    public StudentController() {
        this.client = new Client();
    }

    @FXML
    private void loginAsStudent(ActionEvent event) throws IOException, InterruptedException {
        AlertPopUp alert = new AlertPopUp();
        boolean isLogged = false;

        // Input values.
        String emailValue = this.email.getText();       
        String indexValue = this.index.getText();
        String passwordValue = this.password.getText();

        System.out.println(emailValue + " " + indexValue + " " + passwordValue);

        // Check if email is good for student login
        EmailValidator emailValidator = new EmailValidator(emailValue);

        if (emailValidator.checkEmailDomain("stud.urz.pl")) {
            System.out.println(client);
            isLogged = this.client.authorizeClient(emailValue, passwordValue, indexValue);
        } else {
            isLogged = false;
        }

        if (isLogged) {
            alert.showAlert(AlertType.CONFIRMATION, "Logowanie powiodło się", "Logowanie powiodło się, kliknij OK aby kontynuować");
            // All this magic below is only for fetching grades from API before scene builds. We are manually assign Controller to this FXML (It is not connected in this teacherPanel.fxml file)
            // We have to load teacherPanel.fxml and pass the client to the Controller constructor
            FXMLLoader loader = new FXMLLoader(getClass().getResource("studentPanel.fxml"));
            // Pass the client to the controller's constructor
            StudentPanelController studentPanelController = new StudentPanelController(this.client);
            // Setting up the controller to the loader
            loader.setController(studentPanelController); 
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } else {
            alert.showAlert(AlertType.ERROR, "Logowanie nie powiodło się", "Logowanie nie powiodło się, sprawdź email, hasło, indeks");
            this.password.setText("");
        }


    }


}