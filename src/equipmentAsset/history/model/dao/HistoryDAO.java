package equipmentAsset.history.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dbConn.*;
import equipmentAsset.history.model.entity.EquipmentHistory;
import equipmentAsset.history.view.HistoryView;
import equipmentAsset.repair.model.entity.RepairRequest;
import lombok.Getter;

@Getter
public class HistoryDAO extends BaseDAO {
    private HistoryView historyView = new HistoryView();

    /** =-=-=-=-=-=-=-=-=-=-=-=-= 조회 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/

    // - 모든 이력 조회
    public boolean findAllHistory() {
        try {
            rs = stmt.executeQuery("SELECT * FROM V_EQUIPMENT_HISTORY ORDER BY HISTORY_ID");

            // 결과가 비어있는지 확인
            if (!rs.isBeforeFirst()) {
                System.out.println("❌ 등록된 이력이 없습니다.");
                return false;
            }

            while (rs.next()) {
                EquipmentHistory equipmentHistory = createEquipmentHistoryFromResultSet(rs);
                System.out.println(equipmentHistory);
            }

            return true;
        } catch (SQLException e) {
            System.out.println("❌ 이력 조회 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // - 특정 장비의 이력 조회
    public boolean findHistoryByEquipmentId(int equipmentId) {
        try {
            String sql = "SELECT * FROM V_EQUIPMENT_HISTORY WHERE EQUIPMENT_ID = ? ORDER BY HISTORY_ID";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, equipmentId);
            rs = pstmt.executeQuery();

            // 결과가 비어있는지 확인
            if (!rs.isBeforeFirst()) {
                System.out.println("❌ 장비 ID " + equipmentId + "에 해당하는 이력이 없습니다.");
                return false;
            }

            while (rs.next()) {
                EquipmentHistory equipmentHistory = createEquipmentHistoryFromResultSet(rs);
                System.out.println(equipmentHistory);
            }

            return true;
        } catch (SQLException e) {
            System.out.println("❌ 장비별 이력 조회 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // - 점검 이력 조회
    public boolean findInspectionHistory() {
        try {
            rs = stmt.executeQuery("SELECT * FROM V_INSPECTION_HISTORY ORDER BY HISTORY_ID");

            // 결과가 비어있는지 확인
            if (!rs.isBeforeFirst()) {
                System.out.println("❌ 등록된 점검 이력이 없습니다.");
                return false;
            }

            while (rs.next()) {
                EquipmentHistory equipmentHistory = createEquipmentHistoryFromResultSet(rs);
                System.out.println(equipmentHistory);
            }

            return true;
        } catch (SQLException e) {
            System.out.println("❌ 점검 이력 조회 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // - 특정 장비의 점검 이력 조회
    public boolean findInspectionHistoryByEquipmentId(int equipmentId) {
        try {
            String sql = "SELECT * FROM V_INSPECTION_HISTORY WHERE EQUIPMENT_ID = ? ORDER BY HISTORY_ID";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, equipmentId);
            rs = pstmt.executeQuery();

            // 결과가 비어있는지 확인
            if (!rs.isBeforeFirst()) {
                System.out.println("❌ 장비 ID " + equipmentId + "에 해당하는 점검 이력이 없습니다.");
                return false;
            }

            while (rs.next()) {
                EquipmentHistory equipmentHistory = createEquipmentHistoryFromResultSet(rs);
                System.out.println(equipmentHistory);
            }

            return true;
        } catch (SQLException e) {
            System.out.println("❌ 장비별 점검 이력 조회 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // - 점검 결과별 이력 조회
    public boolean findInspectionHistoryByResult(String result) {
        try {
            String sql = "SELECT * FROM V_INSPECTION_HISTORY WHERE DESCRIPTION LIKE ? ORDER BY HISTORY_ID";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + result + "%");
            rs = pstmt.executeQuery();

            // 결과가 비어있는지 확인
            if (!rs.isBeforeFirst()) {
                System.out.println(result + "❌ 결과에 해당하는 점검 이력이 없습니다.");
                return false;
            }

            while (rs.next()) {
                EquipmentHistory equipmentHistory = createEquipmentHistoryFromResultSet(rs);
                System.out.println(equipmentHistory);
            }

            return true;
        } catch (SQLException e) {
            System.out.println("❌ 점검 결과별 이력 조회 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // - 수리 이력 조회
    public boolean findRepairHistory() {
        try {
            rs = stmt.executeQuery("SELECT * FROM V_REPAIR_HISTORY ORDER BY HISTORY_ID");

            // 결과가 비어있는지 확인
            if (!rs.isBeforeFirst()) {
                System.out.println("❌ 등록된 수리 이력이 없습니다.");
                return false;
            }

            while (rs.next()) {
                EquipmentHistory equipmentHistory = createEquipmentHistoryFromResultSet(rs);
                System.out.println(equipmentHistory);
            }

            return true;
        } catch (SQLException e) {
            System.out.println("❌ 수리 이력 조회 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // - 특정 장비의 수리 이력 조회
    public boolean findRepairHistoryByEquipmentId(int equipmentId) {
        try {
            String sql = "SELECT * FROM V_REPAIR_HISTORY WHERE EQUIPMENT_ID = ? ORDER BY HISTORY_ID";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, equipmentId);
            rs = pstmt.executeQuery();

            // 결과가 비어있는지 확인
            if (!rs.isBeforeFirst()) {
                System.out.println("❌ 장비 ID " + equipmentId + "에 해당하는 수리 이력이 없습니다.");
                return false;
            }

            while (rs.next()) {
                EquipmentHistory equipmentHistory = createEquipmentHistoryFromResultSet(rs);
                System.out.println(equipmentHistory);
            }

            return true;
        } catch (SQLException e) {
            System.out.println("❌ 장비별 수리 이력 조회 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // - 수리 결과별 이력 조회
    public boolean findRepairHistoryByResult(String result) {
        try {
            String sql = "SELECT * FROM V_REPAIR_HISTORY WHERE HISTORY_TYPE = ? ORDER BY HISTORY_ID";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, result);
            rs = pstmt.executeQuery();

            // 결과가 비어있는지 확인
            if (!rs.isBeforeFirst()) {
                System.out.println("❌ "+ result + " 유형의 수리 이력이 없습니다.");
                return false;
            }

            while (rs.next()) {
                EquipmentHistory equipmentHistory = createEquipmentHistoryFromResultSet(rs);
                System.out.println(equipmentHistory);
            }

            return true;
        } catch (SQLException e) {
            System.out.println("❌ 수리 결과별 이력 조회 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // - 폐기 이력 조회
    public boolean findDisposalHistory() {
        try {
            rs = stmt.executeQuery("SELECT * FROM V_DISPOSAL_HISTORY ORDER BY HISTORY_ID");

            // 결과가 비어있는지 확인
            if (!rs.isBeforeFirst()) {
                System.out.println("❌ 등록된 폐기 이력이 없습니다.");
                return false;
            }

            while (rs.next()) {
                EquipmentHistory equipmentHistory = createEquipmentHistoryFromResultSet(rs);
                System.out.println(equipmentHistory);
            }

            return true;
        } catch (SQLException e) {
            System.out.println("❌ 폐기 이력 조회 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // - 특정 장비의 폐기 이력 조회
    public boolean findDisposalHistoryByEquipmentId(int equipmentId) {
        try {
            String sql = "SELECT * FROM V_DISPOSAL_HISTORY WHERE EQUIPMENT_ID = ? ORDER BY HISTORY_ID";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, equipmentId);
            rs = pstmt.executeQuery();

            // 결과가 비어있는지 확인
            if (!rs.isBeforeFirst()) {
                System.out.println("❌ 장비 ID " + equipmentId + "에 해당하는 폐기 이력이 없습니다.");
                return false;
            }

            while (rs.next()) {
                EquipmentHistory equipmentHistory = createEquipmentHistoryFromResultSet(rs);
                System.out.println(equipmentHistory);
            }

            return true;
        } catch (SQLException e) {
            System.out.println("❌ 장비별 폐기 이력 조회 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * =-=-=-=-=-=-=-=-=-=-=-=-= 재사용 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=
     **/

    // ResultSet에서 장비이력 객체를 생성하는 메서드
    public EquipmentHistory createEquipmentHistoryFromResultSet(ResultSet rs) throws SQLException {
        EquipmentHistory equipmentHistory = new EquipmentHistory();

        equipmentHistory.setEquipmentId(rs.getInt("EQUIPMENT_ID"));
        equipmentHistory.setHistoryId(rs.getInt("HISTORY_ID"));
        equipmentHistory.setHistoryType(rs.getString("HISTORY_TYPE"));
        equipmentHistory.setDescription(rs.getString("DESCRIPTION"));
        equipmentHistory.setOccurrenceDate(rs.getDate("OCCURRENCE_DATE"));
        equipmentHistory.setRelatedId(rs.getInt("RELATED_ID"));

        return equipmentHistory;
    }




}