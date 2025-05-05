package equipmentAsset.inspection.model.dao;

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
import equipmentAsset.inspection.model.entity.InspectionResult;
import equipmentAsset.inspection.model.entity.InspectionSchedule;
import equipmentAsset.inspection.view.InspectionView;
import lombok.Getter;

@Getter
public class InspectionDAO extends BaseDAO {
	private final String TABLE_NAME = "INSPECTION_SCHEDULE";
	private InspectionView inspectionView = new InspectionView();

	/** =-=-=-=-=-=-=-=-=-=-=-=-= 조회 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/

	// - 모든 일정 목록 조회
	public boolean findAllInspection() {
		try {
			rs = stmt.executeQuery("SELECT S.*, E.EQUIPMENT_NAME" + " FROM INSPECTION_SCHEDULE S"
					+ " JOIN EQUIPMENT E ON S.EQUIPMENT_ID = E.EQUIPMENT_ID");

			// 결과가 비어있는지 확인
			if (!rs.isBeforeFirst()) {
				System.out.println("등록된 점검 일정이 없습니다.");
				return false;
			}

			inspectionView.findAllInspection(rs);
			return true;
		} catch (SQLException e) {
			System.out.println("점검 일정 조회 중 오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// - 장비 ID로 일정 조회
	public boolean findByIdInspection(int equipmentId) {
		try {
			rs = stmt.executeQuery("SELECT S.*, E.EQUIPMENT_NAME" + " FROM INSPECTION_SCHEDULE S"
					+ " JOIN EQUIPMENT E ON S.EQUIPMENT_ID = E.EQUIPMENT_ID WHERE S.EQUIPMENT_ID = " + equipmentId);

			// 결과가 비어있는지 확인
			if (!rs.isBeforeFirst()) {
				System.out.println("장비 ID " + equipmentId + "에 해당하는 점검 일정이 없습니다.");
				return false;
			}

			inspectionView.findAllInspection(rs);
			return true;
		} catch (SQLException e) {
			System.out.println("장비 ID로 점검 일정 조회 중 오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// - 일정 ID로 일정 조회
	public boolean findByScheduleId(int scheduleId) {
		try {
			rs = stmt.executeQuery("SELECT S.*, E.EQUIPMENT_NAME" + " FROM INSPECTION_SCHEDULE S"
					+ " JOIN EQUIPMENT E ON S.EQUIPMENT_ID = E.EQUIPMENT_ID WHERE S.SCHEDULE_ID = " + scheduleId);

			// 결과가 비어있는지 확인
			if (!rs.isBeforeFirst()) {
				System.out.println("일정 ID " + scheduleId + "에 해당하는 점검 일정이 없습니다.");
				return false;
			}

			inspectionView.findAllInspection(rs);
			return true;
		} catch (SQLException e) {
			System.out.println("일정 ID로 점검 일정 조회 중 오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// 점검 상태별 조회 메소드
	public boolean findInspectionByStatus(String status) {
		try {
			String sql = "SELECT S.*, E.EQUIPMENT_NAME" + " FROM INSPECTION_SCHEDULE S"
					+ " JOIN EQUIPMENT E ON S.EQUIPMENT_ID = E.EQUIPMENT_ID" + " WHERE S.STATUS = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, status);
			rs = pstmt.executeQuery();

			// ResultSet이 비어 있는지 확인
			if (!rs.isBeforeFirst()) {
				System.out.println(status + " 상태의 점검 일정이 없습니다.");
				return false;
			}

			inspectionView.findAllInspection(rs);
			return true;
		} catch (SQLException e) {
			System.out.println("상태별 점검 일정 조회 중 오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// 점검 주기별 조회 메소드
	public boolean findInspectionByCycle(String cycle) {
		try {
			String sql = "SELECT S.*, E.EQUIPMENT_NAME" + " FROM INSPECTION_SCHEDULE S"
					+ " JOIN EQUIPMENT E ON S.EQUIPMENT_ID = E.EQUIPMENT_ID" + " WHERE S.INSPECTION_CYCLE = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cycle);
			rs = pstmt.executeQuery();

			// ResultSet이 비어 있는지 확인
			if (!rs.isBeforeFirst()) {
				System.out.println(cycle + " 주기의 점검 일정이 없습니다.");
				return false;
			}

			inspectionView.findAllInspection(rs);
			return true;
		} catch (SQLException e) {
			System.out.println("주기별 점검 일정 조회 중 오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// 점검 유형별 조회 메소드
	public boolean findInspectionByType(String type) {
		try {
			String sql = "SELECT S.*, E.EQUIPMENT_NAME" + " FROM INSPECTION_SCHEDULE S"
					+ " JOIN EQUIPMENT E ON S.EQUIPMENT_ID = E.EQUIPMENT_ID" + " WHERE S.INSPECTION_TYPE = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, type);
			rs = pstmt.executeQuery();

			// ResultSet이 비어 있는지 확인
			if (!rs.isBeforeFirst()) {
				System.out.println(type + " 유형의 점검 일정이 없습니다.");
				return false;
			}

			inspectionView.findAllInspection(rs);
			return true;
		} catch (SQLException e) {
			System.out.println("유형별 점검 일정 조회 중 오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// 점검 상태별 조회 - 기타 옵션 추가
	public boolean findInspectionByStatusOthers() {
		try {
			String sql = "SELECT S.*, E.EQUIPMENT_NAME" + " FROM INSPECTION_SCHEDULE S"
					+ " JOIN EQUIPMENT E ON S.EQUIPMENT_ID = E.EQUIPMENT_ID" + " WHERE S.STATUS NOT IN ('예정', '완료')";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// ResultSet이 비어 있는지 확인
			if (!rs.isBeforeFirst()) {
				System.out.println("기타 상태의 점검 일정이 없습니다.");
				return false;
			}

			inspectionView.findAllInspection(rs);
			return true;
		} catch (SQLException e) {
			System.out.println("기타 상태별 점검 일정 조회 중 오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// 점검 주기별 조회 - 기타 옵션 추가
	public boolean findInspectionByCycleOthers() {
		try {
			String sql = "SELECT S.*, E.EQUIPMENT_NAME" + " FROM INSPECTION_SCHEDULE S"
					+ " JOIN EQUIPMENT E ON S.EQUIPMENT_ID = E.EQUIPMENT_ID"
					+ " WHERE S.INSPECTION_CYCLE NOT IN ('월간', '분기', '반기', '연간', '비정기')";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// ResultSet이 비어 있는지 확인
			if (!rs.isBeforeFirst()) {
				System.out.println("기타 주기의 점검 일정이 없습니다.");
				return false;
			}

			inspectionView.findAllInspection(rs);
			return true;
		} catch (SQLException e) {
			System.out.println("기타 주기별 점검 일정 조회 중 오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// 점검 유형별 조회 - 기타 옵션 추가
	public boolean findInspectionByTypeOthers() {
		try {
			String sql = "SELECT S.*, E.EQUIPMENT_NAME" + " FROM INSPECTION_SCHEDULE S"
					+ " JOIN EQUIPMENT E ON S.EQUIPMENT_ID = E.EQUIPMENT_ID"
					+ " WHERE S.INSPECTION_TYPE NOT IN ('초기점검', '정기점검', '긴급점검')";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// ResultSet이 비어 있는지 확인
			if (!rs.isBeforeFirst()) {
				System.out.println("기타 유형의 점검 일정이 없습니다.");
				return false;
			}

			inspectionView.findAllInspection(rs);
			return true;
		} catch (SQLException e) {
			System.out.println("기타 유형별 점검 일정 조회 중 오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// - 모든 점검 결과 조회
	public boolean findAllInspectionResult() {
		try {
			rs = stmt.executeQuery("SELECT * FROM V_INSPECTION_RESULT ORDER BY RESULT_ID");

			// 결과가 비어있는지 확인
			if (!rs.isBeforeFirst()) {
				System.out.println("등록된 점검 결과가 없습니다.");
				return false;
			}

			inspectionView.findAllInspectionResult(rs);
			return true;
		} catch (SQLException e) {
			System.out.println("점검 결과 조회 중 오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// - 장비 ID로 점검 결과 조회
	public boolean findInspectionResultByEquipmentId(int equipmentId) {
		try {
			rs = stmt.executeQuery(
					"SELECT * FROM V_INSPECTION_RESULT WHERE EQUIPMENT_ID = " + equipmentId + " ORDER BY RESULT_ID");

			// 결과가 비어있는지 확인
			if (!rs.isBeforeFirst()) {
				System.out.println("장비 ID " + equipmentId + "에 해당하는 점검 결과가 없습니다.");
				return false;
			}

			inspectionView.findAllInspectionResult(rs);
			return true;
		} catch (SQLException e) {
			System.out.println("장비 ID로 점검 결과 조회 중 오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// - 결과 ID로 점검 결과 조회
	public boolean findInspectionResultById(int resultId) {
		try {
			rs = stmt.executeQuery("SELECT * FROM V_INSPECTION_RESULT WHERE RESULT_ID = " + resultId);

			// 결과가 비어있는지 확인
			if (!rs.isBeforeFirst()) {
				System.out.println("결과 ID " + resultId + "에 해당하는 점검 결과가 없습니다.");
				return false;
			}

			inspectionView.findAllInspectionResult(rs);
			return true;
		} catch (SQLException e) {
			System.out.println("결과 ID로 점검 결과 조회 중 오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// - 결과 유형별 점검 결과 조회
	public boolean findInspectionResultByType(String resultType) {
		try {
			String sql = "SELECT * FROM V_INSPECTION_RESULT WHERE INSPECTION_RESULT = ? ORDER BY RESULT_ID";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, resultType);
			rs = pstmt.executeQuery();

			// 결과가 비어있는지 확인
			if (!rs.isBeforeFirst()) {
				System.out.println(resultType + " 유형의 점검 결과가 없습니다.");
				return false;
			}

			inspectionView.findAllInspectionResult(rs);
			return true;
		} catch (SQLException e) {
			System.out.println("결과 유형별 점검 결과 조회 중 오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	/** =-=-=-=-=-=-=-=-=-=-=-=-= 등록 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/

	// - 새 점검 일정 정보 저장
	public boolean saveInspectionSchedule(InspectionSchedule schedule) {
		String sql = "INSERT INTO INSPECTION_SCHEDULE(SCHEDULE_ID, EQUIPMENT_ID, INSPECTION_TYPE, INSPECTION_CYCLE, SCHEDULED_DATE, STATUS, DESCRIPTION, LAST_UPDATED_DATE) VALUES(?,?,?,?,?,?,?,?)";
		try {
			// 시퀀스에서 ID 가져오기
			int scheduleId = getNextInspectionScheduleId();

			if (scheduleId == 0) {
				System.out.println("일정 ID를 생성하는 데 실패했습니다.");
				return false;
			}

			// 현재 날짜 생성
			java.util.Date currentDate = new java.util.Date();

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, scheduleId);
			pstmt.setInt(2, schedule.getEquipmentId());
			pstmt.setString(3, schedule.getInspectionType());
			pstmt.setString(4, schedule.getInspectionCycle());
			pstmt.setDate(5, new java.sql.Date(schedule.getScheduledDate().getTime()));
			pstmt.setString(6, schedule.getStatus());
			pstmt.setString(7, schedule.getDescription());
			pstmt.setDate(8, new java.sql.Date(currentDate.getTime()));

			int result = pstmt.executeUpdate();
			if (result <= 0) {
				System.out.println("점검 일정 등록에 실패했습니다.");
				return false;
			}
			return true;
		} catch (SQLException e) {
			System.out.println("점검 일정 등록 중 오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// 점검 결과 등록 - 프로시저 사용
	public int saveInspectionResult(InspectionResult result) {
		String sql = "{call PROC_SAVE_INSPECTION_RESULT(?, ?, ?, ?, ?, ?)}";
		int resultId = 0;

		try {
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, result.getScheduleId());
			cstmt.setDate(2, new java.sql.Date(result.getInspectionDate().getTime()));
			cstmt.setString(3, result.getInspectionResult());
			cstmt.setString(4, result.getInspectionContent());
			cstmt.setString(5, result.getSpecialNote());
			cstmt.registerOutParameter(6, Types.NUMERIC); // 결과 ID

			cstmt.execute();

			// 생성된 결과 ID 반환
			resultId = cstmt.getInt(6);

			return resultId;
		} catch (SQLException e) {
			System.out.println("점검 결과 저장 중 오류 발생: " + e.getMessage());
			e.printStackTrace();
			return 0;
		}
	}

	/** =-=-=-=-=-=-=-=-=-=-=-=-= 수정 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/

	// 점검 유형 수정
	public boolean updateInspectionType(int scheduleId, String inspectionType) {
		String sql = "UPDATE INSPECTION_SCHEDULE SET inspection_type = ?, last_updated_date = SYSDATE WHERE schedule_id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, inspectionType);
			pstmt.setInt(2, scheduleId);

			int result = pstmt.executeUpdate();
			if (result <= 0) {
				System.out.println("일정 ID " + scheduleId + "에 해당하는 점검 일정이 없어 점검 유형을 업데이트할 수 없습니다.");
				return false;
			}
			return true;
		} catch (SQLException e) {
			System.out.println("점검 유형 업데이트 중 오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// 점검 주기 수정
	public boolean updateInspectionCycle(int scheduleId, String inspectionCycle) {
		String sql = "UPDATE INSPECTION_SCHEDULE SET inspection_cycle = ?, last_updated_date = SYSDATE WHERE schedule_id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, inspectionCycle);
			pstmt.setInt(2, scheduleId);

			int result = pstmt.executeUpdate();
			if (result <= 0) {
				System.out.println("일정 ID " + scheduleId + "에 해당하는 점검 일정이 없어 점검 주기를 업데이트할 수 없습니다.");
				return false;
			}
			return true;
		} catch (SQLException e) {
			System.out.println("점검 주기 업데이트 중 오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// 예정 일자 수정
	public boolean updateScheduledDate(int scheduleId, String scheduledDateStr) {
		String sql = "UPDATE INSPECTION_SCHEDULE SET scheduled_date = ?, last_updated_date = SYSDATE WHERE schedule_id = ?";
		try {
			java.sql.Date scheduledDate = null;

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date parsedDate = dateFormat.parse(scheduledDateStr);
			scheduledDate = new java.sql.Date(parsedDate.getTime());

			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, scheduledDate);
			pstmt.setInt(2, scheduleId);

			int result = pstmt.executeUpdate();
			if (result <= 0) {
				System.out.println("일정 ID " + scheduleId + "에 해당하는 점검 일정이 없어 예정 일자를 업데이트할 수 없습니다.");
				return false;
			}
			return true;
		} catch (Exception e) {
			System.out.println("예정 일자 업데이트 중 오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// 상태 수정
	public boolean updateStatus(int scheduleId, String status) {
		String sql = "UPDATE INSPECTION_SCHEDULE SET status = ?, last_updated_date = SYSDATE WHERE schedule_id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, status);
			pstmt.setInt(2, scheduleId);

			int result = pstmt.executeUpdate();
			if (result <= 0) {
				System.out.println("일정 ID " + scheduleId + "에 해당하는 점검 일정이 없어 상태를 업데이트할 수 없습니다.");
				return false;
			}
			return true;
		} catch (SQLException e) {
			System.out.println("상태 업데이트 중 오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// 설명 수정
	public boolean updateDescription(int scheduleId, String description) {
		String sql = "UPDATE INSPECTION_SCHEDULE SET description = ?, last_updated_date = SYSDATE WHERE schedule_id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, description);
			pstmt.setInt(2, scheduleId);

			int result = pstmt.executeUpdate();
			if (result <= 0) {
				System.out.println("일정 ID " + scheduleId + "에 해당하는 점검 일정이 없어 설명을 업데이트할 수 없습니다.");
				return false;
			}
			return true;
		} catch (SQLException e) {
			System.out.println("설명 업데이트 중 오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// 점검 일자 수정
	public boolean updateInspectionDate(int resultId, Date inspectionDate) {
		String sql = "{call PROC_UPDATE_INSPECTION_DATE(?, ?)}";

		try {
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, resultId);
			cstmt.setDate(2, new java.sql.Date(inspectionDate.getTime()));

			cstmt.execute();
			return true;
		} catch (SQLException e) {
			System.out.println("점검 일자 수정 중 오류 발생: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// 점검 결과 유형 수정
	public boolean updateInspectionResultType(int resultId, String inspectionResult) {
		String sql = "{call PROC_UPD_INSP_RESULT_TYPE(?, ?)}";

		try {
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, resultId);
			cstmt.setString(2, inspectionResult);

			cstmt.execute();
			return true;
		} catch (SQLException e) {
			System.out.println("점검 결과 유형 수정 중 오류 발생: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// 점검 내용 수정
	public boolean updateInspectionContent(int resultId, String inspectionContent) {
		String sql = "{call PROC_UPDATE_INSPECTION_CONTENT(?, ?)}";

		try {
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, resultId);
			cstmt.setString(2, inspectionContent);

			cstmt.execute();
			return true;
		} catch (SQLException e) {
			System.out.println("점검 내용 수정 중 오류 발생: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// 특이사항 수정
	public boolean updateSpecialNote(int resultId, String specialNote) {
		String sql = "{call PROC_UPDATE_SPECIAL_NOTE(?, ?)}";

		try {
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, resultId);
			cstmt.setString(2, specialNote);

			cstmt.execute();
			return true;
		} catch (SQLException e) {
			System.out.println("특이사항 수정 중 오류 발생: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	/** =-=-=-=-=-=-=-=-=-=-=-=-= 삭제 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/

	// 점검 일정 삭제 - 프로시저 사용
	public boolean deleteInspectionSchedule(int scheduleId) {
	    String sql = "{call PROC_DEL_INSP_SCHEDULE(?, ?)}";
	    try {
	        cstmt = conn.prepareCall(sql);
	        cstmt.setInt(1, scheduleId);
	        cstmt.registerOutParameter(2, Types.NUMERIC); // 삭제 성공 여부
	        
	        cstmt.execute();
	        
	        int deleteResult = cstmt.getInt(2);
	        
	        if (deleteResult == 0) {
	            System.out.println("이미 결과가 등록된 점검 일정은 삭제할 수 없습니다.");
	            return false;
	        } else if (deleteResult == 2) {
	            System.out.println("일정 등록 후 24시간이 경과하여 삭제할 수 없습니다.");
	            return false;
	        } else if (deleteResult == -1) {
	            System.out.println("점검 일정 삭제 중 오류가 발생했습니다.");
	            return false;
	        }
	        
	        return true;
	    } catch (SQLException e) {
	        System.out.println("점검 일정 삭제 중 오류 발생: " + e.getMessage());
	        e.printStackTrace();
	        return false;
	    }
	}

	// 점검 결과 삭제 - 프로시저 사용
	public boolean deleteInspectionResult(int resultId) {
	    String sql = "{call PROC_DELETE_INSPECTION_RESULT(?, ?)}";
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
	            System.out.println("점검 결과 삭제 중 오류가 발생했습니다.");
	            return false;
	        }
	        
	        return true;
	    } catch (SQLException e) {
	        System.out.println("점검 결과 삭제 중 오류 발생: " + e.getMessage());
	        e.printStackTrace();
	        return false;
	    }
	}

	/** =-=-=-=-=-=-=-=-=-=-=-=-= 시퀀스 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/

	// - 시퀀스로 다음 점검 일정 ID 값 가져오기
	public int getNextInspectionScheduleId() {
		int nextId = 0;
		try {
			rs = stmt.executeQuery("SELECT SEQ_INSPECTION_SCHEDULE_ID.NEXTVAL FROM DUAL");
			if (rs.next()) {
				nextId = rs.getInt(1);
			} else {
				System.out.println("점검 일정 ID 시퀀스 값을 가져오는데 실패했습니다.");
			}
		} catch (SQLException e) {
			System.out.println("점검 일정 ID 시퀀스 조회 중 오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
		}
		return nextId;
	}
}