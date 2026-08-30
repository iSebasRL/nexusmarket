package application.domain.valueobjects;

public final class OrderStatus extends DomainCatalog {

    public static final OrderStatus CART = new OrderStatus(
            "CART", "Cart", "Selección provisional de productos realizada por el comprador.");
    public static final OrderStatus PENDING_PAYMENT = new OrderStatus(
            "PENDING_PAYMENT", "Pending Payment", "Pedido a la espera de la confirmación financiera.");
    public static final OrderStatus PAID = new OrderStatus(
            "PAID", "Paid", "Pago confirmado e inicio de los procesos de alistamiento.");
    public static final OrderStatus SHIPPED = new OrderStatus(
            "SHIPPED", "Shipped", "Salida física del pedido desde la bodega.");
    public static final OrderStatus DELIVERED = new OrderStatus(
            "DELIVERED", "Delivered", "Entrega confirmada y conclusión del pedido.");

    private OrderStatus(String code, String name, String description) {
        super(code, name, description);
    }
}