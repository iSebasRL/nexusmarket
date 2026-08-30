package application.domain.models;

import application.domain.valueobjects.Currency;
import application.domain.valueobjects.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Order extends BusinessEntity {
    private Buyer buyer;
    private List<OrderItem> items;
    private OrderStatus orderStatus;
    private BigDecimal totalAmount;
    private Currency currency;
    private Address deliveryAddress;
    private LocalDateTime creationDate;
    private Invoice invoice;
    private Shipment shipment;
}