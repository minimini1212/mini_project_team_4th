package stockManagement.controller;

import stockManagement.item.controller.ItemController;
import stockManagement.item.model.service.ItemService;
import stockManagement.order.controller.OrderController;
import stockManagement.order.model.service.OrderService;
import stockManagement.stock.controller.StockController;
import stockManagement.stock.model.service.StockService;

import stockManagement.view.StockManagementView;

import java.util.Scanner;

public class StockManagementController {
//    private final ItemController itemController;
//    private final StockController stockController;
//    private final OrderController orderController;
//    private final ConsumableUsageController consumableUsageController;
//    private final StockManagementView view = new StockManagementView();
//
//    public StockManagementController(ItemService itemService, StockService stockService,
//                                     OrderService orderService, ConsumableUsageController consumableUsageController) {
//        this.itemController = new ItemController(itemService);
//        this.stockController = new StockController(stockService);
//        this.orderController = new OrderController(orderService);
//        this.consumableUsageController = new ConsumableUsageController(consumableUsageService);
//    }
//
//    public void run(Scanner scanner) {
//        while (true) {
//            view.showMainMenu();
//            String input = scanner.nextLine();
//            if (input.isBlank()) continue;
//            int choice = Integer.parseInt(input);
//            switch (choice) {
//                case 1 -> itemController.run(scanner);
//                case 2 -> stockController.run(scanner);
//                case 3 -> consumableUsageController.run(scanner);
//                case 4 -> orderController.run(scanner);
//                case 0 -> {
//                    System.out.println("메인 메뉴로 돌아갑니다.");
//                    return;
//                }
//                default -> view.showInvalid();
//            }
//        }
//    }
}
