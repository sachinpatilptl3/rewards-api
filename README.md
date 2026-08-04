# Rewards Program API

## Overview

This project is a Spring Boot REST API that calculates reward points for customers based on their purchases.

The API calculates reward points earned by a customer over a specified date range and provides both monthly and total reward points.

## Reward Calculation

The reward rules are:

- No points for purchases up to $50.
- 1 point for every dollar spent between $50 and $100.
- 2 points for every dollar spent above $100.

### Example

Purchase Amount: $120

Reward Points:

- 50 points for the amount between $50 and $100.
- 40 points for the amount above $100.

Total = 90 Reward Points

---

## Technologies Used

- Java 17+ (or your Java version)
- Spring Boot
- Maven
- Lombok
- Java Streams

---

## Project Structure

```
src
 ├── controller
 ├── dto
 ├── model
 ├── repository
 ├── service
 └── RewardsApplication
```

---

## Prerequisites

- Java JDK
- Maven
- IntelliJ IDEA (or any Java IDE)

---

## Setup

Clone the repository

```
git clone <your-github-url>
```

Move to project folder

```
cd rewards
```

Build the project

```
mvn clean install
```

Run the application

```
mvn spring-boot:run
```

Or run `RewardsApplication.java` from your IDE.

---

## API Endpoint

### Get Customer Rewards

```
GET /api/rewards/{customerId}
```

### Request Parameters

| Parameter | Description |
|----------|-------------|
| customerId | Customer ID |
| startDate | Start Date (yyyy-MM-dd) |
| endDate | End Date (yyyy-MM-dd) |

### Example Request

```
GET http://localhost:8080/api/rewards/101?startDate=2025-01-01&endDate=2025-03-31
```

### Example Response

```json
{
  "customerId":101,
  "customerName":"John",
  "monthlyRewards":{
    "JANUARY":115,
    "FEBRUARY":250
  },
  "totalRewards":365
}
```

---

## Assumptions

- Transaction data is hardcoded for demonstration.
- Reward points are calculated per transaction.
- Date range is inclusive.
- Customer IDs are unique.
- The application does not use a database.

---

## Design Decisions

- Used layered architecture (Controller → Service → Repository).
- Used DTOs to separate API responses from the data model.
- Used Java Streams for grouping transactions by month.
- Used constructor injection for dependency injection.
- Repository returns sample data to demonstrate the solution without requiring a database.

---

## Future Enhancements

- Integrate with a relational database (PostgreSQL/MySQL).
- Add unit and integration tests.
- Add exception handling using `@ControllerAdvice`.
- Improve month formatting (e.g., January instead of JANUARY).
- Add pagination and customer search.
