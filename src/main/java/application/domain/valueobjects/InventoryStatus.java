package application.domain.valueobjects;

public final class InventoryStatus extends DomainCatalog {

    public static final InventoryStatus AVAILABLE = new InventoryStatus(
            "AVAILABLE", "Available", "Existencias disponibles para reserva y venta.");
    public static final InventoryStatus RESERVED = new InventoryStatus(
            "RESERVED", "Reserved", "Existencias comprometidas para un pedido en curso.");
    public static final InventoryStatus DAMAGED = new InventoryStatus(
            "DAMAGED", "Damaged", "Existencias no aptas para la venta.");

    private InventoryStatus(String code, String name, String description) {
        super(code, name, description);
    }
}