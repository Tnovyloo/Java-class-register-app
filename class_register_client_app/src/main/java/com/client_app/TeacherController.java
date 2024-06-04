package com.client_app;

import java.io.IOException;

import com.client_app.component.Client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import java.lang.Thread;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;


import com.client_app.TeacherPanelController;

public class TeacherController {

    private Client client;

    private Stage stage;

    public TeacherController() {
        this.client = new Client();
    }

    @FXML
    private Label loginStatusLabel;

    @FXML
    private TextField email;
    
    @FXML
    private PasswordField password;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void loginAsTeacher(ActionEvent event) throws IOException, InterruptedException {        
        String emailValue = this.email.getText();       
        String passwordValue = this.password.getText();

        boolean isLogged = this.client.authorizeClient(emailValue, passwordValue);
        if (isLogged) {
            // Send info to user and change FXML.
            this.loginStatusLabel.setText("Logowanie powiodło się");
            showAlert(AlertType.CONFIRMATION ,"Logowanie powiodło się", "Logowanie powiodło się, zostałeś zalogowany jako: " + emailValue);
            
            // All this magic below is only for fetching grades from API before scene builds. We are manually assign Controller to this FXML (It is not connected in this teacherPanel.fxml file)
            // We have to load teacherPanel.fxml and pass the client to the Controller constructor
            FXMLLoader loader = new FXMLLoader(getClass().getResource("teacherPanel.fxml"));
            // Pass the client to the controller's constructor
            TeacherPanelController teacherPanelController = new TeacherPanelController(this.client);
            // Setting up the controller to the loader
            loader.setController(teacherPanelController); 
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } else {
            // Send info to user
            showAlert(AlertType.ERROR, "Logowanie nie powiodło się", "Sprawdź email i hasło");
            this.password.setText("");
        }

    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
