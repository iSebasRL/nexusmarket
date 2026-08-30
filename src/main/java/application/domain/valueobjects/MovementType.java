package application.domain.valueobjects;

public final class MovementType extends DomainCatalog {

    public static final MovementType INBOUND = new MovementType(
            "INBOUND", "Inbound", "Ingreso de existencias a una bodega.");
    public static final MovementType RESERVATION = new MovementType(
            "RESERVATION", "Reservation", "Compromiso de existencias para un pedido en curso.");
    public static final MovementType SALE_OUTBOUND = new MovementType(
            "SALE_OUTBOUND", "Sale Outbound", "Salida de existencias por venta confirmada.");
    public static final MovementType ADJUSTMENT = new MovementType(
            "ADJUSTMENT", "Adjustment", "Corrección de existencias registradas en una bodega.");
    public static final MovementType RETURN = new MovementType(
            "RETURN", "Return", "Reingreso de existencias por devolución de un pedido.");

    private MovementType(String code, String name, String description) {
        super(code, name, description);
    }
}