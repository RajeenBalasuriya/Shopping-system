/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.shopping;

/**
 *
 * @author rajeen
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {

    private static WestminsterShoppingManager instance;
    //product list in the system  
    private final List<Product> productListSystem;  

    // Private constructor to avoid instanziation of object outside the class
    private WestminsterShoppingManager() {
        this.productListSystem=new ArrayList<>();
    }

    //enable global level access to ShoppingManager instance

    public static WestminsterShoppingManager getInstance() {
        if (instance == null) {
            instance = new WestminsterShoppingManager();
        }
        return instance;
    }

    // Other methods and fields as before...

    // Method to display the console menu using a do-while loop
    public void displayConsoleMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("===== Console Menu =====");
            System.out.println("1. Add Product");
            System.out.println("2. Delete Product");
            System.out.println("3. Display Product List");
            System.out.println("4. Save Product List to File");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    // Call addProduct method
                    break;
                case 2:
                    // Call deleteProduct method
                    break;
                case 3:
                    // Call displayProductList method
                    break;
                case 4:
                    // Call saveProductListToFile method
                    break;
                case 5:
                    System.out.println("Exiting the console menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }
    
    //abstract methods from the ShoppingManager interface are implemented below

    @Override
    public void addProduct() {
        
        
        
        
        if(productListSystem.size()<50){
            productListSystem.add(product);
        }
        else{
            System.out.println("System can onlt have 50 type of products !");
        }
    }

    @Override
    public void removeProduct() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void printProducts() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void saveProducts() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    

    
}

