# Rapidex Mobile API

Rapidex is a robust backend solution designed to power mobile logistics and order management. This repository contains the REST API currently consumed by the Android client, with an MVC Web Interface currently under development.

## Getting Started

### Base URL

All API requests should be made to:

```
http://localhost:8080/api
```

## Technical Specifications

- **Data Format**: All requests and responses utilize JSON.
- **Date Handling**: Dates are returned as formatted Strings.
- **Nullability**: `null` values are explicitly allowed where specified in the schema.
- **Empty States**: Empty lists (`[]`) are returned as valid responses when no data is found.

## API Endpoints

### Employee Authentication

#### `POST employees/login`
**Description**: Authenticates an employee using username and password.
If the credentials are valid returns basic employee information, if not,
returns and error with basic information.

**Request** — `Body (JSON)`
```json
{
  "username": "jdoe",
  "password": "1234"
}
```

**Success Response** — `200 OK`
```json
{
  "id": 3,
  "firstName": "John",
  "lastName": "Doe",
  "username": "jdoe"
}
```

**Error Response** — `400 Bad Request`
```json
{
  "status": 400,
  "error": "Bad Request",
  "message": "Invalid username or password"
}

```

### Orders Management

#### `GET /orders`

**Description**: Retrieves a comprehensive list of all orders within the system. Each order object includes nested product details and the assigned employee information.

**Response** — `200 OK`

```json
[
  {
    "id": 7,
    "products": [
      {
        "id": 1,
        "productName": "Laptop",
        "productCategory": "Electronics",
        "productDescription": "15-inch business laptop"
      }
    ],
    "employee": {
      "id": 3,
      "firstName": "Carlos",
      "lastName": "Ramirez"
    },
    "prepDate": "2026-01-10",
    "dispatchDate": null
  }
]
```

#### `GET /orders/pending`

**Description**: Retrieves a comprehensive list of all the *pending* orders within the system. A pending order consists on an order with products linked to it but not claimed by any employee and not having preparation date nor dispatch date.

**Response** — `200 OK`

```json
[
  {
    "id": 1,
    "products": [
      {
        "id": 1,
        "productName": "Laptop",
        "productCategory": "Electronics",
        "productDescription": "15-inch business laptop"
      },
      {
        "id": 2,
        "productName": "Office Chair",
        "productCategory": "Furniture",
        "productDescription": "Ergonomic adjustable chair"
      }
    ],
    "employee": null,
    "prepDate": null,
    "dispatchDate": null
  }
]
```

#### `DELETE /orders/{orderId}`

**Description**: Deletes an order only if is marked as **_finished_**.
An order is considered **_finalized_** / **_finished_** when:
* Is assigned to an employee
* It has a preparation date
* It has a dispatch date

If none of these conditions are met, the order **will not be deleted**.

**Response** — `204 No Content`
>_No response body._

The order is completely removed from the database.

### Incidents Management

#### `GET /incidents`

**Description**: Retrieves a list of all incidents reported in the system. Each incident includes the type, description, associated order, and employee information.

**Response** — `200 OK`

```json
[
  {
    "id": 1,
    "type": "no_stock",
    "description": null,
    "orderId": 5,
    "employeeId": 3,
    "employeeName": "Carlos Ramirez"
  },
  {
    "id": 2,
    "type": "no_stock",
    "description": null,
    "orderId": 6,
    "employeeId": 4,
    "employeeName": "Diana Lopez"
  },
  {
    "id": 3,
    "type": "no_stock",
    "description": null,
    "orderId": 7,
    "employeeId": 5,
    "employeeName": "Eva Martinez"
  }
]
```

#### `GET /incidents/employee/{id}`

**Description**: Retrieves all incidents associated with a specific employee by their ID.

**Path Parameters**:
- `id` (Int): The unique identifier of the employee.

**Response** — `200 OK`

```json
[
  {
    "id": 1,
    "type": "no_stock",
    "description": null,
    "orderId": 5,
    "employeeId": 3,
    "employeeName": "Carlos Ramirez"
  }
]
```

#### `POST /incidents`

**Description**: Creates a new incident in the system.

**Request Body**:

```json
{
  "type": "no_stock",
  "description": "Optional description of the incident",
  "orderId": 5,
  "employeeId": 3
}
```

**Response** — `200 OK`

```json
{
  "id": 1,
  "type": "no_stock",
  "description": "Optional description of the incident",
  "orderId": 5,
  "employeeId": 3,
  "employeeName": "Carlos Ramirez"
}
```

## Error Handling

The API uses standard HTTP status codes and returns JSON error responses for all error cases.

### Error Response Format

All errors follow a consistent JSON structure:

```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Order not found"
}
```

### HTTP Status Codes

#### `400 Bad Request`

Returned when the request is malformed or contains invalid data.

**Common scenarios**:
- Attempting to create an incident for an order that is not assigned to an employee.
- Missing required fields in the request body.
- Invalid data types or formats.

**Example**:

```json
{
  "status": 400,
  "error": "Bad Request",
  "message": "Order not assigned to any employee"
}
```

#### `404 Not Found`

Returned when a requested resource does not exist in the system.

**Common scenarios**:
- Requesting an order that doesn't exist.
- Requesting an employee that doesn't exist.
- Accessing a non-existent endpoint.

**Example**:

```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Order not found"
}
```

#### `500 Internal Server Error`

Returned when an unexpected error occurs on the server side.

**Common scenarios**:
- Database connection failures.
- Unexpected runtime exceptions.
- Server configuration issues.

**Example**:

```json
{
  "status": 500,
  "error": "Internal Server Error",
  "message": "An unexpected error occurred"
}
```

## Web Interface (MVC)

### Getting Started (Admin Panel Setup)

This section explains how to clone, configure, and run the Rapidex project in order to access
the Web Administration Panel.  
These steps are written so that **developers with little or no Spring experience** can follow them
without issues.

---

#### Cloning the Repository (IntelliJ IDEA)

``git clone https://github.com/dariusxdmemes/rapidex-mobile-api``

1. Open **IntelliJ IDEA**
2. On the welcome screen, select **“Get from VCS”**
3. Choose **Git** as the version control system
4. Paste the repository URL into the **URL** field
5. Select a local destination folder
6. Click **Clone**

Once the project is cloned, IntelliJ will automatically open it and start indexing the files.

>  If IntelliJ asks to trust the project, select **Trust Project**.

---

#### Project Configuration

Before running the application, make sure the following configuration files are present and correctly set.

---

##### `application.properties`

This file configures the database connection and JPA behavior.

```properties
spring.application.name=rapidex-mobile-api

# DATA SOURCE (CHANGE localhost TO VPS DOMAIN IF DEPLOYED)
spring.datasource.url=jdbc:mysql://localhost:3306/rapidex_db?serverTimezone=UTC
spring.datasource.username=rapidex
spring.datasource.password=R@pidex_123
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA / HIBERNATE
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```

# Important

Ensure MySQL is running

The database `rapidex_db` must exist

Update credentials if necessary

## build.gradle.kts – Dependencies

The project relies on the following dependencies.
```kotlin
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // THIS DEPENDENCY DOES NOT WORK FOR MVC
    // implementation("org.springframework.boot:spring-boot-starter-webmvc")

    // CORRECT DEPENDENCY FOR MVC (USE THIS)
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("org.springframework.boot:spring-boot-starter-thymeleaf") // Thymeleaf templates
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("tools.jackson.module:jackson-module-kotlin")

    testImplementation("org.springframework.boot:spring-boot-starter-security")
    runtimeOnly("com.mysql:mysql-connector-j")

    testImplementation("org.springframework.boot:spring-boot-starter-test-classic")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
```

### Critical Note

`spring-boot-starter-webmvc` DOES NOT WORK for this project

Always use:
```kotlin
implementation("org.springframework.boot:spring-boot-starter-web")
```

This dependency includes:

- Spring MVC
- Embedded Tomcat
- Request mapping
- View resolution (Thymeleaf support)

## Running the Application

1. Locate the main Spring Boot class (annotated with `@SpringBootApplication`)
2. Right-click the file
3. Select Run

Once the application starts successfully, open a browser and navigate to:
```
http://localhost:8080/rapidex
```

## Web Interface (Administration Panel)

Rapidex includes a web-based administration panel designed for warehouse supervisors and managers.
This interface allows full control and monitoring of employees, orders, and products through a simple
and intuitive UI built with Spring MVC, Thymeleaf, and Bootstrap.

The web interface is completely independent from the REST API consumed by the Android application.

### Accessing the Admin Panel

Once the application is running, access the administration panel at:
```
http://localhost:8080/rapidex
```

You will be greeted with the main dashboard, which serves as the entry point to all administrative features.

### Dashboard Overview

The dashboard provides quick access to the main management areas:

- Employees
- Orders
- Products

Each section is accessible through clearly labeled cards.

## Employee Management

### Viewing Employees

Navigate to:
```
/rapidex/employees
```

This screen displays all registered employees in a table format.

Each employee row includes:

- Employee ID
- First name
- Last name
- Username
- Access to detailed employee information

### Creating a New Employee

From the employee list, click "Register new employee".

Required information:

- First name
- Last name
- Password

> The system **automatically generates and validates** usernames to prevent duplicates.

### Employee Details

Click "Employee Info" to view detailed information about a specific employee.

## Order Management

### Viewing Orders

Navigate to:
```
/rapidex/orders
```

This screen displays all orders, including:

- Assigned products
- Assigned employee (if any)
- Preparation date
- Dispatch date

### Order Details

Click "Order Info" to view detailed information for a specific order.

### Deleting Orders (Admin Override)

**Important**: Orders are automatically removed once they are completed (assigned employee, preparation date, and dispatch date).

However, administrators can force-delete an order in exceptional cases such as:

- Faulty orders
- Test data cleanup

A confirmation dialog is shown before deletion.

## Product Management

### Viewing Products

Navigate to:
```
/rapidex/products
```

This section displays all available products stored in the warehouse.

### Creating a Product

Click "Register new product".

Available product categories are dynamically loaded from the database:

- Electronics
- Furniture
- Peripherals

Products can be created without an image, allowing image URLs to be added later.

### Editing Products

Products can be edited at any time to update:

- Name
- Category
- Description
- Image URL

### Deleting Products

Products can be permanently removed from the system if they are no longer available or have been discontinued.

## Architecture Notes

- The web interface uses Spring MVC + Thymeleaf
- The Android application consumes a separate REST API
- Both layers share the same service and repository logic
- Business rules are enforced at the service level, not in the UI.