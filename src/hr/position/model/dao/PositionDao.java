package hr.position.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PositionDao {
    private final Connection conn;

    public PositionDao(Connection conn) {
        this.conn = conn;
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

