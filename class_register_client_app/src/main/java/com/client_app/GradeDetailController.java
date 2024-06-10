package com.client_app;

import java.util.HashMap;

import com.client_app.component.AlertPopUp;
import com.client_app.component.Client;
import com.client_app.fetchedData.Grade;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GradeDetailController {
    
    @FXML
    private Label gradeLabel;
    
    @FXML
    private Label teacherLabel;
    
    @FXML
    private Label idLabel;

    private Grade selectedGrade;

        // Post Grade text inputs
    @FXML
    private TextField fullNameInput;
    @FXML
    private TextField indexInput;
    @FXML
    private TextField subjectInput;
    @FXML
    private TextField gradeInput;
    @FXML
    private TextField descriptionInput;

    private Client client;


    public void initialize() {
        // Initialization if needed
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setGrade(Grade grade) {
        this.selectedGrade = grade;
        this.fullNameInput.setText(grade.getStudentName());
        this.indexInput.setText(grade.getStudentIndex());
        this.descriptionInput.setText(grade.getDescription());
        this.gradeInput.setText(grade.getGrade());
        this.subjectInput.setText(grade.getSubject());

        System.out.println(grade.getId());
    }

    @FXML
    private void handleUpdate() {
        // Handle the update logic here
        HashMap<String, Object> requestHashMap = new HashMap<String, Object>();

        AlertPopUp alert = new AlertPopUp();

        requestHashMap.put("studentName", fullNameInput.getText().toString());
        requestHashMap.put("subject", subjectInput.getText().toString());
        requestHashMap.put("grade", gradeInput.getText().toString());
        requestHashMap.put("studentIndex", indexInput.getText().toString());
        requestHashMap.put("description", descriptionInput.getText().toString());
        requestHashMap.put("id", selectedGrade.getId());

        System.out.println(requestHashMap.toString());

        boolean isSuccess = client.putRequest("api/grades/" + selectedGrade.getId(), requestHashMap);
        if (isSuccess) {
            alert.showAlert(AlertType.CONFIRMATION, "Zaaktualizowano", "Zaaktualizowano ocene");
        } else {
            alert.showAlert(AlertType.ERROR, "Błąd przy aktualizacji", "Sprawdz literówki, bądź zaloguj się ponownie, być może zmieniasz ocenę innego nauczyciela.");
        }
        
    }

    @FXML
    private void handleDelete() {
        AlertPopUp alert = new AlertPopUp();

        System.out.println("Deleting Grade.");

        boolean isSuccess = client.deleteRequest("api/grades/" + selectedGrade.getId());
        if (isSuccess) {
            alert.showAlert(AlertType.CONFIRMATION, "Usunięto", "Poprawnie usunięto ocenę");
        } else {
            alert.showAlert(AlertType.ERROR, "Błąd przy usuwaniu", "Nie jesteś nauczycielem wystawiającym tą ocenę .");
        }
         
    }
}

