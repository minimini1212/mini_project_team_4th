package equipmentAsset.equipment.model.dao;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import dbConn.*;
import equipmentAsset.equipment.model.entity.Equipment;
import equipmentAsset.equipment.view.EquipmentView;
import humanResource.employee.model.entity.Employee;
import lombok.Getter;

@Getter

public class EquipmentDAO extends BaseDAO {
    private final String TABLE_NAME = "EQUIPMENT";
    private EquipmentView equipmentVIew = new EquipmentView();

    /**
     * =-=-=-=-=-=-=-=-=-=-=-=-= 조회 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=
     **/

    // - 모든 장비 목록 조회
    public boolean findAllEquipment() {
        try {
            // V_EQUIPMENT_DETAIL 뷰에서 모든 장비 정보를 가져오는 쿼리
            rs = stmt.executeQuery("SELECT * FROM V_EQUIPMENT_DETAIL ORDER BY EQUIPMENT_ID");

            // 결과가 비어있는지 확인
            if (!rs.isBeforeFirst()) {
                System.out.println("등록된 장비가 없습니다.");
                return false;
            }

            // ResultSet에서 Equipment 객체를 생성하고 출력
            while (rs.next()) {
                Equipment equipment = createEquipmentFromResultSet(rs);
                System.out.println(equipment);
            }
            return true;
        } catch (SQLException e) {
            System.out.println("장비 조회 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    } // end findAll

    // - ID로 특정 장비 조회
    public boolean findByIdEquipment(int equipmentId) {
        try {
            rs = stmt.executeQuery("SELECT * FROM V_EQUIPMENT_DETAIL WHERE EQUIPMENT_ID = " + equipmentId);

            // 결과가 비어있는지 확인
            if (!rs.isBeforeFirst()) {
                System.out.println("장비 ID " + equipmentId + "에 해당하는 장비가 없습니다.");
                return false;
            }

            // ResultSet에서 Equipment 객체를 생성하고 출력
            while (rs.next()) {
                Equipment equipment = createEquipmentFromResultSet(rs);
                System.out.println(equipment);
            }
            return true;
        } catch (SQLException e) {
            System.out.println("장비 ID 조회 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    } // end findById

    // - 특정 카테고리의 장비 조회
    public boolean findByCategoryEquipment(String categoryName) {
        try {
            rs = stmt.executeQuery("SELECT * FROM V_EQUIPMENT_DETAIL WHERE CATEGORY_NAME = '" + categoryName + "'");

            // 결과가 비어있는지 확인
            if (!rs.isBeforeFirst()) {
                System.out.println("카테고리 '" + categoryName + "'에 해당하는 장비가 없습니다.");
                return false;
            }

            // ResultSet에서 Equipment 객체를 생성하고 출력
            while (rs.next()) {
                Equipment equipment = createEquipmentFromResultSet(rs);
                System.out.println(equipment);
            }
            return true;
        } catch (SQLException e) {
            System.out.println("카테고리별 장비 조회 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    } // end findByCategory

    // - 특정 부서의 장비 조회
    public boolean findByDepartmentEquipment(String departmentName) {
        try {
            rs = stmt.executeQuery("SELECT * FROM V_EQUIPMENT_DETAIL WHERE DEPARTMENT_NAME = '" + departmentName + "'");

            // 결과가 비어있는지 확인
            if (!rs.isBeforeFirst()) {
                System.out.println("부서 '" + departmentName + "'에 해당하는 장비가 없습니다.");
                return false;
            }

            // ResultSet에서 Equipment 객체를 생성하고 출력
            while (rs.next()) {
                Equipment equipment = createEquipmentFromResultSet(rs);
                System.out.println(equipment);
            }
            return true;
        } catch (SQLException e) {
            System.out.println("부서별 장비 조회 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    // - 특정 상태(정상, 점검필요, 수리중 등)의 장비 조회
    public boolean findByStatusEquipment(String status) {
        try {
            rs = stmt.executeQuery("SELECT * FROM V_EQUIPMENT_DETAIL WHERE STATUS = '" + status + "'");

            // 결과가 비어있는지 확인
            if (!rs.isBeforeFirst()) {
                System.out.println("상태 '" + status + "'에 해당하는 장비가 없습니다.");
                return false;
            }

            // ResultSet에서 Equipment 객체를 생성하고 출력
            while (rs.next()) {
                Equipment equipment = createEquipmentFromResultSet(rs);
                System.out.println(equipment); // toString() 메서드 호출하여 예쁘게 출력
            }
            return true;
        } catch (SQLException e) {
            System.out.println("상태별 장비 조회 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // - 장비가 특정 상태인지 확인
    public boolean isEquipmentStatus(int equipmentId, String status) {
        try {
            String sql = "SELECT COUNT(*) FROM EQUIPMENT WHERE EQUIPMENT_ID = ? AND STATUS = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, equipmentId);
            pstmt.setString(2, status);
            rs = pstmt.executeQuery();

            if(rs.next() && rs.getInt(1) > 0) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println("장비 상태 확인 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // 담당자 조회 메소드
    public boolean displayManagerList() {
        try {
            rs = stmt.executeQuery("SELECT * FROM V_EMPLOYEE_INFO ORDER BY EMPLOYEE_ID");

            // 결과가 비어있는지 확인
            if (!rs.isBeforeFirst()) {
                System.out.println("등록된 담당자가 없습니다.");
                return false;
            }

            // 더 간결한 형태로 직원 목록 출력 (ID, 이름, 직무만 표시)
            System.out.println();
            System.out.println("━━━━━━━  👥 \033[1;36m담당자 목록\033[0m 👥 ━━━━━━");
            System.out.println();
            System.out.printf("  \033[1;34m%-6s\033[0m\t\t\033[1;34m%-15s\033[0m \033[1;34m%-15s\033[0m\n", "직원ID", "이름", "직무");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━");

            while (rs.next()) {
                int employeeId = rs.getInt("employee_id");
                String name = rs.getString("employee_name");
                String job = rs.getString("job_name");

                System.out.printf("  \033[0;97m%-6d\033[0m\t\t\033[0;97m%-15s\033[0m \033[0;97m%-15s\033[0m\n", employeeId, name, job);
            }
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━");

            return true;
        } catch (SQLException e) {
            System.out.println("담당자 목록 조회 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * =-=-=-=-=-=-=-=-=-=-=-=-= 등록 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=
     **/

    // - 새 장비 정보 저장
    public boolean saveEquipment(Equipment equipment) {
        String sql = "INSERT INTO EQUIPMENT(EQUIPMENT_ID, EQUIPMENT_NAME, MODEL_NAME, MANUFACTURER, SERIAL_NUMBER, STATUS, LAST_UPDATED_DATE) VALUES(?,?,?,?,?,?,?)";
        try {
            // 시퀀스에서 ID 가져오기
            int equipmentId = getNextEquipmentId();

            if (equipmentId == 0) {
                System.out.println("장비 ID를 생성하는 데 실패했습니다.");
                return false;
            }

            // 현재 날짜 생성
            java.util.Date currentDate = new java.util.Date();

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, equipmentId);
            pstmt.setString(2, equipment.getEquipmentName());
            pstmt.setString(3, equipment.getModelName());
            pstmt.setString(4, equipment.getManufacturer());
            pstmt.setString(5, equipment.getSerialNumber());
            pstmt.setString(6, "정상");
            pstmt.setDate(7, new java.sql.Date(currentDate.getTime()));

            int result = pstmt.executeUpdate();
            if (result <= 0) {
                System.out.println("장비 등록에 실패했습니다.");
                return false;
            }

            // 생성된 ID 설정
            equipment.setEquipmentId(equipmentId);
            return true;
        } catch (SQLException e) {
            System.out.println("장비 등록 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    } // end save

    /**
     * =-=-=-=-=-=-=-=-=-=-=-=-= 수정 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=
     **/

    // - 장비 구매날짜 업데이트
    public boolean updatePurchaseDate(int equipmentId, String purchaseDateStr) {
        String sql = "UPDATE EQUIPMENT SET PURCHASE_DATE = ?, LAST_UPDATED_DATE = SYSDATE WHERE EQUIPMENT_ID = ?";
        try {
            java.sql.Date purchaseDate = null;

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate;

            try {
                // 문자열을 날짜 형식으로 변환
                parsedDate = dateFormat.parse(purchaseDateStr);
                purchaseDate = new java.sql.Date(parsedDate.getTime());
            } catch (ParseException e) {
                System.out.println("날짜 형식이 잘못되었습니다: " + e.getMessage());
                e.printStackTrace();
                return false;
            }

            pstmt = conn.prepareStatement(sql);
            pstmt.setDate(1, purchaseDate);
            pstmt.setInt(2, equipmentId);

            int result = pstmt.executeUpdate();
            if (result <= 0) {
                System.out.println("장비 ID " + equipmentId + "에 해당하는 장비가 없어 구매날짜를 업데이트할 수 없습니다.");
                return false;
            }
            return true;
        } catch (SQLException e) {
            System.out.println("구매날짜 업데이트 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    } // end updatePurchaseDate

    // - 장비 구매가격 업데이트
    public boolean updatePurchasePrice(int equipmentId, int purchasePrice) {
        String sql = "UPDATE EQUIPMENT SET PURCHASE_PRICE = ?, LAST_UPDATED_DATE = SYSDATE WHERE EQUIPMENT_ID = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, purchasePrice);
            pstmt.setInt(2, equipmentId);

            int result = pstmt.executeUpdate();
            if (result <= 0) {
                System.out.println("장비 ID " + equipmentId + "에 해당하는 장비가 없어 구매가격을 업데이트할 수 없습니다.");
                return false;
            }
            return true;
        } catch (SQLException e) {
            System.out.println("구매가격 업데이트 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // - 카테고리 업데이트
    public boolean updateEquipmentCategory(int equipmentId, int categoryId) {
        String sql = "UPDATE EQUIPMENT SET CATEGORY_ID = ?, LAST_UPDATED_DATE = SYSDATE WHERE EQUIPMENT_ID = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, categoryId);
            pstmt.setInt(2, equipmentId);

            int result = pstmt.executeUpdate();
            if (result <= 0) {
                System.out.println("장비 ID " + equipmentId + "에 해당하는 장비가 없어 카테고리를 업데이트할 수 없습니다.");
                return false;
            }
            return true;
        } catch (SQLException e) {
            System.out.println("카테고리 업데이트 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // - 담당자 업데이트
    public boolean updateEquipmentManager(int equipmentId, int managerId) {
        String sql = "UPDATE EQUIPMENT SET MANAGER_ID = ?, LAST_UPDATED_DATE = SYSDATE WHERE EQUIPMENT_ID = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, managerId);
            pstmt.setInt(2, equipmentId);

            int result = pstmt.executeUpdate();
            if (result <= 0) {
                System.out.println("장비 ID " + equipmentId + "에 해당하는 장비가 없어 담당자를 업데이트할 수 없습니다.");
                return false;
            }
            return true;
        } catch (SQLException e) {
            System.out.println("담당자 업데이트 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // - 장비 상태 업데이트
    public boolean updateEquipmentStatus(int equipmentId, String status) {
        String sql = "UPDATE EQUIPMENT SET STATUS = ?, LAST_UPDATED_DATE = SYSDATE WHERE EQUIPMENT_ID = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, status);
            pstmt.setInt(2, equipmentId);

            int result = pstmt.executeUpdate();
            if (result <= 0) {
                System.out.println("장비 ID " + equipmentId + "에 해당하는 장비가 없어 상태를 업데이트할 수 없습니다.");
                return false;
            }
            return true;
        } catch (SQLException e) {
            System.out.println("상태 업데이트 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // - 장비 설명 업데이트
    public boolean updateEquipmentDescription(int equipmentId, String description) {
        String sql = "UPDATE EQUIPMENT SET DESCRIPTION = ?, LAST_UPDATED_DATE = SYSDATE WHERE EQUIPMENT_ID = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, description);
            pstmt.setInt(2, equipmentId);

            int result = pstmt.executeUpdate();
            if (result <= 0) {
                System.out.println("장비 ID " + equipmentId + "에 해당하는 장비가 없어 설명을 업데이트할 수 없습니다.");
                return false;
            }
            return true;
        } catch (SQLException e) {
            System.out.println("설명 업데이트 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * =-=-=-=-=-=-=-=-=-=-=-=-= 삭제 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=
     **/

    // - 장비 정보 삭제
    public boolean deleteEquipment(int equipmentId) {
        String sql = "{call PROC_DELETE_EQUIPMENT(?, ?)}";
        try {
            cstmt = conn.prepareCall(sql);
            cstmt.setInt(1, equipmentId);
            cstmt.registerOutParameter(2, Types.NUMERIC); // 삭제 성공 여부

            cstmt.execute();

            int deleteResult = cstmt.getInt(2);

            if (deleteResult == 0) {
                System.out.println("점검 또는 수리 이력이 있는 장비는 삭제할 수 없습니다.");
                return false;
            } else if (deleteResult == 2) {
                System.out.println("장비 등록 후 24시간이 경과하여 삭제할 수 없습니다.");
                return false;
            } else if (deleteResult == -1) {
                System.out.println("장비 삭제 중 오류가 발생했습니다.");
                return false;
            }
            return true;
        } catch (SQLException e) {
            System.out.println("장비 삭제 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // 장비 폐기 메서드 추가
    public boolean disposeEquipment(int equipmentId, String disposeReason) {
        String sql = "{call PROC_DISPOSE_EQUIPMENT(?, ?, ?)}";
        try {
            cstmt = conn.prepareCall(sql);
            cstmt.setInt(1, equipmentId);
            cstmt.setString(2, disposeReason);
            cstmt.registerOutParameter(3, Types.NUMERIC); // 처리 성공 여부

            cstmt.execute();

            int result = cstmt.getInt(3);

            if (result == 0) {
                System.out.println("해당 장비가 폐기예정 상태가 아닙니다. 폐기예정 상태의 장비만 폐기할 수 있습니다.");
                return false;
            } else if (result == -1) {
                System.out.println("존재하지 않는 장비입니다.");
                return false;
            } else if (result == -2) {
                System.out.println("장비 폐기 처리 중 오류가 발생했습니다.");
                return false;
            }

            return true;
        } catch (SQLException e) {
            System.out.println("장비 폐기 처리 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * =-=-=-=-=-=-=-=-=-=-=-=-= 집계 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=
     **/

    // - 상태별 장비 개수 집계
    public boolean countByStatus() {
        try {
            rs = stmt.executeQuery(
                    "SELECT STATUS, COUNT(*) FROM V_EQUIPMENT_DETAIL GROUP BY STATUS ORDER BY COUNT(*) DESC");

            // 결과가 비어있는지 확인
            if (!rs.isBeforeFirst()) {
                System.out.println("등록된 장비가 없어 상태별 집계가 불가능합니다.");
                return false;
            }

            equipmentVIew.countByStatus(rs);
            return true;
        } catch (SQLException e) {
            System.out.println("상태별 장비 개수 집계 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // - 카테고리별 장비 개수 집계
    public boolean countByCategory() {
        try {
            rs = stmt.executeQuery(
                    "SELECT CATEGORY_NAME, COUNT(*) FROM V_EQUIPMENT_DETAIL GROUP BY CATEGORY_NAME ORDER BY COUNT(*) DESC");

            // 결과가 비어있는지 확인
            if (!rs.isBeforeFirst()) {
                System.out.println("등록된 장비가 없어 카테고리별 집계가 불가능합니다.");
                return false;
            }

            equipmentVIew.countByCategory(rs);
            return true;
        } catch (SQLException e) {
            System.out.println("카테고리별 장비 개수 집계 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // - 부서별 장비 개수 집계
    public boolean countByDepartment() {
        try {
            rs = stmt.executeQuery(
                    "SELECT DEPARTMENT_NAME, COUNT(*) FROM V_EQUIPMENT_DETAIL GROUP BY DEPARTMENT_NAME ORDER BY COUNT(*) DESC");

            // 결과가 비어있는지 확인
            if (!rs.isBeforeFirst()) {
                System.out.println("등록된 장비가 없어 부서별 집계가 불가능합니다.");
                return false;
            }

            equipmentVIew.countByDepartment(rs);
            return true;
        } catch (SQLException e) {
            System.out.println("부서별 장비 개수 집계 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // - 카테고리별 구매 가격 합계
    public boolean sumPurchasePriceByCategory() {
        try {
            rs = stmt.executeQuery(
                    "SELECT CATEGORY_NAME, SUM(PURCHASE_PRICE) FROM V_EQUIPMENT_DETAIL GROUP BY CATEGORY_NAME ORDER BY SUM(PURCHASE_PRICE) DESC");

            // 결과가 비어있는지 확인
            if (!rs.isBeforeFirst()) {
                System.out.println("등록된 장비가 없어 카테고리별 구매 가격 합계를 계산할 수 없습니다.");
                return false;
            }

            equipmentVIew.sumPurchasePriceByCategory(rs);
            return true;
        } catch (SQLException e) {
            System.out.println("카테고리별 구매 가격 합계 계산 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // - 최근에 업데이트된 장비 목록
    public boolean getRecentlyUpdatedEquipments() {
        try {
            rs = stmt.executeQuery("SELECT * FROM (SELECT EQUIPMENT_ID, EQUIPMENT_NAME, STATUS, LAST_UPDATED_DATE"
                    + " FROM EQUIPMENT ORDER BY LAST_UPDATED_DATE DESC) WHERE ROWNUM <=5");

            // 결과가 비어있는지 확인
            if (!rs.isBeforeFirst()) {
                System.out.println("등록된 장비가 없어 최근 업데이트 장비를 조회할 수 없습니다.");
                return false;
            }

            equipmentVIew.getRecentlyUpdatedEquipments(rs);
            return true;
        } catch (SQLException e) {
            System.out.println("최근 업데이트 장비 조회 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * =-=-=-=-=-=-=-=-=-=-=-=-= 시퀀스 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=
     **/

    // - 시퀀스로 다음 장비 ID 값 가져오기
    public int getNextEquipmentId() {
        int nextId = 0;
        try {
            rs = stmt.executeQuery("SELECT SEQ_EQUIPMENT_ID.NEXTVAL FROM DUAL");
            if (rs.next()) {
                nextId = rs.getInt(1);
            } else {
                System.out.println("장비 ID 시퀀스 값을 가져오는데 실패했습니다.");
            }
        } catch (SQLException e) {
            System.out.println("장비 ID 시퀀스 조회 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
        }
        return nextId;
    }

    /**
     * =-=-=-=-=-=-=-=-=-=-=-=-= 재사용 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=
     **/

    // ResultSet에서 Equipment 객체를 생성하는 메서드
    private Equipment createEquipmentFromResultSet(ResultSet rs) throws SQLException {
        Equipment equipment = new Equipment();

        // 기본 필드 설정 (수정된 V_EQUIPMENT_DETAIL 뷰에 있는 모든 필드)
        equipment.setEquipmentId(rs.getInt("equipment_id"));
        equipment.setEquipmentName(rs.getString("equipment_name"));
        equipment.setModelName(rs.getString("model_name"));
        equipment.setManufacturer(rs.getString("manufacturer"));
        equipment.setSerialNumber(rs.getString("serial_number"));
        equipment.setPurchaseDate(rs.getDate("purchase_date"));
        equipment.setPurchasePrice(rs.getInt("purchase_price"));
        equipment.setStatus(rs.getString("status"));

        // 뷰에 새로 추가된 필드들을 설정
        equipment.setDescription(rs.getString("description"));
        equipment.setLastUpdatedDate(rs.getDate("last_updated_date"));

        // 추가 정보 설정
        equipment.setCategoryName(rs.getString("category_name"));
        equipment.setDepartmentName(rs.getString("department_name"));
        equipment.setManagerName(rs.getString("manager_name"));
        equipment.setPositionName(rs.getString("position_name"));
        equipment.setJobName(rs.getString("job_name"));

        return equipment;
    }


    // ResultSet에서 Employee 객체를 생성하는 메서드
    private Employee createEmployeeFromResultSet(ResultSet rs) throws SQLException {
        Employee employee = new Employee();

        // 기본 필드 설정
        employee.setEmployeeId(rs.getInt("employee_id"));
        employee.setName(rs.getString("employee_name"));

        // V_EMPLOYEE_INFO 뷰에서 제공하는 정보
        employee.setDepartmentId(rs.getInt("department_id"));
        employee.setPositionId(rs.getInt("position_id"));
        employee.setJobId(rs.getInt("job_id"));

        // 이름 정보 설정 (V_EMPLOYEE_INFO 뷰에 있음)
        employee.setDepartmentName(rs.getString("department_name"));
        employee.setPositionName(rs.getString("position_name"));
        employee.setJobName(rs.getString("job_name"));

        return employee;
    }

} // end class
