/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.shopping;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rajeen
 */
public class ShoppingCart {
     private static ShoppingCart instance;
    private Map<String, Product> productMap;

    // Private constructor to avoid instantiation outside the class
    private ShoppingCart() {
        this.productMap = new HashMap<>();
    }

    // Enable global level access to the instance
    public static synchronized ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart(); // Instantiate ShoppingCart object if and only if there is no other instance
        }
        return instance;
    }
    
    // Methods for editing the cart

    public void addProduct(Product product) {
        productMap.put(product.getProductId(), product);
    }

    public void removeProduct(String productId) {
        productMap.remove(productId);
    }

    public double calculateTotalCost() {
        double totalCost = 0.0;
        for (Product product : productMap.values()) {
            totalCost += product.getMarketPrice();
        }
        return totalCost;
    }

    public Map<String, Product> getProductMap() {
        return productMap;
    }

    // You can add more methods as needed



    public static void setInstance(ShoppingCart newInstance) {
        instance = newInstance;
    }


    
}
