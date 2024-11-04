package com.example.btllthdt.dao;

import com.example.btllthdt.model.Province;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProvinceDao {
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

    public void insertProvince(Province province) {
        String SQL = "INSERT INTO provinces(id, name) VALUES(?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setInt(1, province.getId());
            pstmt.setString(2, province.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Province> getAllProvinces() {
        List<Province> provinces = new ArrayList<>();
        String SQL = "SELECT * FROM provinces";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Province province = new Province();
                province.setId(rs.getInt("id"));
                province.setName(rs.getString("name"));
                provinces.add(province);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return provinces;
    }
}
