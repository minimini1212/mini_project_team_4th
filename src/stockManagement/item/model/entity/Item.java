package stockManagement.item.model.entity;

import common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stockManagement.order.model.entity.Order;
import stockManagement.stock.model.entity.Stock;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item extends BaseEntity {
//    public final String ClassName = "Item";

    private int itemId;
    private String itemCode;
    private String itemName;
    private String category;

    private Stock stock;
    private List<Order> orders = new ArrayList<>();
//    private List<ConsumableUsage> consumableUsages = new ArrayList<>();
}
