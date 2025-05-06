package equipmentAsset.history.model.service;

import equipmentAsset.history.model.dao.HistoryDAO;

public class HistoryService {
    HistoryDAO historyDAO = new HistoryDAO();

    /** =-=-=-=-=-=-=-=-=-=-=-=-= 조회 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/

    // - 모든 이력 목록 조회
    public boolean findAllHistory() {
        try {
            historyDAO.connect();
            return historyDAO.findAllHistory();
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            historyDAO.close();
        }
    }

    // - 특정 장비의 이력 조회
    public boolean findHistoryByEquipmentId(int equipmentId) {
        try {
            historyDAO.connect();
            return historyDAO.findHistoryByEquipmentId(equipmentId);
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            historyDAO.close();
        }
    }

    // - 모든 점검 이력 조회
    public boolean findInspectionHistory() {
        try {
            historyDAO.connect();
            return historyDAO.findInspectionHistory();
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            historyDAO.close();
        }
    }

    // - 특정 장비의 점검 이력 조회
    public boolean findInspectionHistoryByEquipmentId(int equipmentId) {
        try {
            historyDAO.connect();
            return historyDAO.findInspectionHistoryByEquipmentId(equipmentId);
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            historyDAO.close();
        }
    }

    // - 점검 결과별 이력 조회
    public boolean findInspectionHistoryByResult(String result) {
        try {
            historyDAO.connect();
            return historyDAO.findInspectionHistoryByResult(result);
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            historyDAO.close();
        }
    }

    // - 모든 수리 이력 조회
    public boolean findRepairHistory() {
        try {
            historyDAO.connect();
            return historyDAO.findRepairHistory();
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            historyDAO.close();
        }
    }

    // - 특정 장비의 수리 이력 조회
    public boolean findRepairHistoryByEquipmentId(int equipmentId) {
        try {
            historyDAO.connect();
            return historyDAO.findRepairHistoryByEquipmentId(equipmentId);
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            historyDAO.close();
        }
    }

    // - 수리 결과별 이력 조회
    public boolean findRepairHistoryByResult(String result) {
        try {
            historyDAO.connect();
            return historyDAO.findRepairHistoryByResult(result);
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            historyDAO.close();
        }
    }

    // - 모든 폐기 이력 조회
    public boolean findDisposalHistory() {
        try {
            historyDAO.connect();
            return historyDAO.findDisposalHistory();
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            historyDAO.close();
        }
    }

    // - 특정 장비의 폐기 이력 조회
    public boolean findDisposalHistoryByEquipmentId(int equipmentId) {
        try {
            historyDAO.connect();
            return historyDAO.findDisposalHistoryByEquipmentId(equipmentId);
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            historyDAO.close();
        }
    }
}