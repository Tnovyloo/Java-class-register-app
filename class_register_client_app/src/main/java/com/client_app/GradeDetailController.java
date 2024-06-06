package com.client_app;

import com.client_app.component.Client;
import com.client_app.fetchedData.Grade;

import javafx.fxml.FXML;
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

    }

    @FXML
    private void handleUpdate() {
        // Handle the update logic here
        System.out.println("PUT REQUEST HERE");
    }

    @FXML
    private void handleDelete() {
        // Handle the delete logic here
        System.out.println("DELETE REQUEST HERE");
    }
}

