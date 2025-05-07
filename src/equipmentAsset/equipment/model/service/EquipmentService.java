package equipmentAsset.equipment.model.service;

import equipmentAsset.equipment.model.dao.CategoryDAO;
import equipmentAsset.equipment.model.dao.EquipmentDAO;
import equipmentAsset.equipment.model.entity.Equipment;
import equipmentAsset.equipment.model.entity.EquipmentCategory;

/**
 * 서비스 클래스에서는 트랜잭션 처리와 db 연결만 담당한다
 **/

public class EquipmentService {
    EquipmentDAO equipmentDAO = new EquipmentDAO();
    CategoryDAO categoryDAO = new CategoryDAO();

    /**
     * =-=-=-=-=-=-=-=-=-=-=-=-= 장비 조회 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=
     **/

    // - 모든 장비 목록 조회
    public boolean findAllEquipment() {
        try {
            equipmentDAO.connect();
            return equipmentDAO.findAllEquipment();
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            equipmentDAO.close();
        }
    }

    // - ID로 특정 장비 조회
    public boolean findByIdEquipment(int equipmentId) {
        try {
            equipmentDAO.connect();
            return equipmentDAO.findByIdEquipment(equipmentId);
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            equipmentDAO.close();
        }
    }

    // - 특정 카테고리의 장비 조회
    public boolean findByCategoryEquipment(String categoryName) {
        try {
            equipmentDAO.connect();
            return equipmentDAO.findByCategoryEquipment(categoryName);
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            equipmentDAO.close();
        }
    }

    // - 특정 부서의 장비 조회
    public boolean findByDepartmentEquipment(String departmentName) {
        try {
            equipmentDAO.connect();
            return equipmentDAO.findByDepartmentEquipment(departmentName);
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            equipmentDAO.close();
        }
    }

    // - 특정 상태(정상, 점검필요, 수리중 등)의 장비 조회
    public boolean findByStatusEquipment(String status) {
        try {
            equipmentDAO.connect();
            return equipmentDAO.findByStatusEquipment(status);
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            equipmentDAO.close();
        }
    }


    // 카테고리 ID를 받아서 이름을 반환
    public String getCategoryNameById(int categoryId) {
        try {
            categoryDAO.connect();
            String categoryName = categoryDAO.getCategoryNameById(categoryId);
            if (categoryName == null) {
                System.out.println("해당 ID의 카테고리가 존재하지 않습니다.");
                return null;
            }
            return categoryName;
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return null;
        } finally {
            categoryDAO.close();
        }
    }

    // 담당자 조회
    public boolean displayManagerList() {
        try {
            equipmentDAO.connect();
            return equipmentDAO.displayManagerList();
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            equipmentDAO.close();
        }
    }

    /**
     * =-=-=-=-=-=-=-=-=-=-=-=-= 장비 등록 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=
     **/

    // - 새 장비 정보 저장
    public boolean saveEquipment(Equipment equipment) {
        try {
            equipmentDAO.connect();

            // 오토커밋 해제
            equipmentDAO.getConn().setAutoCommit(false);

            if (!equipmentDAO.saveEquipment(equipment)) {
                // 등록 실패시 롤백
                equipmentDAO.getConn().rollback();
                return false;
            }

            // 등록에 성공했을 때 커밋
            equipmentDAO.getConn().commit();
            return true;
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            equipmentDAO.close();
        }
    }

    // - 장비가 특정 상태인지 확인
    public boolean isEquipmentStatus(int equipmentId, String status) {
        try {
            equipmentDAO.connect();
            return equipmentDAO.isEquipmentStatus(equipmentId, status);
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            equipmentDAO.close();
        }
    }

    /**
     * =-=-=-=-=-=-=-=-=-=-=-=-= 장비 수정 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=
     **/

    // - 장비 구매날짜 업데이트
    public boolean updatePurchaseDate(int equipmentId, String purchaseDateStr) {
        try {
            equipmentDAO.connect();

            // 오토커밋 해제
            equipmentDAO.getConn().setAutoCommit(false);

            if (!equipmentDAO.updatePurchaseDate(equipmentId, purchaseDateStr)) {
                // 업데이트 실패시 롤백
                equipmentDAO.getConn().rollback();
                return false;
            }

            // 업데이트에 성공했을 때 커밋
            equipmentDAO.getConn().commit();
            return true;
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            equipmentDAO.close();
        }
    }

    // - 장비 구매가격 업데이트
    public boolean updatePurchasePrice(int equipmentId, int purchasePrice) {
        try {
            equipmentDAO.connect();

            // 오토커밋 해제
            equipmentDAO.getConn().setAutoCommit(false);

            if (!equipmentDAO.updatePurchasePrice(equipmentId, purchasePrice)) {
                // 업데이트 실패시 롤백
                equipmentDAO.getConn().rollback();
                return false;
            }

            // 업데이트에 성공했을 때 커밋
            equipmentDAO.getConn().commit();
            return true;
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            equipmentDAO.close();
        }
    }

    // - 카테고리 업데이트
    public boolean updateEquipmentCategory(int equipmentId, int categoryId) {
        try {
            equipmentDAO.connect();

            // 오토커밋 해제
            equipmentDAO.getConn().setAutoCommit(false);

            if (!equipmentDAO.updateEquipmentCategory(equipmentId, categoryId)) {
                // 업데이트 실패시 롤백
                equipmentDAO.getConn().rollback();
                return false;
            }

            // 업데이트에 성공했을 때 커밋
            equipmentDAO.getConn().commit();
            return true;
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            equipmentDAO.close();
        }
    }

    // - 담당자 업데이트
    public boolean updateEquipmentManager(int equipmentId, int managerId) {
        try {
            equipmentDAO.connect();

            // 오토커밋 해제
            equipmentDAO.getConn().setAutoCommit(false);

            if (!equipmentDAO.updateEquipmentManager(equipmentId, managerId)) {
                // 업데이트 실패시 롤백
                equipmentDAO.getConn().rollback();
                return false;
            }

            // 업데이트에 성공했을 때 커밋
            equipmentDAO.getConn().commit();
            return true;
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            equipmentDAO.close();
        }
    }

    // - 장비 상태 업데이트
    public boolean updateEquipmentStatus(int equipmentId, String status) {
        try {
            equipmentDAO.connect();

            // 오토커밋 해제
            equipmentDAO.getConn().setAutoCommit(false);

            if (!equipmentDAO.updateEquipmentStatus(equipmentId, status)) {
                // 업데이트 실패시 롤백
                equipmentDAO.getConn().rollback();
                return false;
            }

            // 업데이트에 성공했을 때 커밋
            equipmentDAO.getConn().commit();
            return true;
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            equipmentDAO.close();
        }
    }

    // - 장비 설명 업데이트
    public boolean updateEquipmentDescription(int equipmentId, String description) {
        try {
            equipmentDAO.connect();

            // 오토커밋 해제
            equipmentDAO.getConn().setAutoCommit(false);

            if (!equipmentDAO.updateEquipmentDescription(equipmentId, description)) {
                // 업데이트 실패시 롤백
                equipmentDAO.getConn().rollback();
                return false;
            }

            // 업데이트에 성공했을 때 커밋
            equipmentDAO.getConn().commit();
            return true;
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            equipmentDAO.close();
        }
    }

    /**
     * =-=-=-=-=-=-=-=-=-=-=-=-= 장비 삭제 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=
     **/

    // - 장비 정보 삭제
    public boolean deleteEquipment(int equipmentId) {
        try {
            equipmentDAO.connect();

            // 오토커밋 해제
            equipmentDAO.getConn().setAutoCommit(false);

            if (!equipmentDAO.deleteEquipment(equipmentId)) {
                // 삭제 실패시 롤백
                equipmentDAO.getConn().rollback();
                return false;
            }

            // 삭제에 성공했을 때 커밋
            equipmentDAO.getConn().commit();
            return true;
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            equipmentDAO.close();
        }
    }

    // 장비 폐기 메서드 추가
    public boolean disposeEquipment(int equipmentId, String disposeReason) {
        try {
            equipmentDAO.connect();

            // 오토커밋 해제
            equipmentDAO.getConn().setAutoCommit(false);

            if (!equipmentDAO.disposeEquipment(equipmentId, disposeReason)) {
                // 폐기 실패시 롤백
                equipmentDAO.getConn().rollback();
                return false;
            }

            // 폐기 성공시 커밋
            equipmentDAO.getConn().commit();
            return true;
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            equipmentDAO.close();
        }
    }

    /**
     * =-=-=-=-=-=-=-=-=-=-=-=-= 장비 집계 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=
     **/

    // - 상태별 장비 개수 집계
    public boolean countByStatus() {
        try {
            equipmentDAO.connect();
            return equipmentDAO.countByStatus();
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            equipmentDAO.close();
        }
    }

    // - 카테고리별 장비 개수 집계
    public boolean countByCategory() {
        try {
            equipmentDAO.connect();
            return equipmentDAO.countByCategory();
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            equipmentDAO.close();
        }
    }

    // - 부서별 장비 개수 집계
    public boolean countByDepartment() {
        try {
            equipmentDAO.connect();
            return equipmentDAO.countByDepartment();
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            equipmentDAO.close();
        }
    }

    // - 카테고리별 구매 가격 합계
    public boolean sumPurchasePriceByCategory() {
        try {
            equipmentDAO.connect();
            return equipmentDAO.sumPurchasePriceByCategory();
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            equipmentDAO.close();
        }
    }

    // - 최근에 업데이트된 장비 목록
    public boolean getRecentlyUpdatedEquipments() {
        try {
            equipmentDAO.connect();
            return equipmentDAO.getRecentlyUpdatedEquipments();
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            equipmentDAO.close();
        }
    }

    /**
     * =-=-=-=-=-=-=-=-=-=-=-=- 카테고리 관련 메소드 -=-=-=-=-=-=-=-=-=-=-=-=
     **/

    // - 모든 카테고리 조회
    public boolean findAllCategories() {
        try {
            categoryDAO.connect();
            return categoryDAO.findAllCategories();
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            categoryDAO.close();
        }
    }

    // - 새 카테고리 저장
    public boolean saveCategory(EquipmentCategory category) {
        try {
            categoryDAO.connect();

            // 오토커밋 해제
            categoryDAO.getConn().setAutoCommit(false);

            if (!categoryDAO.saveCategory(category)) {
                // 등록 실패 시 롤백
                categoryDAO.getConn().rollback();
                return false;
            }

            // 등록 성공 시 커밋
            categoryDAO.getConn().commit();
            return true;
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            categoryDAO.close();
        }
    }

    // - 카테고리 삭제
    public boolean deleteCategory(int categoryId) {
        try {
            categoryDAO.connect();

            if (categoryDAO.isUsedInEquipment(categoryId)) {
                System.out.println("사용중인 카테고리여서 삭제할 수 없습니다");
                return false;
            }

            // 오토커밋 해제
            categoryDAO.getConn().setAutoCommit(false);

            if (!categoryDAO.deleteCategory(categoryId)) {
                // 삭제 실패 시 롤백
                categoryDAO.getConn().rollback();
                return false;
            }

            // 삭제 성공 시 커밋
            categoryDAO.getConn().commit();
            return true;
        } catch (Exception e) {
            System.out.println("데이터베이스 연결 중 오류가 발생하였습니다");
            return false;
        } finally {
            categoryDAO.close();
        }
    }
} // end class
