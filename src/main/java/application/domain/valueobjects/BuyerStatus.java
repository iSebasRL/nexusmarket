package application.domain.valueobjects;

public final class BuyerStatus extends DomainCatalog {

    public static final BuyerStatus ACTIVE = new BuyerStatus(
            "ACTIVE", "Active", "El comprador está habilitado para realizar compras.");
    public static final BuyerStatus SUSPENDED = new BuyerStatus(
            "SUSPENDED", "Suspended", "El comprador existe pero no puede realizar compras temporalmente.");
    public static final BuyerStatus BLOCKED = new BuyerStatus(
            "BLOCKED", "Blocked", "La participación comercial del comprador ha sido suspendida.");

    private BuyerStatus(String code, String name, String description) {
        super(code, name, description);
    }
}