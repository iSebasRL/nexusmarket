# Domain Value Objects

## Introducción

Los Value Objects representan conceptos inmutables dentro del dominio del marketplace.

A diferencia de las Entidades, los Value Objects no tienen identidad propia. Se definen completamente por sus valores y se utilizan para encapsular conceptos de negocio controlados, mejorar la expresividad del dominio y evitar el uso de valores primitivos o cadenas de texto dispersas a lo largo de la aplicación.

El dominio del marketplace utiliza Value Objects para catálogos de negocio como roles, estados, tipos de producto, tipos de bodega, tipos de movimiento y monedas.

Todos los catálogos de negocio heredan de `DomainCatalog`.

---

# Jerarquía de Value Objects

```text
DomainCatalog (Abstract)
├── SystemRole
├── UserStatus
├── BuyerStatus
├── WarehouseType
├── ProductType
├── ProductStatus
├── InventoryStatus
├── MovementType
├── OrderStatus
├── OperationType
└── Currency
```

---

# SystemRole

## Descripción

Representa las responsabilidades y permisos asignados a una persona dentro del marketplace.

El rol es una característica de `Person` porque representa lo que la persona significa dentro del sistema y las responsabilidades asociadas a ella.

Cada usuario tiene un único rol dentro del sistema.

## Hereda de

`DomainCatalog`

## Valores permitidos

| Código | Nombre | Descripción |
| ------ | ------ | ----------- |
| BUYER | Buyer | Persona que adquiere productos publicados en el marketplace. |
| SELLER | Seller | Responsable de registrar y administrar sus productos. |
| LOGISTICS_OPERATOR | Logistics Operator | Encargado de la operación física de bodegas y despachos. |
| ADMINISTRATOR | Administrator | Responsable de la administración de vendedores y bodegas. |
| SUPERVISOR | Supervisor | Perfil de consulta y seguimiento operativo. |

---
# WarehouseType

## Descripción

Representa la clasificación de una bodega según la entidad responsable de su administración.

## Hereda de

`DomainCatalog`

## Valores permitidos

| Código | Nombre | Descripción |
| ------ | ------ | ----------- |
| MARKETPLACE | Marketplace Warehouse | Bodega administrada directamente por el marketplace. |
| SELLER | Seller Warehouse | Bodega administrada por un vendedor. |

---

# ProductType

## Descripción

Representa la naturaleza de un producto publicado en el catálogo.

Determina si el producto requiere inventario y despacho físico, o si su entrega es inmediata tras el pago.

## Hereda de

`DomainCatalog`

## Valores permitidos

| Código | Nombre | Descripción |
| ------ | ------ | ----------- |
| PHYSICAL | Physical Product | Producto que requiere inventario y despacho físico. |
| DIGITAL | Digital Product | Producto de entrega inmediata tras la confirmación del pago. |

---

# ProductStatus

## Descripción

Representa la condición de un producto dentro del catálogo del marketplace.

Determina si el producto es visible y puede ser adquirido por los compradores.

## Hereda de

`DomainCatalog`

## Valores permitidos

| Código | Nombre | Descripción |
| ------ | ------ | ----------- |
| PUBLISHED | Published | El producto es visible en el catálogo y puede ser adquirido. |
| SUSPENDED | Suspended | El producto no es visible temporalmente en el catálogo. |
| DISCONTINUED | Discontinued | El producto ha sido retirado definitivamente del catálogo. |

---