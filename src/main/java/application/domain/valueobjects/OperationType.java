package application.domain.valueobjects;

public final class OperationType extends DomainCatalog {

    // Seller and warehouse operations
    public static final OperationType SELLER_REGISTRATION = new OperationType(
            "SELLER_REGISTRATION", "Seller Registration", "Incorporación de un vendedor por parte del administrador.");
    public static final OperationType WAREHOUSE_REGISTRATION = new OperationType(
            "WAREHOUSE_REGISTRATION", "Warehouse Registration", "Registro de una bodega asociada a un vendedor o al marketplace.");

    // Catalog operations
    public static final OperationType PRODUCT_REGISTRATION = new OperationType(
            "PRODUCT_REGISTRATION", "Product Registration", "Registro de un producto en el catálogo.");
    public static final OperationType PRODUCT_PUBLICATION = new OperationType(
            "PRODUCT_PUBLICATION", "Product Publication", "Publicación de un producto en el catálogo público.");
    public static final OperationType PRODUCT_SUSPENSION = new OperationType(
            "PRODUCT_SUSPENSION", "Product Suspension", "Suspensión temporal de un producto del catálogo.");
    public static final OperationType PRODUCT_DISCONTINUATION = new OperationType(
            "PRODUCT_DISCONTINUATION", "Product Discontinuation", "Retiro definitivo de un producto del catálogo.");

    // Inventory operations
    public static final OperationType INVENTORY_INBOUND = new OperationType(
            "INVENTORY_INBOUND", "Inventory Inbound", "Ingreso de existencias a una bodega.");
    public static final OperationType INVENTORY_RESERVATION = new OperationType(
            "INVENTORY_RESERVATION", "Inventory Reservation", "Reserva de existencias para un pedido en curso.");
    public static final OperationType INVENTORY_ADJUSTMENT = new OperationType(
            "INVENTORY_ADJUSTMENT", "Inventory Adjustment", "Ajuste de las existencias registradas en una bodega.");

    // Order operations
    public static final OperationType ORDER_CREATION = new OperationType(
            "ORDER_CREATION", "Order Creation", "Confirmación de un pedido por parte del comprador.");
    public static final OperationType ORDER_PAYMENT = new OperationType(
            "ORDER_PAYMENT", "Order Payment", "Confirmación del pago de un pedido.");
    public static final OperationType ORDER_DISPATCH = new OperationType(
            "ORDER_DISPATCH", "Order Dispatch", "Despacho de un pedido desde la bodega.");
    public static final OperationType ORDER_DELIVERY = new OperationType(
            "ORDER_DELIVERY", "Order Delivery", "Confirmación de la entrega de un pedido.");

    // Billing and post-sale operations
    public static final OperationType INVOICE_ISSUANCE = new OperationType(
            "INVOICE_ISSUANCE", "Invoice Issuance", "Emisión de la factura asociada a un pedido.");
    public static final OperationType RETURN_REQUEST = new OperationType(
            "RETURN_REQUEST", "Return Request", "Solicitud de devolución realizada por un comprador.");
    public static final OperationType RETURN_APPROVAL = new OperationType(
            "RETURN_APPROVAL", "Return Approval", "Aprobación de una solicitud de devolución.");
    public static final OperationType REFUND_EXECUTION = new OperationType(
            "REFUND_EXECUTION", "Refund Execution", "Ejecución del reembolso asociado a una devolución.");

    private OperationType(String code, String name, String description) {
        super(code, name, description);
    }
}