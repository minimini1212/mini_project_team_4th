package com.hospital.leave.model.dao;

import com.hospital.leave.model.entity.Leave;
import dbConn.CloseHelper;
import dbConn.ConnectionHelper;
import dbConn.ConnectionSingletonHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeaveDao {
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

    public boolean insert(Leave l) {
        String sql = "INSERT INTO LEAVE_REQUEST (leave_id, employee_id, leave_type, start_date, end_date, days, reason, status, approver_id, apply_date) " +
                "VALUES (LEAVE_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, 'REQUEST', ?, ?)";
        try {
            pstmt = conn.prepareStatement(sql);
            int i = 1;
            pstmt.setLong(i++, l.getEmployeeId());
            pstmt.setString(i++, l.getLeaveType());
            pstmt.setDate(i++, Date.valueOf(l.getStartDate()));
            pstmt.setDate(i++, Date.valueOf(l.getEndDate()));
            pstmt.setDouble(i++, l.getDays());
            pstmt.setString(i++, l.getReason());
            pstmt.setLong(i++, l.getApproverId());
            pstmt.setDate(i,   Date.valueOf(l.getApplyDate()));
            boolean ok = pstmt.executeUpdate() == 1;
            conn.commit();
            return ok;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateStatus(Long id, String status, Long approverId) {
        String sql = "UPDATE LEAVE_REQUEST SET status=?, approver_id=? WHERE leave_id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, status);
            pstmt.setLong(2, approverId);
            pstmt.setLong(3, id);
            boolean ok = pstmt.executeUpdate() == 1;
            conn.commit();
            return ok;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Leave> findByEmployee(Long empId) {
        List<Leave> list = new ArrayList<>();
        String sql = "SELECT * FROM LEAVE_REQUEST WHERE employee_id=? ORDER BY start_date";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, empId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new Leave(
                        rs.getLong("leave_id"),
                        rs.getLong("employee_id"),
                        rs.getString("leave_type"),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate(),
                        rs.getDouble("days"),
                        rs.getString("reason"),
                        rs.getString("status"),
                        rs.getLong("approver_id"),
                        rs.getDate("apply_date").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM LEAVE_REQUEST WHERE leave_id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            boolean ok = pstmt.executeUpdate() == 1;
            conn.commit();
            return ok;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}