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
  "unique_id": "FX2317",
  "from_currency": "EUR",
  "to_currency": "USD",
  "deal_timestamp": "2023-10-22T19:18:50.685Z",
  "deal_amount": 110
}
```

**Success Response**:

```json
{
  "id": "22dcd65c-b730-46a1-aede-afdb5e9ce01c",
  "unique_id": "FX2317",
  "from_currency": "EUR",
  "to_currency": "USD",
  "deal_timestamp": "2023-10-22T19:18:50.685Z",
  "deal_amount": 110
}
```

**Error Response (Handling duplication of Deal entries)**:

```json
{
  "success": false,
  "error": true,
  "data": "Deal already exists with ID: FX2315",
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
  "unique_id": "FX2317"
}
```
**Success Response**:

```json
{
  "success": true,
  "error": false,
  "data": {
    "id": "22dcd65c-b730-46a1-aede-afdb5e9ce01c",
    "unique_id": "FX2317",
    "from_currency": "EUR",
    "to_currency": "USD",
    "deal_timestamp": "2023-10-22T19:18:50.685Z",
    "deal_amount": 110
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
    "id": "ed6ced87-1bf8-430d-b0ee-062a989eee42",
    "unique_id": "FX2315",
    "from_currency": "EUR",
    "to_currency": "USD",
    "deal_timestamp": "2023-10-22T19:18:50.685Z",
    "deal_amount": 110
  },
  {
    "id": "705181c7-6ed9-46b6-be98-d3fa077ee3ad",
    "unique_id": "FX2316",
    "from_currency": "EUR",
    "to_currency": "USD",
    "deal_timestamp": "2023-10-22T19:18:50.685Z",
    "deal_amount": 110
  },
  {
    "id": "22dcd65c-b730-46a1-aede-afdb5e9ce01c",
    "unique_id": "FX2317",
    "from_currency": "EUR",
    "to_currency": "USD",
    "deal_timestamp": "2023-10-22T19:18:50.685Z",
    "deal_amount": 110
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

