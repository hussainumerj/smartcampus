/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampus;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class Main {
    public static void main(String[] args) {
        URI baseUri = URI.create("http://0.0.0.0:8081/api/v1/");

        ResourceConfig rc = new ResourceConfig()
        .register(com.mycompany.smartcampus.resource.DiscoveryResource.class)
        .register(com.mycompany.smartcampus.resource.RoomResource.class)
        .register(com.mycompany.smartcampus.resource.SensorResource.class)
        .register(com.mycompany.smartcampus.resource.SensorReadingResource.class)
        .packages("com.mycompany.smartcampus.mapper");

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, rc);

        System.out.println("Server running at " + baseUri);
    }
}