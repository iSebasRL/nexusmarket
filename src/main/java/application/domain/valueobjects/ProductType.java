package application.domain.valueobjects;

public final class ProductType extends DomainCatalog {

    public static final ProductType PHYSICAL = new ProductType(
            "PHYSICAL", "Physical Product", "Producto que requiere inventario y despacho físico.");
    public static final ProductType DIGITAL = new ProductType(
            "DIGITAL", "Digital Product", "Producto de entrega inmediata tras la confirmación del pago.");

    private ProductType(String code, String name, String description) {
        super(code, name, description);
    }
}