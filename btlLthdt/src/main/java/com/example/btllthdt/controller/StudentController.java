package com.example.btllthdt.controller;

import com.example.btllthdt.model.Province;
import com.example.btllthdt.model.Student;
import com.example.btllthdt.service.ProvinceService;
import com.example.btllthdt.service.StudentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class StudentController {

    @FXML private TextField studentName;
    @FXML private TextField studentID;
    @FXML private DatePicker studentDob;
    @FXML private ComboBox<Province> studentProvinceCombo;
    @FXML private TextField studentAddressProvince; // Address Province TextField

    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, Integer> colStudentID;
    @FXML private TableColumn<Student, String> colStudentName;
    @FXML private TableColumn<Student, Date> colStudentDob;
    @FXML private TableColumn<Student, Integer> colAddressProvince;
    @FXML private TableColumn<Student, Integer> colProvince;

    @FXML private TextField searchField;

    private final StudentService studentService = new StudentService();
    private final ProvinceService provinceService = new ProvinceService();
    private ObservableList<Student> studentList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Set up TableView columns to use Student properties
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colStudentDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colAddressProvince.setCellValueFactory(new PropertyValueFactory<>("addressProvince"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));

        // Load student data
        loadStudentData();
        loadProvinces();

        // Add listener for table click
        studentTable.setOnMouseClicked(this::onTableClick);
    }

    private void loadStudentData() {
        List<Student> students = studentService.getAllStudents();
        studentList.setAll(students);
        studentTable.setItems(studentList);
    }

    private void loadProvinces() {
        List<Province> provinces = provinceService.getAllProvinces();
        ObservableList<Province> provinceList = FXCollections.observableArrayList(provinces);
        studentProvinceCombo.setItems(provinceList);
    }


    @FXML
    private void onAddStudent() {
        try {
            String name = studentName.getText();
            String studentIdText = studentID.getText();
            Province selectedProvince = studentProvinceCombo.getValue();
            String addressProvinceText = studentAddressProvince.getText();
            Date dob = Date.valueOf(studentDob.getValue()); // Convert DatePicker value to Date

            // Validate input
            if (name.isEmpty() || studentIdText.isEmpty() || selectedProvince == null || addressProvinceText.isEmpty() || dob == null) {
                showAlert("Error", "Please fill in all fields.");
                return;
            }

            // Try to parse the ID and address province as integers
            int studentId = 0;
            int addressProvince = 0;

            try {
                studentId = Integer.parseInt(studentIdText);
                addressProvince = Integer.parseInt(addressProvinceText);
            } catch (NumberFormatException e) {
                showAlert("Error", "Please enter valid numbers for ID and Address Province.");
                return;
            }

            // Create a new Student object
            Student student = new Student();
            student.setId(studentId);
            student.setName(name);
            student.setDob(dob);
            student.setProvince(selectedProvince.getId()); // Use the ID of the selected province
            student.setAddressProvince(addressProvince);

            studentService.addStudent(student);
            loadStudentData();
            clearFormFields();
            showAlert("Success", "Thêm sinh viên thành công");

        } catch (Exception ex) {
            showAlert("Fail", ex.getMessage());
        }
    }

    private void clearFormFields() {
        studentID.clear();
        studentName.clear();
        studentDob.setValue(null);
        studentProvinceCombo.setValue(null);
        studentAddressProvince.clear();
    }

    public void onTableClick(MouseEvent event) {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            studentID.setText(String.valueOf(selectedStudent.getId()));
            studentName.setText(selectedStudent.getName());
            studentDob.setValue(selectedStudent.getDob().toLocalDate());
            studentProvinceCombo.setValue(findProvinceById(selectedStudent.getProvince()));
            studentAddressProvince.setText(String.valueOf(selectedStudent.getAddressProvince()));
        }
    }

    private Province findProvinceById(int provinceId) {
        return studentProvinceCombo.getItems().stream()
                .filter(province -> province.getId() == provinceId)
                .findFirst()
                .orElse(null);
    }


    @FXML
    private void onUpdateStudent(ActionEvent event) {
        try {
            int studentId = Integer.parseInt(studentID.getText());
            String name = studentName.getText();
            Date dob = Date.valueOf(studentDob.getValue());

            // Lấy giá trị từ ComboBox
            Province selectedProvince = studentProvinceCombo.getValue();


            if (selectedProvince == null) {
                showAlert("Error", "Vui lòng chọn tỉnh/thành phố.");
                return;
            }
            String addressProvinceText = studentAddressProvince.getText();
            // Tạo đối tượng Student với thông tin đã cập nhật
            Student student = new Student();
            student.setId(studentId);
            student.setName(name);
            student.setDob(dob);
            student.setProvince(selectedProvince.getId());
            student.setAddressProvince(Integer.parseInt(addressProvinceText));

            studentService.updateStudent(student);
            loadStudentData();
            clearFormFields();
            showAlert("Success", "Cập nhật sinh viên thành công");
        } catch (Exception ex) {
            showAlert("Fail", ex.getMessage());
        }
    }

    @FXML
    private void onDeleteStudent(ActionEvent event) {
        try {
            int id = Integer.parseInt(studentID.getText());
            studentService.deleteStudent(id);
            loadStudentData();
            showAlert("Success", "Xóa sinh viên thành công");
        } catch (Exception ex) {
            showAlert("Fail", ex.getMessage());
        }
    }



    @FXML
    private void onSearchStudent(ActionEvent event) {
        String keyword = searchField.getText().toLowerCase();
        ObservableList<Student> filteredList = FXCollections.observableArrayList();

        for (Student student : studentList) {
            if (student.getName().toLowerCase().contains(keyword) ||
                    String.valueOf(student.getId()).contains(keyword)) {
                filteredList.add(student);
            }
        }
        studentTable.setItems(filteredList);
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
            Stage stage = (Stage) studentTable.getScene().getWindow();

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
            Stage stage = (Stage) studentTable.getScene().getWindow();

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
            Stage stage = (Stage) studentTable.getScene().getWindow();

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
            Stage stage = (Stage) studentTable.getScene().getWindow();

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
            Stage stage = (Stage) studentTable.getScene().getWindow();

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
