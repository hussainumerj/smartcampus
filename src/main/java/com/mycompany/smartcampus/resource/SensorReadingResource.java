/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampus.resource;

import com.mycompany.smartcampus.model.Sensor;
import com.mycompany.smartcampus.model.SensorReading;
import com.mycompany.smartcampus.store.DataStore;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Produces(MediaType.APPLICATION_JSON)
public class SensorReadingResource {

    private final String sensorId;

    public SensorReadingResource(String sensorId) {
        this.sensorId = sensorId;
    }

    @GET
    public List<SensorReading> getReadings() {
        Sensor s = DataStore.sensors.get(sensorId);
        if (s == null) throw new NotFoundException("Sensor not found");
        return DataStore.getReadingsList(sensorId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addReading(SensorReading reading) {
        Sensor s = DataStore.sensors.get(sensorId);
        if (s == null) throw new NotFoundException("Sensor not found");

        if (reading.getId() == null || reading.getId().isBlank()) {
            reading.setId(UUID.randomUUID().toString());
        }
        if (reading.getTimestamp() == 0L) {
            reading.setTimestamp(System.currentTimeMillis());
        }

        DataStore.getReadingsList(sensorId).add(reading);

        // side effect: update sensor currentValue
        s.setCurrentValue(reading.getValue());

        return Response.status(201).entity(reading).build();
    }
}
