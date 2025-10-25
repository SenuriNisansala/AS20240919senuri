/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication12;

/**
 *
 * @author User
 */
public class cityManagement {

    /**
     * @param args the command line arguments
     */
    

 public static void main(String[] args) {
     
     
     
     
  
 }
 static void addCity() {
        if(cityCount >= MAX_CITIES) { System.out.println("City limit reached."); return; }
        System.out.print("Enter city name: ");
        String name = sc.nextLine();
        for(int i=0;i<cityCount;i++)
            if(cities[i].equalsIgnoreCase(name)) { System.out.println("City already exists."); return; }
        cities[cityCount++] = name;
        System.out.println("City added successfully.");
 }
}