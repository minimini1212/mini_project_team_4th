package budgetAccounting.expenditure.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import budgetAccounting.expenditure.model.entity.Expenditure;

public class ExpenditureDao {
	private Connection conn;

	public ExpenditureDao(Connection conn) {
		this.conn = conn;
	}

	// 지출 생성
	public void insertExpenditure(Expenditure expenditure) throws SQLException {
		String sql = "INSERT INTO expenditure (expenditure_id, expenditure_request_id, department_id, expenditure_date, amount, category_id, description, year) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		int sequence = getNextExpenditureId();

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, sequence);

			if (expenditure.getExpenditureId() == 0) {
				pstmt.setNull(2, Types.INTEGER);
			} else {
				pstmt.setInt(2, expenditure.getExpenditureRequestId());
			}
			pstmt.setInt(3, expenditure.getDepartmentId());
			pstmt.setDate(4, new java.sql.Date(expenditure.getExpenditureDate().getTime()));
			pstmt.setInt(5, expenditure.getAmount());
			pstmt.setInt(6, expenditure.getCategoryId());
			pstmt.setString(7, expenditure.getDescription());
			pstmt.setInt(8, expenditure.getYear());

			pstmt.executeUpdate();
		}
	}

	// 지츨 전체 조회
	public List<Expenditure> findAllExpenditure() throws SQLException {

		String sql = "SELECT * FROM expenditure WHERE del_yn IN ('N', 'n')";
		List<Expenditure> list = new ArrayList<>();

		if (conn == null || conn.isClosed()) {
			throw new SQLException("DB 연결이 유효하지 않습니다.");
		}

		// try-with-resources 문법을 사용 -> 자원을 자동으로 닫도록 설정
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				Expenditure expenditure = new Expenditure();
				expenditure.setExpenditureId(rs.getInt("expenditure_id"));
				expenditure.setExpenditureRequestId(rs.getInt("expenditure_request_id"));
				expenditure.setDepartmentId(rs.getInt("department_id"));
				expenditure.setExpenditureDate(rs.getDate("expenditure_date"));
				expenditure.setAmount(rs.getInt("amount"));
				expenditure.setCategoryId(rs.getInt("category_id"));
				expenditure.setDescription(rs.getString("description"));
				expenditure.setYear(rs.getInt("year"));

				list.add(expenditure);

			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 발생: " + e.getMessage());
			System.out.println("SQL 상태: " + e.getSQLState());
			System.out.println("오류 코드: " + e.getErrorCode());
			e.printStackTrace();
		}

		return list;
	}

	// 지출 부분 조회
	public List<Expenditure> findByExpenditureId(int requestId) throws SQLException {
		String sql = "SELECT * FROM expenditure WHERE expenditure_id = ? AND del_yn IN ('N', 'n')";
		List<Expenditure> list = new ArrayList<>();

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, requestId); // 먼저 값 바인딩
			try (ResultSet rs = pstmt.executeQuery()) { // 그리고 실행
				while (rs.next()) {
					Expenditure expenditure = new Expenditure();
					expenditure.setExpenditureId(rs.getInt("expenditure_id"));
					expenditure.setExpenditureRequestId(rs.getInt("expenditure_request_id"));
					expenditure.setDepartmentId(rs.getInt("department_id"));
					expenditure.setExpenditureDate(rs.getDate("expenditure_date"));
					expenditure.setAmount(rs.getInt("amount"));
					expenditure.setCategoryId(rs.getInt("category_id"));
					expenditure.setDescription(rs.getString("description"));
					expenditure.setYear(rs.getInt("year"));

					list.add(expenditure);
				}
			} catch (SQLException e) {
				System.out.println("SQL 오류 발생: " + e.getMessage());
				System.out.println("SQL 상태: " + e.getSQLState());
				System.out.println("오류 코드: " + e.getErrorCode());
				e.printStackTrace();
			}
		}

		return list;
	}

	// 특정 지출 수정
	public void updateByExpenditureId(Expenditure expenditure) throws SQLException {
		String sql = "UPDATE expenditure SET amount = ?, description = ? WHERE expenditure_id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, expenditure.getAmount());
			pstmt.setString(2, expenditure.getDescription());
			pstmt.setInt(3, expenditure.getExpenditureId());

			pstmt.executeUpdate();
			
		} 
	}

	// 지출 소프트딜리트
	public void softDeleteByExpenditureId(int requestId) throws SQLException {
		String sql = "UPDATE expenditure SET del_yn = 'Y' WHERE expenditure_id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, requestId);

			pstmt.executeUpdate();
		}
	}

	// 기본키 가져오는 메서드
	public int getNextExpenditureId() throws SQLException {
		String sql = "SELECT SEQ_expenditure_ID.NEXTVAL id FROM DUAL";
		try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				throw new SQLException("시퀀스 값을 가져오지 못했습니다.");
			}
		}
	}
}
