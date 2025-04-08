# E-Commerce Platform Template - Java 21 Spring + Typescript React Tailwind & React Query (Building)

## Project Overview
This project is a modern e-commerce platform built with Spring Boot that provides a robust foundation for online retail operations. The system is designed with scalability, maintainability, and extensibility in mind, employing clean architecture principles and domain-driven design.

## Architecture

### Clean Architecture / Hexagonal Architecture
The project follows a modular, layered architecture inspired by Clean Architecture and Hexagonal (Ports and Adapters) principles, organized as follows:

```
commerce/
├── modules/             # Business modules organized by domain
│   ├── product/         # Product management module
│   ├── order/           # Order processing module
│   ├── user/            # User management module
│   ├── category/        # Category management module
│   └── report/          # Reporting module
├── shared/              # Shared components
│   ├── domain/          # Common domain objects and patterns
│   └── infrastructure/  # Shared infrastructure components
├── config/              # Application configuration
└── exception/           # Global exception handling
```

Each business module follows a consistent layered structure:

```
module/
├── domain/              # Domain models, repositories interfaces, business rules
│   ├── entity/          # Domain entities
│   └── repository/      # Repository interfaces
├── application/         # Use cases, services, DTOs
└── infrastructure/      # Implementation details, adapters, controllers
```

## Technology Stack

- **Spring Boot 3.4**: Modern Java framework for building enterprise applications
- **Spring Data JPA**: Simplified data access layer
- **Spring Data Redis**: Caching solution for improved performance
- **PostgreSQL**: Primary relational database
- **H2 Database**: In-memory database for development and testing
- **Lombok**: Reduces boilerplate code
- **Spring RestDocs**: API documentation
- **TestContainers**: Integration testing with containerized dependencies
- **Maven**: Build and dependency management

## Benefits of This Architecture

### 1. Testability
- Domain logic can be tested independently of infrastructure
- Use of dependency inversion facilitates mock-based testing
- TestContainers for integration testing with real dependencies

### 2. Flexibility and Maintainability
- Infrastructure implementations can be swapped without changing domain logic
- New features can be added by creating new modules without disrupting existing functionality
- Clear code organization makes the system easier to understand and maintain

### 3. Scalability
- Modular design allows for independent scaling of components
- Redis integration provides caching capabilities for high-traffic scenarios
- Clear separation of read and write operations facilitates CQRS patterns if needed

## Flow of Control

```
HTTP Request → Controller → Application Service → Domain Service → Repository → Database
     ↑                                               ↓
└───────────────────── Response ───────────────┘
```

1. **HTTP Request**: Client sends a request to an endpoint
2. **Controller**: Receives the request, validates input, and calls the appropriate application service
3. **Application Service**: Orchestrates the use case, coordinates domain objects
4. **Domain Service**: Implements business rules and domain logic
5. **Repository**: Provides data access abstraction
6. **Database**: Stores and retrieves data

## Getting Started

### Prerequisites
- Java 21
- Maven
- Docker (for running TestContainers)

### Running the Application
```bash
./mvnw spring-boot:run
```

### Running Tests
```bash
./mvnw test
```

## Future Enhancements
- API Gateway integration
- Event-driven architecture with message queues
- Microservices decomposition
- Advanced monitoring and observability
