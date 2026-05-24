# 🌱 Spring Boot Feature Development Workflow

A step-by-step guide for building any feature in a Spring Boot project using layered architecture.

---

## 🧱 1. Model Layer (Entity)

- Represents the structure of the data (typically maps to a database table).
- Example: `Category` class with fields like `categoryId`, `name`.
- Annotated with `@Entity`.

📝 **Example fields**:
- `Long categoryId`
- `String name`

---

## 🗃️ 2. Repository Layer

- Interface that extends `JpaRepository` or `CrudRepository`.
- Provides CRUD operations out of the box.
- You can define custom methods like:
    - `findByName(String name)`

📝 **Example method**:
- `Optional<Category> findByName(String name)`

---

## 🧠 3. Service Layer

### Interface
- Defines business operations such as:
    - `createCategory(Category category)`
    - `getAllCategories()`
    - `getCategoryById(Long id)`
    - `deleteCategory(Long id)`

### Implementation
- Contains logic to perform these operations using the repository.

📝 **Responsibilities**:
- Call repository methods
- Add validations
- Apply business rules

---

## 🌐 4. Controller Layer

- Exposes REST API endpoints using `@RestController`.
- Maps HTTP requests (GET, POST, PUT, DELETE) to service layer calls.

📝 **Example endpoints**:
- `POST /api/categories` → create a category
- `GET /api/categories` → retrieve all categories
- `GET /api/categories/{id}` → retrieve by ID
- `DELETE /api/categories/{id}` → delete by ID

---

## 🗂️ Suggested Project Structure

```text
com.example.project
├── controller
│   └── CategoryController.java
├── service
│   ├── CategoryService.java
│   └── CategoryServiceImpl.java
├── repository
│   └── CategoryRepository.java
├── model
│   └── Category.java
└── dto (optional)
    └── CategoryDto.java
