package com.client_app.component;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

// Class for making API Calls to backend.
public class Client {
    private final HttpClient client = HttpClient.newHttpClient();

    private String bearerToken;
    private final String apiIP = "http://localhost:8888/";

    public Client() {}

    public Client(String bearerToken) {
        this.bearerToken = bearerToken;
    }

    public void setBearerToken(String bearerToken) {
        this.bearerToken = bearerToken;
    }

    public void postRequest(String path, String context) {
        System.out.println("Make api call here");
    }

    // Function for authorize Teacher user
    public boolean authorizeClient(String email, String password) {
        // HttpClient client = httpClient.newHttpClient();

        Map<String, String> data = new HashMap<String, String>() {{
            put("email", email);
            put("password", password);
        }};

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonData = objectMapper.writeValueAsString(data);
            
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(apiIP + "rest/auth/login"))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(jsonData))
                .build();

            HttpResponse<String> response = this.client.send(request, BodyHandlers.ofString());
            Map<String, Object> responseBody = objectMapper.readValue(response.body(), HashMap.class);
            String token = (String) responseBody.get("token");
            System.out.println(token);
            if (token == null) {
                return false;
            } else {
                setBearerToken(token);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }

    // Function for authorize Student user
    public boolean authorizeClient(String email, String password, String studentIndex) {

        Map<String, String> data = new HashMap<String, String>() {{
            put("email", email);
            put("password", password);
            put("studentIndex", studentIndex);
        }};

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonData = objectMapper.writeValueAsString(data);
            
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(apiIP + "rest/auth/login"))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(jsonData))
                .build();

            HttpResponse<String> response = this.client.send(request, BodyHandlers.ofString());
            Map<String, Object> responseBody = objectMapper.readValue(response.body(), HashMap.class);
            
            String token = (String) responseBody.get("token");
            System.out.println(token);
            if (token == null) {
                return false;
            } else {
                setBearerToken(token);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public ArrayList<Object> getListRequest(String path) {
        ArrayList<Object> resultList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(apiIP + path))
                .header("Authorization", "Bearer " + bearerToken)
                .header("Content-Type", "application/json")
                .GET()
                .build();

            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            System.out.println("Response status code: " + response.statusCode());
            System.out.println("Response body: " + response.body());

            // Parse the JSON response into an ArrayList<Object>
            resultList = objectMapper.readValue(response.body(), new TypeReference<ArrayList<Object>>() {});
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return resultList;
    }

    @Override
    public String toString() {
        return this.bearerToken;
    }


}
