package equipmentAsset.inspection.model.service;

import java.sql.SQLException;

import equipmentAsset.inspection.model.dao.InspectionDAO;
import equipmentAsset.inspection.model.entity.InspectionSchedule;

/**
 * 서비스 클래스에서는 트랜잭션 처리와 db 연결만 담당한다
 **/

public class InspectionService {

	InspectionDAO inspectionDao = new InspectionDAO();

	/** =-=-=-=-=-=-=-=-=-=-=-=-= 일정 조회 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/

	// - 모든 일정 목록 조회
	public boolean findAllInspection() {
		try {
			inspectionDao.connect();
			if (!inspectionDao.findAllInspection()) {
				System.out.println("조회에 실패하였습니다.");
				return false;
			}
			;
			return true;
		} catch (Exception e) {
			System.out.println("조회 중 오류가 발생하였습니다");
			return false;
		} finally {
			inspectionDao.close();
		}
	}

	// - 장비 ID로 일정 조회
	public boolean findByIdInspection(int equipmentId) {
		try {
			inspectionDao.connect();
			if (!inspectionDao.findByIdInspection(equipmentId)) {
				System.out.println("조회에 실패하였습니다.");
				return false;
			}
			;
			return true;
		} catch (Exception e) {
			System.out.println("조회 중 오류가 발생하였습니다");
			return false;
		} finally {
			inspectionDao.close();
		}
	}

	// - 일정 ID로 일정 조회
	public boolean findByScheduleId(int scheduleId) {
		try {
			inspectionDao.connect();
			if (!inspectionDao.findByScheduleId(scheduleId)) {
				System.out.println("조회에 실패하였습니다.");
				return false;
			}
			;
			return true;
		} catch (Exception e) {
			System.out.println("조회 중 오류가 발생하였습니다");
			return false;
		} finally {
			inspectionDao.close();
		}
	}

	/** =-=-=-=-=-=-=-=-=-=-=-=-= 등록 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/

	// - 새 점검 일정 정보 저장
	public boolean saveInspectionSchedule(InspectionSchedule schedule) {
		try {
			inspectionDao.connect();
			if (!inspectionDao.saveInspectionSchedule(schedule)) {
				System.out.println("등록에 실패하였습니다.");
				return false;
			}
			;
			return true;
		} catch (Exception e) {
			System.out.println("등록 중 오류가 발생하였습니다");
			return false;
		} finally {
			inspectionDao.close();
		}
	}

} // end class
