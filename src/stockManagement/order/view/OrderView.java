package stockManagement.order.view;

import stockManagement.order.model.entity.Order;

import java.util.List;

public class OrderView {
    public void printMenu() {
        System.out.println("\n===== 발주 관리 메뉴 =====");
        System.out.println("1. 발주 생성");
        System.out.println("2. 발주 승인 (관리자)");
        System.out.println("3. 발주 거절 (관리자)");
        System.out.println("4. 발주 취소 (요청자)");
        System.out.println("5. 발주 수정 (요청자)");
        System.out.println("6. 전체 발주 조회");
        System.out.println("7. 상태별 발주 조회");
        System.out.println("0. 종료");
        System.out.print("선택: ");
    }

    public void printExit() {
        System.out.println("발주 관리 메뉴를 종료합니다.");
    }

    public void printInvalid() {
        System.out.println("⚠ 잘못된 입력입니다.");
    }

    public void printOrderList(List<Order> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("❌ 조회된 발주가 없습니다.");
            return;
        }

        System.out.println("\n===== 발주 목록 =====");
        for (Order o : list) {
            System.out.println("---------------");
            System.out.println("ID: " + o.getOrderId());
            System.out.println("품목 ID: " + o.getItemId());
            System.out.println("수량: " + o.getQuantity());
            System.out.println("요청자: " + o.getRequesterId());
            System.out.println("승인자: " + (o.getApproverId() == 0 ? "-" : o.getApproverId()));
            System.out.println("요청일: " + o.getRequestDate());
            System.out.println("승인일: " + (o.getApprovalDate() == null ? "-" : o.getApprovalDate()));
            System.out.println("상태: " + o.getStatus());
        }
    }

    public void printSuccess(String message) {
        System.out.println("✅ " + message);
    }

    public void printError(String message) {
        System.out.println("❌ " + message);
    }
}
