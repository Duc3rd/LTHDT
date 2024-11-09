package com.example.btllthdt.dao;

import com.example.btllthdt.model.Certificate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CertificateDao {
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

    public void issueCertificate(Certificate certificate) {
        String SQL = "INSERT INTO lthdtbtl.certificate (student_id, class_id, status) VALUES (?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setInt(1, certificate.getStudentId());
            pstmt.setInt(2, certificate.getClassId());
            pstmt.setString(3, certificate.getStatus());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Certificate> getAllCertificates() {
        List<Certificate> certificates = new ArrayList<>();
        String SQL = "SELECT * FROM lthdtbtl.certificate";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Certificate certificate = new Certificate();
                certificate.setId(rs.getInt("id"));
                certificate.setStudentId(rs.getInt("student_id"));
                certificate.setClassId(rs.getInt("class_id"));
                certificate.setStatus(rs.getString("status"));
                certificates.add(certificate);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return certificates;
    }
}
