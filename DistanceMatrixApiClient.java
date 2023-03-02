package org.example;

import org.json.JSONObject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class DistanceMatrixApiClient {
    private static final String API_KEY = "AIzaSyB2Gy1rH3_qM4nbYWwNoCB1AIcS4yoLjoI";

    public static String getTravelTime(String origin, String destination) throws IOException {
        // Build the URL for the Google Maps Distance Matrix API request
        String urlString = String.format("https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=%s&destinations=%s&key=%s", origin, destination, API_KEY);

        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            Scanner scanner = new Scanner(url.openStream());
            String response = scanner.useDelimiter("\\A").next();
            scanner.close();

            JSONObject json = new JSONObject(response);
            String status = json.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(0).getString("status");
            if (status.equals("OK")) {
                int durationSeconds = json.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(0).getJSONObject("duration").getInt("value");
                int hours = durationSeconds / 3600;
                int minutes = (durationSeconds % 3600) / 60;
                if (hours > 0) {
                    return String.format("The estimated travel time is %d hours and %d minutes.", hours, minutes);
                } else {
                    return String.format("The estimated travel time is %d minutes.", minutes);
                }
            } else if (status.equals("NOT_FOUND")) {
                return "One or both of the addresses entered could not be found. Please try again.";
            } else {
                return "There was an error with the Distance Matrix API. Please try again later.";
            }
        } else {
            return "There was an error retrieving the travel time. Please try again later.";
        }
    }
}


