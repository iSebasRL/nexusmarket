package application.domain.valueobjects;

public final class WarehouseType extends DomainCatalog {

    public static final WarehouseType MARKETPLACE = new WarehouseType(
            "MARKETPLACE", "Marketplace Warehouse", "Bodega administrada directamente por el marketplace.");
    public static final WarehouseType SELLER = new WarehouseType(
            "SELLER", "Seller Warehouse", "Bodega administrada por un vendedor.");

    private WarehouseType(String code, String name, String description) {
        super(code, name, description);
    }
}