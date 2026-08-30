package application.domain.models;

import application.domain.valueobjects.InventoryStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Inventory extends BusinessEntity {
    private Product product;
    private Warehouse warehouse;
    private Integer quantity;
    private InventoryStatus inventoryStatus;
}