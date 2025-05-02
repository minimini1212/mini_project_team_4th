package stockManagement.view;

import dbConn.ConnectionSingletonHelper;
import hr.employee.model.entity.Employee;
import stockManagement.item.controller.ItemController;
import stockManagement.item.model.dao.ItemDao;
import stockManagement.item.model.service.ItemService;
import stockManagement.order.controller.OrderController;
import stockManagement.order.model.dao.OrderDao;
import stockManagement.order.model.service.OrderService;
import stockManagement.stock.controller.StockController;
import stockManagement.stock.model.dao.StockDao;
import stockManagement.stock.model.service.StockService;


import java.sql.Connection;
import java.util.Scanner;


public class StockManagementView {

    private final Connection conn;

    public StockManagementView(Connection conn) {
        this.conn = conn;
    }

    public void run(Scanner scanner, String role, Employee emp) {
        try {
            // 2. DAO/Service 객체 생성
            ItemDao itemDao = new ItemDao(conn);
            OrderDao orderDao = new OrderDao(conn);
            StockDao stockDao = new StockDao(conn);

            OrderService orderService = new OrderService(orderDao);
            ItemService itemService = new ItemService(conn, itemDao, stockDao);
            StockService stockService = new StockService(stockDao);

            // 3. 컨트롤러 객체 생성
            OrderController orderController = new OrderController(orderService);
            ItemController itemController = new ItemController(itemService);
            StockController stockController = new StockController(stockService);

            // 4. 사용자 메뉴 선택
            while (true) {
                System.out.println("\n====== 병원 재고 시스템 ======");
                System.out.println("1. 물품 관리");
                System.out.println("2. 재고 관리");
                System.out.println("3. 입출 기록 관리");
                System.out.println("4. 발주 관리");
                System.out.println("0. 종료");
                System.out.print("메뉴 선택: ");

                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> itemController.run();
                    case 2 -> stockController.run();
                    case 4 -> orderController.run();
                    case 0 -> {
                        System.out.println("프로그램을 종료합니다.");
                        return;
                    }
                    default -> System.out.println("잘못된 입력입니다.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
