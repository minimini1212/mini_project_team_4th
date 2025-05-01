package equipmentAsset.equipment.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import equipmentAsset.common.util.CloseHelper;
import equipmentAsset.common.util.ConnectionSingletonHelper;
import equipmentAsset.equipment.model.entity.EquipmentCategory;
import equipmentAsset.equipment.view.EquipmentView;
import lombok.Getter;


/** =-=-=-=-=-=-=-=-=-=-=-=-=-= equipment.CategoryDAO Class =-=-=-=-=-=-=-=-=-=-=-=-=-=

	--- 카테고리 조회
	void findAllCategories() : 모든 카테고리 조회
	
	--- 카테고리 추가
	void saveCategory(EquipmentCategory category) : 새 카테고리 저장
	
	--- 카테고리 삭제
	void deleteCategory(int categoryId) : 카테고리 삭제

	=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- **/

@Getter

public class CategoryDAO {
	private final String TABLE_NAME = "EQUIPMENT_CATEGORY";

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

	// - 모든 카테고리 조회
	public boolean findAllCategories() {
		try {
			rs = stmt.executeQuery("SELECT * FROM " + TABLE_NAME);
			equipmentVIew.findAllCategories(rs);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	/** =-=-=-=-=-=-=-=-=-=-=-=-= 등록 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/
	
	// - 새 카테고리 저장
	public boolean saveCategory(EquipmentCategory category) {
		String sql = "INSERT INTO EQUIPMENT_CATEGORY VALUES(?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, category.getCategoryId());
			pstmt.setString(2, category.getCategoryName());
			pstmt.setString(3, category.getCategoryCode());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	} //end saveCategory
	
	/** =-=-=-=-=-=-=-=-=-=-=-=-= 삭제 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/
	
	// - 카테고리 삭제
	public boolean deleteCategory(int categoryId) {
		String sql = "DELETE FROM EQUIPMENT_CATEGORY WHERE CATEGORY_ID = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryId);

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}	
	} //end deleteCategory
	
}
