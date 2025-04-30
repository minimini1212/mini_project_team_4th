package stockManagement.stock.controller;

import stockManagement.stock.model.entity.Stock;
import stockManagement.stock.model.service.StockService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StockController {
    private final StockService stockService;
    private final Scanner scanner;

    public StockController(StockService stockService) {
        this.stockService = stockService;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            printMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            try {
                switch (choice) {
                    case 1 -> listAllStocks();
                    case 2 -> viewStockByItemId();
                    case 3 -> updateStockQuantity(); // IN/OUT 처리
                    case 0 -> {
                        System.out.println("재고 관리 종료");
                        return;
                    }
                    default -> System.out.println("잘못된 입력입니다.");
                }
            } catch (Exception e) {
                System.out.println("⚠오류 발생: " + e.getMessage());
            }
        }
    }

    private void printMenu() {
        System.out.println("\n===== 재고 관리 메뉴 =====");
        System.out.println("1. 전체 재고 조회");
        System.out.println("2. 특정 품목 재고 확인");
        System.out.println("3. 재고 수량 갱신 (IN/OUT)");
        System.out.println("0. 종료");
        System.out.print("선택: ");
    }

    private void listAllStocks() throws SQLException {
        List<Stock> stocks = stockService.getAllStocks();
        if (stocks.isEmpty()) {
            System.out.println("재고 데이터가 없습니다.");
        } else {
            for (Stock stock : stocks) {
                printStock(stock);
            }
        }
    }

    private void viewStockByItemId() throws SQLException {
        System.out.print("조회할 품목 ID: ");
        int itemId = Integer.parseInt(scanner.nextLine());
        Stock stock = stockService.getStockByItemId(itemId);
        if (stock == null) {
            System.out.println("해당 품목의 재고 정보가 없습니다.");
        } else {
            printStock(stock);
        }
    }

    private void updateStockQuantity() throws SQLException {
        System.out.print("품목 ID: ");
        int itemId = Integer.parseInt(scanner.nextLine());

        System.out.print("수량 변경 (양수: 입고 / 음수: 출고): ");
        int amount = Integer.parseInt(scanner.nextLine());

        stockService.updateStock(itemId, amount);
        System.out.println("재고가 갱신되었습니다.");
    }

    private void printStock(Stock stock) {
        System.out.println("---------------");
        System.out.println("Stock ID: " + stock.getStockId());
        System.out.println("Item ID: " + stock.getItemId());
        System.out.println("현재 수량: " + stock.getQuantity());
    }
}
