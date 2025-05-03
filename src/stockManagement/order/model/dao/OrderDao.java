package stockManagement.order.model.dao;

import stockManagement.order.model.entity.Order;
import stockManagement.order.model.entity.OrderStatus;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    private final Connection conn;

    public OrderDao(Connection conn) {
        this.conn = conn;
    }

    // 1. 발주 생성
    public void insertOrder(Order order) throws SQLException {
        String sql = "INSERT INTO orders (order_id, item_id, quantity, requester_id, request_date, status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, order.getOrderId());
            pstmt.setInt(2, order.getItemId());
            pstmt.setInt(3, order.getQuantity());
            pstmt.setInt(4, order.getRequesterId());
            pstmt.setTimestamp(5, Timestamp.valueOf(order.getRequestDate()));
            pstmt.setString(6, order.getStatus().name());
            pstmt.executeUpdate();
        }
    }

    // 2. 상태 변경
    public void updateStatus(int orderId, OrderStatus status, Integer approverId) throws SQLException {
        String sql = "UPDATE orders SET status = ?, approver_id = ?, approval_date = ? WHERE order_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status.name());
            pstmt.setInt(2, approverId != null ? approverId : 0);
            pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setInt(4, orderId);
            pstmt.executeUpdate();
        }
    }

    // 3. 발주 수정 (승인 전만)
    public void updateOrder(Order order) throws SQLException {
        String sql = "UPDATE orders SET item_id = ?, quantity = ? WHERE order_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, order.getItemId());
            pstmt.setInt(2, order.getQuantity());
            pstmt.setLong(3, order.getOrderId());
            pstmt.executeUpdate();
        }
    }

    // 4. 전체 목록
    public List<Order> findAll() throws SQLException {
        String sql = "SELECT * FROM orders ORDER BY request_date DESC";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            List<Order> list = new ArrayList<>();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
            return list;
        }
    }

    // 5. 상태별 조회
    public List<Order> findByStatus(OrderStatus status) throws SQLException {
        String sql = "SELECT * FROM orders WHERE status = ? ORDER BY request_date DESC";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status.name());
            ResultSet rs = pstmt.executeQuery();
            List<Order> list = new ArrayList<>();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
            return list;
        }
    }

    // 6. 단건 조회
    public Order findById(int orderId) throws SQLException {
        String sql = "SELECT * FROM orders WHERE order_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
            return null;
        }
    }

    // row → Order 매핑
    private Order mapRow(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setOrderId(rs.getInt("order_id"));
        order.setItemId(rs.getInt("item_id"));
        order.setQuantity(rs.getInt("quantity"));
        order.setRequesterId(rs.getInt("requester_id"));
        order.setApproverId(rs.getInt("approver_id"));
        order.setRequestDate(rs.getTimestamp("request_date").toLocalDateTime());

        Timestamp approvalTime = rs.getTimestamp("approval_date");
        if (approvalTime != null) {
            order.setApprovalDate(approvalTime.toLocalDateTime());
        }

        order.setStatus(OrderStatus.valueOf(rs.getString("status")));
        return order;
    }
}
