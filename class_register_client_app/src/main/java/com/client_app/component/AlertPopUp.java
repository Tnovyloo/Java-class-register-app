package com.client_app.component;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertPopUp {
    
    public void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }    

}
