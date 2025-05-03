package equipmentAsset.inspection.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import equipmentAsset.common.util.CloseHelper;
import equipmentAsset.common.util.ConnectionSingletonHelper;
import equipmentAsset.inspection.model.entity.InspectionSchedule;
import equipmentAsset.inspection.view.InspectionView;

public class InspectionDAO {
	private final String TABLE_NAME = "INSPECTION_SCHEDULE";
	private InspectionView inspectionView = new InspectionView();

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
	public boolean findAllInspection() {
		try {
			rs = stmt.executeQuery("SELECT S.*, E.EQUIPMENT_NAME" + " FROM INSPECTION_SCHEDULE S"
					+ " JOIN EQUIPMENT E ON S.EQUIPMENT_ID = E.EQUIPMENT_ID");
			inspectionView.findAllInspection(rs);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	} // end findAll

	// - 장비 ID로 일정 조회
	public boolean findByIdInspection(int equipmentId) {
		try {
			rs = stmt.executeQuery("SELECT S.*, E.EQUIPMENT_NAME" + " FROM INSPECTION_SCHEDULE S"
					+ " JOIN EQUIPMENT E ON S.EQUIPMENT_ID = E.EQUIPMENT_ID WHERE S.EQUIPMENT_ID = " + equipmentId);
			inspectionView.findAllInspection(rs);
			return true;
		} catch (SQLException e) {
			return false;
		}
	} // end findById

	// - 일정 ID로 일정 조회
	public boolean findByScheduleId(int scheduleId) {
		try {
			rs = stmt.executeQuery("SELECT S.*, E.EQUIPMENT_NAME" + " FROM INSPECTION_SCHEDULE S"
					+ " JOIN EQUIPMENT E ON S.EQUIPMENT_ID = E.EQUIPMENT_ID WHERE S.SCHEDULE_ID = " + scheduleId);
			inspectionView.findAllInspection(rs);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	} // end findById

	/** =-=-=-=-=-=-=-=-=-=-=-=-= 등록 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/

	// - 새 점검 일정 정보 저장
	public boolean saveInspectionSchedule(InspectionSchedule schedule) {
	    String sql = "INSERT INTO INSPECTION_SCHEDULE(SCHEDULE_ID, EQUIPMENT_ID, INSPECTION_TYPE, INSPECTION_CYCLE, SCHEDULED_DATE, ESTIMATED_DURATION, ESTIMATED_COST, STATUS, DESCRIPTION, LAST_UPDATED_DATE) VALUES(?,?,?,?,?,?,?,?,?,?)";
	    try {
	        // 시퀀스에서 ID 가져오기
	        int scheduleId = getNextInspectionScheduleId();

	        // 현재 날짜 생성
	        java.util.Date currentDate = new java.util.Date();

	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, scheduleId);
	        pstmt.setInt(2, schedule.getEquipmentId());
	        pstmt.setString(3, schedule.getInspectionType());
	        pstmt.setString(4, schedule.getInspectionCycle());
	        pstmt.setDate(5, new java.sql.Date(schedule.getScheduledDate().getTime()));
	        pstmt.setInt(6, schedule.getEstimatedDuration());
	        pstmt.setDouble(7, schedule.getEstimatedCost());
	        pstmt.setString(8, schedule.getStatus());
	        pstmt.setString(9, schedule.getDescription());
	        pstmt.setDate(10, new java.sql.Date(currentDate.getTime()));

	        pstmt.executeUpdate();
	        return true;
	    } catch (SQLException e) {
	        return false;
	    }
	} // end saveInspectionSchedule
	
	/** =-=-=-=-=-=-=-=-=-=-=-=-= 시퀀스 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/
	
	// - 시퀀스로 다음 점검 일정 ID 값 가져오기
	public int getNextInspectionScheduleId() {
	    int nextId = 0;
	    try {
	        rs = stmt.executeQuery("SELECT SEQ_INSPECTION_SCHEDULE_ID.NEXTVAL FROM DUAL");
	        if (rs.next()) {
	            nextId = rs.getInt(1);
	        }
	    } catch (SQLException e) {
	        return 0;
	    }
	    return nextId;
	}

	// - 시퀀스로 다음 점검 결과 ID 값 가져오기
	public int getNextInspectionResultId() {
	    int nextId = 0;
	    try {
	        rs = stmt.executeQuery("SELECT SEQ_INSPECTION_RESULT_ID.NEXTVAL FROM DUAL");
	        if (rs.next()) {
	            nextId = rs.getInt(1);
	        }
	    } catch (SQLException e) {
	        return 0;
	    }
	    return nextId;
	}
	
}
