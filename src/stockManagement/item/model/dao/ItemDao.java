package stockManagement.item.model.dao;

import stockManagement.item.model.entity.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDao {
    private final Connection conn;

    public ItemDao(Connection conn) {
        this.conn = conn;
    }

    public void insert(Item item) {
        String sql = "INSERT INTO item (item_id, item_code, item_name, category) VALUES (seq_item_id.NEXTVAL, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, item.getItemCode());
            pstmt.setString(2, item.getItemName());
            pstmt.setString(3, item.getCategory());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("물품 등록 중 오류 발생", e);
        }
    }

    public void update(Item item) {
        String sql = "UPDATE item SET item_code = ?, item_name = ?, category = ? WHERE item_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, item.getItemCode());
            pstmt.setString(2, item.getItemName());
            pstmt.setString(3, item.getCategory());
            pstmt.setInt(4, item.getItemId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("물품 수정 중 오류 발생", e);
        }
    }

    public void delete(int itemId) {
        String sql = "DELETE FROM item WHERE item_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, itemId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("물품 삭제 중 오류 발생", e);
        }
    }

    public List<Item> findAll() {
        String sql = "SELECT * FROM item ORDER BY item_id";
        List<Item> list = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("물품 목록 조회 중 오류", e);
        }
        return list;
    }

    public Item findById(int id) {
        String sql = "SELECT * FROM item WHERE item_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("물품 ID 조회 중 오류", e);
        }
        return null;
    }

    public List<Item> searchByKeyword(String keyword) {
        String sql = "SELECT * FROM item WHERE item_name LIKE ? OR category LIKE ? OR item_code LIKE ?";
        List<Item> list = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String query = "%" + keyword + "%";
            pstmt.setString(1, query);
            pstmt.setString(2, query);
            pstmt.setString(3, query);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("검색 중 오류 발생", e);
        }
        return list;
    }

    private Item mapRow(ResultSet rs) throws SQLException {
        Item item = new Item();
        item.setItemId(rs.getInt("item_id"));
        item.setItemCode(rs.getString("item_code"));
        item.setItemName(rs.getString("item_name"));
        item.setCategory(rs.getString("category"));
        return item;
    }
}