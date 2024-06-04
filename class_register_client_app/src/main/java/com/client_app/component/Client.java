package com.client_app.component;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// Class for making API Calls to backend.
public class Client {
    private HttpClient HttpClient;

    private String bearerToken;

    public Client(String bearerToken) {
        this.bearerToken = bearerToken;
    }

    public void makeAPICall() {
        System.out.println("Make api call here");
    }

}
