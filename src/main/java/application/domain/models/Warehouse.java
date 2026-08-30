package application.domain.models;

import application.domain.valueobjects.WarehouseType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Warehouse extends BusinessEntity {
    private String name;
    private WarehouseType warehouseType;
    private Address address;
    private Seller seller;
}