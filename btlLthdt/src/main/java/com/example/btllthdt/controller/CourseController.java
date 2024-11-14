package com.example.btllthdt.controller;

import com.example.btllthdt.model.Class;
import com.example.btllthdt.service.ClassService;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

public class CourseController {

    @FXML
    private TableView<Class> classTable;
    @FXML
    private TableColumn<Class, String> colClassName;
    @FXML
    private TableColumn<Class, Integer> colClassId;
    @FXML
    private TableColumn<Class, String> colStartDate;
    @FXML
    private TableColumn<Class, String> colEndDate;
    @FXML
    private TextField searchField;

    private ClassService classService;
    private ObservableList<Class> classList;

    public CourseController() {
        classService = new ClassService();
    }

    @FXML
    public void initialize() {

        colClassName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        colClassId.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        colStartDate.setCellValueFactory(data -> new SimpleStringProperty(dateFormat.format(data.getValue().getStartDate())));
        colEndDate.setCellValueFactory(data -> new SimpleStringProperty(dateFormat.format(data.getValue().getEndDate())));


        loadClassData();
    }

    private void loadClassData() {
        List<Class> classes = classService.getAllClasses();
        classList = FXCollections.observableArrayList(classes);
        classTable.setItems(classList);
    }

    @FXML
    public void onSearchCouser() {
        String searchText = searchField.getText().toLowerCase();
        List<Class> filteredClasses = classList.stream()
                .filter(c -> c.getName().toLowerCase().contains(searchText))
                .collect(Collectors.toList());
        classTable.setItems(FXCollections.observableArrayList(filteredClasses));
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void onHome() {
        showAlert("Home", "Navigating to Home");
    }

    @FXML
    private void onManageStudents() {
        try {
            // Load the new scene (course-management.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/btllthdt/hello-view.fxml"));

            Parent root = loader.load();

            // Get the current stage (window)
            Stage stage = (Stage) classTable.getScene().getWindow();

            // Set the new scene
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the Course Management page.");
        }
    }

    @FXML
    private void onManageCourses() {
        try {
            // Load the new scene (course-management.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/btllthdt/course_management.fxml"));
            Parent root = loader.load();

            // Get the current stage (window)
            Stage stage = (Stage) classTable.getScene().getWindow();

            // Set the new scene
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the Course Management page.");
        }
    }
    @FXML
    private void onCertifiManagent() {
        try {
            // Load the new scene (course-management.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/btllthdt/Certificate.fxml"));
            Parent root = loader.load();

            // Get the current stage (window)
            Stage stage = (Stage) classTable.getScene().getWindow();

            // Set the new scene
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the Course Management page.");
        }
    }

    @FXML
    private void onProvinceManagent() {
        try {
            // Load the new scene (course-management.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/btllthdt/Province.fxml"));
            Parent root = loader.load();

            // Get the current stage (window)
            Stage stage = (Stage) classTable.getScene().getWindow();

            // Set the new scene
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the Course Management page.");
        }
    }

    @FXML
    private void onStudentClassManagent() {
        try {
            // Load the new scene (course-management.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/btllthdt/StudentClass.fxml"));
            Parent root = loader.load();

            // Get the current stage (window)
            Stage stage = (Stage) classTable.getScene().getWindow();

            // Set the new scene
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the Course Management page.");
        }
    }
}
