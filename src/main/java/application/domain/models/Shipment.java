package application.domain.models;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Shipment extends BusinessEntity {
    private Order order;
    private Warehouse warehouse;
    private Address deliveryAddress;
    private LocalDateTime dispatchDate;
    private LocalDateTime deliveryDate;
    private User handledBy;
}