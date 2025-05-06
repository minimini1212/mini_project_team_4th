package budgetAccounting.budgetRequest.view;

public class BudgetRequestView {

//	public static void main(String[] args) {
//        System.out.println("========== 예산 회계 시스템에 오신 것을 환영합니다 ==========");
//
//        try {
//        	BudgetRequestView budgetRequestView = new BudgetRequestView();
//            BudgetRequestController controller = new BudgetRequestController(budgetRequestView);
//            controller.run(); // 메인 메뉴 및 기능 실행
//        } catch (Exception e) {
//            System.err.println("서버 오류: " + e.getMessage());
//        }
//    }
	
	public void menu() {
		System.out.println("\n==== 예산 신청 관리 ====");
		System.out.println("1. 예산 신청 등록");
		System.out.println("2. 예산 신청 전체 조회");
		System.out.println("3. 특정 예산 신청 조회");
		System.out.println("4. 예산 신청 승인 및 예산 등록");
		System.out.println("5. 예산 신청 수정");
		System.out.println("6. 예산 신청 삭제");
		System.out.println("0. 뒤로 가기");
		System.out.print("선택: ");
	}
	
}
