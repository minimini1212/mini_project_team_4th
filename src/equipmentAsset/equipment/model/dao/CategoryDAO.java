package equipmentAsset.equipment.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import dbConn.*;
import equipmentAsset.equipment.model.entity.Equipment;
import equipmentAsset.equipment.model.entity.EquipmentCategory;
import equipmentAsset.equipment.view.EquipmentView;
import lombok.Getter;

@Getter

public class CategoryDAO extends BaseDAO {
    private final String TABLE_NAME = "EQUIPMENT_CATEGORY";
    private EquipmentView equipmentVIew = new EquipmentView();

    /**
     * =-=-=-=-=-=-=-=-=-=-=-=-= 조회 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=
     **/

    // - 모든 카테고리 조회
    public boolean findAllCategories() {
        try {
            rs = stmt.executeQuery("SELECT * FROM EQUIPMENT_CATEGORY");

            // 결과가 비어있는지 확인
            if (!rs.isBeforeFirst()) {
                System.out.println("카테고리 정보가 없습니다.");
                return false;
            }

            while (rs.next()) {
                EquipmentCategory equipmentCategory = createCategoryFromResultSet(rs);
                System.out.println(equipmentCategory);
            }

            return true;
        } catch (SQLException e) {
            System.out.println("카테고리 조회 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // 카테고리 ID를 받아서 이름을 반환
    public String getCategoryNameById(int categoryId) {
        String categoryName = null;
        try {
            String sql = "SELECT CATEGORY_NAME FROM EQUIPMENT_CATEGORY WHERE CATEGORY_ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, categoryId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                categoryName = rs.getString("CATEGORY_NAME");
            } else {
                System.out.println("카테고리 ID " + categoryId + "에 해당하는 카테고리가 없습니다.");
            }
        } catch (SQLException e) {
            System.out.println("카테고리 이름 조회 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
        }
        return categoryName;
    }

    /**
     * =-=-=-=-=-=-=-=-=-=-=-=-= 등록 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=
     **/

    // - 새 카테고리 저장
    public boolean saveCategory(EquipmentCategory category) {
        String sql = "INSERT INTO EQUIPMENT_CATEGORY VALUES(?,?,?)";

        // 시퀀스에서 아이디 가져와서 다음값 저장
        category.setCategoryId(getNextCategoryId());

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, category.getCategoryId());
            pstmt.setString(2, category.getCategoryName());
            pstmt.setString(3, category.getCategoryCode());

            int result = pstmt.executeUpdate();
            if (result <= 0) {
                System.out.println("카테고리 등록에 실패했습니다.");
                return false;
            }
            return true;
        } catch (SQLException e) {
            System.out.println("카테고리 등록 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    } // end saveCategory

    /**
     * =-=-=-=-=-=-=-=-=-=-=-=-= 삭제 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=
     **/

    // - 카테고리 삭제
    public boolean deleteCategory(int categoryId) {
        String sql = "DELETE FROM EQUIPMENT_CATEGORY WHERE CATEGORY_ID = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, categoryId);
            int result = pstmt.executeUpdate();

            if (result <= 0) {
                System.out.println("카테고리 ID " + categoryId + "에 해당하는 카테고리가 없어 삭제할 수 없습니다.");
                return false;
            }
            return true;
        } catch (SQLException e) {
            System.out.println("카테고리 삭제 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    } // end deleteCategory

    /**
     * =-=-=-=-=-=-=-=-=-=-=-=-= 시퀀스 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=
     **/

    // - 시퀀스로 다음 카테고리 ID 값 가져오기
    public int getNextCategoryId() {
        int nextId = 0;
        try {
            rs = stmt.executeQuery("SELECT SEQ_CATEGORY_ID.NEXTVAL FROM DUAL");
            if (rs.next()) {
                nextId = rs.getInt(1);
            } else {
                System.out.println("시퀀스 값을 가져오는데 실패했습니다.");
            }
        } catch (SQLException e) {
            System.out.println("시퀀스 조회 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
        }
        return nextId;
    }

    /**
     * =-=-=-=-=-=-=-=-=-=-=-=-= 유틸 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=
     **/

    // 카테고리가 장비에서 사용 중인지 확인
    public boolean isUsedInEquipment(int categoryId) {
        try {
            String sql = "SELECT COUNT(*) FROM EQUIPMENT WHERE CATEGORY_ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, categoryId);
            rs = pstmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("카테고리 ID " + categoryId + "는 현재 " + rs.getInt(1) + "개의 장비에서 사용 중입니다.");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("카테고리 사용 여부 확인 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }


    /**
     * =-=-=-=-=-=-=-=-=-=-=-=-= 재사용 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=
     **/

    // ResultSet에서 카테고리 객체를 생성하는 메서드
    private EquipmentCategory createCategoryFromResultSet(ResultSet rs) throws SQLException {
        EquipmentCategory category = new EquipmentCategory();

        category.setCategoryId(rs.getInt("CATEGORY_ID"));
        category.setCategoryName(rs.getString("CATEGORY_NAME"));
        category.setCategoryCode(rs.getString("CATEGORY_CODE"));

        return category;
    }


}
