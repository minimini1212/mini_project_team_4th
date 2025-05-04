package stockManagement.stock.model.dao;

import stockManagement.stock.model.entity.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDao {
    private final Connection conn;

    public StockDao(Connection conn) {
        this.conn = conn;
    }

    public void insert(Stock stock) {
        String sql = "INSERT INTO stock (stock_id, item_id, quantity) VALUES (seq_stock_id.NEXTVAL, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, stock.getItemId());
            pstmt.setInt(2, stock.getQuantity());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("재고 등록 중 오류 발생", e);
        }
    }

    public Stock findByItemId(int itemId) {
        String sql = "SELECT * FROM stock WHERE item_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, itemId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("재고 조회 중 오류", e);
        }
        return null;
    }

    public void updateQuantity(int itemId, int qtyChange) {
        String sql = "UPDATE stock SET quantity = quantity + ? WHERE item_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, qtyChange);
            pstmt.setInt(2, itemId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("재고 수량 수정 중 오류", e);
        }
    }

    private Stock mapRow(ResultSet rs) throws SQLException {
        Stock stock = new Stock();
        stock.setStockId(rs.getInt("stock_id"));
        stock.setItemId(rs.getInt("item_id"));
        stock.setQuantity(rs.getInt("quantity"));
        return stock;
    }
}