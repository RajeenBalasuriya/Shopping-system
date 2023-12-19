/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.shopping;

import java.io.Serializable;

/**
 *
 * @author rajeen
 */
public abstract class Product implements Serializable {
    
    
    
    private String productId;
    private String productName;
    private int noOfAvailableItems;
    private double marketPrice;
    private double buyingPrice;
    
    
    

    
    //constructor for product
    public Product(String productName,int noOfAvailableItems,double marketPrice,double buyingPrice,String productId){
        
        this.productName=productName;
        this.noOfAvailableItems=noOfAvailableItems;
        this.marketPrice=marketPrice;
        this.buyingPrice=buyingPrice;
        this.productId=productId;
        
    }
    
    //getter and setter for productID

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        
        
            this.productId=productId;
        
    }
    
    
    //getters and setters for other variables

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getNoOfAvailableItems() {
        return noOfAvailableItems;
    }

    public void setNoOfAvailableItems(int noOfAvailableItems) {
        this.noOfAvailableItems = noOfAvailableItems;
    }

    public double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }
    

    
    @Override
    public String toString(){
        return ("Product ID :"+productId+" Product Name:"+productName+" Number of available items:"+noOfAvailableItems+" Market Price:"+marketPrice+" Buying Price:"+buyingPrice);
    }
    
    
}
