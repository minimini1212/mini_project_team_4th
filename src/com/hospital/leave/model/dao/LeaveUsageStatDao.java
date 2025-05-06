package com.hospital.leave.model.dao;

import com.hospital.leave.model.entity.LeaveUsageStat;
import dbConn.CloseHelper;
import dbConn.ConnectionHelper;
import dbConn.ConnectionSingletonHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeaveUsageStatDao {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public void connect() {
        try {
            conn = ConnectionSingletonHelper.getConnection("oracle");
            conn.setAutoCommit(false);
        } catch (Exception e) { e.printStackTrace(); }
    }
    public void close() {
        try { CloseHelper.close(rs); CloseHelper.close(pstmt); CloseHelper.close(conn); } catch (Exception e) { e.printStackTrace(); }
    }

    /**
     * MERGE(UPSERT) 휴가 사용 통계
     */
    public boolean upsert(LeaveUsageStat s) {
        String sql = """
            MERGE INTO LEAVE_USAGE_STAT t
            USING (SELECT ? dept, ? yr, ? mon FROM DUAL) src
            ON (t.department_id = src.dept AND t.year = src.yr AND t.month = src.mon)
            WHEN MATCHED THEN
              UPDATE SET total_leave_days = ?, annual_used = ?, sick_used = ?, other_used = ?
            WHEN NOT MATCHED THEN
              INSERT (usage_stat_id, department_id, year, month, total_leave_days, annual_used, sick_used, other_used)
              VALUES (LUS_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)""";
        try {
            pstmt = conn.prepareStatement(sql);
            int i = 1;
            // merge key
            pstmt.setLong(i++, s.getDepartmentId());
            pstmt.setInt(i++,  s.getYear());
            pstmt.setInt(i++,  s.getMonth());
            // update set
            pstmt.setDouble(i++, s.getTotalLeaveDays());
            pstmt.setDouble(i++, s.getAnnualUsed());
            pstmt.setDouble(i++, s.getSickUsed());
            pstmt.setDouble(i++, s.getOtherUsed());
            // insert values
            pstmt.setLong(i++, s.getDepartmentId());
            pstmt.setInt(i++,  s.getYear());
            pstmt.setInt(i++,  s.getMonth());
            pstmt.setDouble(i++, s.getTotalLeaveDays());
            pstmt.setDouble(i++, s.getAnnualUsed());
            pstmt.setDouble(i++, s.getSickUsed());
            pstmt.setDouble(i,   s.getOtherUsed());
            boolean ok = pstmt.executeUpdate() > 0;
            conn.commit();
            return ok;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<LeaveUsageStat> findByDeptMonth(Long deptId, int yr, int mon) {
        List<LeaveUsageStat> list = new ArrayList<>();
        String sql = "SELECT * FROM LEAVE_USAGE_STAT WHERE department_id = ? AND year = ? AND month = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, deptId);
            pstmt.setInt(2, yr);
            pstmt.setInt(3, mon);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new LeaveUsageStat(
                        rs.getLong("usage_stat_id"), rs.getLong("department_id"),
                        rs.getInt("year"), rs.getInt("month"),
                        rs.getDouble("total_leave_days"), rs.getDouble("annual_used"),
                        rs.getDouble("sick_used"), rs.getDouble("other_used")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}