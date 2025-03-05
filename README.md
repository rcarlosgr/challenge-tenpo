# 🚀 Calculation API

## 📌 Descripción
Esta API permite realizar cálculos con porcentaje dinámico, obteniéndolo desde un servicio externo y almacenándolo en caché. Además, mantiene un historial de llamadas en PostgreSQL.

---

## 📦 Tecnologías Utilizadas
- **Java 21**
- **Spring Boot 3**
- **PostgreSQL 16** (en Docker)
- **Swagger (Springdoc OpenAPI)**
- **Caffeine Cache**
- **Lombok**
- **Docker & Docker Compose**

---

## 🚀 Instalación y Ejecución
- **No se debe tener ocupado los puertos 5433, 8080**
- **La imagen se encuentra en ** [https://hub.docker.com/r/rcarlosgr/calculation-api](https://hub.docker.com/r/rcarlosgr/calculation-api)

### Clonar el repositorio y ejecutar manualmente
```bash
git clone https://github.com/rcarlosgr/challenge-tenpo.git
cd challenge-tenpo
```

### Ejecutar docker compose
```bash
docker compose up -d
```
---

## 📌 Endpoints de la API

### 📍 1. Calcular suma con porcentaje dinámico

**URL:** `POST http://localhost:8080/api/calculation/sum`  
**Ejemplo de Request:**
```json
{
    "num1": 5,
    "num2": 6
}
```
**Ejemplo de Respuesta:**
```json
{
  "result": 12.1
}
```

### 📍 1. Obtener historial de llamadas 
**URL:** `GET http://localhost:8080/api/api-call-history`   
**Ejemplo de Respuesta:**
```json
[
  {
    "id": 1,
    "endpoint": "/api/calculation/sum",
    "parameters": {
      "num1": 5.0,
      "num2": 6.0
    },
    "response": {
      "result": 12.1
    },
    "error": null,
    "timestamp": "2025-03-05T04:32:59.14227"
  }
]
```

## 📌 Documentación con Swagger
Después de iniciar la API, puedes acceder a **Swagger UI** en:

- **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **OpenAPI JSON:** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)
