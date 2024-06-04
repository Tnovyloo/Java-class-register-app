package com.client_app;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import com.client_app.component.Client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;


public class TeacherPanelController implements Initializable {
    private Client client;

    @FXML
    private Label title;
    
    public TeacherPanelController() {
        this.client = new Client();
    }

    public TeacherPanelController(Client client) {
        this.client = client;
    }
    
    public void setClient(Client client) {
        this.client = client;
    }
    
    public Client getClient() {
        return this.client;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Call fetchGrades method when the scene is initialized
        System.out.println("XXDXDXDXD");
        System.out.println(this.client);
        fetchGrades();
    }

    public void fetchGrades() {

        ArrayList<Object> gradesList = this.client.getListRequest("api/grades");
        System.out.println(gradesList.toString());

        for (Object obj : gradesList) {
            if (obj instanceof HashMap) {
                HashMap<String, Object> grade = (HashMap<String, Object>) obj;

                Object subject = grade.get("subject");
                System.out.println(grade.toString());
            }
        }
    }
}
