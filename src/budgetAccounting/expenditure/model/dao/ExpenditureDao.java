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
		conn.setAutoCommit(false); // 트랜잭션 시작

		// 쿼리문 미리 선언
		String selectBudgetSql = "SELECT budget_id, remaining_amount FROM budget "
				+ "WHERE department_id = ? AND category_id = ? AND year = ? AND del_yn = 'N' FOR UPDATE";

		String insertExpenditureSql = "INSERT INTO expenditure (expenditure_id, expenditure_request_id, department_id, expenditure_date, amount, category_id, description, year) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		String updateBudgetSql = "UPDATE budget SET remaining_amount = remaining_amount - ? WHERE budget_id = ?";

		int sequence = getNextExpenditureId();

		try (PreparedStatement pstmt1 = conn.prepareStatement(selectBudgetSql)) {
			pstmt1.setInt(1, expenditure.getDepartmentId());
			pstmt1.setInt(2, expenditure.getCategoryId());
			pstmt1.setInt(3, expenditure.getYear());

			try (ResultSet rs = pstmt1.executeQuery()) {
				if (!rs.next()) {
					throw new SQLException("해당 조건에 맞는 예산이 존재하지 않습니다.");
				}

				int budgetId = rs.getInt("budget_id");
				int remaining = rs.getInt("remaining_amount");

				if (remaining < expenditure.getAmount()) {
					throw new SQLException("잔여 예산이 부족합니다.");
				}

				try (PreparedStatement pstmt2 = conn.prepareStatement(insertExpenditureSql)) {
					pstmt2.setInt(1, sequence);

					if (expenditure.getExpenditureId() == 0) {
						pstmt2.setNull(2, Types.INTEGER);
					} else {
						pstmt2.setInt(2, expenditure.getExpenditureRequestId());
					}
					pstmt2.setInt(3, expenditure.getDepartmentId());
					pstmt2.setDate(4, new java.sql.Date(expenditure.getExpenditureDate().getTime()));
					pstmt2.setInt(5, expenditure.getAmount());
					pstmt2.setInt(6, expenditure.getCategoryId());
					pstmt2.setString(7, expenditure.getDescription());
					pstmt2.setInt(8, expenditure.getYear());

					int departmentId = expenditure.getDepartmentId();
					int categoryId = expenditure.getCategoryId();

					int[] departmentIds = { 1, 2, 3 }; // 허용된 부서 ID

					if (!contains(departmentIds, departmentId)) {
						throw new IllegalArgumentException("존재하지 않는 부서 ID입니다: " + departmentId);
					}

					int[] categoryIds = { 1, 2, 3 }; // 허용된 부서 ID

					if (!contains(categoryIds, categoryId)) {
						throw new IllegalArgumentException("존재하지 않는 부서 ID입니다: " + categoryId);
					}

					int year = expenditure.getYear();

					if (year < 1000 || year > 9999) {
						throw new IllegalArgumentException("연도는 4자리로 입력해주세요.");
					}

					pstmt2.executeUpdate();
				}

				try (PreparedStatement pstmt3 = conn.prepareStatement(updateBudgetSql)) {
					pstmt3.setInt(1, expenditure.getAmount());
					pstmt3.setInt(2, budgetId);
					pstmt3.executeUpdate();
				}

				conn.commit();
				System.out.println("지출 등록 및 예산 차감 성공");
			}

		} catch (SQLException e) {
			conn.rollback();
			System.err.println("에러 발생, 롤백 수행: " + e.getMessage());
			throw e;
		} finally {
			conn.setAutoCommit(true);
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

				// 부서 이름, 카테고리 이름 추가
				int departmentId = rs.getInt("department_id");
				int categoryId = rs.getInt("category_id");
				String departmentName = getDepartmentNameById(departmentId);
				String categoryName = getCategoryNameById(categoryId);

				expenditure.setDepartmentName(departmentName);
				expenditure.setCategoryName(categoryName);

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
			pstmt.setInt(1, requestId);
			try (ResultSet rs = pstmt.executeQuery()) {

				boolean hasData = false;
				while (rs.next()) {
					hasData = true;

					Expenditure expenditure = new Expenditure();
					expenditure.setExpenditureId(rs.getInt("expenditure_id"));
					expenditure.setExpenditureRequestId(rs.getInt("expenditure_request_id"));
					expenditure.setDepartmentId(rs.getInt("department_id"));
					expenditure.setExpenditureDate(rs.getDate("expenditure_date"));
					expenditure.setAmount(rs.getInt("amount"));
					expenditure.setCategoryId(rs.getInt("category_id"));
					expenditure.setDescription(rs.getString("description"));
					expenditure.setYear(rs.getInt("year"));

					// 부서 이름, 카테고리 이름 추가
					int departmentId = rs.getInt("department_id");
					int categoryId = rs.getInt("category_id");
					String departmentName = getDepartmentNameById(departmentId);
					String categoryName = getCategoryNameById(categoryId);

					expenditure.setDepartmentName(departmentName);
					expenditure.setCategoryName(categoryName);

					list.add(expenditure);
				}

				if (!hasData) {
					throw new SQLException("해당 조건에 맞는 지출이 존재하지 않습니다.");
				}
			}
		}

		return list;
	}

	// 특정 지출 수정
	public void updateByExpenditureId(Expenditure expenditure, int requestId) throws SQLException {
		String sql = "UPDATE expenditure SET description = ? WHERE expenditure_id = ?";
		String selectSql = "SELECT * FROM expenditure WHERE expenditure_id = ? AND del_yn IN ('N', 'n')";

		try (PreparedStatement pstmt = conn.prepareStatement(sql);
				PreparedStatement pstmt1 = conn.prepareStatement(selectSql)) {
			pstmt1.setInt(1, requestId);

			try (ResultSet rs = pstmt1.executeQuery()) {
				if (!rs.next()) {
					throw new SQLException("해당 조건에 맞는 지출이 존재하지 않습니다.");
				}

				pstmt.setString(1, expenditure.getDescription());
				pstmt.setInt(2, expenditure.getExpenditureId());

				pstmt.executeUpdate();
				System.out.println("지출이 수정되었습니다.");
			}
		}
	}

	// 지출 소프트딜리트
	public void softDeleteByExpenditureId(int requestId) throws SQLException {
		String sql = "UPDATE expenditure SET del_yn = 'Y' WHERE expenditure_id = ?";
		String selectSql = "SELECT * FROM expenditure WHERE expenditure_id = ? AND del_yn IN ('N', 'n')";

		try (PreparedStatement pstmt = conn.prepareStatement(sql);
				PreparedStatement pstmt1 = conn.prepareStatement(selectSql)) {

			pstmt1.setInt(1, requestId);

			try (ResultSet rs = pstmt1.executeQuery()) { // 그리고 실행

				if (!rs.next()) {
					throw new SQLException("해당 조건에 맞는 지출이 존재하지 않습니다.");
				}

				pstmt.setInt(1, requestId);

				pstmt.executeUpdate();
				System.out.println("지출이 소프트 삭제되었습니다.");
			}

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

	// 카테고리ID로 카테고리명 찾는 메서드
	public String getCategoryNameById(int categoryId) throws SQLException {
		String sql = "SELECT category_name FROM vw_category_name WHERE category_id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, categoryId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getString("category_name");
				} else {
					throw new SQLException("카테고리명을 가져오지 못했습니다.");
				}
			}
		}
	}

	// 부서ID로 부서명 찾는 메서드
	public String getDepartmentNameById(int departmentId) throws SQLException {
		String sql = "SELECT department_name FROM vw_department_name WHERE department_id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, departmentId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getString("department_name");
				} else {
					throw new SQLException("부서명을 가져오지 못했습니다.");
				}
			}
		}
	}

	// 존재하는 값인지 아닌지 비교 (부서ID, 카테고리 ID)
	private boolean contains(int[] arr, int value) {
		for (int num : arr) {
			if (num == value) {
				return true;
			}
		}
		return false;
	}
}
