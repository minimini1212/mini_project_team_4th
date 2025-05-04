package equipmentAsset.repair.model.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import equipmentAsset.repair.model.dao.RepairDAO;
import equipmentAsset.repair.model.entity.RepairRequest;
import equipmentAsset.repair.model.entity.RepairResult;

/**
 * 서비스 클래스에서는 트랜잭션 처리와 db 연결만 담당한다
 **/

public class RepairService {
    
    RepairDAO repairDAO = new RepairDAO();
    
    /** =-=-=-=-=-=-=-=-=-=-=-=-= 조회 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/
    
    // - 모든 수리 요청 목록 조회
    public boolean findAllRepairRequests() {
        try {
            repairDAO.connect();
            return repairDAO.findAllRepairRequests();
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            repairDAO.close();
        }
    }
    
    // - 장비 ID로 수리 요청 조회
    public boolean findRepairRequestByEquipmentId(int equipmentId) {
        try {
            repairDAO.connect();
            return repairDAO.findRepairRequestByEquipmentId(equipmentId);
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            repairDAO.close();
        }
    }
    
    // - 요청 ID로 수리 요청 조회
    public boolean findRepairRequestById(int requestId) {
        try {
            repairDAO.connect();
            return repairDAO.findRepairRequestById(requestId);
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            repairDAO.close();
        }
    }
    
    // - 상태별 수리 요청 조회
    public boolean findRepairRequestByStatus(String status) {
        try {
            repairDAO.connect();
            return repairDAO.findRepairRequestByStatus(status);
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            repairDAO.close();
        }
    }
    
    // - 모든 수리 결과 조회
    public boolean findAllRepairResults() {
        try {
            repairDAO.connect();
            return repairDAO.findAllRepairResults();
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            repairDAO.close();
        }
    }
    
    // - 요청 ID로 수리 결과 조회
    public boolean findRepairResultByRequestId(int requestId) {
        try {
            repairDAO.connect();
            return repairDAO.findRepairResultByRequestId(requestId);
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            repairDAO.close();
        }
    }
    
    // - 장비 ID로 수리 결과 조회
    public boolean findRepairResultByEquipmentId(int equipmentId) {
        try {
            repairDAO.connect();
            return repairDAO.findRepairResultByEquipmentId(equipmentId);
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            repairDAO.close();
        }
    }
    
    // - 결과별 수리 결과 조회
    public boolean findRepairResultByType(String resultType) {
        try {
            repairDAO.connect();
            return repairDAO.findRepairResultByType(resultType);
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            repairDAO.close();
        }
    }
    
    // - 수리필요 상태 장비 조회
    public boolean findRepairableEquipment() {
        try {
            repairDAO.connect();
            return repairDAO.findRepairableEquipment();
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            repairDAO.close();
        }
    }
    
    // - 예정 상태의 수리 요청 조회
    public boolean findPendingRepairRequests() {
        try {
            repairDAO.connect();
            return repairDAO.findPendingRepairRequests();
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            repairDAO.close();
        }
    }
    
    /** =-=-=-=-=-=-=-=-=-=-=-=-= 등록 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/
    
    // - 수리 요청 저장
    public boolean saveRepairRequest(RepairRequest request) {
        try {
            repairDAO.connect();
            
            int requestId = repairDAO.saveRepairRequest(request);
            if (requestId <= 0) {
                return false;
            }
            
            // 생성된 ID 설정
            request.setRequestId(requestId);
            return true;
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            repairDAO.close();
        }
    }
    
    // - 수리 결과 저장
    public boolean saveRepairResult(RepairResult result) {
        try {
            repairDAO.connect();
            
            int resultId = repairDAO.saveRepairResult(result);
            if (resultId <= 0) {
                return false;
            }
            
            // 생성된 ID 설정
            result.setResultId(resultId);
            return true;
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            repairDAO.close();
        }
    }
    
    /** =-=-=-=-=-=-=-=-=-=-=-=-= 수정 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/
    
    // - 수리 요청 일자 업데이트
    public boolean updateRequestDate(int requestId, String requestDateStr) {
        try {
            repairDAO.connect();
            
            // 날짜 변환
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date requestDate = dateFormat.parse(requestDateStr);
            
            return repairDAO.updateRequestDate(requestId, requestDate);
        } catch (Exception e) {
            System.out.println("데이터베이스 작업 중 오류가 발생하였습니다: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            repairDAO.close();
        }
    }
    
    // - 수리 요청 증상 업데이트
    public boolean updateFailureSymptom(int requestId, String failureSymptom) {
        try {
            repairDAO.connect();
            return repairDAO.updateFailureSymptom(requestId, failureSymptom);
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            repairDAO.close();
        }
    }
    
    // - 수리 요청 상태 업데이트
    public boolean updateRequestStatus(int requestId, String status) {
        try {
            repairDAO.connect();
            return repairDAO.updateRequestStatus(requestId, status);
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            repairDAO.close();
        }
    }
    
    // - 수리 내용 업데이트
    public boolean updateRepairContent(int resultId, String repairContent) {
        try {
            repairDAO.connect();
            return repairDAO.updateRepairContent(resultId, repairContent);
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            repairDAO.close();
        }
    }
    
    // - 수리 비용 업데이트
    public boolean updateRepairCost(int resultId, int repairCost) {
        try {
            repairDAO.connect();
            return repairDAO.updateRepairCost(resultId, repairCost);
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            repairDAO.close();
        }
    }
    
    // - 수리 결과 유형 업데이트
    public boolean updateRepairResultType(int resultId, String repairResult) {
        try {
            repairDAO.connect();
            return repairDAO.updateRepairResultType(resultId, repairResult);
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            repairDAO.close();
        }
    }
    
    /** =-=-=-=-=-=-=-=-=-=-=-=-= 삭제 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/
    
 // - 수리 요청 삭제
    public boolean deleteRepairRequest(int requestId) {
        try {
            repairDAO.connect();
            return repairDAO.deleteRepairRequest(requestId);
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            repairDAO.close();
        }
    }

    // - 수리 결과 삭제
    public boolean deleteRepairResult(int resultId) {
        try {
            repairDAO.connect();
            return repairDAO.deleteRepairResult(resultId);
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            repairDAO.close();
        }
    }
}