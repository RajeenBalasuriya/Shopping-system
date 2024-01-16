
package com.mycompany.shopping;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rajeen
 */
public class ShoppingCart {
    private static ShoppingCart instance;
    private final Map<String, Product> productMap;
    private double totalDiscount;

    // Private constructor to avoid instantiation outside the class
    private ShoppingCart() {
        this.productMap = new HashMap<>();
        this.totalDiscount = 0;
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
            totalCost += product.getMarketPrice() * product.getCartCount();
        }
        return totalCost;

    }

    public double calculateFirstPurchaseDiscount(double totalCost) {
        totalDiscount = totalDiscount + totalCost * 0.1;
        return (totalCost * 10) / 100;

    }

    public double calculateCategoryDiscount(double totalCost) {
        totalDiscount = totalDiscount + totalCost * 0.2;
        return totalCost * 0.2;
    }

    public Map<String, Product> getProductMap() {
        return productMap;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;

    }


}
