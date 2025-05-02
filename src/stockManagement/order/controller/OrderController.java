package stockManagement.order.controller;

import stockManagement.order.model.entity.Order;
import stockManagement.order.model.entity.OrderStatus;
import stockManagement.order.model.service.OrderService;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class OrderController {
    private final OrderService orderService;
    private final Scanner scanner;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            printMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            try {
                switch (choice) {
                    case 1 -> createOrder();
                    case 2 -> approveOrder();
                    case 3 -> rejectOrder();
                    case 4 -> cancelOrder();
                    case 5 -> modifyOrder();
                    case 6 -> listAllOrders();
                    case 7 -> listOrdersByStatus();
                    case 0 -> {
                        System.out.println("종료합니다.");
                        return;
                    }
                    default -> System.out.println("잘못된 입력입니다.");
                }
            } catch (Exception e) {
                System.out.println("오류 발생: " + e.getMessage());
            }
        }
    }

    private void printMenu() {
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

    private void createOrder() throws SQLException {
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

    private void approveOrder() throws SQLException {
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

    private void rejectOrder() throws SQLException {
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

    private void cancelOrder() throws SQLException {
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

    private void modifyOrder() throws SQLException {
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
        System.out.println("\n전체 발주 목록:");
        for (Order o : list) {
            printOrder(o);
        }
    }

    private void listOrdersByStatus() throws SQLException {
        System.out.print("조회할 상태 (REQUESTED / APPROVED / REJECTED / CANCELLED): ");
        OrderStatus status = OrderStatus.valueOf(scanner.nextLine().trim().toUpperCase());
        List<Order> list = orderService.getOrdersByStatus(status);
        for (Order o : list) {
            printOrder(o);
        }
    }

    private void printOrder(Order o) {
        System.out.println("---------------");
        System.out.println("ID: " + o.getOrderId());
        System.out.println("품목 ID: " + o.getItemId());
        System.out.println("수량: " + o.getQuantity());
        System.out.println("요청자: " + o.getRequesterId());
        System.out.println("승인자: " + o.getApproverId());
        System.out.println("요청일: " + o.getRequestDate());
        System.out.println("승인일: " + o.getApprovalDate());
        System.out.println("상태: " + o.getStatus());
    }
}
