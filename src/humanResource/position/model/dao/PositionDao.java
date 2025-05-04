package humanResource.position.model.dao;

import dbConn.CloseHelper;
import dbConn.ConnectionSingletonHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PositionDao {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    /** 연결 **/
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

    public int findRankOrderByPositionId(int positionId) {
        String sql = "SELECT rank_order FROM position WHERE position_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, positionId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("rank_order");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // 조회 실패 시 -1 반환
    }
}

