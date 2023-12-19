/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.shopping;

/**
 *
 * @author rajeen
 */
public class Clothing extends Product {
    
    private int size;
    private String colour;
    
    
    //constructor for clothing class
    public Clothing(int size, String colour, String productName, int noOfAvailableItems, double marketPrice, double buyingPrice,String productId) {
        super(productName, noOfAvailableItems, marketPrice, buyingPrice,productId);
        this.size = size;
        this.colour = colour;
    }
    
    
    //getters and setters for clothing class
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
    
    //overing the toString method
    @Override
    public String toString(){
        return (super.toString() + "Colour:"+colour+" Size :"+size+"Product type:Clothing");
    }
    
    
    
    
    
    
    
    
}
