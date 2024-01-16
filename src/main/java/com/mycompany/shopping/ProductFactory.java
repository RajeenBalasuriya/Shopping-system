package com.mycompany.shopping;

import java.util.Scanner;

public class ProductFactory {

    /**
     * This method has the logic of creating the product depending on the user input .
     */
    public Product createProduct() {

        int productType;
        String productId;
        String productName;
        int noOfAvailableItems;
        double marketPrice;
        double buyingPrice;

        boolean validateStatus;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the type of the product you need to add to the system (electronic-1/clothing-2)");
        productType = Validator.getValidIntInput(scanner);

        do {
            System.out.println("please enter the product id (must contain numbers and letters)");
            productId = scanner.next();
            scanner.nextLine(); // consume the line

            // validating the productId
            validateStatus = Validator.isAlphaNumeric(productId);

            if (!validateStatus) {
                System.out.println("Please enter a valid productId");
            }

        } while (!validateStatus);

        System.out.println("Please enter the product Name");
        productName = scanner.nextLine();

        System.out.println("Please enter the number of the product");
        noOfAvailableItems = Validator.getValidIntInput(scanner);

        System.out.println("Please enter the market price");
        marketPrice = Validator.getValidDoubleInput(scanner);

        System.out.println("please enter the buying price");
        buyingPrice = Validator.getValidDoubleInput(scanner);

        scanner.nextLine();

        if (productType == 1) {
            String brand;
            String warrantyPeriod;

            System.out.println("Please enter the brand");
            brand = scanner.nextLine();
            System.out.println("Please enter the warranty period");
            warrantyPeriod = scanner.nextLine();

            return new Electronics(brand, warrantyPeriod, productName, noOfAvailableItems, marketPrice, buyingPrice, productId);
        } else {
            int size;
            String colour;

            System.out.println("please enter the size");
            size = Validator.getValidIntInput(scanner);
            scanner.nextLine(); // consume the line

            System.out.println("please enter colour");
            colour = scanner.nextLine();

            return new Clothing(size, colour, productName, noOfAvailableItems, marketPrice, buyingPrice, productId);
        }
    }


}
