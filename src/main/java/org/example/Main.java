package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.google.gson.Gson;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Main {
    public static void main(String[] args) throws IOException {
        // Create a scanner object to get user input
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter pickup location : ");
        String pickup = scan.nextLine();
        System.out.println("Enter dropoff location :");
        String dropoff = scan.nextLine();

        // Build the URL for the API request
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://maps.googleapis.com/maps/api/distancematrix/json").newBuilder();
        urlBuilder.addQueryParameter("origins", pickup);
        urlBuilder.addQueryParameter("destinations", dropoff);
        urlBuilder.addQueryParameter("units", "imperial");
        urlBuilder.addQueryParameter("key", "AIzaSyB2Gy1rH3_qM4nbYWwNoCB1AIcS4yoLjoI");

        // Create a new OkHttpClient to execute the request
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        // Build the request with the URL and HTTP method
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {
            // Read the response body as a string
            String jsonString = response.body().string();

            // Print the JSON response to the console
            System.out.println(jsonString);

            // Create a new file object and specify the file name and location
            FileWriter fileWriter = new FileWriter("response.json");

            // Use Gson to parse the JSON and write it to the file
            Gson gson = new Gson();
            gson.toJson(jsonString, fileWriter);

            // Close the file writer
            fileWriter.close();
        } catch (IOException e) {
            // If an exception occurs, throw a runtime exception
            throw new RuntimeException(e);
        }
    }
}
