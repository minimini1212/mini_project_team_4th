package equipmentAsset.history.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import equipmentAsset.common.util.CloseHelper;
import equipmentAsset.common.util.ConnectionSingletonHelper;
import equipmentAsset.history.view.HistoryView;
import lombok.Getter;

@Getter
public class HistoryDAO {
    private HistoryView historyView = new HistoryView();

    // 연결 관련 객체
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private Connection conn = null;

    // connect
    public void connect() {
        try {
            conn = ConnectionSingletonHelper.getConnection("oracle");
            stmt = conn.createStatement();
            conn.setAutoCommit(false); // 자동커밋 끄기
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // close
    public void close() {
        try {
            CloseHelper.close(rs);
            CloseHelper.close(stmt);
            CloseHelper.close(pstmt);
            CloseHelper.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** =-=-=-=-=-=-=-=-=-=-=-=-= 조회 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/

    // - 모든 이력 조회
    public boolean findAllHistory() {
        try {
            rs = stmt.executeQuery("SELECT * FROM V_EQUIPMENT_HISTORY ORDER BY HISTORY_ID DESC");

            // 결과가 비어있는지 확인
            if (!rs.isBeforeFirst()) {
                System.out.println("등록된 이력이 없습니다.");
                return false;
            }

            historyView.displayHistory(rs);
            return true;
        } catch (SQLException e) {
            System.out.println("이력 조회 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // - 특정 장비의 이력 조회
    public boolean findHistoryByEquipmentId(int equipmentId) {
        try {
            String sql = "SELECT * FROM V_EQUIPMENT_HISTORY WHERE EQUIPMENT_ID = ? ORDER BY HISTORY_ID DESC";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, equipmentId);
            rs = pstmt.executeQuery();

            // 결과가 비어있는지 확인
            if (!rs.isBeforeFirst()) {
                System.out.println("장비 ID " + equipmentId + "에 해당하는 이력이 없습니다.");
                return false;
            }

            historyView.displayHistory(rs);
            return true;
        } catch (SQLException e) {
            System.out.println("장비별 이력 조회 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // - 점검 이력 조회
    public boolean findInspectionHistory() {
        try {
            rs = stmt.executeQuery("SELECT * FROM V_INSPECTION_HISTORY ORDER BY HISTORY_ID DESC");

            // 결과가 비어있는지 확인
            if (!rs.isBeforeFirst()) {
                System.out.println("등록된 점검 이력이 없습니다.");
                return false;
            }

            historyView.displayHistory(rs);
            return true;
        } catch (SQLException e) {
            System.out.println("점검 이력 조회 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // - 특정 장비의 점검 이력 조회
    public boolean findInspectionHistoryByEquipmentId(int equipmentId) {
        try {
            String sql = "SELECT * FROM V_INSPECTION_HISTORY WHERE EQUIPMENT_ID = ? ORDER BY HISTORY_ID DESC";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, equipmentId);
            rs = pstmt.executeQuery();

            // 결과가 비어있는지 확인
            if (!rs.isBeforeFirst()) {
                System.out.println("장비 ID " + equipmentId + "에 해당하는 점검 이력이 없습니다.");
                return false;
            }

            historyView.displayHistory(rs);
            return true;
        } catch (SQLException e) {
            System.out.println("장비별 점검 이력 조회 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // - 점검 결과별 이력 조회
    public boolean findInspectionHistoryByResult(String result) {
        try {
            String sql = "SELECT * FROM V_INSPECTION_HISTORY WHERE DESCRIPTION LIKE ? ORDER BY HISTORY_ID DESC";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + result + "%");
            rs = pstmt.executeQuery();

            // 결과가 비어있는지 확인
            if (!rs.isBeforeFirst()) {
                System.out.println(result + " 결과에 해당하는 점검 이력이 없습니다.");
                return false;
            }

            historyView.displayHistory(rs);
            return true;
        } catch (SQLException e) {
            System.out.println("점검 결과별 이력 조회 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // - 수리 이력 조회
    public boolean findRepairHistory() {
        try {
            rs = stmt.executeQuery("SELECT * FROM V_REPAIR_HISTORY ORDER BY HISTORY_ID DESC");

            // 결과가 비어있는지 확인
            if (!rs.isBeforeFirst()) {
                System.out.println("등록된 수리 이력이 없습니다.");
                return false;
            }

            historyView.displayHistory(rs);
            return true;
        } catch (SQLException e) {
            System.out.println("수리 이력 조회 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // - 특정 장비의 수리 이력 조회
    public boolean findRepairHistoryByEquipmentId(int equipmentId) {
        try {
            String sql = "SELECT * FROM V_REPAIR_HISTORY WHERE EQUIPMENT_ID = ? ORDER BY HISTORY_ID DESC";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, equipmentId);
            rs = pstmt.executeQuery();

            // 결과가 비어있는지 확인
            if (!rs.isBeforeFirst()) {
                System.out.println("장비 ID " + equipmentId + "에 해당하는 수리 이력이 없습니다.");
                return false;
            }

            historyView.displayHistory(rs);
            return true;
        } catch (SQLException e) {
            System.out.println("장비별 수리 이력 조회 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // - 수리 결과별 이력 조회
    public boolean findRepairHistoryByResult(String result) {
        try {
            String sql = "SELECT * FROM V_REPAIR_HISTORY WHERE HISTORY_TYPE = ? ORDER BY HISTORY_ID DESC";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, result);
            rs = pstmt.executeQuery();

            // 결과가 비어있는지 확인
            if (!rs.isBeforeFirst()) {
                System.out.println(result + " 유형의 수리 이력이 없습니다.");
                return false;
            }

            historyView.displayHistory(rs);
            return true;
        } catch (SQLException e) {
            System.out.println("수리 결과별 이력 조회 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // - 폐기 이력 조회
    public boolean findDisposalHistory() {
        try {
            rs = stmt.executeQuery("SELECT * FROM V_DISPOSAL_HISTORY ORDER BY HISTORY_ID DESC");

            // 결과가 비어있는지 확인
            if (!rs.isBeforeFirst()) {
                System.out.println("등록된 폐기 이력이 없습니다.");
                return false;
            }

            historyView.displayHistory(rs);
            return true;
        } catch (SQLException e) {
            System.out.println("폐기 이력 조회 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // - 특정 장비의 폐기 이력 조회
    public boolean findDisposalHistoryByEquipmentId(int equipmentId) {
        try {
            String sql = "SELECT * FROM V_DISPOSAL_HISTORY WHERE EQUIPMENT_ID = ? ORDER BY HISTORY_ID DESC";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, equipmentId);
            rs = pstmt.executeQuery();

            // 결과가 비어있는지 확인
            if (!rs.isBeforeFirst()) {
                System.out.println("장비 ID " + equipmentId + "에 해당하는 폐기 이력이 없습니다.");
                return false;
            }

            historyView.displayHistory(rs);
            return true;
        } catch (SQLException e) {
            System.out.println("장비별 폐기 이력 조회 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}