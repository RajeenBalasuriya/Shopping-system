/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.shopping;

import java.util.List;

/**
 *
 * @author rajeen
 */
public interface ShoppingManager {
    
    void addProduct(List<Product> productListSystem);
    void removeProduct();
    void printProducts();
    void saveProducts();
    
}
