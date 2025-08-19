# 📖 Spring Boot 3 & JPA Workshop

This project is a practical workshop focused on learning **Spring Boot 3** and the **JPA (Java Persistence API)**. It simulates a back-end application with a **RESTful API** for data management, exploring essential concepts of modern development with the Spring ecosystem.

---

### 🚀 Technologies Used

* **Java 21**: The primary programming language.
* **Spring Boot 3**: The framework that simplifies the creation of production-ready Java applications.
* **Spring Data JPA**: A powerful tool that reduces boilerplate code for database interactions.
* **Hibernate**: The JPA implementation used for object-relational mapping.
* **Maven**: The project's dependency manager.
* **H2 Database**: A lightweight in-memory database, ideal for development and testing.

---

### 🏗️ Project Architecture

The project follows a layered architecture, a widely used design pattern in the industry.

* **Resource Layer**: Responsible for exposing the REST API endpoints. This is the layer that receives HTTP requests and delegates operations to the service layer.
* **Service Layer**: Contains the application's **business logic**. Here, rules and validations are implemented before interacting with the database.
* **Repository Layer**: The data persistence layer. Thanks to Spring Data JPA, you only need to define the interfaces, and Spring takes care of implementing the CRUD operations.

---

### 🛠️ How to Run the Project

Make sure you have **JDK 21** or higher and **Maven** installed on your machine.

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/dcxrvalho/spring-boot-jpa-workshop.git](https://github.com/dcxrvalho/spring-boot-jpa-workshop.git)
    ```
2.  **Navigate to the project folder:**
    ```bash
    cd spring-boot-jpa-workshop
    ```
3.  **Execute the application:**
    ```bash
    mvn spring-boot:run
    ```

The application will start and will be accessible at `http://localhost:8080`.

---

### 🌐 API Endpoints

The API allows for standard CRUD (Create, Read, Update, Delete) operations on several entities. The endpoints are designed to be intuitive and follow RESTful principles.

#### **User Endpoints (`/users`)**

* **`GET /users`**
    * Retrieves a list of all users.
    * **Response**: `200 OK`
* **`GET /users/{id}`**
    * Retrieves a user by their unique ID.
    * **Path Variable**: `{id}` is the user's ID.
    * **Response**: `200 OK` if found, `404 Not Found` otherwise.
* **`POST /users`**
    * Creates a new user. The request body should be a JSON object containing the user's data.
    * **Request Body**: `{"name": "...", "email": "...", "phone": "...", "password": "..."}`
    * **Response**: `201 Created` with the location of the new resource in the `Location` header.
* **`PUT /users/{id}`**
    * Updates an existing user. The request body should contain the updated user data.
    * **Path Variable**: `{id}` is the user's ID.
    * **Response**: `200 OK` if successful, `404 Not Found` if the user does not exist.
* **`DELETE /users/{id}`**
    * Deletes a user by their unique ID.
    * **Path Variable**: `{id}` is the user's ID.
    * **Response**: `204 No Content` if successful, `404 Not Found` if the user does not exist.

#### **Product Endpoints (`/products`)**

* **`GET /products`**
    * Retrieves a list of all products.
    * **Response**: `200 OK`
* **`GET /products/{id}`**
    * Retrieves a product by their unique ID.
    * **Path Variable**: `{id}` is the product's ID.
    * **Response**: `200 OK` if found, `404 Not Found` otherwise.
* **`POST /products`**
    * Creates a new product.
    * **Request Body**: `{"name": "...", "price": "...", "description": "...", "imgUrl": "...", "categories": [{"id": 1}, {"id": 2}]}`
    * **Response**: `201 Created`
* **`PUT /products/{id}`**
    * Updates an existing product.
    * **Path Variable**: `{id}` is the product's ID.
    * **Response**: `200 OK` if successful, `404 Not Found` if the product does not exist.
* **`DELETE /products/{id}`**
    * Deletes a product by their unique ID.
    * **Path Variable**: `{id}` is the product's ID.
    * **Response**: `204 No Content` if successful, `404 Not Found` if the product does not exist.

#### **Order Endpoints (`/orders`)**

* **`GET /orders`**
    * Retrieves a list of all orders.
    * **Response**: `200 OK`
* **`GET /orders/{id}`**
    * Retrieves an order by its unique ID.
    * **Path Variable**: `{id}` is the order's ID.
    * **Response**: `200 OK` if found, `404 Not Found` otherwise.
* **`POST /orders`**
    * Creates a new order. The request body must contain the client's ID and the order items.
    * **Request Body**: `{"client": {"id": 1}}`
    * **Response**: `201 Created`
* **`PUT /orders/{id}`**
    * Updates the status of an existing order.
    * **Path Variable**: `{id}` is the order's ID.
    * **Request Body**: `{"orderStatus": "PAID"}`
    * **Response**: `200 OK` if successful, `404 Not Found` if the order does not exist.
* **`DELETE /orders/{id}`**
    * Deletes an order by its unique ID.
    * **Path Variable**: `{id}` is the order's ID.
    * **Response**: `204 No Content` if successful, `404 Not Found` otherwise.

#### **Category Endpoints (`/categories`)**

* **`GET /categories`**
    * Retrieves a list of all categories.
    * **Response**: `200 OK`
* **`GET /categories/{id}`**
    * Retrieves a category by its unique ID.
    * **Path Variable**: `{id}` is the category's ID.
    * **Response**: `200 OK` if found, `404 Not Found` otherwise.
* **`POST /categories`**
    * Creates a new category.
    * **Request Body**: `{"name": "..."}`
    * **Response**: `201 Created`
* **`PUT /categories/{id}`**
    * Updates an existing category.
    * **Path Variable**: `{id}` is the category's ID.
    * **Response**: `200 OK` if successful, `404 Not Found` if the category does not exist.
* **`DELETE /categories/{id}`**
    * Deletes a category by its unique ID.
    * **Path Variable**: `{id}` is the category's ID.
    * **Response**: `204 No Content` if successful, `404 Not Found` if not found, `409 Conflict` if still associated with products.

---

### 🗄️ H2 Database Access

To view the data and database structure in real-time, you can access the H2 console.

* **URL:** `http://localhost:8080/h2-console`
* **JDBC URL:** `jdbc:h2:mem:testdb`
* **User:** `sa`
* **Password:** *(Leave blank)*

---
