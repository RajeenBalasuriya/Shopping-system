/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.shopping;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rajeen
 */
public class ShoppingCart {
    
    private static ShoppingCart instance;
    private List<Product> productList;
    
    //initialize of private constructor to avoid instantiation outside the class
    
    private ShoppingCart(){
        this.productList=new ArrayList<>();
    }
    
    //enable global level access to the instance
    public static synchronized ShoppingCart getInstance(){
        if(instance==null){
            instance=new ShoppingCart();//instantiate shoppingcart object if and only if there is no any other instances
        }
        return instance;
    }
    
    
    //following methods are for the purpose of editing cart
    
    public void addProduct(Product product) {
        productList.add(product);
    }

    public void removeProduct(Product product) {
        productList.remove(product);
    }
    

    public double calculateTotalCost() {
        double totalCost = 0.0;
        for (Product product : productList) {
            totalCost += product.getMarketPrice();
        }
        return totalCost;
    }

    public List<Product> getProductList() {
        return productList;
    }

    
}
