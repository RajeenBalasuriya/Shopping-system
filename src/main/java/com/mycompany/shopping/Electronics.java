/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.shopping;

/**
 *
 * @author rajeen
 */
public class Electronics extends Product{
    
    private String brand;
    private String warrantyPeriod;
    
    

    public Electronics(String brand, String warrantyPeriod,String productName,int noOfAvailableItems,double marketPrice,double buyingPrice) {
        
        super(productName,noOfAvailableItems,marketPrice,buyingPrice);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
        
    }
    
    //getters and setters for electronics products

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(String warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }
    
    @Override
    public String toString(){
        return (super.toString() + "Brand:"+brand+" Warranty :"+warrantyPeriod);
    }
    

    
    
    
    
    
}
