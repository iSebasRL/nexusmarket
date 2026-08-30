package application.domain.valueobjects;

public final class ProductStatus extends DomainCatalog {

    public static final ProductStatus PUBLISHED = new ProductStatus(
            "PUBLISHED", "Published", "El producto es visible en el catálogo y puede ser adquirido.");
    public static final ProductStatus SUSPENDED = new ProductStatus(
            "SUSPENDED", "Suspended", "El producto no es visible temporalmente en el catálogo.");
    public static final ProductStatus DISCONTINUED = new ProductStatus(
            "DISCONTINUED", "Discontinued", "El producto ha sido retirado definitivamente del catálogo.");

    private ProductStatus(String code, String name, String description) {
        super(code, name, description);
    }
}