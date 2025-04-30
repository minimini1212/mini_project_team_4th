package stockManagement.stock.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Stock {
    private int stockId;
    private int quantity;

    private int itemId;
}
