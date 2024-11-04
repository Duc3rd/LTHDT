package com.example.btllthdt.dao;

import com.example.btllthdt.model.Class;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClassDao {
    private Connection connect() {
        String url = "jdbc:postgresql://localhost/postgres";
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

    public void insertClass(Class course) {
        String SQL = "INSERT INTO classes(id, startDate, endDate, name, description) VALUES(?, ?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setInt(1, course.getId());
            pstmt.setDate(2, course.getStartDate());
            pstmt.setDate(3, course.getEndDate());
            pstmt.setString(4, course.getName());
            pstmt.setString(5, course.getDescription());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
