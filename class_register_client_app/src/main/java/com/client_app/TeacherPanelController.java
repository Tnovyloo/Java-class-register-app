package com.client_app;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;



public class TeacherPanelController implements Initializable {
    private Client client;

    @FXML
    private Label title;

    @FXML
    private TableView<Grade> gradesTable;
    @FXML
    private TableColumn<Grade, String> idColumn;
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
        

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set up the cell value factories
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        studentIndexColumn.setCellValueFactory(new PropertyValueFactory<>("studentIndex"));
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));

        fetchGrades();        
    }

    public void fetchGrades() {

        ArrayList<Object> gradesList = this.client.getListRequest("api/grades");
        System.out.println(gradesList.toString());

        // Creating observableArrayList to save our results and add them to TableView.
        ObservableList<Grade> gradesData = FXCollections.observableArrayList();

        for (Object obj : gradesList) {
            if (obj instanceof HashMap) {
                // Casting obj to HashMap<String, Object>.
                HashMap<String, Object> gradeMap = (HashMap<String, Object>) obj;
                gradesData.add(new Grade(gradeMap.get("id").toString(), gradeMap.get("studentIndex").toString(), gradeMap.get("studentName").toString(), gradeMap.get("subject").toString(), gradeMap.get("grade").toString()));

            }
        }

        gradesTable.setItems(gradesData);
    }


}
