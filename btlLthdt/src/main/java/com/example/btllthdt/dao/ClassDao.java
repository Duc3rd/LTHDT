package com.example.btllthdt.dao;

import com.example.btllthdt.model.Class;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassDao {
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

    public List<Class> getAllClasses() {
        List<Class> classes = new ArrayList<>();
        String SQL = "SELECT * FROM lthdtbtl.class";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Class course = new Class();
                course.setId(rs.getInt("id"));
                course.setStartDate(rs.getDate("startDate"));
                course.setEndDate(rs.getDate("endDate"));
                course.setName(rs.getString("name"));
                course.setDescription(rs.getString("description"));
                classes.add(course);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return classes;
    }


    public void insertClass(Class course) {
        String SQL = "INSERT INTO lthdtbtl.class(id, start_date, end_date, name, description) VALUES(?, ?, ?, ?, ?)";
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