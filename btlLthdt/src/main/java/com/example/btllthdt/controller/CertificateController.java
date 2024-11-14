package com.example.btllthdt.controller;

import com.example.btllthdt.model.Certificate;
import com.example.btllthdt.service.CertificateService;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class CertificateController {

    @FXML
    private TextField searchField, studentIdField, classIdField, statusField;

    @FXML
    private TableView<Certificate> certificateTable;

    @FXML
    private TableColumn<Certificate, Integer> colId, colStudentId, colClassId;

    @FXML
    private TableColumn<Certificate, String> colStatus;

    @FXML
    private ComboBox<String> statusComboBox;


    private CertificateService certificateService;
    private ObservableList<Certificate> certificateList;

    public CertificateController() {
        certificateService = new CertificateService();
    }

    @FXML
    public void initialize() {
        // Set up columns for table
        colId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        colStudentId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStudentId()).asObject());
        colClassId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getClassId()).asObject());
        colStatus.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));

        // Load all certificates
        loadCertificates();
    }

    private void loadCertificates() {
        List<Certificate> certificates = certificateService.getAllCertificates();
        certificateList = FXCollections.observableArrayList(certificates);
        certificateTable.setItems(certificateList);
    }


    @FXML
    private void onIssueCertificate() {
        try {
            int studentId = Integer.parseInt(studentIdField.getText());
            int classId = Integer.parseInt(classIdField.getText());
            String status = statusComboBox.getSelectionModel().getSelectedItem();
            Certificate certificate = new Certificate(0, studentId, classId, status);
            certificateService.issueCertificate(certificate);
            loadCertificates();


            clearForm();
            showAlert("Thành công", "Thêm chứng chỉ thành công");

        } catch (NumberFormatException e) {
            showAlert("Lỗi", "Vui lòng nhập đúng định dạng số cho Mã Sinh Viên và Mã Lớp");
        }
    }

    // Method to clear the input form
    private void clearForm() {
        studentIdField.clear();
        classIdField.clear();

    }


    @FXML
    private void onSearchCertificate() {
        String searchText = searchField.getText().toLowerCase();
        ObservableList<Certificate> filteredList = FXCollections.observableArrayList();

        for (Certificate certificate : certificateList) {
            if (certificate.getStatus().toLowerCase().contains(searchText) ||
                    String.valueOf(certificate.getStudentId()).contains(searchText) ||
                    String.valueOf(certificate.getClassId()).contains(searchText)) {
                filteredList.add(certificate);
            }
        }

        certificateTable.setItems(filteredList);
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void onClearForm() {
        clearForm();
        showAlert("OK", "Đã làm mới form");
    }


    @FXML
    private void onHome() {
        showAlert("Home", "Navigating to Home");
    }

    @FXML
    private void onManageStudents() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/btllthdt/hello-view.fxml"));

            Parent root = loader.load();


            Stage stage = (Stage) certificateTable.getScene().getWindow();


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

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/btllthdt/course_management.fxml"));
            Parent root = loader.load();


            Stage stage = (Stage) certificateTable.getScene().getWindow();


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
            Stage stage = (Stage) certificateTable.getScene().getWindow();

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
            Stage stage = (Stage) certificateTable.getScene().getWindow();

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
            Stage stage = (Stage) certificateTable.getScene().getWindow();

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
