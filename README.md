# SmartCampus REST API (5COSC022W Coursework)

This project is a RESTful API for a “Smart Campus” system. It manages:
- Rooms (campus spaces)
- Sensors (devices installed in rooms)
- Sensor Readings (historical readings per sensor)

The API is built using JAX-RS (Jersey) with an embedded HTTP server, and stores data **in-memory** using Java collections (no database).

> Base path: `/api/v1`  
> Example base URL (NetBeans output): `http://localhost:8081/api/v1/`

---

## API Design Overview

### Resources
- **Discovery**
  - `GET /api/v1/` returns a small JSON document listing key endpoints.
- **Rooms**
  - `GET /api/v1/rooms` list rooms
  - `POST /api/v1/rooms` create a room
  - `GET /api/v1/rooms/{roomId}` get one room
  - `DELETE /api/v1/rooms/{roomId}` delete a room  
    - returns **409 Conflict** if the room still has sensors (RoomNotEmpty)
- **Sensors**
  - `GET /api/v1/sensors` list sensors
  - `GET /api/v1/sensors?type=CO2` filter sensors by type
  - `POST /api/v1/sensors` create a sensor (validates roomId exists)
- **Readings (sub-resource)**
  - `GET /api/v1/sensors/{sensorId}/readings` list readings for that sensor
  - `POST /api/v1/sensors/{sensorId}/readings` add reading  
    - also updates the parent sensor’s `currentValue`

### Error handling & observability
The API uses Exception Mappers and Filters to ensure it never returns raw stack traces and logs requests/responses.  
Required scenarios include:
- 409 Conflict when deleting a room that still has sensors
- 422 Unprocessable Entity(or 400) when creating a sensor with a non-existent `roomId`
- 403 Forbidden if readings are posted while sensor status is `MAINTENANCE`
- 500 catch-all mapper
- request/response logging filter (method + URI, and response status)
