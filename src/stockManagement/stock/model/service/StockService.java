package stockManagement.stock.model.service;

import stockManagement.stock.model.dao.StockDao;
import stockManagement.stock.model.entity.Stock;

import java.sql.SQLException;
import java.util.List;

public class StockService {
    private final StockDao stockDao;

    public StockService(StockDao stockDao) {
        this.stockDao = stockDao;
    }

    public void createStock(Stock stock) {
        stockDao.insert(stock);
    }

    public Stock findByItemId(int itemId) {
        return stockDao.findByItemId(itemId);
    }

    public void updateQuantity(int itemId, int qtyChange) {
        stockDao.updateQuantity(itemId, qtyChange);
    }
}
