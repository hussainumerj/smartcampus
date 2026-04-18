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
        URI baseUri = URI.create("http://0.0.0.0:8080/");

        ResourceConfig rc = new ResourceConfig()
                .packages("com.mycompany.smartcampus"); // scans all resources/mappers

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, rc);

        System.out.println("Server running at " + baseUri + "api/v1");
    }
}
