/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.shopping;

/**
 *
 * @author rajeen
 */
import graphicalUserInterface.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {

    private static WestminsterShoppingManager instance;
    //product list in the system  
    private final List<Product> productListSystem;

    // Private constructor to avoid instanziation of object outside the class
    private WestminsterShoppingManager() {
        this.productListSystem = new ArrayList<>();
    }

    //enable global level access to ShoppingManager instance
    public static WestminsterShoppingManager getInstance() {
        if (instance == null) {
            instance = new WestminsterShoppingManager();
        }
        return instance;
    }

    
    // Method to display the console menu using a do-while loop
    public void displayConsoleMenu() {

        //load existing data to the system
        loadProducts();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("===== Console Menu =====");
            System.out.println("1. Add Product");
            System.out.println("2. Delete Product");
            System.out.println("3. Display Product List");
            System.out.println("4. Save Product List to File");
            System.out.println("5.Graphical user interface");
            System.out.println("6. Exit");
            System.out.println("Enter your choice: ");
            

            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    removeProduct();
                    break;
                case 3:
                    printProducts();
                    break;
                case 4:
                    saveProducts();
                    break;
                case 5:
                    HomeFrame homeFrame = new HomeFrame(this.getProductList());
                    
                    break;
                case 6:
                    System.out.println("Exiting the console menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6 );

    }

    //abstract methods from the ShoppingManager interface are implemented below
    @Override
    public void addProduct() {

        if (productListSystem.size() < 50) {
            productFactory produce = new productFactory();
            boolean add = productListSystem.add(produce.createProduct());

            if (!add) {
                System.out.println("Product did not added to the system");
            } else {
                System.out.println("Product sucessfully added to the system");
            }

        } else {
            System.out.println("System can onlt have 50 type of products !");
        }
    }

    @Override
    public void removeProduct() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the Id of the product that you want to remove:");

        String productId = scanner.nextLine();

        for (Product product : productListSystem) {

            if (productId.equals(product.getProductId())) {
                System.out.println(product);

                if (productListSystem.remove(product)) {

                    System.out.println("Product removed successfully");
                    break;
                } else {
                    System.out.println("Product removed failed");
                    break;
                }
            }
        }
    }

    @Override
    public void printProducts() {

        // Sorting based on the productId attribute using a custom comparator
        Collections.sort(productListSystem, Comparator.comparing(Product::getProductId));

        // Printing the sorted list
        for (Product product : productListSystem) {
            System.out.println(product);
        }

    }

    @Override
    public void saveProducts() {
        try {
            File file = new File("productObject.ser");

            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            // write object to file
            for (Product product : productListSystem) {
                oos.writeObject(product);

            }

            System.out.println("Saved");
        } catch (IOException e) {
            System.out.println("Products not saved");
        }
    }

    //implementation of load products method
    public void loadProducts() {
        try {
            File file = new File("productObject.ser");

            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);

                // Clear the existing list before loading new objects
                productListSystem.clear();

                // read objects from file and add them to the list
                while (true) {
                    try {
                        Product product = (Product) ois.readObject();
                        productListSystem.add(product);
                    } catch (ClassNotFoundException | IOException e) {

                        break;
                    }
                }

                System.out.println("Products loaded");
            } else {
                System.out.println("File does not exist. No products loaded.");
            }
        } catch (IOException e) {
            System.out.println("Error loading products");
        }

    }
    
    public List<Product> getProductList(){
        return productListSystem;
    }


}
