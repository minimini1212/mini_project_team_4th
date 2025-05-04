package com.hospital.certification.model.dao;

import com.hospital.certification.model.entity.EmployeeCertification;
import dbConn.ConnectionHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeCertificationDAO {

    public boolean insert(EmployeeCertification ec) {
        String sql = "INSERT INTO EMPLOYEE_CERTIFICATION "
                + "(emp_cert_id, employee_id, cert_id, acquisition_date, expiry_date, cert_number, renewal_required, alert_date) "
                + "VALUES (ECERT_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionHelper.getConnection("oracle");
             PreparedStatement ps = conn.prepareStatement(sql)) {
            int i=1;
            ps.setLong(i++, ec.getEmployeeId());
            ps.setLong(i++, ec.getCertId());
            ps.setDate(i++, Date.valueOf(ec.getAcquisitionDate()));
            ps.setDate(i++, Date.valueOf(ec.getExpiryDate()));
            ps.setString(i++, ec.getCertNumber());
            ps.setString(i++, ec.isRenewalRequired() ? "Y" : "N");
            ps.setDate(i, Date.valueOf(ec.getAlertDate()));
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<EmployeeCertification> selectByEmployee(Long empId) {
        List<EmployeeCertification> list = new ArrayList<>();
        String sql = "SELECT * FROM EMPLOYEE_CERTIFICATION WHERE employee_id = ? ORDER BY expiry_date";
        try (Connection conn = ConnectionHelper.getConnection("oracle");
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, empId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new EmployeeCertification(
                            rs.getLong("emp_cert_id"),
                            rs.getLong("employee_id"),
                            rs.getLong("cert_id"),
                            rs.getDate("acquisition_date").toLocalDate(),
                            rs.getDate("expiry_date").toLocalDate(),
                            rs.getString("cert_number"),
                            "Y".equals(rs.getString("renewal_required")),
                            rs.getDate("alert_date").toLocalDate()
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean delete(Long empCertId) {
        String sql = "DELETE FROM EMPLOYEE_CERTIFICATION WHERE emp_cert_id = ?";
        try (Connection conn = ConnectionHelper.getConnection("oracle");
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, empCertId);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}