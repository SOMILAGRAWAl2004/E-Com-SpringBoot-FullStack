# 🛒 Spring Boot E-Commerce Application

A robust, full-featured e-commerce backend built with Spring Boot 3.5.4, featuring layered architecture, JWT authentication, PostgreSQL database, and Swagger API documentation.

---

## 📋 Table of Contents

- [Project Overview](#project-overview)
- [Built Features](#built-features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Upcoming Features](#upcoming-features)
- [Configuration](#configuration)

---

## 🎯 Project Overview

This Spring Boot E-Commerce application is designed to provide a comprehensive backend solution for online shopping platforms. It follows industry best practices with a layered architecture (Controller → Service → Repository → Model) and includes security features, error handling, file uploads, and RESTful API endpoints.

**Version:** 0.0.1-SNAPSHOT  
**Java Version:** 17  
**Framework:** Spring Boot 3.5.4

---

## ✨ Built Features

### 1. **User Authentication & Authorization**
   - JWT (JSON Web Token) based authentication
   - Role-based access control (Admin, User roles)
   - Secure password management
   - Cookie-based session management
   - Authentication endpoints with token generation

### 2. **Product Management**
   - Create, read, update, and delete products
   - Product categorization
   - Product descriptions and details
   - Price management
   - Product search and filtering

### 3. **Category Management**
   - Organize products by categories
   - Category creation and management
   - Category-based product filtering

### 4. **Shopping Cart**
   - Add/remove items from cart
   - Cart item quantity management
   - Calculate total cart value
   - User-specific cart management

### 5. **Order Management**
   - Create and manage customer orders
   - Order tracking with status updates
   - Order items association
   - Order history retrieval

### 6. **User Address Management**
   - Multiple address storage per user
   - Billing and shipping address support
   - Address CRUD operations

### 7. **Payment Information Storage**
   - Payment details model (ready for integration)
   - Payment status tracking

### 8. **File Management**
   - Image upload functionality
   - File storage and retrieval
   - Product image support

### 9. **Global Exception Handling**
   - Custom exception classes
   - Centralized error handling
   - Consistent API error responses
   - Resource not found handling

### 10. **API Documentation**
   - Swagger/Springfox integration
   - Interactive API documentation
   - Endpoint testing interface

---

## 🛠️ Tech Stack

| Component | Technology |
|-----------|-----------|
| **Framework** | Spring Boot 3.5.4 |
| **Language** | Java 17 |
| **Database** | PostgreSQL |
| **ORM** | JPA/Hibernate |
| **API Documentation** | Swagger (Springfox) |
| **Build Tool** | Maven |
| **Authentication** | JWT (JSON Web Tokens) |
| **Testing** | JUnit 5, Spring Boot Test |

### Dependencies Included:
- Spring Web
- Spring Data JPA
- PostgreSQL Driver
- Spring Security
- JWT Library
- Swagger/Springfox
- Spring Test

---

## 📁 Project Structure

```
sb-ecom/
├── src/main/java/com/ecommerce/project/
│   ├── config/                    # Configuration classes
│   │   ├── AppConfig.java         # Application-level configurations
│   │   ├── AppConstants.java      # Application constants
│   │   └── SwaggerConfig.java     # Swagger/API documentation config
│   │
│   ├── controller/                # REST API Controllers
│   │   ├── AddressController.java
│   │   ├── AuthController.java
│   │   ├── CartController.java
│   │   ├── CategoryController.java
│   │   ├── OrderController.java
│   │   └── ProductController.java
│   │
│   ├── service/                   # Business Logic Layer
│   │   ├── interfaces (Service)
│   │   │   ├── AddressService.java
│   │   │   ├── CartService.java
│   │   │   ├── CategoryService.java
│   │   │   ├── FileService.java
│   │   │   ├── OrderService.java
│   │   │   └── ProductService.java
│   │   │
│   │   └── implementations (ServiceImpl)
│   │       ├── AddressServiceImpl.java
│   │       ├── CartServiceImpl.java
│   │       ├── CategoryServiceImpl.java
│   │       ├── FileServiceImpl.java
│   │       ├── OrderServiceImpl.java
│   │       └── ProductServiceImpl.java
│   │
│   ├── repositories/              # Data Access Layer (JPA Repositories)
│   │   ├── AddressRepository.java
│   │   ├── CartRepository.java
│   │   ├── CategoryRepository.java
│   │   ├── OrderRepository.java
│   │   ├── ProductRepository.java
│   │   └── UserRepository.java
│   │
│   ├── model/                     # Entity Models (Database Entities)
│   │   ├── User.java
│   │   ├── Address.java
│   │   ├── Product.java
│   │   ├── Category.java
│   │   ├── Cart.java
│   │   ├── CartItem.java
│   │   ├── Order.java
│   │   ├── OrderItem.java
│   │   ├── Payment.java
│   │   ├── Role.java
│   │   └── AppRole.java
│   │
│   ├── payload/                   # DTOs (Data Transfer Objects)
│   │   ├── AddressDTO.java
│   │   ├── CartDTO.java
│   │   ├── CartItemDTO.java
│   │   ├── APIResponse.java
│   │   └── Other payload classes
│   │
│   ├── security/                  # Security-related configurations
│   │   └── JWT and auth utilities
│   │
│   ├── exceptions/                # Custom Exceptions
│   │   ├── APIException.java
│   │   ├── ResourceNotFoundException.java
│   │   └── MyGlobalExceptionHandler.java
│   │
│   ├── util/                      # Utility Classes
│   │   └── Helper functions
│   │
│   └── SbEcomApplication.java     # Main Application Class
│
├── src/main/resources/
│   ├── application.properties     # Application configuration
│   ├── static/                    # Static resources
│   └── templates/                 # HTML templates
│
├── pom.xml                        # Maven dependencies and build config
├── mvnw / mvnw.cmd               # Maven Wrapper
└── README.md                      # This file
```

---

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- PostgreSQL database
- Maven 3.6+

### Installation & Setup

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd sb-ecom
   ```

2. **Configure the database:**
   
   Edit `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce
   spring.datasource.username=postgres
   spring.datasource.password=your_password
   ```

3. **Build the project:**
   ```bash
   mvn clean install
   ```

4. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```
   
   Or use Maven Wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```

5. **Access the application:**
   - API Base URL: `http://localhost:8080`
   - Swagger Documentation: `http://localhost:8080/swagger-ui.html`

---

## 📡 API Endpoints

### Authentication
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - User login (returns JWT token)
- `POST /api/auth/logout` - User logout

### Products
- `GET /api/products` - Get all products with pagination & sorting
- `GET /api/products/{id}` - Get product by ID
- `POST /api/products` - Create new product (Admin only)
- `PUT /api/products/{id}` - Update product (Admin only)
- `DELETE /api/products/{id}` - Delete product (Admin only)

### Categories
- `GET /api/categories` - Get all categories
- `GET /api/categories/{id}` - Get category by ID
- `POST /api/categories` - Create category (Admin only)
- `PUT /api/categories/{id}` - Update category (Admin only)
- `DELETE /api/categories/{id}` - Delete category (Admin only)

### Shopping Cart
- `GET /api/cart` - Get user's cart
- `POST /api/cart/add` - Add item to cart
- `PUT /api/cart/update/{itemId}` - Update cart item quantity
- `DELETE /api/cart/remove/{itemId}` - Remove item from cart

### Orders
- `POST /api/orders` - Create new order
- `GET /api/orders` - Get user's orders
- `GET /api/orders/{id}` - Get order details
- `PUT /api/orders/{id}/status` - Update order status (Admin only)

### User Addresses
- `GET /api/addresses` - Get user's addresses
- `POST /api/addresses` - Add new address
- `PUT /api/addresses/{id}` - Update address
- `DELETE /api/addresses/{id}` - Delete address

### Files
- `POST /api/files/upload` - Upload product images
- `GET /api/files/{filename}` - Download/retrieve files

---

## 🔮 Upcoming Features

### 1. **Stripe Payment Integration** 💳
   - Secure payment processing with Stripe
   - Payment intent creation and confirmation
   - Webhook handling for payment events
   - Transaction history and receipts

### 2. **Admin Panel** 👨‍💼
   - Dashboard with analytics
   - User management interface
   - Product and inventory management UI
   - Order management and tracking
   - Sales reports and statistics
   - Customer insights and analytics

### 3. **Additional Features** 🚀
   - Product reviews and ratings
   - Wishlist functionality
   - Email notifications
   - SMS notifications
   - Inventory management
   - Discount and coupon system
   - Advanced product search with filters
   - Product recommendations
   - User account management dashboard
   - Order tracking updates
   - Multi-language support
   - Analytics and reporting

---

## ⚙️ Configuration

### JWT Configuration
Located in `application.properties`:
```properties
spring.app.jwtSecret=mySecretKey123912738qwertyuioplkjhgfdsazxcvbnm
spring.app.jwtExpirationMs=3000000
spring.ecom.app.jwtCookieName=springBootEcom
```

### Database Configuration
Supports both MySQL and PostgreSQL:
```properties
# PostgreSQL (Current)
spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce
spring.datasource.username=postgres
spring.datasource.password=12345678
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

# MySQL (Alternative)
# spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
# spring.datasource.username=root
# spring.datasource.password=12345678
# spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

### Hibernate Configuration
```properties
spring.jpa.hibernate.ddl-auto=update  # Auto-update database schema
spring.jpa.show-sql=false              # Log SQL queries
spring.jpa.properties.hibernate.format_sql=true
```

### File Upload Configuration
```properties
project.image=images/  # Image storage directory
```

---

## 🔒 Security

- **JWT Authentication:** Stateless authentication using JSON Web Tokens
- **Role-Based Access Control:** Different permissions for Admin and User roles
- **Password Security:** Encrypted password storage
- **CORS Configuration:** Configurable cross-origin requests
- **Exception Handling:** Global error handling with consistent responses

---

## 🧪 Testing

Run tests using Maven:
```bash
mvn test
```

Test classes are located in `src/test/java/com/ecommerce/project/`

---

## 📝 Notes

- This is a backend API. Frontend integration will be needed for complete e-commerce experience.
- Database schema is automatically created/updated using Hibernate's `ddl-auto` feature.
- For production deployment, ensure sensitive configuration (JWT secret, DB credentials) is moved to environment variables.

---

## 📄 License

This project is provided as-is for educational and commercial use.

---

## 👨‍💻 Development Team

Currently in active development. For contributions, please follow Spring Boot best practices and maintain the layered architecture pattern.

---

## 📞 Support & Questions

For issues, feature requests, or questions, please create an issue in the repository.

---

**Last Updated:** February 2026  
**Status:** Active Development 🚀

