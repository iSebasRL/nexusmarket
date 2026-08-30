package application.domain.valueobjects;

public final class SystemRole extends DomainCatalog {

    public static final SystemRole BUYER = new SystemRole(
            "BUYER", "Buyer", "Persona que adquiere productos publicados en el marketplace.");
    public static final SystemRole SELLER = new SystemRole(
            "SELLER", "Seller", "Responsable de registrar y administrar sus productos.");
    public static final SystemRole LOGISTICS_OPERATOR = new SystemRole(
            "LOGISTICS_OPERATOR", "Logistics Operator", "Encargado de la operación física de bodegas y despachos.");
    public static final SystemRole ADMINISTRATOR = new SystemRole(
            "ADMINISTRATOR", "Administrator", "Responsable de la administración de vendedores y bodegas.");
    public static final SystemRole SUPERVISOR = new SystemRole(
            "SUPERVISOR", "Supervisor", "Perfil de consulta y seguimiento operativo.");

    private SystemRole(String code, String name, String description) {
        super(code, name, description);
    }
}