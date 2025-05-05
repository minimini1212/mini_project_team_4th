package humanResource.position.model.dao;

import dbConn.CloseHelper;
import dbConn.ConnectionSingletonHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PositionDao {
    private Connection conn;

    // 연결 주입
    public void connect(Connection conn) throws SQLException {
        this.conn = conn;
        if (this.conn.isClosed()) {
            throw new SQLException("⚠ 연결이 이미 종료되었습니다.");
        }
        this.conn.setAutoCommit(false); // 트랜잭션 수동 처리
    }

    // 자원 해제
    public void close() {
        try {
            if (conn != null && !conn.isClosed()) conn.close();
        } catch (SQLException e) {
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

