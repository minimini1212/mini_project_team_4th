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

    // 1. 재고 생성 (Item 생성 시)
    public void insertStock(Stock stock) throws SQLException {
        String sql = "INSERT INTO stock (stock_id, item_id, quantity) VALUES (seq_stock_id.NEXTVAL, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, stock.getItemId());
            pstmt.setInt(2, stock.getQuantity());
            pstmt.executeUpdate();
        }
    }


    // 2. itemId로 재고 조회
    public Stock findByItemId(int itemId) throws SQLException {
        String sql = "SELECT * FROM stock WHERE item_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, itemId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
        }
        return null;
    }

    // 3. 전체 재고 목록
    public List<Stock> findAll() throws SQLException {
        String sql = "SELECT * FROM stock";
        List<Stock> list = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }
        return list;
    }

    // 4. 재고 수량 변경 (입고/출고)
    public void updateQuantity(int itemId, int changeAmount) throws SQLException {
        String sql = "UPDATE stock SET quantity = quantity + ? WHERE item_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, changeAmount); // 양수면 IN, 음수면 OUT
            pstmt.setInt(2, itemId);
            pstmt.executeUpdate();
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