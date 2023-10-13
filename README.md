# FX Deals Data Warehouse API

This application provides a data warehouse solution for foreign exchange deals.

## Table of Contents
- [Prerequisites](#prerequisites)
- [API Reference](#api-reference)
- [Build & Run](#build--run)
- [API Endpoints](#api-endpoints)
    - [Save FX Deal](#save-fx-deal)
    - [Get FX Deal by Unique ID](#get-fx-deal-by-unique-id)
    - [Get All FX Deals](#get-all-fx-deals)
- [Tests](#tests)
- [Contributor](#contributor)

## Prerequisites

- Java 11 or higher
- Docker
- Docker Compose
- Maven

## API Reference
- All URIs are relative to http://localhost:8080 (To use with swagger: http://localhost:8080/swagger-ui/index.html#/).


## Build & Run

## Note:
To run this application, ensure that Docker and make are installed and active. Thereafter, follow these steps:

- Clone the repository: (https://github.com/Realwale/fx-deals-data-warehouse.git)
- Navigate to the fx-deals-datawarehouse directory.

**Build the Project**:

```bash
make build
```

**Run the Application using Docker**:

```bash
make run
```
- The app will run on port 8080.

**Stop the Application**:

```bash
make stop
```

**Run Tests**:

```bash
make test
```

## API Endpoints

### Save FX Deal

**Endpoint**:

```http
POST /api/fx-deal/import
```

**Request**:

```json
{
  "unique_id": "FX346",
  "from_currency": "EUR",
  "to_currency": "USD",
  "deal_timestamp": "2023-10-12T20:17:53.575Z",
  "deal_amount": 100.5
}
```

**Success Response**:

```json
{
  "success": true,
  "error": false,
  "data": {
    "id": "5d28c4a6-2bbc-46fc-8671-e85ff26bebb0",
    "unique_id": "FX346",
    "from_currency": "EUR",
    "to_currency": "USD",
    "deal_timestamp": "2023-10-12T20:17:53.575Z",
    "deal_amount": 100.5
  },
  "status_code": 201
}
```

**Error Response (Attempting duplication of Deal)**:

```json
{
  "success": false,
  "error": true,
  "data": "Deal already exists with ID: FX346",
  "status_code": 409
}
```

**Request (Demonstrating fields validation)**:

```json
{
  "unique_id": "string",
  "from_currency": "string",
  "to_currency": "string",
  "deal_timestamp": "2023-10-12T20:40:15.062Z",
  "deal_amount": 0
}
```

**Response Body (Response for fields validation)**:

```json
{
  "success": false,
  "error": true,
  "data": "Invalid From Currency ISO Code, Invalid To Currency ISO Code, Invalid deal amount",
  "status_code": 400
}
```

Status code `201` for successfully created, `409` for conflict if deal already exists.

### Get FX Deal by Unique ID

**Endpoint**:

```http
GET /api/fx-deal/{uniqueId}
```

**Request**:

```json
{
  "unique_id": "FX159"
}
```
**Success Response**:

```json
{
  "success": true,
  "error": false,
  "data": {
    "id": "96f5a587-13fc-4213-84cd-73578186403c",
    "unique_id": "FX159",
    "from_currency": "USD",
    "to_currency": "EUR",
    "deal_timestamp": "2023-10-12T21:34:25.326Z",
    "deal_amount": 200
  },
  "status_code": 200
}
```

**Error Response for wrong uniqueId: FX150**:

```json

{
  "success": false,
  "error": true,
  "data": "No deal found with ID: FX150",
  "status_code": 404
}

```


Status code `200` for success, `404` if not found.

### Get All FX Deals

**Endpoint**:

```http
GET /api/fx-deal
```

**Response**:

```json
[
  {
    "success": true,
    "error": false,
    "data": {
      "id": "be090e8c-2fe8-43b6-964f-ada8b7f051ce",
      "unique_id": "FX136",
      "from_currency": "EUR",
      "to_currency": "USD",
      "deal_timestamp": "2023-10-12T21:34:25.326Z",
      "deal_amount": 100
    },
    "status_code": 200
  },
  {
    "success": true,
    "error": false,
    "data": {
      "id": "96f5a587-13fc-4213-84cd-73578186403c",
      "unique_id": "FX159",
      "from_currency": "USD",
      "to_currency": "EUR",
      "deal_timestamp": "2023-10-12T21:34:25.326Z",
      "deal_amount": 200
    },
    "status_code": 200
  }
]
```


Status code `200` for success.

## Tests

Unit tests can be run using:

```bash
make test
```

Ensure all dependencies are properly set up before running the tests.

## Contributor

- [Olawale Agboola](https://www.linkedin.com/in/agboolawale)

