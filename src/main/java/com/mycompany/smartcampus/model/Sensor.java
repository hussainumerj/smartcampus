/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampus.model;

public class Sensor {
    public String id;
    public String type;          // e.g. CO2, TEMP
    public String status;        // e.g. ACTIVE, MAINTENANCE
    public double currentValue;  // updated by readings
    public String roomId;        // links to a room

    public Sensor() {}
}