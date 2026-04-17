/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampus.store;

public class SensorReading {
    public String id;        // UUID
    public String timestamp; // ISO string
    public double value;

    public SensorReading() {}

    public SensorReading(String id, String timestamp, double value) {
        this.id = id;
        this.timestamp = timestamp;
        this.value = value;
    }
}