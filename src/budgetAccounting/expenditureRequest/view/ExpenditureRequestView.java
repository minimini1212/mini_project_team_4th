package budgetAccounting.expenditureRequest.view;

public class ExpenditureRequestView {
//	public static void main(String[] args) {
//		System.out.println("========== 예산 회계 시스템에 오신 것을 환영합니다 ==========");
//
//		try {
//			ExpenditureRequestController controller = new ExpenditureRequestController();
//			controller.run(); // 메인 메뉴 및 기능 실행
//		} catch (Exception e) {
//			System.err.println("서버 오류: " + e.getMessage());
//		}
//	}
	
	public void menu() {
		System.out.println("\n==== 지출 신청 관리 ====");
		System.out.println("1. 지출 신청 등록");
		System.out.println("2. 지출 신청 전체 조회");
		System.out.println("3. 특정 지출 신청 조회");
		System.out.println("4. 지출 신청 승인 및 지출 등록");
		System.out.println("5. 지출 신청 수정");
		System.out.println("6. 지출 신청 삭제");
		System.out.println("0. 뒤로 가기");
		System.out.print("선택: ");
	}
}
