package stockManagement.order.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Order {

    private int orderId;
    private int quantity;
    private int approverId;
    private LocalDateTime requestDate;
    private LocalDateTime approvalDate;
    private OrderStatus status;

    private int itemId;
    private int requesterId;

    public Order() {
        this.status = OrderStatus.REQUESTED;
        this.requestDate = LocalDateTime.now();
    }
}
