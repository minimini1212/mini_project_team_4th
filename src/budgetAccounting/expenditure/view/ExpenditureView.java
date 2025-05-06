package budgetAccounting.expenditure.view;

public class ExpenditureView {
//	public static void main(String[] args) {
//		System.out.println("========== 예산 회계 시스템에 오신 것을 환영합니다 ==========");
//
//		try {
//			ExpenditureView expenditureView = new ExpenditureView();
//			ExpenditureController controller = new ExpenditureController(expenditureView);
//			controller.run(); // 메인 메뉴 및 기능 실행
//		} catch (Exception e) {
//			System.err.println("서버 오류: " + e.getMessage());
//		}
//	}

	public void menu() {
		System.out.println();
		System.out.println("  ▌│█║▌║    𝙀 𝙓 𝙋 𝙀 𝙉 𝙎 𝙀    ║▌║█│▌");
		System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
		System.out.println();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println("1️⃣ 지출 등록");
		System.out.println("2️⃣ 지출 전체 조회");
		System.out.println("3️⃣ 특정 지출 조회");
		System.out.println("4️⃣ 지출 수정");
		System.out.println("5️⃣ 지출 삭제");
		System.out.println();
		System.out.print("⏩ ");
	}
}
