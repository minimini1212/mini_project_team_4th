package stockManagement.stock.controller;

import stockManagement.item.controller.ItemController;
import stockManagement.item.model.dao.ItemDao;
import stockManagement.item.model.service.ItemService;
import stockManagement.order.controller.OrderController;
import stockManagement.order.model.service.OrderService;
import stockManagement.stock.model.dao.StockDao;
import stockManagement.stock.model.entity.Stock;
import stockManagement.stock.model.service.StockService;
import stockManagement.stock.view.StockView;
import stockManagement.view.StockManagementView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StockController {
    private final StockService stockService;
    private final StockView stockView = new StockView();

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    public void run(Scanner scanner) {
        while (true) {
            stockView.showMenu();
            String input = scanner.nextLine();
            if (input.isBlank()) continue;
            int choice = Integer.parseInt(input);

            switch (choice) {
                case 1 -> checkStock(scanner);
                case 2 -> updateStock(scanner);
                case 0 -> {
                    return;
                }
                default -> stockView.showInvalidInput();
            }
        }
    }

    private void checkStock(Scanner scanner) {
        int itemId = stockView.getItemIdInput(scanner);
        Stock stock = stockService.findByItemId(itemId);
        stockView.printStock(stock);
    }

    private void updateStock(Scanner scanner) {
        int itemId = stockView.getItemIdInput(scanner);
        int amount = stockView.getQuantityChangeInput(scanner);

        try {
            stockService.updateQuantity(itemId, amount);
            stockView.showSuccess("재고 수량이 업데이트되었습니다.");
        } catch (Exception e) {
            stockView.showError("재고 업데이트 중 오류 발생: " + e.getMessage());
        }
    }
}
