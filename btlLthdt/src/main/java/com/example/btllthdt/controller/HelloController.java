package com.example.btllthdt.controller;



import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import com.example.btllthdt.model.student;

import java.util.List;

public class HelloController {
    @FXML
    private TextField studentName;
    @FXML
    private TextField studentID;
    @FXML
    private TextField studentClass;
    @FXML
    private DatePicker studentDob;
    @FXML
    private TextField studentHometown;
    @FXML
    private TextField searchField;
    @FXML
    private TableView<student> studentTable;
    @FXML
    private TableColumn<student, String> colStudentName;
    @FXML
    private TableColumn<student, String> colStudentID;
    @FXML
    private TableColumn<student, String> colStudentClass;
    @FXML
    private TableColumn<student, String> colStudentDob;
    @FXML
    private TableColumn<student, String> colStudentHometown;

    // Phương thức điều khiển nút "Trang chủ"
    @FXML
    private void onHome() {
        // Logic cho nút "Trang chủ"
        showAlert("Home", "Navigating to Home");
    }

    // Phương thức điều khiển nút "Quản lý học viên"
    @FXML
    private void onManageStudents() {
        // Logic cho nút "Quản lý học viên"
        showAlert("Manage Students", "Navigating to Manage Students");
    }

    // Phương thức điều khiển nút "Quản lý khóa học"
    @FXML
    private void onManageCourses() {
        // Logic cho nút "Quản lý khóa học"
        showAlert("Manage Courses", "Navigating to Manage Courses");
    }

    // Phương thức điều khiển nút "Quản lý học phí"
    @FXML
    private void onStatistics() {
        // Logic cho nút "Quản lý học phí"
        showAlert("Statistics", "Navigating to Statistics");
    }

    // Phương thức điều khiển nút "Lịch sử khóa học"
    @FXML
    private void onLogout() {
        // Logic cho nút "Lịch sử khóa học"
        showAlert("Logout", "Logging out");
    }

    // Phương thức thêm học viên
    @FXML
    private void onAddStudent() {
        // Kiểm tra dữ liệu nhập vào
        if (validateInput()) {
            String name = studentName.getText();
            String id = studentID.getText();
            String studentClassText = studentClass.getText();
            String dob = studentDob.getValue().toString();
            String hometown = studentHometown.getText();

            student newStudent = new student(id, name, studentClassText, dob, hometown);
            studentTable.getItems().add(newStudent);
            clearFields();
        } else {
            showAlert("Invalid Input", "Please enter all required fields.");
        }
    }

    // Phương thức sửa học viên
    @FXML
    private void onEditStudent() {
        // Logic sửa học viên
        showAlert("Edit Student", "Editing student information");
    }

    // Phương thức tìm kiếm học viên
    @FXML
    private void onSearchStudent() {
        // Logic tìm kiếm học viên
        showAlert("Search Student", "Searching for student");
    }

    // Phương thức xóa dữ liệu nhập vào
    private void clearFields() {
        studentName.clear();
        studentID.clear();
        studentClass.clear();
        studentDob.setValue(null);
        studentHometown.clear();
    }

    // Phương thức kiểm tra dữ liệu hợp lệ
    private boolean validateInput() {
        return !studentName.getText().isEmpty() && !studentID.getText().isEmpty() &&
                !studentClass.getText().isEmpty() && studentDob.getValue() != null &&
                !studentHometown.getText().isEmpty();
    }

    // Phương thức hiển thị thông báo
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
