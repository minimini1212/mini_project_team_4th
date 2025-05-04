package com.hospital.certification.model.dao;

import com.hospital.certification.model.entity.Certification;
import dbConn.CloseHelper;
import dbConn.ConnectionHelper;
import dbConn.ConnectionSingletonHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CertificationDao {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public void connect() {
        try {
            conn = ConnectionSingletonHelper.getConnection("oracle");
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            CloseHelper.close(rs);
            CloseHelper.close(pstmt);
            CloseHelper.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Certification> findAll() throws SQLException {
        String sql = "SELECT * FROM CERTIFICATION ORDER BY cert_id";
        List<Certification> list = new ArrayList<>();
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            list.add(new Certification(
                    rs.getLong("cert_id"),
                    rs.getString("cert_name"),
                    rs.getString("issuing_org"),
                    rs.getString("description")
            ));
        }
        return list;
    }

    public Certification findById(long id) throws SQLException {
        String sql = "SELECT * FROM CERTIFICATION WHERE cert_id = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setLong(1, id);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            return new Certification(
                    rs.getLong("cert_id"),
                    rs.getString("cert_name"),
                    rs.getString("issuing_org"),
                    rs.getString("description")
            );
        }
        return null;
    }

    public void insert(Certification cert) throws SQLException {
        String sql = "INSERT INTO CERTIFICATION (cert_id, cert_name, issuing_org, description) " +
                "VALUES (CERT_SEQ.NEXTVAL, ?, ?, ?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, cert.getCertName());
        pstmt.setString(2, cert.getIssuingOrg());
        pstmt.setString(3, cert.getDescription());
        pstmt.executeUpdate();
        conn.commit();
    }

    public void update(Certification cert) throws SQLException {
        String sql = "UPDATE CERTIFICATION SET cert_name = ?, issuing_org = ?, description = ? WHERE cert_id = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, cert.getCertName());
        pstmt.setString(2, cert.getIssuingOrg());
        pstmt.setString(3, cert.getDescription());
        pstmt.setLong(4, cert.getCertId());
        pstmt.executeUpdate();
        conn.commit();
    }

    public void delete(long id) throws SQLException {
        String sql = "DELETE FROM CERTIFICATION WHERE cert_id = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setLong(1, id);
        pstmt.executeUpdate();
        conn.commit();
    }
}