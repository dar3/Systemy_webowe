# RTV / AGD Online Store – Spring Boot Backend

Backend application for an online RTV/AGD store developed in **Java with Spring Boot  (Spring_2 folder)**.
The project focuses on backend architecture, database design, REST API development, and basic access control concepts.

This application was created as a learning project to develop practical skills in backend development, data processing, and system design.

---

## Technologies Used

- **Java**
- **Spring Boot**
- **Spring Data JPA (Hibernate)**
- **REST API**
- **Relational Database (H2 and switched later on to PostgreSQL)**  
- **Gradle**
- **Git**

---

## Features

- Product management (RTV / AGD products)
- User accounts management
- Shopping cart functionality
- Order processing
- RESTful API endpoints
- Database persistence using JPA/Hibernate
- Basic role-based access concepts
- Separation of business logic and data access layers

---

## Database

The application uses a relational database with properly designed entities and relationships, such as:

- Users
- Products
- Categories
- Orders
- Order items
- Shopping cart

The project focuses on:
- entity relationships
- data integrity
- working with repositories and queries

---

## Security & Access Control (Basic)

- User roles and permissions concept
- Controlled access to selected application resources
- Separation of user responsibilities (regular user vs administrator)


---

## Project Structure

- **Controller layer** – REST API endpoints
- **Service layer** – business logic
- **Repository layer** – data access using JPA
- **Entity layer** – database models

