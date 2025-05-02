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

    // 전체 재고 조회
    public List<Stock> getAllStocks() throws SQLException {
        return stockDao.findAll();
    }

    // 특정 itemId의 재고 조회
    public Stock getStockByItemId(int itemId) throws SQLException {
        return stockDao.findByItemId(itemId);
    }

    // 재고 수량 갱신 (입고/출고)
    public void updateStock(int itemId, int changeAmount) throws SQLException {
        stockDao.updateQuantity(itemId, changeAmount);
    }

    // 재고 생성 (itemService에서 사용)
    public void createStock(Stock stock) throws SQLException {
        stockDao.insertStock(stock);
    }
}
