package stockManagement.item.model.service;

import stockManagement.item.model.dao.ItemDao;
import stockManagement.item.model.entity.Item;
import stockManagement.stock.model.dao.StockDao;
import stockManagement.stock.model.entity.Stock;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ItemService {
    private final Connection conn;
    private final ItemDao itemDao;
    private final StockDao stockDao;

    public ItemService(Connection conn, ItemDao itemDao, StockDao stockDao) {
        this.conn = conn;
        this.itemDao = itemDao;
        this.stockDao = stockDao;
    }

    // 1. 물품 생성 + 재고 생성
    public void createItem(Item item, int initialStock) throws SQLException {
        // 1. item insert + commit
        itemDao.insertItem(item);
        conn.commit(); // 이 시점에서 item_id가 DB에 확정됨

        // 2. item_id 조회 (방금 insert한 item)
        Item saved = itemDao.findItemByCode(item.getItemCode());
        if (saved == null) throw new IllegalStateException("item insert 후 조회 실패");
        item.setItemId(saved.getItemId()); // item 객체에 다시 반영

        // 3. stock insert (item_id 존재 보장됨)
        Stock stock = new Stock();
        stock.setItemId(item.getItemId());
        stock.setQuantity(initialStock);
        stockDao.insertStock(stock);

        conn.commit();
        System.out.println("물품이 등록되었습니다");
    }


    // 2. 물품 수정
    public void updateItem(Item item) throws SQLException {
        itemDao.updateItem(item);
    }

    // 3. 물품 삭제
    public void deleteItem(int itemId) throws SQLException {
        itemDao.deleteItem(itemId);
    }

    // 4. 전체 조회
    public List<Item> getAllItems() throws SQLException {
        return itemDao.findAll();
    }

    // 5. 검색 (이름/카테고리)
    public List<Item> searchItems(String keyword) throws SQLException {
        return itemDao.search(keyword);
    }

    public Item findItemById(int itemId) throws SQLException {
        return itemDao.findItemById(itemId);
    }

}