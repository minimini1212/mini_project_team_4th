package stockManagement.order.controller;

import stockManagement.order.model.entity.Order;
import stockManagement.order.model.entity.OrderStatus;
import stockManagement.order.model.service.OrderService;
import stockManagement.order.view.OrderView;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class OrderController {
    private final OrderService orderService;
    private final OrderView orderView = new OrderView();

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public void run(Scanner scanner) {
        while (true) {
            orderView.printMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            try {
                switch (choice) {
                    case 1 -> createOrder(scanner);
                    case 2 -> approveOrder(scanner);
                    case 3 -> rejectOrder(scanner);
                    case 4 -> cancelOrder(scanner);
                    case 5 -> modifyOrder(scanner);
                    case 6 -> listAllOrders();
                    case 7 -> listOrdersByStatus(scanner);
                    case 0 -> {
                        orderView.printExit();
                        return;
                    }
                    default -> orderView.printInvalid();
                }
            } catch (Exception e) {
                System.out.println("오류 발생: " + e.getMessage());
            }
        }
    }

    private void createOrder(Scanner scanner) throws SQLException {
        System.out.print("발주 ID: ");
        int orderId = Integer.parseInt(scanner.nextLine());

        System.out.print("품목 ID: ");
        int itemId = Integer.parseInt(scanner.nextLine());

        System.out.print("수량: ");
        int qty = Integer.parseInt(scanner.nextLine());

        System.out.print("요청자 ID: ");
        int requesterId = Integer.parseInt(scanner.nextLine());

        Order order = new Order();
        order.setOrderId(orderId);
        order.setItemId(itemId);
        order.setQuantity(qty);
        order.setRequesterId(requesterId);
        order.setRequestDate(LocalDateTime.now());
        order.setStatus(OrderStatus.REQUESTED);

        orderService.createOrder(order);
        System.out.println("발주가 등록되었습니다.");
    }

    private void approveOrder(Scanner scanner) throws SQLException {
        System.out.print("발주 ID: ");
        int orderId = Integer.parseInt(scanner.nextLine());
        System.out.print("관리자 ID: ");
        int adminId = Integer.parseInt(scanner.nextLine());

        if (orderService.approveOrder(orderId, adminId)) {
            System.out.println("발주가 승인되었습니다.");
        } else {
            System.out.println("승인할 수 없는 상태입니다.");
        }
    }

    private void rejectOrder(Scanner scanner) throws SQLException {
        System.out.print("발주 ID: ");
        int orderId = Integer.parseInt(scanner.nextLine());
        System.out.print("관리자 ID: ");
        int adminId = Integer.parseInt(scanner.nextLine());

        if (orderService.rejectOrder(orderId, adminId)) {
            System.out.println("발주가 거절되었습니다.");
        } else {
            System.out.println("거절할 수 없는 상태입니다.");
        }
    }

    private void cancelOrder(Scanner scanner) throws SQLException {
        System.out.print("발주 ID: ");
        int orderId = Integer.parseInt(scanner.nextLine());
        System.out.print("요청자 ID: ");
        int requesterId = Integer.parseInt(scanner.nextLine());

        if (orderService.cancelOrder(orderId, requesterId)) {
            System.out.println("발주가 취소되었습니다.");
        } else {
            System.out.println("취소할 수 없는 상태입니다.");
        }
    }

    private void modifyOrder(Scanner scanner) throws SQLException {
        System.out.print("발주 ID: ");
        int orderId = Integer.parseInt(scanner.nextLine());
        System.out.print("요청자 ID: ");
        int requesterId = Integer.parseInt(scanner.nextLine());
        System.out.print("변경할 품목 ID: ");
        int itemId = Integer.parseInt(scanner.nextLine());
        System.out.print("변경할 수량: ");
        int qty = Integer.parseInt(scanner.nextLine());

        Order newData = new Order();
        newData.setOrderId(orderId);
        newData.setItemId(itemId);
        newData.setQuantity(qty);

        if (orderService.modifyOrder(newData, requesterId)) {
            System.out.println("발주가 수정되었습니다.");
        } else {
            System.out.println("수정할 수 없는 상태입니다.");
        }
    }

    private void listAllOrders() throws SQLException {
        List<Order> list = orderService.getAllOrders();
        orderView.printOrderList(list);
    }

    private void listOrdersByStatus(Scanner scanner) throws SQLException {
        System.out.print("조회할 상태 (REQUESTED / APPROVED / REJECTED / CANCELLED): ");
        OrderStatus status = OrderStatus.valueOf(scanner.nextLine().trim().toUpperCase());
        List<Order> list = orderService.getOrdersByStatus(status);
        orderView.printOrderList(list);
    }
}
