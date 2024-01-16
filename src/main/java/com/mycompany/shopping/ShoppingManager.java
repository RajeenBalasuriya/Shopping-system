
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
