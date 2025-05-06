package equipmentAsset.inspection.model.service;

import java.util.Date;
import java.text.SimpleDateFormat;

import equipmentAsset.inspection.model.dao.InspectionDAO;
import equipmentAsset.inspection.model.entity.InspectionResult;
import equipmentAsset.inspection.model.entity.InspectionSchedule;

/**
 * 서비스 클래스에서는 트랜잭션 처리와 db 연결만 담당한다
 **/

public class InspectionService {

    InspectionDAO inspectionDAO = new InspectionDAO();

    /** =-=-=-=-=-=-=-=-=-=-=-=-= 일정 조회 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/

    // - 모든 일정 목록 조회
    public boolean findAllInspection() {
        try {
            inspectionDAO.connect();
            return inspectionDAO.findAllInspection();
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            inspectionDAO.close();
        }
    }

    // - 장비 ID로 일정 조회
    public boolean findByIdInspection(int equipmentId) {
        try {
            inspectionDAO.connect();
            return inspectionDAO.findByIdInspection(equipmentId);
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            inspectionDAO.close();
        }
    }

    // - 일정 ID로 일정 조회
    public boolean findByScheduleId(int scheduleId) {
        try {
            inspectionDAO.connect();
            return inspectionDAO.findByScheduleId(scheduleId);
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            inspectionDAO.close();
        }
    }

    // 특정 상태의 점검 일정 조회
    public boolean findInspectionByStatus(String status) {
        try {
            inspectionDAO.connect();
            return inspectionDAO.findInspectionByStatus(status);
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            inspectionDAO.close();
        }
    }

    // 점검 주기별 조회 메소드
    public boolean findInspectionByCycle(String cycle) {
        try {
            inspectionDAO.connect();
            return inspectionDAO.findInspectionByCycle(cycle);
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            inspectionDAO.close();
        }
    }

    // 점검 유형별 조회 메소드
    public boolean findInspectionByType(String type) {
        try {
            inspectionDAO.connect();
            return inspectionDAO.findInspectionByType(type);
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            inspectionDAO.close();
        }
    }

    // 점검 상태 '기타' 조회 메소드
    public boolean findInspectionByStatusOthers() {
        try {
            inspectionDAO.connect();
            return inspectionDAO.findInspectionByStatusOthers();
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            inspectionDAO.close();
        }
    }

    // 점검 주기 '기타' 조회 메소드
    public boolean findInspectionByCycleOthers() {
        try {
            inspectionDAO.connect();
            return inspectionDAO.findInspectionByCycleOthers();
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            inspectionDAO.close();
        }
    }

    // 점검 유형 '기타' 조회 메소드
    public boolean findInspectionByTypeOthers() {
        try {
            inspectionDAO.connect();
            return inspectionDAO.findInspectionByTypeOthers();
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            inspectionDAO.close();
        }
    }
    
    // - 모든 점검 결과 조회
    public boolean findAllInspectionResult() {
        try {
            inspectionDAO.connect();
            return inspectionDAO.findAllInspectionResult();
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            inspectionDAO.close();
        }
    }

    // - 장비 ID로 점검 결과 조회
    public boolean findInspectionResultByEquipmentId(int equipmentId) {
        try {
            inspectionDAO.connect();
            return inspectionDAO.findInspectionResultByEquipmentId(equipmentId);
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            inspectionDAO.close();
        }
    }

    // - 결과 ID로 점검 결과 조회
    public boolean findInspectionResultById(int resultId) {
        try {
            inspectionDAO.connect();
            return inspectionDAO.findInspectionResultById(resultId);
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            inspectionDAO.close();
        }
    }

    // - 결과 유형별 점검 결과 조회
    public boolean findInspectionResultByType(String resultType) {
        try {
            inspectionDAO.connect();
            return inspectionDAO.findInspectionResultByType(resultType);
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            inspectionDAO.close();
        }
    }

    /** =-=-=-=-=-=-=-=-=-=-=-=-= 등록 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/

    // - 새 점검 일정 정보 저장
    public boolean saveInspectionSchedule(InspectionSchedule schedule) {
        try {
            inspectionDAO.connect();

            // 오토커밋 해제
            inspectionDAO.getConn().setAutoCommit(false);

            if (!inspectionDAO.saveInspectionSchedule(schedule)) {
                // 등록 실패시 롤백
                inspectionDAO.getConn().rollback();
                return false;
            }

            // 등록 성공시 커밋
            inspectionDAO.getConn().commit();
            return true;
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            inspectionDAO.close();
        }
    }

    // - 새 점검 결과 정보 저장 (프로시저 사용)
    public boolean saveInspectionResult(InspectionResult result) {
        try {
            inspectionDAO.connect();
            
            // DAO 메소드 호출 (프로시저 내에서 트랜잭션 처리됨)
            int resultId = inspectionDAO.saveInspectionResult(result);
            if (resultId <= 0) {
                return false;
            }
            
            // 생성된 ID 설정
            result.setResultId(resultId);
            return true;
        } catch (Exception e) {
            System.out.println("데이터베이스 작업 중 오류가 발생하였습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            inspectionDAO.close();
        }
    }

    /** =-=-=-=-=-=-=-=-=-=-=-=-= 수정 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/

    // 점검 일정 > 점검 유형 수정
    public boolean updateInspectionType(int scheduleId, String inspectionType) {
        try {
            inspectionDAO.connect();

            // 오토커밋 해제
            inspectionDAO.getConn().setAutoCommit(false);

            if (!inspectionDAO.updateInspectionType(scheduleId, inspectionType)) {
                // 업데이트 실패시 롤백
                inspectionDAO.getConn().rollback();
                return false;
            }

            // 업데이트 성공시 커밋
            inspectionDAO.getConn().commit();
            return true;
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            inspectionDAO.close();
        }
    }

    // 점검 일정 > 점검 주기 수정
    public boolean updateInspectionCycle(int scheduleId, String inspectionCycle) {
        try {
            inspectionDAO.connect();

            // 오토커밋 해제
            inspectionDAO.getConn().setAutoCommit(false);

            if (!inspectionDAO.updateInspectionCycle(scheduleId, inspectionCycle)) {
                // 업데이트 실패시 롤백
                inspectionDAO.getConn().rollback();
                return false;
            }

            // 업데이트 성공시 커밋
            inspectionDAO.getConn().commit();
            return true;
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            inspectionDAO.close();
        }
    }

    // 점검 일정 > 예정 일자 수정
    public boolean updateScheduledDate(int scheduleId, String scheduledDateStr) {
        try {
            inspectionDAO.connect();

            // 오토커밋 해제
            inspectionDAO.getConn().setAutoCommit(false);

            if (!inspectionDAO.updateScheduledDate(scheduleId, scheduledDateStr)) {
                // 업데이트 실패시 롤백
                inspectionDAO.getConn().rollback();
                return false;
            }

            // 업데이트 성공시 커밋
            inspectionDAO.getConn().commit();
            return true;
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            inspectionDAO.close();
        }
    }

    // 점검 일정 > 상태 수정
    public boolean updateStatus(int scheduleId, String status) {
        try {
            inspectionDAO.connect();

            // 오토커밋 해제
            inspectionDAO.getConn().setAutoCommit(false);

            if (!inspectionDAO.updateStatus(scheduleId, status)) {
                // 업데이트 실패시 롤백
                inspectionDAO.getConn().rollback();
                return false;
            }

            // 업데이트 성공시 커밋
            inspectionDAO.getConn().commit();
            return true;
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            inspectionDAO.close();
        }
    }

    // 점검 일정 > 설명 수정
    public boolean updateDescription(int scheduleId, String description) {
        try {
            inspectionDAO.connect();

            // 오토커밋 해제
            inspectionDAO.getConn().setAutoCommit(false);

            if (!inspectionDAO.updateDescription(scheduleId, description)) {
                // 업데이트 실패시 롤백
                inspectionDAO.getConn().rollback();
                return false;
            }

            // 업데이트 성공시 커밋
            inspectionDAO.getConn().commit();
            return true;
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            inspectionDAO.close();
        }
    }
    
    // - 점검 결과 > 점검 일자 수정
    public boolean updateInspectionDate(int resultId, String inspectionDateStr) {
        try {
            inspectionDAO.connect();
            
            // 날짜 변환
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date inspectionDate = dateFormat.parse(inspectionDateStr);
            
            return inspectionDAO.updateInspectionDate(resultId, inspectionDate);
        } catch (Exception e) {
            System.out.println("데이터베이스 작업 중 오류가 발생하였습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            inspectionDAO.close();
        }
    }

    // - 점검 결과 유형 수정
    public boolean updateInspectionResult(int resultId, String inspectionResult) {
        try {
            inspectionDAO.connect();
            
            return inspectionDAO.updateInspectionResultType(resultId, inspectionResult);
        } catch (Exception e) {
            System.out.println("데이터베이스 작업 중 오류가 발생하였습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            inspectionDAO.close();
        }
    }

    // - 점검 내용 수정
    public boolean updateInspectionContent(int resultId, String inspectionContent) {
        try {
            inspectionDAO.connect();
            
            return inspectionDAO.updateInspectionContent(resultId, inspectionContent);
        } catch (Exception e) {
            System.out.println("데이터베이스 작업 중 오류가 발생하였습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            inspectionDAO.close();
        }
    }

    // - 특이사항 수정
    public boolean updateSpecialNote(int resultId, String specialNote) {
        try {
            inspectionDAO.connect();
            
            return inspectionDAO.updateSpecialNote(resultId, specialNote);
        } catch (Exception e) {
            System.out.println("데이터베이스 작업 중 오류가 발생하였습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            inspectionDAO.close();
        }
    }
   

    /** =-=-=-=-=-=-=-=-=-=-=-=-= 삭제 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/

    // 점검 일정 삭제
    public boolean deleteInspectionSchedule(int scheduleId) {
        try {
            inspectionDAO.connect();

            // 오토커밋 해제
            inspectionDAO.getConn().setAutoCommit(false);

            if (!inspectionDAO.deleteInspectionSchedule(scheduleId)) {
                // 삭제 실패시 롤백
                inspectionDAO.getConn().rollback();
                return false;
            }

            // 삭제 성공시 커밋
            inspectionDAO.getConn().commit();
            return true;
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            inspectionDAO.close();
        }
    }

    // - 점검 결과 삭제 (프로시저 사용)
    public boolean deleteInspectionResult(int resultId) {
        try {
            inspectionDAO.connect();
            
            // DAO 메소드 호출
            return inspectionDAO.deleteInspectionResult(resultId);
        } catch (Exception e) {
            System.out.println("데이터베이스 작업 중 오류가 발생하였습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            inspectionDAO.close();
        }
    }
}