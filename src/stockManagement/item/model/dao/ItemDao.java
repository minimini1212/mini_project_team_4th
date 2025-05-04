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

    // 1. 물품 생성
    public void insertItem(Item item) throws SQLException {
        // 1. 시퀀스에서 미리 id 가져오기
        String getSeqSql = "SELECT seq_item_id.NEXTVAL FROM dual";
        try (PreparedStatement pstmt = conn.prepareStatement(getSeqSql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
//                int newId = rs.getInt(1);
                item.setItemId(rs.getInt(1));
            }
        }

        // 2. 그 id를 포함하여 직접 insert (트리거는 생략 가능)
        String sql = "INSERT INTO item (item_id, item_code, item_name, category) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, item.getItemId());
            stmt.setString(2, item.getItemCode());
            stmt.setString(3, item.getItemName());
            stmt.setString(4, item.getCategory());
            stmt.executeUpdate();
        }
    }


    // 2. 물품 수정
    public void updateItem(Item item) throws SQLException {
        String sql = "UPDATE item SET item_code = ?, item_name = ?, category = ? WHERE item_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, item.getItemCode());
            pstmt.setString(2, item.getItemName());
            pstmt.setString(3, item.getCategory());
            pstmt.setInt(4, item.getItemId());
            pstmt.executeUpdate();
        }
    }

    // 3. 물품 삭제
    public void deleteItem(int itemId) throws SQLException {
        String sql = "UPDATE item SET del_yn = 'Y' WHERE item_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, itemId);
            pstmt.executeUpdate();
        }
    }

    // 4. 전체 물품 조회
    public List<Item> findAll() throws SQLException {
        String sql = "SELECT * FROM item WHERE del_yn = 'N'";
        List<Item> result = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                result.add(mapRow(rs));
            }
        }
        return result;
    }

    // 5. 물품명/카테고리로 검색
    public List<Item> search(String keyword) throws SQLException {
        String sql = "SELECT * FROM item WHERE del_yn = 'N' AND (item_name LIKE ? OR category LIKE ? OR item_code LIKE ?)";
        List<Item> result = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            pstmt.setString(3, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result.add(mapRow(rs));
            }
        }
        return result;
    }

    private Item mapRow(ResultSet rs) throws SQLException {
        Item item = new Item();
        item.setItemId(rs.getInt("item_id"));
        item.setItemCode(rs.getString("item_code"));
        item.setItemName(rs.getString("item_name"));
        item.setCategory(rs.getString("category"));
        return item;
    }

    public Item findItemById(int itemId) throws SQLException {
        String sql = "SELECT * FROM item WHERE item_id = ? AND del_yn = 'N'";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, itemId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Item item = new Item();
                    item.setItemId(rs.getInt("item_id"));
                    item.setItemCode(rs.getString("item_code"));
                    item.setItemName(rs.getString("item_name"));
                    item.setCategory(rs.getString("category"));
                    return item;
                } else {
                    return null;
                }
            }
        }
    }

    public Item findItemByCode(String code) throws SQLException {
        String sql = "SELECT * FROM item WHERE item_code = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, code);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        }
        return null;
    }


}