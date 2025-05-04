package com.hospital.certification.model.dao;

import com.hospital.certification.model.entity.EmployeeCertification;
import dbConn.CloseHelper;
import dbConn.ConnectionHelper;
import dbConn.ConnectionSingletonHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeCertificationDao {
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

    public List<EmployeeCertification> findAll() throws SQLException {
        String sql = "SELECT * FROM EMPLOYEE_CERTIFICATION ORDER BY emp_cert_id";
        List<EmployeeCertification> list = new ArrayList<>();
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            list.add(new EmployeeCertification(
                    rs.getLong("emp_cert_id"),
                    rs.getLong("employee_id"),
                    rs.getLong("cert_id"),
                    rs.getDate("acquisition_date").toLocalDate(),
                    rs.getDate("expiry_date").toLocalDate(),
                    rs.getString("cert_number"),
                    rs.getString("renewal_required"),
                    rs.getDate("alert_date").toLocalDate()
            ));
        }
        return list;
    }

    public EmployeeCertification findById(long id) throws SQLException {
        String sql = "SELECT * FROM EMPLOYEE_CERTIFICATION WHERE emp_cert_id = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setLong(1, id);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            return new EmployeeCertification(
                    rs.getLong("emp_cert_id"),
                    rs.getLong("employee_id"),
                    rs.getLong("cert_id"),
                    rs.getDate("acquisition_date").toLocalDate(),
                    rs.getDate("expiry_date").toLocalDate(),
                    rs.getString("cert_number"),
                    rs.getString("renewal_required"),
                    rs.getDate("alert_date").toLocalDate()
            );
        }
        return null;
    }

    public List<EmployeeCertification> findByEmployeeId(long employeeId) throws SQLException {
        String sql = "SELECT * FROM EMPLOYEE_CERTIFICATION WHERE employee_id = ? ORDER BY emp_cert_id";
        List<EmployeeCertification> list = new ArrayList<>();
        pstmt = conn.prepareStatement(sql);
        pstmt.setLong(1, employeeId);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            list.add(new EmployeeCertification(
                    rs.getLong("emp_cert_id"),
                    rs.getLong("employee_id"),
                    rs.getLong("cert_id"),
                    rs.getDate("acquisition_date").toLocalDate(),
                    rs.getDate("expiry_date").toLocalDate(),
                    rs.getString("cert_number"),
                    rs.getString("renewal_required"),
                    rs.getDate("alert_date").toLocalDate()
            ));
        }
        return list;
    }

    public void insert(EmployeeCertification ec) throws SQLException {
        String sql = "INSERT INTO EMPLOYEE_CERTIFICATION (emp_cert_id, employee_id, cert_id, acquisition_date, expiry_date, cert_number, renewal_required, alert_date) " +
                "VALUES (EMP_CERT_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setLong(1, ec.getEmployeeId());
        pstmt.setLong(2, ec.getCertId());
        pstmt.setDate(3, Date.valueOf(ec.getAcquisitionDate()));
        pstmt.setDate(4, Date.valueOf(ec.getExpiryDate()));
        pstmt.setString(5, ec.getCertNumber());
        pstmt.setString(6, ec.getRenewalRequired());
        pstmt.setDate(7, Date.valueOf(ec.getAlertDate()));
        pstmt.executeUpdate();
        conn.commit();
    }

    public void update(EmployeeCertification ec) throws SQLException {
        String sql = "UPDATE EMPLOYEE_CERTIFICATION SET employee_id = ?, cert_id = ?, acquisition_date = ?, expiry_date = ?, cert_number = ?, renewal_required = ?, alert_date = ? WHERE emp_cert_id = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setLong(1, ec.getEmployeeId());
        pstmt.setLong(2, ec.getCertId());
        pstmt.setDate(3, Date.valueOf(ec.getAcquisitionDate()));
        pstmt.setDate(4, Date.valueOf(ec.getExpiryDate()));
        pstmt.setString(5, ec.getCertNumber());
        pstmt.setString(6, ec.getRenewalRequired());
        pstmt.setDate(7, Date.valueOf(ec.getAlertDate()));
        pstmt.setLong(8, ec.getEmpCertId());
        pstmt.executeUpdate();
        conn.commit();
    }

    public void delete(long id) throws SQLException {
        String sql = "DELETE FROM EMPLOYEE_CERTIFICATION WHERE emp_cert_id = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setLong(1, id);
        pstmt.executeUpdate();
        conn.commit();
    }
}