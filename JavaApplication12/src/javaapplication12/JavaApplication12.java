/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication12;

import java.util.ArrayList;
import java.util.Scanner;



public class JavaApplication12 {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<String> cities = new ArrayList<>();
    

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=== LOGISTICS MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Cities");
            System.out.println("2. View Cities");
            System.out.println("3. Add Delivery");
            System.out.println("4. View All Deliveries");
            System.out.println("5. Exit");
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

