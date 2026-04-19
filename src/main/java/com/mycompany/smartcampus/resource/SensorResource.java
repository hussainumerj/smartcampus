/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampus.resource;

import com.mycompany.smartcampus.exception.LinkedResourceNotFoundException;
import com.mycompany.smartcampus.model.Room;
import com.mycompany.smartcampus.model.Sensor;
import com.mycompany.smartcampus.store.DataStore;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Path("/sensors")
@Produces(MediaType.APPLICATION_JSON)
public class SensorResource {

    @GET
    public Collection<Sensor> getSensors(@QueryParam("type") String type) {
        if (type == null || type.isBlank()) return DataStore.sensors.values();

        String t = type.trim();
        return DataStore.sensors.values().stream()
                .filter(s -> s.getType() != null && s.getType().equalsIgnoreCase(t))
                .collect(Collectors.toList());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSensor(Sensor sensor) {
        if (sensor.getRoomId() == null || sensor.getRoomId().isBlank()) {
            throw new LinkedResourceNotFoundException("Sensor must include a valid roomId");
        }

        Room room = DataStore.rooms.get(sensor.getRoomId());
        if (room == null) {
            throw new LinkedResourceNotFoundException("Room does not exist: " + sensor.getRoomId());
        }

        if (sensor.getId() == null || sensor.getId().isBlank()) {
            sensor.setId(UUID.randomUUID().toString());
        }

        DataStore.sensors.put(sensor.getId(), sensor);

        if (!room.getSensorIds().contains(sensor.getId())) {
            room.getSensorIds().add(sensor.getId());
        }

        return Response.status(201).entity(sensor).build();
    }

    @GET
    @Path("/{sensorId}")
    public Sensor getSensor(@PathParam("sensorId") String sensorId) {
        Sensor s = DataStore.sensors.get(sensorId);
        if (s == null) throw new NotFoundException("Sensor not found");
        return s;
    }

    // ✅ Step 7 sub-resource locator goes here (next step)
    @Path("/{sensorId}/readings")
    public SensorReadingResource readings(@PathParam("sensorId") String sensorId) {
        return new SensorReadingResource(sensorId);
    }
}
