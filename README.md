![Coverage](.github/badges/jacoco.svg)

# Challenge
API RESTful para obtener el precio pvp de un producto, brand y fecha determinada

## Tech Stack
- Java 17
- Spring Boot 3.1.4
- Base de Datos H2

## Arquitectura Hexagonal
![Architecture](src/main/resources/static/DDD-Layers.webp)

## Base de Datos
- Inicializada automáticamente via Flyway.
- Credenciales:
    - url: jdbc:h2:mem:pricesdb
    - user: admin
    - password: admin
- API h2
    - http://localhost:9000/h2-console
    - ```select * from prices;```

## Ejecutar la aplicación
```
./gradlew bootRun
```

## Ejecutar pruebas
```
./gradlew test
```

## Swagger
- http://localhost:9000/swagger-ui/index.html