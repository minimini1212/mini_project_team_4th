package stockManagement.item.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stockManagement.order.model.entity.Order;
import stockManagement.stock.model.entity.Stock;
import stockManagement.stockHistory.model.entity.StockHistory;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
//    public final String ClassName = "Item";

    private int itemId;
    private String itemCode;
    private String itemName;
    private String category;
//    private String unit;

    private Stock stock;
    private List<Order> orders = new ArrayList<>();
    private List<StockHistory> stockHistories = new ArrayList<>();
}
