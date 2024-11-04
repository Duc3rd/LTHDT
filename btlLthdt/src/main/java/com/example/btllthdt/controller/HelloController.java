package com.example.btllthdt.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;

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
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> colStudentName;
    @FXML
    private TableColumn<Student, String> colStudentID;
    @FXML
    private TableColumn<Student, String> colStudentClass;
    @FXML
    private TableColumn<Student, String> colStudentDob;
    @FXML
    private TableColumn<Student, String> colStudentHometown;

    // Các phương thức điều khiển
    @FXML
    private void onAddStudent() {
        // Logic thêm học viên
        String name = studentName.getText();
        String id = studentID.getText();
        String studentClassText = studentClass.getText();
        String dob = studentDob.getValue().toString();
        String hometown = studentHometown.getText();

        Student newStudent = new Student(id, name, studentClassText, dob, hometown);
        // Thêm vào bảng và danh sách
        studentTable.getItems().add(newStudent);
        clearFields();
    }

    @FXML
    private void onEditStudent() {
        // Logic sửa học viên
    }

    @FXML
    private void onSearchStudent() {
        // Logic tìm kiếm học viên
    }

    private void clearFields() {
        studentName.clear();
        studentID.clear();
        studentClass.clear();
        studentDob.setValue(null);
        studentHometown.clear();
    }
}
