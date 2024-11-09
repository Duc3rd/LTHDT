package com.example.btllthdt.dao;

import com.example.btllthdt.model.StudentClass;
import com.example.btllthdt.model.StudentClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentClassDao {
    private Connection connect() {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "Duc03082005";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void registerForClass(StudentClass studentClass) {
        String SQL = "INSERT INTO lthdtbtl.student_class (student_id, class_id) VALUES (?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = connect();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, studentClass.getStudentId());
            pstmt.setInt(2, studentClass.getClassId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public List<StudentClass> getAllStudentClass() {
        List<StudentClass> resultList = new ArrayList<>();
        String SQL = "SELECT * FROM lthdtbtl.student_class";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = connect();
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                StudentClass studentClass = new StudentClass();
                studentClass.setStudentId(rs.getInt("student_id"));
                studentClass.setClassId(rs.getInt("class_id"));
                resultList.add(studentClass);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return resultList;
    }
}
