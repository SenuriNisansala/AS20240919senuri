/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/*package javaapplication12;

import java.util.ArrayList;
import java.util.Scanner;*/
package javaapplication12;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
                    renameCity(); 
                break;
                case 6:
                    removeCity(); 
                break;
                case 7:
                    distanceManagement(); 
                break;
                case 8:
                    viewVehicleInfo(); 
                    break;
                case 9:
                    viewReports();
                    break;
                case 10:
                    saveToFiles();
                    System.out.println("Data saved successfully!");
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 5);
    }
    
    static void addCity() {
        if (cities.size() >= 30) { 
            System.out.println("Maximum city limit (30) reached!");
            return;
        }

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
    
    static void renameCity() {
        if (cities.isEmpty()) {
            System.out.println("No cities available to rename!");
            return;
        }
        
        viewCities();
        System.out.print("Enter city number to rename: ");
        int index = sc.nextInt() - 1;
        sc.nextLine();
        
        if (index < 0 || index >= cities.size()) {
            System.out.println("Invalid city number!");
            return;
        }
        
        System.out.print("Enter new name: ");
        String newName = sc.nextLine();
        
        String oldName = cities.get(index);
        cities.set(index, newName);
        System.out.println("✓ City '" + oldName + "' renamed to '" + newName + "'");
    }
    
    static void removeCity() {
        if (cities.isEmpty()) {
            System.out.println("No cities available to remove!");
            return;
        }
        
        viewCities();
        System.out.print("Enter city number to remove: ");
        int index = sc.nextInt() - 1;
        sc.nextLine();
        
        if (index < 0 || index >= cities.size()) {
            System.out.println("Invalid city number!");
            return;
        }
        
        String removedCity = cities.get(index);
        cities.remove(index);
        
        for (int i = index; i < cities.size(); i++) {
            for (int j = 0; j < cities.size() + 1; j++) {
                if (i + 1 < 30) {
                    distanceMatrix[i][j] = distanceMatrix[i + 1][j];
                }
            }
        }
        for (int i = 0; i < cities.size(); i++) {
            for (int j = index; j < cities.size(); j++) {
                if (j + 1 < 30) {
                    distanceMatrix[i][j] = distanceMatrix[i][j + 1];
                }
            }
        }
        
        System.out.println("✓ City '" + removedCity + "' removed successfully!");
    }
    
    static void distanceManagement() {
        System.out.println("\n--- DISTANCE MANAGEMENT ---");
        System.out.println("1. Add/Edit Distance Between Cities");
        System.out.println("2. View Distance Table");
        System.out.println("3. Back to Main Menu");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                editDistance();
                break;
            case 2:
                viewDistanceTable();
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice!");
        }
    }

    static void editDistance() {
        if (cities.size() < 2) {
            System.out.println("Need at least 2 cities to set distances!");
            return;
        }
        
        viewCities();
        System.out.print("Enter first city number: ");
        int city1 = sc.nextInt() - 1;
        
        System.out.print("Enter second city number: ");
        int city2 = sc.nextInt() - 1;
        sc.nextLine();
        
        if (city1 < 0 || city1 >= cities.size() || city2 < 0 || city2 >= cities.size()) {
            System.out.println("Invalid city number!");
            return;
        }
        
        if (city1 == city2) {
            System.out.println("Cannot set distance from a city to itself!");
            return;
        }
        
        System.out.print("Enter distance (km): ");
        int dist = sc.nextInt();
        sc.nextLine();
        
        if (dist < 0) {
            System.out.println("Distance cannot be negative!");
            return;
        }
        
        distanceMatrix[city1][city2] = dist;
        distanceMatrix[city2][city1] = dist;
        
        System.out.println("✓ Distance set: " + cities.get(city1) + " ↔ " + cities.get(city2) + " = " + dist + " km");
    }
    
      static void viewDistanceTable() {
        if (cities.isEmpty()) {
            System.out.println("No cities available!");
            return;
        }
        
        System.out.println("\n--- DISTANCE TABLE (km) ---");
        
        System.out.printf("%-15s", "");
        for (int i = 0; i < cities.size(); i++) {
            String cityName = cities.get(i).length() > 8 ? cities.get(i).substring(0, 8) : cities.get(i);
            System.out.printf("%-10s", cityName);
        }
        System.out.println();
        
        for (int i = 0; i < cities.size(); i++) {
            String cityName = cities.get(i).length() > 13 ? cities.get(i).substring(0, 13) : cities.get(i);
            System.out.printf("%-15s", cityName);
            for (int j = 0; j < cities.size(); j++) {
                if (i == j) {
                    System.out.printf("%-10s", "-");
                } else {
                    System.out.printf("%-10d", distanceMatrix[i][j]);
                }
            }
            System.out.println();
        }
    }
      
    static void viewVehicleInfo() {
        System.out.println("\n--- VEHICLE INFORMATION ---");
        System.out.println("Type    Capacity  Rate/km  Speed   Fuel Efficiency");
        System.out.println("        (kg)      (LKR)    (km/h)  (km/l)");
        System.out.println("──────────────────────────────────────────────────");
        
        for (int i = 0; i < vehicles.length; i++) {
            System.out.printf("%-8s%-10d%-9.0f%-8d%-10.0f\n", 
                vehicles[i].name, vehicles[i].capacity, vehicles[i].ratePerKm, 
                vehicles[i].speed, vehicles[i].fuelEfficiency);
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
        sc.nextLine();
        
         if (dist < 0) {
            System.out.println("Distance cannot be negative!");
            return;
        }
        
        distanceMatrix[city1][city2] = dist;
        distanceMatrix[city2][city1] = dist;
        
        System.out.println("✓ Distance set: " + cities.get(city1) + " ↔ " + cities.get(city2) + " = " + dist + " km");
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
     
     static void viewReports() {
        if (deliveries.isEmpty()) {
            System.out.println("\nNo deliveries recorded yet!");
            return;
        }
        
        System.out.println("\n---------------------------------------");
        System.out.println("||           PERFORMANCE REPORTS               ||");
        System.out.println("------------------------------------------");
        
        double totalDistance = 0;
        double totalTime = 0;
        double totalRevenue = 0;
        double totalProfit = 0;
        double longestRoute = 0;
        double shortestRoute = Double.MAX_VALUE;
        
        for (Delivery d : deliveries) {
            totalDistance += d.distance;
            totalTime += d.estimatedTime;
            totalRevenue += d.customerCharge;
            totalProfit += d.profit;
            
            if (d.distance > longestRoute) {
                longestRoute = d.distance;
            }
            if (d.distance < shortestRoute) {
                shortestRoute = d.distance;
            }
        }
        
        double avgTime = totalTime / deliveries.size();
        
        System.out.println("\na) Total Deliveries Completed: " + deliveries.size());
        System.out.printf("b) Total Distance Covered: %.2f km\n", totalDistance);
        System.out.printf("c) Average Delivery Time: %.2f hours\n", avgTime);
        System.out.printf("d) Total Revenue: %.2f LKR\n", totalRevenue);
        System.out.printf("   Total Profit: %.2f LKR\n", totalProfit);
        System.out.printf("e) Longest Route: %.2f km\n", longestRoute);
        System.out.printf("   Shortest Route: %.2f km\n", shortestRoute);
        
        System.out.println("\n--- DELIVERY HISTORY ---");
        int count = 1;
        for (Delivery d : deliveries) {
            System.out.printf("%d. %s → %s | %.0f km | %s | %d kg | %.2f LKR\n",
                    count++, d.fromCity, d.toCity, d.distance, 
                    d.vehicle.name, d.weight, d.customerCharge);
        }
    }

    static void saveToFiles() {
        saveRoutesToFile();
        saveDeliveriesToFile();
    }

    static void saveRoutesToFile() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("routes.txt"));
            
            writer.println(cities.size());
            
            for (String city : cities) {
                writer.println(city);
            }
            
            for (int i = 0; i < cities.size(); i++) {
                for (int j = 0; j < cities.size(); j++) {
                    writer.print(distanceMatrix[i][j]);
                    if (j < cities.size() - 1) {
                        writer.print(",");
                    }
                }
                writer.println();
            }
            
            writer.close();
            System.out.println("✓ Routes saved to routes.txt");
            
        } catch (IOException e) {
            System.out.println("Error saving routes.txt: " + e.getMessage());
        }
    }

    static void saveDeliveriesToFile() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("deliveries.txt"));
            
            writer.println(deliveries.size());
            
            for (Delivery d : deliveries) {
                writer.println(d.fromCity);
                writer.println(d.toCity);
                writer.println(d.weight);
                writer.println(d.vehicle.name);
                writer.println(d.distance);
                writer.println(d.customerCharge);
                writer.println(d.estimatedTime);
                writer.println(d.route);
            }
            
            writer.close();
            System.out.println("✓ Deliveries saved to deliveries.txt");
            
        } catch (IOException e) {
            System.out.println("Error saving deliveries.txt: " + e.getMessage());
        }
    }

    static void loadFromFiles() {
        loadRoutesFromFile();
        loadDeliveriesFromFile();
    }

    static void loadRoutesFromFile() {
        try {
            File file = new File("routes.txt");
            if (!file.exists()) {
                return;
            }
            
            BufferedReader reader = new BufferedReader(new FileReader(file));
            
            int cityCount = Integer.parseInt(reader.readLine().trim());
            
            cities.clear();
            for (int i = 0; i < cityCount; i++) {
                cities.add(reader.readLine().trim());
            }
            
            for (int i = 0; i < cityCount; i++) {
                String line = reader.readLine();
                String[] distances = line.split(",");
                for (int j = 0; j < cityCount && j < distances.length; j++) {
                    distanceMatrix[i][j] = Integer.parseInt(distances[j].trim());
                }
            }
            
            reader.close();
            System.out.println("✓ Routes data loaded from routes.txt");
            
        } catch (Exception e) {
            System.out.println("Could not load routes.txt (may not exist yet)");
        }
    }

    static void loadDeliveriesFromFile() {
        try {
            File file = new File("deliveries.txt");
            if (!file.exists()) {
                return;
            }
            
            BufferedReader reader = new BufferedReader(new FileReader(file));
            
            int deliveryCount = Integer.parseInt(reader.readLine().trim());
            
            deliveries.clear();
            for (int i = 0; i < deliveryCount; i++) {
                String fromCity = reader.readLine();
                String toCity = reader.readLine();
                int weight = Integer.parseInt(reader.readLine());
                String vehicleName = reader.readLine();
                double distance = Double.parseDouble(reader.readLine());
                double customerCharge = Double.parseDouble(reader.readLine());
                double estimatedTime = Double.parseDouble(reader.readLine());
                String route = reader.readLine();
                
                Vehicle v = null;
                for (Vehicle vehicle : vehicles) {
                    if (vehicle.name.equals(vehicleName)) {
                        v = vehicle;
                        break;
                    }
                }
                
                Delivery d = new Delivery(fromCity, toCity, weight, v, distance);
                d.customerCharge = customerCharge;
                d.estimatedTime = estimatedTime;
                d.route = route;
                deliveries.add(d);
            }
            
            reader.close();
            System.out.println("✓ Delivery data loaded from deliveries.txt");
            
        } catch (Exception e) {
            System.out.println("Could not load deliveries.txt (may not exist yet)");
        }
    }
}

class RouteResult {
    double distance;
    String routePath;
    
    RouteResult() {
        this.distance = 0;
        this.routePath = "";
    }
}