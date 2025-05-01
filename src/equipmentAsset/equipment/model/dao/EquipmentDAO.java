package equipmentAsset.equipment.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import equipmentAsset.common.util.CloseHelper;
import equipmentAsset.common.util.ConnectionSingletonHelper;
import equipmentAsset.equipment.model.entity.Equipment;
import equipmentAsset.equipment.view.EquipmentView;
import lombok.Getter;

@Getter

public class EquipmentDAO {
	private final String TABLE_NAME = "EQUIPMENT";
	private EquipmentView equipmentVIew = new EquipmentView();

	/** =-=-=-=-=-=-=-=-=-=-=-=-= 연결 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/
	// 연결, 삽입, 삭제, 수정, 검색,......
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
	} // end connect()

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
	} // end close

	/** =-=-=-=-=-=-=-=-=-=-=-=-= 조회 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/

	// - 모든 장비 목록 조회
	public boolean findAllEquipment() {
		try {
			rs = stmt.executeQuery("SELECT * FROM V_EQUIPMENT_DETAIL ORDER BY EQUIPMENT_ID");
			equipmentVIew.displayEquipmentResults(rs);
			return true;
		} catch (SQLException e) {
			return false;
		}
	} // end findAll

	// - ID로 특정 장비 조회
	public boolean findByIdEquipment(int equipmentId) {
		try {
			rs = stmt.executeQuery("SELECT * FROM V_EQUIPMENT_DETAIL WHERE EQUIPMENT_ID IN (" + equipmentId + ")");
			equipmentVIew.displayEquipmentResults(rs);
			return true;
		} catch (SQLException e) {
			return false;
		}
	} // end findById

	// - 특정 카테고리의 장비 조회
	public boolean findByCategoryEquipment(String categoryName) {
		try {
			rs = stmt.executeQuery("SELECT * FROM V_EQUIPMENT_DETAIL WHERE CATEGORY_NAME IN ('" + categoryName + "')");
			equipmentVIew.displayEquipmentResults(rs);
			return true;
		} catch (SQLException e) {
			return false;
		}
	} // end findByCategory

	// - 특정 부서의 장비 조회
	public boolean findByDepartmentEquipment(String departmentName) {
		try {
			rs = stmt.executeQuery(
					"SELECT * FROM V_EQUIPMENT_DETAIL WHERE DEPARTMENT_NAME IN ('" + departmentName + "')");
			equipmentVIew.displayEquipmentResults(rs);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	// - 담당자 명단 출력
	public boolean findAllManager() {
		try {
			rs = stmt.executeQuery("SELECT EMPLOYEE_ID, DEPARTMENT_NAME, JOB_NAME, EMPLOYEE_NAME FROM V_EMPLOYEE_INFO ORDER BY EMPLOYEE_ID");
			equipmentVIew.findAllManager(rs);
			return true;
		} catch (SQLException e) {
			return false;
		}
	} // end findAll
	
	
	// - 특정 상태(정상, 점검필요, 수리중 등)의 장비 조회
	public boolean findByStatusEquipment(String status) {
		try {
			rs = stmt.executeQuery("SELECT * FROM V_EQUIPMENT_DETAIL WHERE STATUS IN ('" + status + "')");
			equipmentVIew.displayEquipmentResults(rs);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	/** =-=-=-=-=-=-=-=-=-=-=-=-= 등록 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/

	// - 새 장비 정보 저장
	public boolean saveEquipment(Equipment equipment) {
		String sql = "INSERT INTO EQUIPMENT(EQUIPMENT_ID, EQUIPMENT_NAME, MODEL_NAME, MANUFACTURER, SERIAL_NUMBER, STATUS, LAST_UPDATED_DATE) VALUES(?,?,?,?,?,?,?)";
		try {
			// 시퀀스에서 ID 가져오기
			int equipmentId = getNextEquipmentId();

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

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	} // end save

	/** =-=-=-=-=-=-=-=-=-=-=-=-= 수정 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/

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
				e.printStackTrace();
			}

			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, purchaseDate);
			pstmt.setInt(2, equipmentId);

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
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

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
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

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
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

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
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

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
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

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	/** =-=-=-=-=-=-=-=-=-=-=-=-= 삭제 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/

	// - 장비 정보 삭제
	public boolean deleteEquipment(int equipmentId) {
		String sql = "DELETE FROM EQUIPMENT WHERE EQUIPMENT_ID = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, equipmentId);

			pstmt.executeUpdate();
			return true;

		} catch (SQLException e) {
			return false;
		}
	} // end deleteEquipment

	/** =-=-=-=-=-=-=-=-=-=-=-=-= 집계 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/

	// - 상태별 장비 개수 집계
	public boolean countByStatus() {
		try {
			rs = stmt.executeQuery(
					"SELECT STATUS, COUNT(*) FROM V_EQUIPMENT_DETAIL GROUP BY STATUS ORDER BY COUNT(*) DESC");
			equipmentVIew.countByStatus(rs);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	// - 카테고리별 장비 개수 집계
	public boolean countByCategory() {
		try {
			rs = stmt.executeQuery(
					"SELECT CATEGORY_NAME, COUNT(*) FROM V_EQUIPMENT_DETAIL GROUP BY CATEGORY_NAME ORDER BY COUNT(*) DESC;");
			equipmentVIew.countByCategory(rs);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	// - 부서별 장비 개수 집계
	public boolean countByDepartment() {
		try {
			rs = stmt.executeQuery(
					"SELECT DEPARTMENT_NAME, COUNT(*) FROM V_EQUIPMENT_DETAIL GROUP BY DEPARTMENT_NAME ORDER BY COUNT(*) DESC");
			equipmentVIew.countByDepartment(rs);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	// - 카테고리별 구매 가격 합계
	public boolean sumPurchasePriceByCategory() {
		try {
			rs = stmt.executeQuery(
					"SELECT CATEGORY_NAME, SUM(PURCHASE_PRICE) FROM V_EQUIPMENT_DETAIL GROUP BY CATEGORY_NAME ORDER BY SUM(PURCHASE_PRICE) DESC");
			equipmentVIew.sumPurchasePriceByCategory(rs);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	// - 최근에 업데이트된 장비 목록
	public boolean getRecentlyUpdatedEquipments() {
		try {
			rs = stmt.executeQuery("SELECT * FROM (SELECT EQUIPMENT_ID, EQUIPMENT_NAME, STATUS, LAST_UPDATED_DATE"
					+ " FROM EQUIPMENT ORDER BY LAST_UPDATED_DATE DESC) WHERE ROWNUM <=5");
			equipmentVIew.getRecentlyUpdatedEquipments(rs);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	/** =-=-=-=-=-=-=-=-=-=-=-=-= 시퀀스 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/

	// - 시퀀스로 다음 장비 ID 값 가져오기
	public int getNextEquipmentId() {
		int nextId = 0;
		try {
			rs = stmt.executeQuery("SELECT SEQ_EQUIPMENT_ID.NEXTVAL FROM DUAL");
			if (rs.next()) {
				nextId = rs.getInt(1);
			}
		} catch (SQLException e) {
			return 0;
		}
		return nextId;
	}

} // end class
