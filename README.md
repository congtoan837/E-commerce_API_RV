
# 🛒 E-commerce_API_Rework
A modern and modular backend for an e-commerce system, built with Spring Boot and Java, following clean architecture principles.

# ⚙️ Tech Stack
- Java 17+
- Spring Boot
- Maven
- JPA / Hibernate
- PostgreSQL
- JWT Authentication
- Logback (for logging)
- Docker

# 📘 API Documentation (Swagger)

This project integrates Swagger UI for easy API exploration and testing.

- 🌐 Swagger UI: [https://congtoan-app.koyeb.app/swagger-ui/index.html](https://congtoan-app.koyeb.app/swagger-ui/index.html)
- 📄 OpenAPI Docs (JSON): [https://congtoan-app.koyeb.app/v3/api-docs](https://congtoan-app.koyeb.app/v3/api-docs)

# 📁 Project Structure
```bash
├── src
│ ├── main
│ │ ├── java
│ │ │ └── com.poly
│ │ │ ├── config → Configuration (security, JWT, CORS, Swagger, etc.)
│ │ │ ├── controller → REST controllers
│ │ │ ├── dto → Data transfer objects (request/response)
│ │ │ ├── entity → JPA entities (User, Product, Order, etc.)
│ │ │ ├── exception → Custom exception handling
│ │ │ ├── mapper → MapStruct
│ │ │ ├── repository → Spring Data JPA repositories
│ │ │ ├── security → JWT utilities, filters, authentication
│ │ │ ├── service → Service interfaces
│ │ │ │ └── impl → Service implementations
│ │ │ └── EcommerceApiReworkApplication.java
│ │ └── resources
│ │ └── application.properties
│ └── test
├── .gitignore
├── mvnw / mvnw.cmd → Maven wrapper scripts
├── pom.xml → Maven build file
├── README.md
```

# 🚀 How to Run
## Clone the repository
git clone https://github.com/congtoan837/E-commerce_API_Rework.git cd E-commerce_API_Rework

## Run with Maven Wrapper
./mvnw spring-boot:run Make sure to configure application.properties or application.yml with your database and JWT settings.
