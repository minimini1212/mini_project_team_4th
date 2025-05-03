package stockManagement.order.model.service;

import stockManagement.order.model.dao.OrderDao;
import stockManagement.order.model.entity.Order;
import stockManagement.order.model.entity.OrderStatus;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
    private final OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    // 1. 발주 생성
    public void createOrder(Order order) throws SQLException {
        orderDao.insertOrder(order);
    }

    // 2. 발주 승인 (관리자만)
    public boolean approveOrder(int orderId, int adminId) throws SQLException {
        Order order = orderDao.findById(orderId);
        if (order == null || order.getStatus() != OrderStatus.REQUESTED) {
            return false;
        }
        orderDao.updateStatus(orderId, OrderStatus.APPROVED, adminId);
        return true;
    }

    // 3. 발주 거절 (관리자만)
    public boolean rejectOrder(int orderId, int adminId) throws SQLException {
        Order order = orderDao.findById(orderId);
        if (order == null || order.getStatus() != OrderStatus.REQUESTED) {
            return false;
        }
        orderDao.updateStatus(orderId, OrderStatus.REJECTED, adminId);
        return true;
    }

    // 4. 발주 취소 (요청자만)
    public boolean cancelOrder(int orderId, int requesterId) throws SQLException {
        Order order = orderDao.findById(orderId);
        if (order == null || order.getRequesterId() != requesterId || order.getStatus() != OrderStatus.REQUESTED) {
            return false;
        }
        orderDao.updateStatus(orderId, OrderStatus.CANCELLED, null);
        return true;
    }

    // 5. 발주 수정 (승인 전인 경우만)
    public boolean modifyOrder(Order newOrderData, int requesterId) throws SQLException {
        Order order = orderDao.findById(newOrderData.getOrderId());
        if (order == null || order.getRequesterId() != requesterId || order.getStatus() != OrderStatus.REQUESTED) {
            return false;
        }
        orderDao.updateOrder(newOrderData);
        return true;
    }

    // 6. 전체 목록 조회
    public List<Order> getAllOrders() throws SQLException {
        return orderDao.findAll();
    }

    // 7. 상태별 조회
    public List<Order> getOrdersByStatus(OrderStatus status) throws SQLException {
        return orderDao.findByStatus(status);
    }
}
