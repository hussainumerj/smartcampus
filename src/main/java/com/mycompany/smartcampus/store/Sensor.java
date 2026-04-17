/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampus.store;


public class Sensor {
    public String id;
    public String type;          // e.g. CO2, TEMP
    public String status;        // e.g. ACTIVE, MAINTENANCE
    public double currentValue;  // updated by readings
    public String roomId;        // linked room

    public Sensor() {}

    public Sensor(String id, String type, String status, double currentValue, String roomId) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.currentValue = currentValue;
        this.roomId = roomId;
    }
}