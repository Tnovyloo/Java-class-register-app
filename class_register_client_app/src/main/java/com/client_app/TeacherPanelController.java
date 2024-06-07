package com.client_app;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.client_app.component.AlertPopUp;
import com.client_app.component.Client;
import com.client_app.fetchedData.Grade;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;



public class TeacherPanelController implements Initializable {
    private Client client;

    @FXML
    private Label title;

    // TableView and Columns in this table
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
    @FXML
    private TableColumn<Grade, String> descriptionColumn;

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
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Add mouse Event on TableView (Method Reference)
        gradesTable.setOnMouseClicked(this::handleRowClick);

        // Same thing but diffrent
        // gradesTable.setOnMouseClicked(event -> handleRowClick(event));

        // Fetch grades from Spring backend
        fetchGrades();        
    }

    private String getStringValue(Object obj) {
        return obj != null ? obj.toString() : "";
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
                // gradesData.add(new Grade(gradeMap.get("id").toString(), gradeMap.get("studentIndex").toString(), gradeMap.get("studentName").toString(), gradeMap.get("subject").toString(), gradeMap.get("grade").toString(), gradeMap.get("description").toString()));
                gradesData.add(new Grade(
                getStringValue(gradeMap.get("id")),
                getStringValue(gradeMap.get("studentIndex")),
                getStringValue(gradeMap.get("studentName")),
                getStringValue(gradeMap.get("subject")),
                getStringValue(gradeMap.get("grade")),
                getStringValue(gradeMap.get("description"))
            ));

            }
        }

        gradesTable.setItems(gradesData);
    }

    public void postGrade() {
        HashMap<String, Object> postData = new HashMap<String, Object>();
        AlertPopUp alert = new AlertPopUp();

        postData.put("studentName", fullNameInput.getText());
        postData.put("subject", subjectInput.getText());
        postData.put("grade", gradeInput.getText());
        postData.put("studentIndex", indexInput.getText());
        postData.put("description", descriptionInput.getText());

        boolean isSended = client.postRequest("api/grades", postData);
        if (isSended) {
            alert.showAlert(AlertType.CONFIRMATION, "Wysłano ocene!", "Udało się dać ocene studentowi o indeksie " + postData.get("studentIndex"));            
            fullNameInput.setText(null);
            indexInput.setText(null);
            gradeInput.setText(null);
            subjectInput.setText(null);
            fetchGrades();
        } else {
            alert.showAlert(AlertType.ERROR, "Nie udało się wysłać oceny", "Błąd przy wysyłaniu oceny na serwer. Przejrzyj czy uzupełniłeś wszystkie pola tekstowe.");
        }
        
    }



    // handleRowClick shows the GradeDetail panel, where we could change data of specific Grade.
    private void handleRowClick(MouseEvent event) {
        if (event.getClickCount() == 1) {
            Grade selectedGrade = gradesTable.getSelectionModel().getSelectedItem();
            if (selectedGrade != null) {
                System.out.println("Selected grade: " + selectedGrade.getGrade() + ", " + selectedGrade.getAssessingTeacher() + ", " + selectedGrade.getId());
                // Show this Grade into detail View where user could delete it or Update it.

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("GradeDetailView.fxml"));
                    Parent root = loader.load();
                    
                    // Get the controller and pass the selected grade
                    GradeDetailController controller = loader.getController();
                    controller.setClient(this.client);
                    controller.setGrade(selectedGrade);
                    
                    // Show the new stage
                    Stage stage = new Stage();
                    stage.setTitle("Szczegóły oceny");
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    
}
