
package com.mycompany.shopping;

import java.io.Serializable;

/**
 * @author Rajeen
 * IIT ID -20220175
 * UOWID-w1953138
 */
public abstract class Product implements Serializable {


    private String productId;
    private String productName;
    private int noOfAvailableItems;
    private double marketPrice;
    private double buyingPrice;
    private int cartCount;


    //constructor for product
    public Product(String productName, int noOfAvailableItems, double marketPrice, double buyingPrice, String productId) {

        this.productName = productName;
        this.noOfAvailableItems = noOfAvailableItems;
        this.marketPrice = marketPrice;
        this.buyingPrice = buyingPrice;
        this.productId = productId;
        this.cartCount = 1;

    }

    //getter and setter for productID

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {


        this.productId = productId;

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


    public abstract String getProductType();

    public int getCartCount() {
        return cartCount;
    }

    public void incrementCartCount() {
        this.cartCount++;

    }


    public abstract String additionalInfo1();//to get separate additional 1 info of each child class

    public abstract String additionalInfo2();//to get separate additional 2 info of each child class

    public abstract String getProductInfo();//will return string of both additionalInfo1 and additionalInfo2


    @Override
    public String toString() {
        return ("Product ID :" + productId + " Product Name:" + productName + " Number of available items:" + noOfAvailableItems + " Market Price:" + marketPrice + " Buying Price:" + buyingPrice);
    }


}
