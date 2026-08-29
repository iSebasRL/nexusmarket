# Decisiones de Diseño

## Introducción

Este documento registra las decisiones tomadas durante el modelado del dominio de NexusMarket que no se derivan de forma literal de la Especificación Funcional del Negocio.

Se distingue entre valores tomados textualmente del documento, valores inferidos a partir de la narrativa, y decisiones estructurales adoptadas por el diseñador.

---

## Decisiones sobre Value Objects

### SystemRole

**Origen:** Sección 5, Participantes del Negocio.

Los cinco roles se toman literalmente del documento. La restricción de un único rol por usuario proviene de RG-02.

### UserStatus

**Origen:** Dominio 1, atributo Estado.

El documento indica "Activo, Bloqueado, etc." sin cerrar la lista. Se agrega `INACTIVE` para representar usuarios registrados que no pueden operar sin haber sido sancionados, diferenciándolo de `BLOCKED`.

### BuyerStatus

**Origen:** Dominio 2, atributo Estado comercial.

El documento define el atributo pero no enumera sus valores. Se adoptan `ACTIVE`, `SUSPENDED` y `BLOCKED` por coherencia con `UserStatus`.

Se mantiene separado de `UserStatus` porque responden a preguntas distintas: uno determina el acceso al sistema y el otro la habilitación comercial.

### WarehouseType

**Origen:** Dominio 4, Clasificación. Literal.

### ProductType

**Origen:** Dominio 5. Literal.

### ProductStatus

**Origen:** Dominio 5, atributo Estado. Literal.

### InventoryStatus

**Origen:** Dominio 6 y Validaciones Críticas.

`DAMAGED` aparece de forma explícita en las Validaciones Críticas. `AVAILABLE` y `RESERVED` se infieren del movimiento de Reserva descrito en el Dominio 6.

### MovementType

**Origen:** Dominio 6, Movimientos. Literal.

### OrderStatus

**Origen:** Dominio 7, Ciclo de Estados del Pedido. Literal.

### OperationType

**Origen:** Inferido.

La especificación no incluye un catálogo de operaciones auditables. Los valores se derivan del Flujo General del Negocio (sección 6) y de los objetivos funcionales OBJ-01 a OBJ-12, registrando cada acción significativa que un usuario puede ejecutar sobre el sistema.

### Currency

**Origen:** No presente en la especificación.

El documento no menciona monedas. Se incluye porque los dominios de facturación y reembolsos manejan valores monetarios, y expresarlos sin una moneda asociada dejaría el modelo incompleto.

---

## Decisiones estructurales

### Roles sin clase propia

`Buyer` y `Seller` se modelan como clases porque poseen atributos propios definidos en los Dominios 2 y 3.

Administrador, Supervisor y Operador Logístico no tienen atributos adicionales en la especificación, por lo que se representan como usuarios diferenciados únicamente por su `SystemRole`.

### Uso de DomainCatalog en lugar de enumeraciones

Los catálogos de negocio se modelan como clases que heredan de `DomainCatalog` y no como enumeraciones de Java, siguiendo la estructura de referencia del proyecto guía.

Esto permite que cada catálogo incorpore atributos adicionales cuando el negocio lo requiere, como ocurre con `Currency`.

---