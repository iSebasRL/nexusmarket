# Domain Model

## Introducción

El Modelo de Dominio representa las entidades centrales de negocio del sistema NexusMarket. Estas entidades encapsulan los datos, relaciones y conceptos de ciclo de vida descritos en la Especificación Funcional del Negocio.

El modelo sigue principios de Diseño Orientado a Objetos y Diseño Guiado por el Dominio (DDD). La herencia se utiliza para representar especialización genuina del dominio, y se prefieren las relaciones explícitas entre objetos por encima de campos genéricos de identificador.

El modelo distingue entre:

* **Personas**, que representan individuos identificables y su rol dentro del sistema.
* **Participantes**, que representan personas con una relación comercial con el marketplace.
* **Usuarios**, que representan identidades del sistema utilizadas para autenticación y autorización.
* **Entidades de Negocio**, que representan los objetos sobre los que se ejecutan las operaciones comerciales y logísticas.
* **Operaciones**, que representan acciones significativas ejecutadas sobre las entidades de negocio.
* **Registros de Auditoría**, que proporcionan un histórico inmutable de las operaciones.

Una entidad de negocio puede generar múltiples operaciones a lo largo de su ciclo de vida. Toda operación significativa debe quedar registrada en la auditoría.

---

# Jerarquía de Clases del Dominio

```text
Person (Abstract)
├── Participant (Abstract)
│   ├── Buyer
│   └── Seller
│
└── User

BusinessEntity (Abstract)
├── Warehouse
├── Product
├── Inventory
├── InventoryMovement
├── Cart
├── Order
├── Invoice
├── Shipment
├── Return
└── Refund

Address

ProductVariant

CartItem

OrderItem

Operation

AuditLog
```

---

# Relaciones del Dominio

```text
Person
   │
   ├── Participant
   │      ├── Buyer
   │      └── Seller
   │
   └── User
          │
          └── participant : Participant (opcional)

Buyer
   ├── mainAddress ────────> Address
   ├── additionalAddresses ─> Address
   ├── owns ───────────────> Cart
   └── places ─────────────> Order

Seller
   ├── owns ───────────────> Warehouse
   └── publishes ──────────> Product

Product
   ├── variants ───────────> ProductVariant
   └── seller ─────────────> Seller

Inventory
   ├── product ────────────> Product
   └── warehouse ──────────> Warehouse

InventoryMovement
   └── inventory ──────────> Inventory

Cart
   ├── buyer ──────────────> Buyer
   └── items ──────────────> CartItem

Order
   ├── buyer ──────────────> Buyer
   ├── items ──────────────> OrderItem
   ├── invoice ────────────> Invoice
   └── shipment ───────────> Shipment

Return
   ├── order ──────────────> Order
   └── refund ─────────────> Refund

BusinessEntity
   └── generates ──────────> Operation
                                │
                                └── recorded in ──────> AuditLog
```

---

# Entidades

---

# Person (Abstract)

## Descripción

Representa a una persona identificable dentro del marketplace.

Esta clase reúne los datos comunes a todos los individuos que participan en el sistema, sin importar si actúan como participantes comerciales o como usuarios del aplicativo.

No puede ser instanciada directamente.

## Atributos

| Atributo | Tipo | Descripción |
| -------- | ---- | ----------- |
| identifier | String | Identificador único de la persona en el marketplace. |
| fullName | String | Nombre oficial de la persona. |
| email | String | Correo electrónico, medio principal de acceso y comunicación. |
| role | SystemRole | Rol que define las responsabilidades y permisos de la persona. |

## Reglas

* El `identifier` debe ser único en toda la plataforma.
* El `email` debe ser único en toda la plataforma.
* El `fullName` no puede estar vacío.
* Cada persona tiene un único `role` dentro del sistema.

---

# Participant (Abstract)

## Descripción

Representa a una persona o entidad que mantiene una relación comercial con el marketplace.

Especializa a `Person` para distinguir a quienes participan en los procesos de compra y venta de quienes únicamente operan el sistema.

No puede ser instanciada directamente.

## Hereda de

`Person`

## Atributos

| Atributo | Tipo | Descripción |
| -------- | ---- | ----------- |
| phoneNumber | String | Número de contacto del participante. |

---

# Buyer

## Descripción

Representa a la persona que adquiere productos publicados en el marketplace.

El comprador únicamente puede consultar y operar sobre su propia información. No administra información de otros compradores ni inventarios.

## Hereda de

`Participant`

## Atributos

| Atributo | Tipo | Descripción |
| -------- | ---- | ----------- |
| mainAddress | Address | Ubicación habitual para entregas. |
| additionalAddresses | List\<Address\> | Ubicaciones secundarias de entrega. |
| commercialStatus | BuyerStatus | Condición del comprador para realizar compras. |

## Reglas

* La `mainAddress` es obligatoria.
* Las `additionalAddresses` son opcionales.
* El `commercialStatus` es obligatorio.
* El comprador no puede acceder a información de otros compradores.

---

# Seller

## Descripción

Representa al responsable de registrar y administrar productos dentro del marketplace.

Los vendedores no pueden auto-registrarse: son incorporados por el Administrador.

## Hereda de

`Participant`

## Atributos

| Atributo | Tipo | Descripción |
| -------- | ---- | ----------- |
| warehouses | List\<Warehouse\> | Bodegas asociadas al vendedor. |
| products | List\<Product\> | Productos publicados por el vendedor. |

## Reglas

* El registro de un vendedor solo puede ser realizado por un usuario con rol `ADMINISTRATOR`.
* Todo vendedor debe tener al menos una bodega registrada al momento de su incorporación.

---

# User

## Descripción

Representa la identidad utilizada para autenticarse y operar dentro del sistema.

Todo usuario debe autenticarse antes de ejecutar cualquier operación, y únicamente puede interactuar con la información correspondiente a las funciones de su rol.

Los roles operativos del marketplace que no mantienen una relación comercial (Operador Logístico, Administrador y Supervisor) se representan únicamente mediante esta clase, diferenciados por su `role`.

## Hereda de

`Person`

## Atributos

| Atributo | Tipo | Descripción |
| -------- | ---- | ----------- |
| username | String | Nombre de usuario utilizado para el acceso al sistema. |
| password | String | Credencial de acceso del usuario. |
| status | UserStatus | Condición operativa del usuario dentro del sistema. |
| participant | Participant | Participante comercial asociado al usuario, cuando aplica. |

## Reglas

* Toda operación debe ser ejecutada por un usuario autenticado.
* El `username` debe ser único en toda la plataforma.
* El `participant` es opcional: los usuarios operativos del marketplace no tienen un participante asociado.
* Ningún usuario puede administrar información fuera del alcance de su rol.

---

# Address

## Descripción

Representa una ubicación registrada por un comprador para la entrega de sus pedidos.

## Atributos

| Atributo | Tipo | Descripción |
| -------- | ---- | ----------- |
| identifier | String | Identificador único de la dirección. |
| recipientName | String | Nombre de la persona que recibe el pedido. |
| addressLine | String | Descripción de la ubicación de entrega. |
| city | String | Ciudad de la ubicación. |
| country | String | País de la ubicación. |
| phoneNumber | String | Número de contacto asociado a la entrega. |

---

# BusinessEntity (Abstract)

## Descripción

Representa cualquier entidad de negocio sobre la cual pueden ejecutarse operaciones significativas dentro del marketplace.

Proporciona un identificador común que permite registrar operaciones y auditoría sobre cualquier entidad del negocio de forma uniforme.

No puede ser instanciada directamente.

## Atributos

| Atributo | Tipo | Descripción |
| -------- | ---- | ----------- |
| identifier | String | Identificador único de la entidad de negocio. |

---

# Warehouse

## Descripción

Representa un espacio físico donde se administra el inventario del marketplace.

Las bodegas pueden pertenecer al marketplace o a un vendedor.

## Hereda de

`BusinessEntity`

## Atributos

| Atributo | Tipo | Descripción |
| -------- | ---- | ----------- |
| name | String | Nombre de la bodega. |
| warehouseType | WarehouseType | Clasificación de la bodega según su administrador. |
| address | Address | Ubicación física de la bodega. |
| seller | Seller | Vendedor propietario de la bodega, cuando aplica. |

## Reglas

* El `seller` es obligatorio cuando el `warehouseType` es `SELLER`.
* El `seller` no aplica cuando el `warehouseType` es `MARKETPLACE`.
* El registro de bodegas es responsabilidad del Administrador.

---

# Product

## Descripción

Representa un bien físico o digital ofrecido en el catálogo del marketplace.

Los productos físicos requieren inventario y despacho. Los productos digitales se entregan de forma inmediata tras la confirmación del pago.

## Hereda de

`BusinessEntity`

## Atributos

| Atributo | Tipo | Descripción |
| -------- | ---- | ----------- |
| name | String | Nombre del producto. |
| description | String | Descripción comercial del producto. |
| productType | ProductType | Naturaleza del producto: físico o digital. |
| productStatus | ProductStatus | Condición del producto dentro del catálogo. |
| price | BigDecimal | Precio de venta del producto. |
| currency | Currency | Moneda en la que se expresa el precio. |
| variants | List\<ProductVariant\> | Variantes disponibles del producto. |
| seller | Seller | Vendedor responsable del producto. |

## Reglas

* Todo producto debe estar asociado a un vendedor.
* El registro de productos es responsabilidad del vendedor.
* Únicamente los productos en estado `PUBLISHED` son visibles en el catálogo público.
* Los productos físicos requieren existencias registradas en al menos una bodega.

---

# ProductVariant

## Descripción

Representa una diferencia específica de un producto, como color, talla o modelo.

## Atributos

| Atributo | Tipo | Descripción |
| -------- | ---- | ----------- |
| identifier | String | Identificador único de la variante. |
| name | String | Nombre de la característica que distingue la variante. |
| value | String | Valor de la característica. |
| product | Product | Producto al que pertenece la variante. |

---

# Inventory

## Descripción

Representa las existencias de un producto disponibles en una bodega específica.

El inventario es distribuido: cada registro vincula obligatoriamente un producto con una bodega.

## Hereda de

`BusinessEntity`

## Atributos

| Atributo | Tipo | Descripción |
| -------- | ---- | ----------- |
| product | Product | Producto al que corresponden las existencias. |
| warehouse | Warehouse | Bodega donde se encuentran las existencias. |
| quantity | Integer | Cantidad de unidades registradas. |
| inventoryStatus | InventoryStatus | Condición de las existencias registradas. |

## Reglas

* Todo registro de inventario debe estar vinculado a un producto y a una bodega.
* La `quantity` no puede ser negativa bajo ninguna circunstancia.
* No se puede reservar inventario inexistente.
* No se puede reservar inventario con `inventoryStatus` igual a `DAMAGED`.

---

# InventoryMovement

## Descripción

Representa un movimiento realizado sobre las existencias de un producto en una bodega.

Cada movimiento deja registro de la variación de las existencias y del usuario que lo ejecutó.

## Hereda de

`BusinessEntity`

## Atributos

| Atributo | Tipo | Descripción |
| -------- | ---- | ----------- |
| inventory | Inventory | Registro de inventario afectado por el movimiento. |
| movementType | MovementType | Naturaleza del movimiento ejecutado. |
| quantity | Integer | Cantidad de unidades involucradas en el movimiento. |
| movementDate | LocalDateTime | Fecha y hora en que se ejecutó el movimiento. |
| performedBy | User | Usuario que ejecutó el movimiento. |

## Reglas

* La `quantity` debe ser estrictamente mayor que cero.
* Ningún movimiento puede dejar las existencias del inventario en un valor negativo.
* Todo movimiento debe quedar registrado con su fecha y el usuario responsable.

---

# Cart

## Descripción

Representa la selección provisional de productos realizada por un comprador antes de confirmar su pedido.

## Hereda de

`BusinessEntity`

## Atributos

| Atributo | Tipo | Descripción |
| -------- | ---- | ----------- |
| buyer | Buyer | Comprador propietario del carrito. |
| items | List\<CartItem\> | Productos seleccionados provisionalmente. |
| lastUpdateDate | LocalDateTime | Fecha y hora de la última modificación del carrito. |

## Reglas

* Todo carrito pertenece a un único comprador.
* Un comprador únicamente puede acceder a su propio carrito.

---

# CartItem

## Descripción

Representa un producto seleccionado dentro del carrito de un comprador, junto con la cantidad deseada.

## Atributos

| Atributo | Tipo | Descripción |
| -------- | ---- | ----------- |
| identifier | String | Identificador único del ítem del carrito. |
| product | Product | Producto seleccionado. |
| variant | ProductVariant | Variante seleccionada del producto, cuando aplica. |
| quantity | Integer | Cantidad de unidades seleccionadas. |
| cart | Cart | Carrito al que pertenece el ítem. |

## Reglas

* La `quantity` debe ser estrictamente mayor que cero.
* Únicamente pueden agregarse productos en estado `PUBLISHED`.

---

# Order

## Descripción

Representa el compromiso comercial formal adquirido por un comprador.

El ciclo de vida del pedido es el proceso central del sistema y avanza de forma secuencial desde el carrito hasta la entrega confirmada.

## Hereda de

`BusinessEntity`

## Atributos

| Atributo | Tipo | Descripción |
| -------- | ---- | ----------- |
| buyer | Buyer | Comprador que realizó el pedido. |
| items | List\<OrderItem\> | Productos incluidos en el pedido. |
| orderStatus | OrderStatus | Etapa actual del ciclo de vida del pedido. |
| totalAmount | BigDecimal | Valor total del pedido. |
| currency | Currency | Moneda en la que se expresa el valor del pedido. |
| deliveryAddress | Address | Dirección de entrega del pedido. |
| creationDate | LocalDateTime | Fecha y hora en que se confirmó el pedido. |
| invoice | Invoice | Factura asociada al pedido. |
| shipment | Shipment | Envío asociado al pedido, cuando aplica. |

## Reglas

* Todo pedido pertenece a un único comprador.
* Un pedido en estado `DELIVERED` no puede ser modificado bajo ninguna circunstancia.
* El paso a estado `PAID` requiere la confirmación del pago.
* El paso a estado `SHIPPED` requiere la salida física del pedido desde la bodega.
* Un comprador únicamente puede consultar sus propios pedidos.

---

# OrderItem

## Descripción

Representa un producto incluido en un pedido, con la cantidad y el precio registrados al momento de la confirmación.

## Atributos

| Atributo | Tipo | Descripción |
| -------- | ---- | ----------- |
| identifier | String | Identificador único del ítem del pedido. |
| product | Product | Producto incluido en el pedido. |
| variant | ProductVariant | Variante del producto, cuando aplica. |
| quantity | Integer | Cantidad de unidades solicitadas. |
| unitPrice | BigDecimal | Precio unitario registrado al confirmar el pedido. |
| order | Order | Pedido al que pertenece el ítem. |

## Reglas

* La `quantity` debe ser estrictamente mayor que cero.
* El `unitPrice` se registra al confirmar el pedido y no se modifica posteriormente.

---

# Invoice

## Descripción

Representa la información comercial asociada a la venta de un pedido.

La factura se emite una vez confirmado el pago del pedido.

## Hereda de

`BusinessEntity`

## Atributos

| Atributo | Tipo | Descripción |
| -------- | ---- | ----------- |
| order | Order | Pedido facturado. |
| issueDate | LocalDateTime | Fecha y hora de emisión de la factura. |
| totalAmount | BigDecimal | Valor total facturado. |
| currency | Currency | Moneda en la que se expresa el valor facturado. |

## Reglas

* Toda factura corresponde a un único pedido.
* La factura solo puede emitirse cuando el pedido se encuentra en estado `PAID`.
* El `totalAmount` debe coincidir con el valor total del pedido.

---

# Shipment

## Descripción

Representa el proceso logístico de despacho y transporte de un pedido hacia la dirección de entrega del comprador.

Únicamente los pedidos que contienen productos físicos generan un envío.

## Hereda de

`BusinessEntity`

## Atributos

| Atributo | Tipo | Descripción |
| -------- | ---- | ----------- |
| order | Order | Pedido despachado. |
| warehouse | Warehouse | Bodega desde la que se realiza el despacho. |
| deliveryAddress | Address | Dirección de entrega del envío. |
| dispatchDate | LocalDateTime | Fecha y hora del despacho. |
| deliveryDate | LocalDateTime | Fecha y hora de la entrega confirmada. |
| handledBy | User | Operador logístico responsable del envío. |

## Reglas

* Todo envío corresponde a un único pedido.
* El despacho es responsabilidad del Operador Logístico.
* Los pedidos compuestos únicamente por productos digitales no generan envío.
* La `deliveryDate` solo se registra cuando la entrega ha sido confirmada.

---

# Return

## Descripción

Representa la solicitud de devolución de un pedido realizada por un comprador.

## Hereda de

`BusinessEntity`

## Atributos

| Atributo | Tipo | Descripción |
| -------- | ---- | ----------- |
| order | Order | Pedido sobre el que se solicita la devolución. |
| reason | String | Motivo de la devolución indicado por el comprador. |
| requestDate | LocalDateTime | Fecha y hora de la solicitud. |
| approvalDate | LocalDateTime | Fecha y hora de la aprobación de la devolución. |
| requestedBy | Buyer | Comprador que solicitó la devolución. |
| approvedBy | User | Usuario que aprobó la devolución. |
| refund | Refund | Reembolso asociado a la devolución. |

## Reglas

* Toda devolución corresponde a un único pedido.
* La devolución solo puede solicitarse sobre pedidos entregados.
* La aprobación de la devolución genera un movimiento de inventario de tipo `RETURN`.

---

# Refund

## Descripción

Representa la restitución del valor pagado por un comprador tras la aprobación de una devolución.

## Hereda de

`BusinessEntity`

## Atributos

| Atributo | Tipo | Descripción |
| -------- | ---- | ----------- |
| returnRequest | Return | Devolución que originó el reembolso. |
| amount | BigDecimal | Valor reembolsado al comprador. |
| currency | Currency | Moneda en la que se expresa el reembolso. |
| executionDate | LocalDateTime | Fecha y hora de la ejecución del reembolso. |
| executedBy | User | Usuario que ejecutó el reembolso. |

## Reglas

* Todo reembolso corresponde a una única devolución.
* El reembolso solo puede ejecutarse sobre devoluciones aprobadas.
* El `amount` debe ser estrictamente mayor que cero.

---

# Operation

## Descripción

Representa una acción significativa ejecutada por un usuario sobre una entidad de negocio del marketplace.

Cada operación identifica qué se hizo, sobre qué entidad, quién la ejecutó y en qué momento.

## Atributos

| Atributo | Tipo | Descripción |
| -------- | ---- | ----------- |
| operationId | String | Identificador único de la operación. |
| operationType | OperationType | Categoría de la operación ejecutada. |
| executionDate | LocalDateTime | Fecha y hora de ejecución de la operación. |
| performedBy | User | Usuario que ejecutó la operación. |
| affectedEntity | BusinessEntity | Entidad de negocio afectada por la operación. |

## Reglas

* Toda operación debe estar asociada a un usuario autenticado.
* Toda operación debe registrar la entidad de negocio sobre la que se ejecutó.

---

# AuditLog

## Descripción

Representa el registro histórico e inmutable de una operación ejecutada dentro del marketplace.

Permite el seguimiento y la trazabilidad de las acciones realizadas sobre las entidades de negocio.

## Atributos

| Atributo | Tipo | Descripción |
| -------- | ---- | ----------- |
| auditId | String | Identificador único del registro de auditoría. |
| operationType | OperationType | Categoría de la operación registrada. |
| operationDate | LocalDateTime | Fecha y hora en que se generó el registro. |
| performedBy | User | Usuario que ejecutó la operación. |
| userRole | SystemRole | Rol del usuario en el momento de la operación. |
| affectedEntity | BusinessEntity | Entidad de negocio afectada por la operación. |
| details | Map\<String, Object\> | Datos variables según el tipo de operación registrada. |

## Reglas

* Los registros de auditoría son inmutables: no pueden modificarse ni eliminarse.
* Toda operación significativa debe generar un registro de auditoría.
* El `userRole` se registra tal como estaba al momento de la operación.

---