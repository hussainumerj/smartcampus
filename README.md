# SmartCampus REST API (5COSC022W Coursework)

## Overview
This project implements a SmartCampus REST API using JAX-RS (Jersey) and an in-memory datastore (no database).

### Base URL
http://localhost:8080/api/v1

### Main Endpoints
- GET /api/v1 (Discovery)
- GET/POST /api/v1/rooms
- GET/DELETE /api/v1/rooms/{roomId}
- GET/POST /api/v1/sensors
- GET /api/v1/sensors/{sensorId}
- GET/POST /api/v1/sensors/{sensorId}/readings

---

## How to build and run

### Requirements
- Java JDK 17
- Maven

### Build
```bash
mvn clean package
