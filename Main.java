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
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter pickup address : ");
        String pickup = scanner.nextLine();
        System.out.print("Enter drop off address : ");
        String dropoff = scanner.nextLine();

        try {
            String travelTime = DistanceMatrixApiClient.getTravelTime(pickup, dropoff);
            System.out.println(travelTime);
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        scanner.close();
    }
}




