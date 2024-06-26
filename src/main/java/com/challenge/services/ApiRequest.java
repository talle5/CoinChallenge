package com.challenge.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class ApiRequest {
    public static String getHttps(URI destino) {
        try (var client = HttpClient.newHttpClient()) {
            var request = HttpRequest.newBuilder(destino).build();
            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
