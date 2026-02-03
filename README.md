# Rapidex Mobile API

Rapidex is a robust backend solution designed to power mobile logistics and order management. This repository contains the REST API currently consumed by the Android client, with an MVC Web Interface currently under development.

## Getting Started

### Base URL

All API requests should be made to:

```
http://localhost:8080/api
```

### Authentication

> Authentication is currently not implemented.
## Technical Specifications

- **Data Format**: All requests and responses utilize JSON.
- **Date Handling**: Dates are returned as ***dd-MM-yyyy/HH-mm-ss*** formatted Strings.
- **Nullability**: `null` values are explicitly allowed where specified in the schema.
- **Empty States**: Empty lists (`[]`) are returned as valid responses when no data is found.

## API Endpoints

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

**Description**: Retrieves a comprehensive list of all the *pending* orders within the system. A pending order consists on a order with products linked to it but not claimed by any employee and not having preparation date nor dispatch date.

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
- `id` (Long): The unique identifier of the employee.

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

The project is currently expanding to include a web-based administration dashboard.

- **Status**: In Progress / Not yet implemented.
- **Planned Features**:
    - Visual order managing.
    - Employee management dashboard.