/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/*package javaapplication12;

import java.util.ArrayList;
import java.util.Scanner;*/
package javaapplication12;


import java.util.*;

class Vehicle {
    String name;
    int capacity;
    int speed;
    double ratePerKm;
    double fuelEfficiency;

    Vehicle (String name, int capacity,int speed,  double ratePerKm, double fuelEfficiency) {
        this.name = name;
        this.capacity = capacity;
        this.speed = speed ;
        this.ratePerKm = ratePerKm;
        this.fuelEfficiency = fuelEfficiency;
    }
}
class Delivery {
    String fromCity;
    String toCity;
    int weight;
    Vehicle vehicle;
    double distance;
    double fuelUsed;
    double baseCost;
    double fuelCost;
    double operationalCost;
    double profit;
    double customerCharge;
    double estimatedTime;
    String route;

    Delivery(String fromCity, String toCity, int weight, Vehicle vehicle, double distance , String route) {
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.weight = weight;
        this.vehicle = vehicle;
        this.distance = distance;
        this.route = route;
    }
}

public class JavaApplication12 {
    
    static Scanner sc = new Scanner(System.in);
    
    static String[] cities = new String[30];
    static int cityCount = 0;
    static int[][] distanceMatrix = new int[30][30];
    
    static Delivery[] deliveries = new Delivery[50];
    static int deliveryCount = 0;
    
    static Vehicle[] vehicles = {
        new Vehicle ("Van", 1000, 30, 12),
        new Vehicle("Truck", 5000, 40, 6),
        new Vehicle("Lorry", 10000, 80, 4)
    };

    static final double FUEL_PRICE = 310;
     static final double PROFIT_MARGIN = 0.25;

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=== LOGISTICS MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Cities");
            System.out.println("2. View Cities");
            System.out.println("3. Add Delivery");
            System.out.println("4. View All Deliveries");
            System.out.println("5. Exit");
            System.out.println("5. Rename City");
            System.out.println("6. Remove City"); 
            System.out.println("7. Distance Management"); 
            System.out.println("8. View Vehicle Information"); 
            System.out.println("9. View Reports"); 
            System.out.println("10. Save and Exit");
            System.out.print("Enter your choice: ");
            
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addCity(); 
                break;
                case 2: 
                    viewCities(); 
                break;
                case 3:
                    addDelivery();
                break;
                case 4:
                    viewDeliveries();
                break;
                case 5:
                    System.out.println("Exiting....");
                break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 5);
    }
    
    //add a city
    static void addCity() {
        System.out.print("Enter city name: ");
        String name = sc.nextLine();
        if (cities.contains(name)) {
            System.out.println("City already exists!");
        } else {
            cities.add(name);
            System.out.println("City added successfully!");
        }
    }
    static void viewCities() {
        if (cities.isEmpty()) {
            System.out.println("No cities added yet.");
            return;
        }
        System.out.println("\n--- List of Cities ---");
        for (int i = 0; i < cities.size(); i++) {
            System.out.println((i + 1) + ". " + cities.get(i));
        }
    }
     static void addDelivery() {
        if (cities.size() < 2) {
            System.out.println("Please add at least 2 cities first.");
            return;
        }

        System.out.println("\nSelect source city:");
        viewCities();
        int src = sc.nextInt() - 1;

        System.out.println("Select destination city:");
        viewCities();
        int dest = sc.nextInt() - 1;
        sc.nextLine();

        if (src == dest) {
            System.out.println("Source and destination cannot be same!");
            return;
        }

        System.out.print("Enter package weight (kg): ");
        int weight = sc.nextInt();

        System.out.println("\nSelect vehicle type:");
        for (int i = 0; i < vehicles.length; i++) {
            System.out.printf("%d. %s (Capacity: %dkg, Rate: %.2f LKR/km, Efficiency: %.2f km/l)\n",
                    (i + 1), vehicles[i].name, vehicles[i].capacity,
                    vehicles[i].ratePerKm, vehicles[i].fuelEfficiency);
        }
        int vChoice = sc.nextInt() - 1;
        Vehicle v = vehicles[vChoice];

        if (weight > v.capacity) {
            System.out.println("Weight exceeds vehicle capacity!");
            return;
        }

        System.out.print("Enter distance between cities (km): ");
        double distance = sc.nextDouble();

        Delivery d = new Delivery(cities.get(src), cities.get(dest), weight, v, distance);
        calculateCost(d);
        deliveries.add(d);

        System.out.println("\nDelivery added successfully!");
        displayDelivery(d);
    }
     
    static void calculateCost(Delivery d) {
        d.fuelUsed = d.distance/ d.vehicle.fuelEfficiency;
        double fuelCost = d.fuelUsed * FUEL_PRICE;
        double baseCost = d.distance * d.vehicle.ratePerKm;
        d.cost = baseCost + fuelCost;
    }
    
    static void displayDelivey(Delivery d){
        System.out.println("--------------------------------------------");
        System.out.println("From: " + d.fromCity);
        System.out.println("To: " + d.toCity);
        System.out.println("Vehicle: " + d.vehicle.name);
        System.out.println("Distance: " + d.distance + " km");
        System.out.println("Weight: " + d.weight + " kg");
        System.out.printf("Fuel Used: %.2f L\n", d.fuelUsed);
        System.out.printf("Total Cost: %.2f LKR\n", d.cost);
        System.out.println("--------------------------------------");
    }
    
     static void viewDeliveries() {
        if (deliveries.isEmpty()) {
            System.out.println("No deliveries yet.");
            return;
        }
        System.out.println("\n--- All Deliveries ---");
        for (Delivery d : deliveries) {
            displayDelivery(d);
        }
    }