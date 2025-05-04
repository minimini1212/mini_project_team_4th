package stockManagement.stock.view;

import stockManagement.stock.model.entity.Stock;

import java.util.Scanner;

public class StockView {
    public void showMenu() {
        System.out.println("\n===== 재고 관리 =====");
        System.out.println("1. 재고 확인");
        System.out.println("2. 재고 수량 갱신");
        System.out.println("0. 뒤로 가기");
        System.out.print("선택: ");
    }

    public int getItemIdInput(Scanner scanner) {
        System.out.print("물품 ID 입력: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public int getQuantityChangeInput(Scanner scanner) {
        System.out.print("변경 수량 (입고: 양수, 출고: 음수): ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void printStock(Stock stock) {
        if (stock == null) {
            System.out.println("❌ 해당 물품의 재고 정보를 찾을 수 없습니다.");
        } else {
            System.out.println("\n재고 정보:");
            System.out.println("재고 ID: " + stock.getStockId());
            System.out.println("물품 ID: " + stock.getItemId());
            System.out.println("현재 수량: " + stock.getQuantity());
        }
    }

    public void showSuccess(String message) {
        System.out.println("✅ " + message);
    }

    public void showError(String message) {
        System.out.println("❌ " + message);
    }

    public void showInvalidInput() {
        System.out.println("⚠ 잘못된 입력입니다.");
    }
}
