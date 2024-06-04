package com.client_app;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import com.client_app.component.Client;
import com.client_app.fetchedData.Grade;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;



public class TeacherPanelController implements Initializable {
    private Client client;

    @FXML
    private Label title;

    @FXML
    private TableView<Grade> gradesTable;
    @FXML
    private TableColumn<Grade, String> studentIndexColumn;
    @FXML
    private TableColumn<Grade, String> studentNameColumn;
    @FXML
    private TableColumn<Grade, String> subjectColumn;
    @FXML
    private TableColumn<Grade, String> gradeColumn;
    
    public TeacherPanelController() {
        this.client = new Client();
    }

    public TeacherPanelController(Client client) {
        this.client = client;
    }
    
    // public void setClient(Client client) {
    //     this.client = client;
    // }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Call fetchGrades method when the scene is initialized
        studentIndexColumn.setCellValueFactory(new PropertyValueFactory<>("studentIndex"));
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));
        
        fetchGrades();
    }

    public void fetchGrades() {

        ArrayList<Object> gradesList = this.client.getListRequest("api/grades");
        System.out.println(gradesList.toString());

        // Creating ObservableList<Grade> data to add this variable later to our gradesTable (gradesTable.setItems() - requires ObservableArrayList)
        ObservableList<Grade> data = FXCollections.observableArrayList();

        for (Object obj : gradesList) {
            if (obj instanceof HashMap) {
                HashMap<String, Object> gradeMap = (HashMap<String, Object>) obj;
                Grade grade = new Grade(
                    (String) gradeMap.get("studentIndex"),
                    (String) gradeMap.get("studentName"),
                    (String) gradeMap.get("subject"),
                    (String) gradeMap.get("grade")
                );
                data.add(grade);
                System.out.println(grade.toString());
            }
        }

        System.out.println(data);
        gradesTable.setItems(data);
    }
}
