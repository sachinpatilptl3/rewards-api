# Customer Rewards API

## Overview

The Customer Rewards API is a Spring Boot REST application that calculates reward points for customers based on their purchase transactions.

The application uses:

- Spring Boot 3.5.3
- Spring Data JPA
- H2 In-Memory Database
- Maven
- Java 17

---

# Architecture Diagram

```
                    +----------------------+
                    |      Client          |
                    | (Postman / Browser)  |
                    +----------+-----------+
                               |
                               |
                               v
                 +-----------------------------+
                 |     RewardController        |
                 +-------------+---------------+
                               |
                               |
                               v
                 +-----------------------------+
                 |     RewardServiceImpl       |
                 +-------------+---------------+
                               |
                               |
                               v
                 +-----------------------------+
                 |  TransactionRepository      |
                 +-------------+---------------+
                               |
                               |
                               v
                      +------------------+
                      |   H2 Database    |
                      +------------------+
```

---

# Design

The application follows a layered architecture.

- Controller Layer
    - Handles HTTP requests.
    - Validates request parameters.

- Service Layer
    - Implements reward calculation logic.
    - Builds response DTOs.

- Repository Layer
    - Retrieves transaction data using Spring Data JPA.

- Utility Layer
    - Calculates reward points.

- Exception Layer
    - Provides centralized exception handling using `@ControllerAdvice`.

---

# API Endpoint

```
GET /api/rewards/customers/{customerId}/rewards
```

---

# Sample Request

```
GET http://localhost:8080/api/rewards/customers/101/rewards?startDate=2025-01-01&endDate=2025-03-31
```

---

# Sample Success Response

```json
{
  "customerId": 101,
  "customerName": "John Doe",
  "monthlyRewards": [
    {
      "year": 2025,
      "month": "January",
      "points": 90
    },
    {
      "year": 2025,
      "month": "February",
      "points": 30
    }
  ],
  "transactions": [
    {
      "transactionDate": "2025-01-10",
      "amount": 120.00,
      "points": 90
    },
    {
      "transactionDate": "2025-02-15",
      "amount": 80.00,
      "points": 30
    }
  ],
  "totalRewards": 120
}
```

---

# Sample Error Response

```json
{
  "timestamp": "2026-08-07T14:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "startDate must not be after endDate"
}
```

Example:

```
GET /api/rewards/customers/101/rewards?startDate=2025-04-01&endDate=2025-01-01
```

---

# Build Instructions

Clone the repository

```bash
git clone <repository-url>
```

Move to the project directory

```bash
cd rewards
```

Compile the project

```bash
./mvnw clean compile
```

Run the application

```bash
./mvnw spring-boot:run
```

---

# Running Tests

Run all unit tests

```bash
./mvnw test
```

or

```bash
./mvnw clean test
```

Successful execution should display:

```
BUILD SUCCESS
```

---

# H2 Database

Console URL

```
http://localhost:8080/h2-console
```

JDBC URL

```
jdbc:h2:mem:testdb
```

Username

```
sa
```

Password

```
(blank)
```