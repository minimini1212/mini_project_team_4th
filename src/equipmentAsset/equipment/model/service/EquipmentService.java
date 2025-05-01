package equipmentAsset.equipment.model.service;

import java.sql.SQLException;

import equipmentAsset.equipment.model.dao.CategoryDAO;
import equipmentAsset.equipment.model.dao.EquipmentDAO;
import equipmentAsset.equipment.model.entity.Equipment;
import equipmentAsset.equipment.model.entity.EquipmentCategory;

/**
 * 서비스 클래스에서는 트랜잭션 처리와 db 연결만 담당한다
 **/

public class EquipmentService {
	EquipmentDAO equipmentDao = new EquipmentDAO();
	CategoryDAO categoryDAO = new CategoryDAO();
	
	/**=-=-=-=-=-=-=-=-=-=-=-=-= 장비 조회 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=**/
	
	// - 모든 장비 목록 조회
	public boolean findAllEquipment() {
		try {
			equipmentDao.connect();
			if (!equipmentDao.findAllEquipment()) {
				System.out.println("조회에 실패하였습니다.");
				return false;
			}
			;
			return true;
		} catch (Exception e) {
			System.out.println("조회 중 오류가 발생하였습니다");
			return false;
		} finally {
			equipmentDao.close();
		}
	}

	// - ID로 특정 장비 조회
	public boolean findByIdEquipment(int equipmentId) {
		try {
			equipmentDao.connect();
			if (!equipmentDao.findByIdEquipment(equipmentId)) {
				System.out.println("조회할 장비가 없습니다");
				return false;
			}
			;
			return true;
		} catch (Exception e) {
			System.out.println("조회 중 오류가 발생하였습니다");
			return false;
		} finally {
			equipmentDao.close();
		}
	}

	// - 특정 카테고리의 장비 조회
	public boolean findByCategoryEquipment(String categoryName) {
		try {
			equipmentDao.connect();
			if (!equipmentDao.findByCategoryEquipment(categoryName)) {
				System.out.println("조회할 장비가 없습니다");
				return false;
			}
			;
			return true;
		} catch (Exception e) {
			System.out.println("조회 중 오류가 발생하였습니다");
			return false;
		} finally {
			equipmentDao.close();
		}
	}

	// - 특정 부서의 장비 조회
	public boolean findByDepartmentEquipment(String departmentName) {
		try {
			equipmentDao.connect();
			if (!equipmentDao.findByDepartmentEquipment(departmentName)) {
				System.out.println("조회할 장비가 없습니다");
				return false;
			}
			;
			return true;
		} catch (Exception e) {
			System.out.println("조회 중 오류가 발생하였습니다");
			return false;
		} finally {
			equipmentDao.close();
		}
	}

	// - 특정 상태(정상, 점검필요, 수리중 등)의 장비 조회
	public boolean findByStatusEquipment(String status) {
		try {
			equipmentDao.connect();
			if (!equipmentDao.findByStatusEquipment(status)) {
				System.out.println("조회할 장비가 없습니다");
				return false;
			}
			;
			return true;
		} catch (Exception e) {
			System.out.println("조회 중 오류가 발생하였습니다");
			return false;
		} finally {
			equipmentDao.close();
		}
	}
	
	/**=-=-=-=-=-=-=-=-=-=-=-=-= 장비 등록 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=**/
	
	// - 새 장비 정보 저장
	public boolean saveEquipment(Equipment equipment) {
		try {
			equipmentDao.connect();

			// 오토커밋 해제
			equipmentDao.getConn().setAutoCommit(false);

			if (!equipmentDao.saveEquipment(equipment)) {
				System.out.println("장비 등록에 실패하였습니다");

				// 등록 실패시 롤백
				equipmentDao.getConn().rollback();
				return false;
			}
			;
			// 등록에 성공했을 때 커밋
			equipmentDao.getConn().commit();
			return true;
		} catch (Exception e) {
			System.out.println("등록 중 오류가 발생하였습니다");
			return false;
		} finally {
			equipmentDao.close();
		}
	}

	/**=-=-=-=-=-=-=-=-=-=-=-=-= 장비 수정 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=**/
	
	// - 장비 상태 업데이트
	public boolean updateStatusEquipment(int equipmentId, String status) {
		try {
			equipmentDao.connect();

			// 오토커밋 해제
			equipmentDao.getConn().setAutoCommit(false);

			if (!equipmentDao.updateStatusEquipment(equipmentId, status)) {
				System.out.println("업데이트에 실패하였습니다");

				// 업데이트 실패시 롤백
				equipmentDao.getConn().rollback();
				return false;
			}
			;
			// 업데이트에 성공했을 때 커밋
			equipmentDao.getConn().commit();
			return true;
		} catch (Exception e) {
			System.out.println("업데이트 중 오류가 발생하였습니다");
			return false;
		} finally {
			equipmentDao.close();
		}
	}

	// - 장비 담당자 업데이트
	public boolean updateManagerEquipment(int equipmentId, int managerId) {
		try {
			equipmentDao.connect();

			// 오토커밋 해제
			equipmentDao.getConn().setAutoCommit(false);

			if (!equipmentDao.updateManagerEquipment(equipmentId, managerId)) {
				System.out.println("업데이트에 실패하였습니다");

				// 업데이트 실패시 롤백
				equipmentDao.getConn().rollback();
				return false;
			}
			;
			// 업데이트에 성공했을 때 커밋
			equipmentDao.getConn().commit();
			return true;
		} catch (Exception e) {
			System.out.println("업데이트 중 오류가 발생하였습니다");
			return false;
		} finally {
			equipmentDao.close();
		}
	}
	
	/**=-=-=-=-=-=-=-=-=-=-=-=-= 장비 삭제 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=**/
	
	// - 장비 정보 삭제
	public boolean deleteEquipment(int equipmentId) {
		try {
			equipmentDao.connect();

			// 오토커밋 해제
			equipmentDao.getConn().setAutoCommit(false);

			if (!equipmentDao.deleteEquipment(equipmentId)) {
				System.out.println("장비 삭제에 실패하였습니다");

				// 삭제 실패시 롤백
				equipmentDao.getConn().rollback();
				return false;
			}
			;
			// 삭제에 성공했을 때 커밋
			equipmentDao.getConn().commit();
			return true;
		} catch (Exception e) {
			System.out.println("삭제 중 오류가 발생하였습니다");
			return false;
		} finally {
			equipmentDao.close();
		}
	}

	/**=-=-=-=-=-=-=-=-=-=-=-=-= 장비 집계 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=**/
	
	// - 상태별 장비 개수 집계
	public boolean countByStatus() {
		try {
			equipmentDao.connect();
			if (!equipmentDao.countByStatus()) {
				System.out.println("조회에 실패하였습니다.");
				return false;
			}
			;
			return true;
		} catch (Exception e) {
			System.out.println("조회 중 오류가 발생하였습니다");
			return false;
		} finally {
			equipmentDao.close();
		}
	}

	// - 카테고리별 장비 개수 집계
	public boolean countByCategory() {
		try {
			equipmentDao.connect();
			if (!equipmentDao.countByCategory()) {
				System.out.println("조회에 실패하였습니다.");
				return false;
			}
			;
			return true;
		} catch (Exception e) {
			System.out.println("조회 중 오류가 발생하였습니다");
			return false;
		} finally {
			equipmentDao.close();
		}
	}

	// - 부서별 장비 개수 집계
	public boolean countByDepartment() {
		try {
			equipmentDao.connect();
			if (!equipmentDao.countByDepartment()) {
				System.out.println("조회에 실패하였습니다.");
				return false;
			}
			;
			return true;
		} catch (Exception e) {
			System.out.println("조회 중 오류가 발생하였습니다");
			return false;
		} finally {
			equipmentDao.close();
		}
	}

	// - 카테고리별 구매 가격 합계
	public boolean sumPurchasePriceByCategory() {
		try {
			equipmentDao.connect();
			if (!equipmentDao.sumPurchasePriceByCategory()) {
				System.out.println("조회에 실패하였습니다.");
				return false;
			}
			;
			return true;
		} catch (Exception e) {
			System.out.println("조회 중 오류가 발생하였습니다");
			return false;
		} finally {
			equipmentDao.close();
		}
	}

	// - 최근에 업데이트된 장비 목록
	public boolean getRecentlyUpdatedEquipments() {
		try {
			equipmentDao.connect();
			if (!equipmentDao.getRecentlyUpdatedEquipments()) {
				System.out.println("조회에 실패하였습니다.");
				return false;
			}
			;
			return true;
		} catch (Exception e) {
			System.out.println("조회 중 오류가 발생하였습니다");
			return false;
		} finally {
			equipmentDao.close();
		}
	}

	/**=-=-=-=-=-=-=-=-=-=-=-=- 카테고리 관련 메소드 -=-=-=-=-=-=-=-=-=-=-=-=**/
	
	// - 모든 카테고리 조회
	public boolean findAllCategories() {
		try {
			categoryDAO.connect();
			if (!categoryDAO.findAllCategories()) {
				System.out.println("조회에 실패하였습니다.");
				return false;
			}
			;
			return true;
		} catch (Exception e) {
			System.out.println("조회 중 오류가 발생하였습니다");
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
				System.out.println("등록에 실패하였습니다.");
				//등록 실패 시 롤백
				categoryDAO.getConn().rollback();
				return false;
			}
			;
			//등록 성공 시 커밋
			categoryDAO.getConn().commit();
			return true;
		} catch (Exception e) {
			System.out.println("등록 중 오류가 발생하였습니다");
			return false;
		} finally {
			categoryDAO.close();
		}
	} // end saveCategory

	// - 카테고리 삭제
	public boolean deleteCategory(int categoryId) {
		try {
			categoryDAO.connect();
			
			// 오토커밋 해제
			categoryDAO.getConn().setAutoCommit(false);
						
			if (!categoryDAO.deleteCategory(categoryId)) {
				System.out.println("삭제에 실패하였습니다.");
				//삭제 실패 시 롤백
				categoryDAO.getConn().rollback();
				return false;
			}
			;
			//삭제 성공 시 커밋
			categoryDAO.getConn().commit();
			return true;
		} catch (Exception e) {
			System.out.println("삭제 중 오류가 발생하였습니다");
			return false;
		} finally {
			categoryDAO.close();
		}
	}
}
