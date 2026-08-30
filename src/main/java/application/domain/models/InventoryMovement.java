package application.domain.models;

import application.domain.valueobjects.MovementType;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InventoryMovement extends BusinessEntity {
    private Inventory inventory;
    private MovementType movementType;
    private Integer quantity;
    private LocalDateTime movementDate;
    private User performedBy;
}