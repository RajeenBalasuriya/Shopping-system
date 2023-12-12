/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.shopping;

import java.util.Scanner;

/**
 *
 * @author rajeen
 */
public class productFactory {
    
    /**
     *
     * @return
     */
    public Product createProduct(){
       
       int productType;//used to track which type of porduct is needed to be created
       
       String productId;
       String productName;
       int noOfAvailableItems;
       double marketPrice;
       double buyingPrice;
       
       boolean validateStatus;
       
       
       Scanner scanner=new Scanner(System.in);
       
       System.out.println("Please enter the type of the product you need to add to the system (electronic-1/clothing-2)");
       productType=scanner.nextInt();
       
       do{
           System.out.println("please enter the product id (must contain numbers and letters)");
           productId=scanner.nextLine();
           
           //validating the productId
           if(Validator.isAlphaNumeric(productId)){
               validateStatus=true;
           }
           else{
               System.out.println("Please enter a valid productId");
               validateStatus=false;
           }
       
       }while(!validateStatus);
       
       System.out.println("Please enter the product Name");
       productName=scanner.nextLine();
       
       System.out.println("Please enter the number of the product");
       noOfAvailableItems=scanner.nextInt();
       
       System.out.println("Please enter the market price");
       marketPrice=scanner.nextDouble();
       
       System.out.println("please enter the buying price");
       buyingPrice=scanner.nextDouble();
       
       //below code sinppet will determine type of the product creating
       if(productType==1){
           String brand;
           String warrantyPeriod;
           
           System.out.println("Please enter the brand");
           brand=scanner.nextLine();
           System.out.println("Please enter the warranty period");
           warrantyPeriod=scanner.nextLine();
           
           //intialize of the electronic product
           return new Electronics( brand,warrantyPeriod,productName,noOfAvailableItems,marketPrice,buyingPrice);
       }
       else{
          int size;
          String colour;
          
           System.out.println("please enter the size");
           size=scanner.nextInt();
           //consuming the line
           scanner.nextLine();
           
           System.out.println("please enter colour");
           colour=scanner.nextLine();
           
           return new Clothing (size,colour,productName,noOfAvailableItems,marketPrice,buyingPrice);
       }
       
       
       
            
       
       
   }
    
}
