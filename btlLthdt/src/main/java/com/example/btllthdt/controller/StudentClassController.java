package com.example.btllthdt.controller;

import com.example.btllthdt.model.Class;
import com.example.btllthdt.model.Student;
import com.example.btllthdt.model.StudentClass;
import com.example.btllthdt.service.ClassService;
import com.example.btllthdt.service.StudentClassService;
import com.example.btllthdt.service.StudentService;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class StudentClassController {

    @FXML
    private TableView<StudentClass> studentClassTable;

    @FXML
    private TableColumn<StudentClass, Integer> colStudentId;

    @FXML
    private TableColumn<StudentClass, Integer> colClassId;

    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<Student> studentComboBox;

    @FXML
    private ComboBox<Class> classComboBox;

    private ClassService classService = new ClassService();
    private StudentService studentService = new StudentService();

    private StudentClassService studentClassService;
    private ObservableList<StudentClass> studentClassList;

    public StudentClassController() {
        studentClassService = new StudentClassService();
    }

    @FXML
    public void initialize() {
        colStudentId.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getStudentId()).asObject());

        colClassId.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getClassId()).asObject());


        // Load data into the table
        loadStudentClassData();

        List<Class> classes = classService.getAllClasses();
        List<Student> students = studentService.getAllStudents();

        // Create ObservableLists from the data
        ObservableList<Class> classObservableList = FXCollections.observableList(classes);
        ObservableList<Student> studentObservableList = FXCollections.observableList(students);

        // Set the ObservableLists to the ComboBoxes
        classComboBox.setItems(classObservableList);
        studentComboBox.setItems(studentObservableList);

        // Optionally, you can set a cell factory if you want to display a specific field in the ComboBox (e.g., class name or student name)
        classComboBox.setCellFactory(param -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(Class item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });

        studentComboBox.setCellFactory(param -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(Student item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
    }


    private FilteredList<StudentClass> filteredList; // Khai báo filteredList

    private void loadStudentClassData() {
        List<StudentClass> studentClasses = studentClassService.getAllStudentClass();
        studentClassList = FXCollections.observableArrayList(studentClasses);

        // Khởi tạo filteredList từ studentClassList
        filteredList = new FilteredList<>(studentClassList);

        // Thiết lập filteredList vào TableView
        studentClassTable.setItems(filteredList);
    }



    @FXML
    private void onSearchStudentClass() {
        String searchText = searchField.getText().toLowerCase();

        // Khởi tạo filteredList từ studentClassList
        if (studentClassList != null) {
            filteredList.setPredicate(studentClass -> {
             
                if (searchText.isEmpty()) {
                    return true; // Khi không có từ khóa, hiển thị lại toàn bộ danh sách
                }

                return String.valueOf(studentClass.getStudentId()).toLowerCase().contains(searchText) ||
                        String.valueOf(studentClass.getClassId()).toLowerCase().contains(searchText);
            });

            studentClassTable.setItems(filteredList);
        }
    }



    @FXML
    private void onAddStudentClass() {

      try{
          Student selectedStudent = studentComboBox.getSelectionModel().getSelectedItem();
          Class selectedClass = classComboBox.getSelectionModel().getSelectedItem();
          if (selectedStudent != null && selectedClass != null) {
              StudentClass studentClass = new StudentClass(selectedStudent.getId(), selectedClass.getId());
              studentClassService.registerForClass(studentClass);
              loadStudentClassData();
              showAlert("Thành công", "Thêm thành công");
          } else {
              // Handle the case where either student or class is not selected (optional)
              showAlert("Lỗi", "Vui lòng chọn student và class");
          }
      }catch (Exception ex){
          showAlert("Lỗi", ex.getMessage());
      }
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
            Stage stage = (Stage) studentClassTable.getScene().getWindow();

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
            Stage stage = (Stage) studentClassTable.getScene().getWindow();

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
            Stage stage = (Stage) studentClassTable.getScene().getWindow();

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
            Stage stage = (Stage) studentClassTable.getScene().getWindow();

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
            Stage stage = (Stage) studentClassTable.getScene().getWindow();

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
