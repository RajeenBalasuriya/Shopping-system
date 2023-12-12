/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.shopping;

/**
 *
 * @author rajeen
 */
public class Shopping {

    public static void main(String[] args) {
        
        Clothing clothe1 = new Clothing(12,"black", "winFashion",20, 200.00,100.00);
        Clothing clothe2 = new Clothing(12,"blue", "winFashion",20, 200.00,100.00);
        clothe1.setProductId("win1");
        clothe2.setProductId("win2");
        Electronics elec1=new Electronics("panasonic","2Years","kettle",21,4000.0,3000.00);
        elec1.setProductId("pan1");
        
        ShoppingCart cart =ShoppingCart.getInstance();
        cart.addProduct(elec1);
        cart.addProduct(clothe1);
        cart.addProduct(clothe2);
        
        double x=cart.calculateTotalCost();
        System.out.println(x);
        
        
        cart.removeProduct(elec1);
        x=cart.calculateTotalCost();
        System.out.println(x);
        
        cart.removeProduct(clothe1);
        x=cart.calculateTotalCost();
        System.out.println(x);
        
        
    }
}
