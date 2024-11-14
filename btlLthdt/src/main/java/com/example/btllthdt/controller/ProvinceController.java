package com.example.btllthdt.controller;

import com.example.btllthdt.model.Province;
import com.example.btllthdt.service.ProvinceService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ProvinceController {

    @FXML
    private TableView<Province> provinceTable;

    @FXML
    private TableColumn<Province, Integer> colProvinceId;

    @FXML
    private TableColumn<Province, String> colProvinceName;


    @FXML
    private TextField searchField;

    private ProvinceService provinceService;
    private ObservableList<Province> provinceList;
    private FilteredList<Province> filteredList;

    public ProvinceController() {
        provinceService = new ProvinceService();
    }

    @FXML
    public void initialize() {

        colProvinceId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProvinceName.setCellValueFactory(new PropertyValueFactory<>("name"));


        List<Province> provinces = provinceService.getAllProvinces();
        provinceList = FXCollections.observableArrayList(provinces);


        filteredList = new FilteredList<>(provinceList, p -> true);
        provinceTable.setItems(filteredList);
    }


    @FXML
    private void onSearchProvince() {
        String searchText = searchField.getText().toLowerCase();

        filteredList.setPredicate(province -> {

            if (searchText.isEmpty()) {
                return true;
            }

            return province.getName().toLowerCase().contains(searchText);
        });
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
            Stage stage = (Stage) provinceTable.getScene().getWindow();

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
            Stage stage = (Stage) provinceTable.getScene().getWindow();

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
            Stage stage = (Stage) provinceTable.getScene().getWindow();

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
            Stage stage = (Stage) provinceTable.getScene().getWindow();

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
            Stage stage = (Stage) provinceTable.getScene().getWindow();

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
