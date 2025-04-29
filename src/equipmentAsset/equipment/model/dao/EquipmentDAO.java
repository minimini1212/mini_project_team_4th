package equipmentAsset.equipment.model.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.Scanner;

import equipmentAsset.common.util.CloseHelper;
import equipmentAsset.common.util.ConnectionSingletonHelper;
import equipmentAsset.equipment.model.entity.Equipment;
import equipmentAsset.equipment.view.EquipmentView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class EquipmentDAO {
	private static final String TABLE_NAME = "EQUIPMENT";
	
	private Equipment equipment = new Equipment();
	private EquipmentView equipmentVIew = new EquipmentView();
	
	// 연결, 삽입, 삭제, 수정, 검색,......
	private Scanner sc = new Scanner(System.in);
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Connection conn = null;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
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
	} //end close
	
	/**=-=-=-=-=-=-=-=-=-=-=-=-= 조회 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=**/
	
	// - ID로 특정 장비 조회
	public void findById (int equipmentId) {
		try {
			rs = stmt.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE EQUIPMENT_ID = " + equipmentId);
			equipmentVIew.displayEquipmentResults(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	} // end findById
	
	// - 모든 장비 목록 조회
	public void findAll() {
		try {
			rs = stmt.executeQuery("SELECT * FROM " + TABLE_NAME);
			equipmentVIew.displayEquipmentResults(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} 		
	} // end findAll
	
	// - 특정 카테고리의 장비 조회
	public void findByCategory(int categoryId) {
		try {
			rs = stmt.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE CATEGORY_ID = " + categoryId);
			equipmentVIew.displayEquipmentResults(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
	} // end findByCategory
	
	// - 특정 부서의 장비 조회
	public void findByDepartment(int departmentId) {
		try {
			rs = stmt.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE DEPARTMENT_ID = " + departmentId);
			equipmentVIew.displayEquipmentResults(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
	}
	
	// - 특정 상태(정상, 점검필요, 수리중 등)의 장비 조회
	public void findByStatus(String status) {
		try {
			rs = stmt.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE STATUS = '" + status +"'");
			equipmentVIew.displayEquipmentResults(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
	}	
	
	/**=-=-=-=-=-=-=-=-=-=-=-=-= 등록 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=**/
	// - 새 장비 정보 저장
	public void save(Equipment equipment) {
		String sql = "INSERT INTO EQUIPMENT VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, equipment.getEquipmentId());
	        pstmt.setString(2, equipment.getEquipmentName());
	        pstmt.setString(3, equipment.getModelName());
	        pstmt.setString(4, equipment.getManufacturer());
	        pstmt.setString(5, equipment.getSerialNumber());
	        pstmt.setDate(6, new java.sql.Date(equipment.getPurchaseDate().getTime()));
	        pstmt.setDouble(7, equipment.getPurchasePrice());
	        pstmt.setInt(8, equipment.getCategoryId());
	        pstmt.setInt(9, equipment.getDepartmentId());
	        pstmt.setInt(10, equipment.getManagerId());
	        pstmt.setString(11, equipment.getStatus());
	        pstmt.setString(12, equipment.getDescription());
	        pstmt.setDate(13, new java.sql.Date(equipment.getLastUpdatedDate().getTime()));
	        
	        int result = pstmt.executeUpdate();
			System.out.println(result + " 개 데이터 추가 성공!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}			
	}
	
} //end class
