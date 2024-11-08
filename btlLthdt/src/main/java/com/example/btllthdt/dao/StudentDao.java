package com.example.btllthdt.dao;

import com.example.btllthdt.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    private Connection connect() {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "Duc03082005";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("db connected");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insertStudent(Student student) {
        String SQL = "INSERT INTO student(id, name, dob, addressProvince, province) VALUES(?, ?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setInt(1, student.getId());
            pstmt.setString(2, student.getName());
            pstmt.setDate(3, student.getDob());
            pstmt.setInt(4, student.getAddressProvince());
            pstmt.setInt(5, student.getProvince());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String SQL = "SELECT * FROM lthdtbtl.student";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL);

             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setDob(rs.getDate("dob"));
                student.setAddressProvince(rs.getInt("address_province"));
                student.setProvince(rs.getInt("province"));
                students.add(student);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return students;
    }

    public List<Student> getStudentsByProvince(int provinceId) {
        List<Student> students = new ArrayList<>();
        String SQL = "SELECT * FROM students WHERE province = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setInt(1, provinceId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getInt("id"));
                    student.setName(rs.getString("name"));
                    student.setDob(rs.getDate("dob"));
                    student.setAddressProvince(rs.getInt("addressProvince"));
                    student.setProvince(rs.getInt("province"));
                    students.add(student);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return students;
    }

    public void updateStudent(Student student) {
        String SQL = "UPDATE students SET name = ?, dob = ?, addressProvince = ?, province = ? WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, student.getName());
            pstmt.setDate(2, student.getDob());
            pstmt.setInt(3, student.getAddressProvince());
            pstmt.setInt(4, student.getProvince());
            pstmt.setInt(5, student.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteStudent(int studentId) {
        String SQL = "DELETE FROM students WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setInt(1, studentId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
