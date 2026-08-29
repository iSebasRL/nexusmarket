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

# InventoryStatus

## Descripción

Representa la condición de las existencias registradas en una bodega.

Determina si las existencias pueden ser reservadas y vendidas.

## Hereda de

`DomainCatalog`

## Valores permitidos

| Código | Nombre | Descripción |
| ------ | ------ | ----------- |
| AVAILABLE | Available | Existencias disponibles para reserva y venta. |
| RESERVED | Reserved | Existencias comprometidas para un pedido en curso. |
| DAMAGED | Damaged | Existencias no aptas para la venta. |

---

# MovementType

## Descripción

Representa la naturaleza de un movimiento realizado sobre el inventario.

Cada movimiento afecta las existencias asociadas a un producto y una bodega específica.

## Hereda de

`DomainCatalog`

## Valores permitidos

| Código | Nombre | Descripción |
| ------ | ------ | ----------- |
| INBOUND | Inbound | Ingreso de existencias a una bodega. |
| RESERVATION | Reservation | Compromiso de existencias para un pedido en curso. |
| SALE_OUTBOUND | Sale Outbound | Salida de existencias por venta confirmada. |
| ADJUSTMENT | Adjustment | Corrección de existencias registradas en una bodega. |
| RETURN | Return | Reingreso de existencias por devolución de un pedido. |

---

# OrderStatus

## Descripción

Representa la etapa del ciclo de vida de un pedido dentro del marketplace.

El pedido avanza de forma secuencial a través de estas etapas hasta su finalización.

## Hereda de

`DomainCatalog`

## Valores permitidos

| Código | Nombre | Descripción |
| ------ | ------ | ----------- |
| CART | Cart | Selección provisional de productos realizada por el comprador. |
| PENDING_PAYMENT | Pending Payment | Pedido a la espera de la confirmación financiera. |
| PAID | Paid | Pago confirmado e inicio de los procesos de alistamiento. |
| SHIPPED | Shipped | Salida física del pedido desde la bodega. |
| DELIVERED | Delivered | Entrega confirmada y conclusión del pedido. |

---

# OperationType

## Descripción

Representa la categoría de una operación significativa ejecutada dentro del marketplace.

Toda operación relevante realizada por un usuario debe quedar registrada con su tipo correspondiente para efectos de trazabilidad y seguimiento.

## Hereda de

`DomainCatalog`

## Valores permitidos

| Código | Nombre | Descripción |
| ------ | ------ | ----------- |
| SELLER_REGISTRATION | Seller Registration | Incorporación de un vendedor por parte del administrador. |
| WAREHOUSE_REGISTRATION | Warehouse Registration | Registro de una bodega asociada a un vendedor o al marketplace. |
| PRODUCT_REGISTRATION | Product Registration | Registro de un producto en el catálogo. |
| PRODUCT_PUBLICATION | Product Publication | Publicación de un producto en el catálogo público. |
| PRODUCT_SUSPENSION | Product Suspension | Suspensión temporal de un producto del catálogo. |
| PRODUCT_DISCONTINUATION | Product Discontinuation | Retiro definitivo de un producto del catálogo. |
| INVENTORY_INBOUND | Inventory Inbound | Ingreso de existencias a una bodega. |
| INVENTORY_RESERVATION | Inventory Reservation | Reserva de existencias para un pedido en curso. |
| INVENTORY_ADJUSTMENT | Inventory Adjustment | Ajuste de las existencias registradas en una bodega. |
| ORDER_CREATION | Order Creation | Confirmación de un pedido por parte del comprador. |
| ORDER_PAYMENT | Order Payment | Confirmación del pago de un pedido. |
| ORDER_DISPATCH | Order Dispatch | Despacho de un pedido desde la bodega. |
| ORDER_DELIVERY | Order Delivery | Confirmación de la entrega de un pedido. |
| INVOICE_ISSUANCE | Invoice Issuance | Emisión de la factura asociada a un pedido. |
| RETURN_REQUEST | Return Request | Solicitud de devolución realizada por un comprador. |
| RETURN_APPROVAL | Return Approval | Aprobación de una solicitud de devolución. |
| REFUND_EXECUTION | Refund Execution | Ejecución del reembolso asociado a una devolución. |

---

# Currency

## Descripción

Representa la moneda en la que se expresan los valores monetarios del marketplace.

## Hereda de

`DomainCatalog`

## Atributos adicionales

| Atributo | Tipo | Descripción |
| -------- | ---- | ----------- |
| isoCode | String | Código de la moneda según el estándar ISO 4217. |
| symbol | String | Símbolo utilizado para representar la moneda. |

## Valores permitidos

| Código | Nombre | Código ISO | Símbolo |
| ------ | ------ | ---------- | ------- |
| COP | Colombian Peso | COP | $ |
| USD | United States Dollar | USD | $ |
| EUR | Euro | EUR | € |

---

