package stockManagement.item.model.service;

import stockManagement.item.model.dao.ItemDao;
import stockManagement.item.model.entity.Item;
import stockManagement.stock.model.dao.StockDao;
import stockManagement.stock.model.entity.Stock;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ItemService {
    private final ItemDao itemDao;
    private final StockDao stockDao;

    public ItemService(ItemDao itemDao, StockDao stockDao) {
        this.itemDao = itemDao;
        this.stockDao = stockDao;
    }

    // 1. 아이템 생성 시 재고도 함께 생성
    public void createItem(Item item, int initialQuantity) {
        itemDao.insert(item);

        Stock stock = new Stock();
        stock.setItemId(item.getItemId());
        stock.setQuantity(initialQuantity);
        stockDao.insert(stock);
    }

    public void updateItem(Item item) {
        itemDao.update(item);
    }

    public void deleteItem(int itemId) {
        itemDao.delete(itemId);
    }

    public List<Item> findAll() {
        return itemDao.findAll();
    }

    public Item findById(int id) {
        return itemDao.findById(id);
    }

    public List<Item> searchItems(String keyword) {
        return itemDao.searchByKeyword(keyword);
    }

}