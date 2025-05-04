package com.hospital.certification.model.dao;

import com.hospital.certification.model.entity.Certification;
import dbConn.ConnectionHelper;
import dbConn.CloseHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CertificationDAO {

    public List<Certification> selectAll() {
        List<Certification> list = new ArrayList<>();
        String sql = "SELECT * FROM CERTIFICATION ORDER BY cert_id";
        try (Connection conn = ConnectionHelper.getConnection("oracle");
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Certification(
                        rs.getLong("cert_id"),
                        rs.getString("cert_name"),
                        rs.getString("issuing_org"),
                        rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Certification selectById(Long id) {
        String sql = "SELECT * FROM CERTIFICATION WHERE cert_id = ?";
        try (Connection conn = ConnectionHelper.getConnection("oracle");
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Certification(
                            rs.getLong("cert_id"),
                            rs.getString("cert_name"),
                            rs.getString("issuing_org"),
                            rs.getString("description")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insert(Certification c) {
        String sql = "INSERT INTO CERTIFICATION (cert_id, cert_name, issuing_org, description) "
                + "VALUES (CERT_SEQ.NEXTVAL, ?, ?, ?)";
        try (Connection conn = ConnectionHelper.getConnection("oracle");
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getCertName());
            ps.setString(2, c.getIssuingOrg());
            ps.setString(3, c.getDescription());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Certification c) {
        String sql = "UPDATE CERTIFICATION SET cert_name=?, issuing_org=?, description=? WHERE cert_id=?";
        try (Connection conn = ConnectionHelper.getConnection("oracle");
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getCertName());
            ps.setString(2, c.getIssuingOrg());
            ps.setString(3, c.getDescription());
            ps.setLong(4, c.getCertId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM CERTIFICATION WHERE cert_id = ?";
        try (Connection conn = ConnectionHelper.getConnection("oracle");
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}