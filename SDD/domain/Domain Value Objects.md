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
---