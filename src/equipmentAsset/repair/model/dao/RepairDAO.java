package equipmentAsset.repair.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;

import dbConn.*;
import equipmentAsset.repair.model.entity.RepairRequest;
import equipmentAsset.repair.model.entity.RepairResult;
import equipmentAsset.repair.view.RepairView;
import lombok.Getter;

@Getter
public class RepairDAO extends BaseDAO {
	private final String REQUEST_TABLE = "REPAIR_REQUEST";
	private final String RESULT_TABLE = "REPAIR_RESULT";
	private RepairView repairView = new RepairView();

	/** =-=-=-=-=-=-=-=-=-=-=-=-= 조회 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/

	// - 모든 수리 요청 목록 조회
	public boolean findAllRepairRequests() {
		try {
			rs = stmt.executeQuery("SELECT RR.*, E.EQUIPMENT_NAME FROM REPAIR_REQUEST RR "
					+ "JOIN EQUIPMENT E ON RR.EQUIPMENT_ID = E.EQUIPMENT_ID ORDER BY REQUEST_ID");

			// 결과가 비어있는지 확인
			if (!rs.isBeforeFirst()) {
				System.out.println("등록된 수리 요청이 없습니다.");
				return false;
			}

			repairView.displayRepairRequests(rs);
			return true;
		} catch (SQLException e) {
			System.out.println("수리 요청 조회 중 오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// - 장비 ID로 수리 요청 조회
	public boolean findRepairRequestByEquipmentId(int equipmentId) {
		try {
			rs = stmt.executeQuery("SELECT RR.*, E.EQUIPMENT_NAME FROM REPAIR_REQUEST RR "
					+ "JOIN EQUIPMENT E ON RR.EQUIPMENT_ID = E.EQUIPMENT_ID " + "WHERE RR.EQUIPMENT_ID = " + equipmentId
					+ " ORDER BY REQUEST_ID");

			// 결과가 비어있는지 확인
			if (!rs.isBeforeFirst()) {
				System.out.println("장비 ID " + equipmentId + "에 해당하는 수리 요청이 없습니다.");
				return false;
			}

			repairView.displayRepairRequests(rs);
			return true;
		} catch (SQLException e) {
			System.out.println("장비 ID로 수리 요청 조회 중 오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// - 요청 ID로 수리 요청 조회
	public boolean findRepairRequestById(int requestId) {
		try {
			rs = stmt.executeQuery("SELECT RR.*, E.EQUIPMENT_NAME FROM REPAIR_REQUEST RR "
					+ "JOIN EQUIPMENT E ON RR.EQUIPMENT_ID = E.EQUIPMENT_ID " + "WHERE RR.REQUEST_ID = " + requestId);

			// 결과가 비어있는지 확인
			if (!rs.isBeforeFirst()) {
				System.out.println("요청 ID " + requestId + "에 해당하는 수리 요청이 없습니다.");
				return false;
			}

			repairView.displayRepairRequests(rs);
			return true;
		} catch (SQLException e) {
			System.out.println("요청 ID로 수리 요청 조회 중 오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// - 상태별 수리 요청 조회
	public boolean findRepairRequestByStatus(String status) {
		try {
			String sql = "SELECT RR.*, E.EQUIPMENT_NAME FROM REPAIR_REQUEST RR "
					+ "JOIN EQUIPMENT E ON RR.EQUIPMENT_ID = E.EQUIPMENT_ID "
					+ "WHERE RR.STATUS = ? ORDER BY REQUEST_ID";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, status);
			rs = pstmt.executeQuery();

			// 결과가 비어있는지 확인
			if (!rs.isBeforeFirst()) {
				System.out.println(status + " 상태의 수리 요청이 없습니다.");
				return false;
			}

			repairView.displayRepairRequests(rs);
			return true;
		} catch (SQLException e) {
			System.out.println("상태별 수리 요청 조회 중 오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// - 모든 수리 결과 조회
	public boolean findAllRepairResults() {
		try {
			rs = stmt.executeQuery("SELECT * FROM V_REPAIR_DETAIL WHERE RESULT_ID IS NOT NULL ORDER BY RESULT_ID");

			// 결과가 비어있는지 확인
			if (!rs.isBeforeFirst()) {
				System.out.println("등록된 수리 결과가 없습니다.");
				return false;
			}

			repairView.displayRepairResults(rs);
			return true;
		} catch (SQLException e) {
			System.out.println("수리 결과 조회 중 오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// - 요청 ID로 수리 결과 조회
	public boolean findRepairResultByRequestId(int requestId) {
		try {
			rs = stmt.executeQuery("SELECT * FROM V_REPAIR_DETAIL WHERE REQUEST_ID = " + requestId);

			// 결과가 비어있는지 확인
			if (!rs.isBeforeFirst()) {
				System.out.println("요청 ID " + requestId + "에 해당하는 수리 결과가 없습니다.");
				return false;
			}

			repairView.displayRepairResults(rs);
			return true;
		} catch (SQLException e) {
			System.out.println("요청 ID로 수리 결과 조회 중 오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// - 장비 ID로 수리 결과 조회
	public boolean findRepairResultByEquipmentId(int equipmentId) {
		try {
			rs = stmt.executeQuery("SELECT * FROM V_REPAIR_DETAIL WHERE EQUIPMENT_ID = " + equipmentId
					+ " AND RESULT_ID IS NOT NULL ORDER BY RESULT_ID");

			// 결과가 비어있는지 확인
			if (!rs.isBeforeFirst()) {
				System.out.println("장비 ID " + equipmentId + "에 해당하는 수리 결과가 없습니다.");
				return false;
			}

			repairView.displayRepairResults(rs);
			return true;
		} catch (SQLException e) {
			System.out.println("장비 ID로 수리 결과 조회 중 오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// - 결과별 수리 결과 조회
	public boolean findRepairResultByType(String resultType) {
		try {
			String sql = "SELECT * FROM V_REPAIR_DETAIL WHERE REPAIR_RESULT = ? AND RESULT_ID IS NOT NULL ORDER BY RESULT_ID";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, resultType);
			rs = pstmt.executeQuery();

			// 결과가 비어있는지 확인
			if (!rs.isBeforeFirst()) {
				System.out.println(resultType + " 유형의 수리 결과가 없습니다.");
				return false;
			}

			repairView.displayRepairResults(rs);
			return true;
		} catch (SQLException e) {
			System.out.println("결과 유형별 수리 결과 조회 중 오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// - 수리필요 상태 장비 조회
	public boolean findRepairableEquipment() {
		try {
			rs = stmt.executeQuery("SELECT * FROM EQUIPMENT WHERE STATUS = '수리필요' ORDER BY EQUIPMENT_ID");

			// 결과가 비어있는지 확인
			if (!rs.isBeforeFirst()) {
				System.out.println("수리가 필요한 장비가 없습니다.");
				return false;
			}

			System.out.printf("%-7s %-25s\t%-20s\t%-18s\t%-17s\t%-12s\t%-12s\n", "장비ID", "장비명", "모델명", "제조사", "시리얼번호",
					"구매일", "구매가격");
			System.out.println(
					"--------------------------------------------------------------------------------------------------------------");

			while (rs.next()) {
				int equipmentId = rs.getInt("equipment_id");
				String equipmentName = rs.getString("equipment_name");
				String modelName = rs.getString("model_name");
				String manufacturer = rs.getString("manufacturer");
				String serialNumber = rs.getString("serial_number");
				java.sql.Date purchaseDate = rs.getDate("purchase_date");
				int purchasePrice = rs.getInt("purchase_price");

				System.out.printf("%-7d %-25s\t%-20s\t%-18s\t%-17s\t%-8s\t%6d만원\n", equipmentId, equipmentName,
						modelName, manufacturer, serialNumber, purchaseDate, purchasePrice / 10000);
			}
			return true;
		} catch (SQLException e) {
			System.out.println("수리 필요 장비 조회 중 오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// - 예정 상태의 수리 요청 조회
	public boolean findPendingRepairRequests() {
		try {
			rs = stmt.executeQuery("SELECT RR.*, E.EQUIPMENT_NAME FROM REPAIR_REQUEST RR "
					+ "JOIN EQUIPMENT E ON RR.EQUIPMENT_ID = E.EQUIPMENT_ID "
					+ "WHERE RR.STATUS = '예정' ORDER BY REQUEST_ID");

			// 결과가 비어있는지 확인
			if (!rs.isBeforeFirst()) {
				System.out.println("결과 등록이 필요한 수리 요청이 없습니다.");
				return false;
			}

			repairView.displayRepairRequests(rs);
			return true;
		} catch (SQLException e) {
			System.out.println("예정 상태 수리 요청 조회 중 오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	/** =-=-=-=-=-=-=-=-=-=-=-=-= 등록 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/

	// - 새 수리 요청 정보 저장 (프로시저 사용)
	public int saveRepairRequest(RepairRequest request) {
		String sql = "{call PROC_SAVE_REPAIR_REQUEST(?, ?, ?, ?)}";
		int requestId = 0;

		try {
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, request.getEquipmentId());
			cstmt.setDate(2, new java.sql.Date(request.getRequestDate().getTime()));
			cstmt.setString(3, request.getFailureSymptom());
			cstmt.registerOutParameter(4, Types.NUMERIC); // 결과 ID

			cstmt.execute();

			// 생성된 요청 ID 반환
			requestId = cstmt.getInt(4);

			return requestId;
		} catch (SQLException e) {
			System.out.println("수리 요청 저장 중 오류 발생: " + e.getMessage());
			e.printStackTrace();
			return 0;
		}
	}

	// - 수리 결과 정보 저장 (프로시저 사용)
	public int saveRepairResult(RepairResult result) {
		String sql = "{call PROC_SAVE_REPAIR_RESULT(?, ?, ?, ?, ?)}";
		int resultId = 0;

		try {
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, result.getRequestId());
			cstmt.setString(2, result.getRepairContent());
			cstmt.setInt(3, result.getRepairCost());
			cstmt.setString(4, result.getResult());
			cstmt.registerOutParameter(5, Types.NUMERIC); // 결과 ID

			cstmt.execute();

			// 생성된 결과 ID 반환
			resultId = cstmt.getInt(5);

			return resultId;
		} catch (SQLException e) {
			System.out.println("수리 결과 저장 중 오류 발생: " + e.getMessage());
			e.printStackTrace();
			return 0;
		}
	}

	/** =-=-=-=-=-=-=-=-=-=-=-=-= 수정 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/

	// - 수리 요청 일자 업데이트
	public boolean updateRequestDate(int requestId, Date requestDate) {
		String sql = "{call PROC_UPDATE_REQUEST_DATE(?, ?)}";
		try {
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, requestId);
			cstmt.setDate(2, new java.sql.Date(requestDate.getTime()));

			cstmt.execute();
			return true;
		} catch (SQLException e) {
			System.out.println("요청 일자 업데이트 중 오류 발생: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// - 수리 요청 증상 업데이트
	public boolean updateFailureSymptom(int requestId, String failureSymptom) {
		String sql = "{call PROC_UPDATE_FAILURE_SYMPTOM(?, ?)}";
		try {
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, requestId);
			cstmt.setString(2, failureSymptom);

			cstmt.execute();
			return true;
		} catch (SQLException e) {
			System.out.println("고장 증상 업데이트 중 오류 발생: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	// - 수리 요청 상태 업데이트
	public boolean updateRequestStatus(int requestId, String status) {
		String sql = "{call PROC_UPDATE_REQUEST_STATUS(?, ?)}";
		try {
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, requestId);
			cstmt.setString(2, status);

			cstmt.execute();
			return true;
		} catch (SQLException e) {
			System.out.println("요청 상태 업데이트 중 오류 발생: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// - 수리 내용 업데이트
	public boolean updateRepairContent(int resultId, String repairContent) {
		String sql = "{call PROC_UPDATE_REPAIR_CONTENT(?, ?)}";
		try {
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, resultId);
			cstmt.setString(2, repairContent);

			cstmt.execute();
			return true;
		} catch (SQLException e) {
			System.out.println("수리 내용 업데이트 중 오류 발생: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// - 수리 비용 업데이트
	public boolean updateRepairCost(int resultId, int repairCost) {
		String sql = "{call PROC_UPDATE_REPAIR_COST(?, ?)}";
		try {
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, resultId);
			cstmt.setInt(2, repairCost);

			cstmt.execute();
			return true;
		} catch (SQLException e) {
			System.out.println("수리 비용 업데이트 중 오류 발생: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// - 수리 결과 유형 업데이트
	public boolean updateRepairResultType(int resultId, String repairResult) {
		String sql = "{call PROC_UPDATE_REPAIR_RESULT_TYPE(?, ?)}";
		try {
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, resultId);
			cstmt.setString(2, repairResult);

			cstmt.execute();
			return true;
		} catch (SQLException e) {
			System.out.println("결과 유형 업데이트 중 오류 발생: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	/** =-=-=-=-=-=-=-=-=-=-=-=-= 삭제 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/

	// - 수리 요청 삭제 (24시간 제한)
	public boolean deleteRepairRequest(int requestId) {
	    String sql = "{call PROC_DELETE_REPAIR_REQUEST(?, ?)}";
	    try {
	        cstmt = conn.prepareCall(sql);
	        cstmt.setInt(1, requestId);
	        cstmt.registerOutParameter(2, Types.NUMERIC); // 삭제 성공 여부
	        
	        cstmt.execute();
	        
	        int deleteResult = cstmt.getInt(2);
	        
	        if (deleteResult == 0) {
	            System.out.println("이미 결과가 등록된 수리 요청은 삭제할 수 없습니다.");
	            return false;
	        } else if (deleteResult == 2) {
	            System.out.println("요청 등록 후 24시간이 경과하여 삭제할 수 없습니다.");
	            return false;
	        } else if (deleteResult == -1) {
	            System.out.println("수리 요청 삭제 중 오류가 발생했습니다.");
	            return false;
	        }
	        
	        return true;
	    } catch (SQLException e) {
	        System.out.println("수리 요청 삭제 중 오류 발생: " + e.getMessage());
	        e.printStackTrace();
	        return false;
	    }
	}

	// - 수리 결과 삭제 (24시간 제한)
	public boolean deleteRepairResult(int resultId) {
	    String sql = "{call PROC_DELETE_REPAIR_RESULT(?, ?)}";
	    try {
	        cstmt = conn.prepareCall(sql);
	        cstmt.setInt(1, resultId);
	        cstmt.registerOutParameter(2, Types.NUMERIC); // 삭제 성공 여부
	        
	        cstmt.execute();
	        
	        int deleteResult = cstmt.getInt(2);
	        
	        if (deleteResult == 0) {
	            System.out.println("결과 등록 후 24시간이 경과하여 삭제할 수 없습니다.");
	            return false;
	        } else if (deleteResult == -1) {
	            System.out.println("수리 결과 삭제 중 오류가 발생했습니다.");
	            return false;
	        }
	        
	        return true;
	    } catch (SQLException e) {
	        System.out.println("수리 결과 삭제 중 오류 발생: " + e.getMessage());
	        e.printStackTrace();
	        return false;
	    }
	}

	/** =-=-=-=-=-=-=-=-=-=-=-=-= 시퀀스 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/

	// - 시퀀스로 다음 수리 요청 ID 값 가져오기
	public int getNextRepairRequestId() {
		int nextId = 0;
		try {
			rs = stmt.executeQuery("SELECT SEQ_REPAIR_REQUEST_ID.NEXTVAL FROM DUAL");
			if (rs.next()) {
				nextId = rs.getInt(1);
			} else {
				System.out.println("수리 요청 ID 시퀀스 값을 가져오는데 실패했습니다.");
			}
		} catch (SQLException e) {
			System.out.println("수리 요청 ID 시퀀스 조회 중 오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
		}
		return nextId;
	}

	// - 시퀀스로 다음 수리 결과 ID 값 가져오기
	public int getNextRepairResultId() {
		int nextId = 0;
		try {
			rs = stmt.executeQuery("SELECT SEQ_REPAIR_RESULT_ID.NEXTVAL FROM DUAL");
			if (rs.next()) {
				nextId = rs.getInt(1);
			} else {
				System.out.println("수리 결과 ID 시퀀스 값을 가져오는데 실패했습니다.");
			}
		} catch (SQLException e) {
			System.out.println("수리 결과 ID 시퀀스 조회 중 오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
		}
		return nextId;
	}
}