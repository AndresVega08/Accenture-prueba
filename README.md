#  API de Franquicias - Backend

API REST desarrollada en **Spring Boot** para la gestión de franquicias, sucursales y productos, cumpliendo los criterios de la prueba técnica.

---

##  Tecnologías utilizadas

* Java 17
* Spring Boot
* Spring Data JPA
* MySQL
* Lombok
* Maven

---

##  Descripción del sistema

Una **franquicia** contiene múltiples **sucursales**, y cada sucursal tiene una lista de **productos** con su respectivo stock.

---

##  Modelo de datos

* **Franquicia**

  * id
  * nombre

* **Sucursal**

  * id
  * nombre
  * franquicia

* **Producto**

  * id
  * nombre
  * stock
  * sucursal

---

##  Configuración del proyecto

* Java 17
* Maven
* MySQL
---

## Endpoints principales

### Crear franquicia

```
POST /franquicias
```

---

### Agregar sucursal a franquicia

```
POST /franquicias/{id}/sucursales
```

---

### Agregar producto a sucursal

```
POST /franquicias/sucursales/{id}/productos
```

---

### Obtener producto con mayor stock por sucursal

```
GET /franquicias/sucursales/{id}/producto-mayor-stock
```

---

## Funcionalidades adicionales 

Actualizar stock de producto

```
PUT /franquicias/productos/{id}/stock
```

Eliminar producto

```
DELETE /franquicias/productos/{id}
```

Obtener producto con mayor stock por franquicia

```
GET /franquicias/{id}/productos-mayor-stock
```

Actualizar nombre de franquicia, sucursal y producto

```
PUT /franquicias/{id}
PUT /franquicias/sucursales/{id}
PUT /franquicias/productos/{id}
```

---

## Buenas prácticas implementadas

* Uso de DTOs para separación de responsabilidades
* Manejo de errores con estructura estándar (`ResponseDTO`)
* Validaciones con `@Valid`
* Arquitectura en capas (Controller - Service - Repository)
* Uso de JPA para persistencia

---
