package org.example;

import java.io.IOException;
import java.util.Scanner;
/**

 This class is used to calculate the travel time between two locations.
 It takes in user input for the pickup and drop off addresses, calls the
 DistanceMatrixApiClient to get the travel time, and prints the result.
 */
public class Main {
    public static void main(String[] args) {

        String pickup =args[0] ;
        String dropoff = args[1];

        try {
            String travelTime = DistanceMatrixApiClient.getTravelTime(pickup, dropoff);
            System.out.println(travelTime);
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }


    }
}




