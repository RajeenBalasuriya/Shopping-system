
package com.mycompany.shopping;

import graphicalUserInterface.HomeFrame;

import java.io.*;
import java.util.*;

public class WestminsterShoppingManager implements ShoppingManager {

    private static WestminsterShoppingManager instance;
    //product list in the system
    private final List<Product> productListSystem;

    //list to store user objects
    private final List<User> userObjects = new ArrayList<>();

    //shopping cart object
    ShoppingCart userCart = ShoppingCart.getInstance();

    // Private constructor to avoid instantiation of object outside the class
    private WestminsterShoppingManager() {
        this.productListSystem = new ArrayList<>();
    }

    //enable global level access to ShoppingManager instance
    public static WestminsterShoppingManager getInstance() {
        if (instance == null) {
            instance = new WestminsterShoppingManager();
        }
        return instance;
    }

    // Method to display the console menu using a do-while loop
    public void displayConsoleMenu() {

        //load existing data to the system
        loadProducts();
        //load existing users to the system
        loadUsers();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("===== Console Menu =====");
            System.out.println("1. Add Product");
            System.out.println("2. Delete Product");
            System.out.println("3. Display Product List");
            System.out.println("4. Save Product List to File");
            System.out.println("5.Graphical user interface");
            System.out.println("6. Exit");
            System.out.println("Enter your choice: ");

            choice =Validator.getValidIntInput(scanner);


            switch (choice) {
                case 1 -> addProduct(productListSystem);
                case 2 -> removeProduct();
                case 3 -> printProducts();
                case 4 -> {
                    saveProducts();
                    saveUsers();
                }
                case 5 -> manageUser(scanner);//will handle the gui inside the method
                case 6 -> System.out.println("Exiting the console menu.");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);

    }

    //abstract methods from the ShoppingManager interface are implemented below
    @Override
    public void addProduct(List<Product> productListSystem) {

        if (productListSystem.size() < 50) {
            boolean add = true;  // Set to true by default

            ProductFactory produce = new ProductFactory();
            Product productToBeAdded = produce.createProduct();

            for (Product product : productListSystem) {
                if (product.getProductId().equals(productToBeAdded.getProductId())) {
                    System.out.println("Product with this ID is available");
                    System.out.println("Do you want to add more items for the available product? (Y/n)");

                    Scanner scan = new Scanner(System.in);
                    String decision = scan.next();

                    if (decision.equals("Y") || decision.equals("y")) {
                        int currentNoOfItems = product.getNoOfAvailableItems() + productToBeAdded.getNoOfAvailableItems();
                        product.setNoOfAvailableItems(currentNoOfItems);
                        System.out.println("Items are added to the same product ID");
                        add = false;  // Set to false since the product was not added
                        break;
                    } else {
                        System.out.println("Add the product with a different ID.");
                        add = false;  // Set to false since the product was not added
                        break;
                    }
                }
            }

            if (add) {
                productListSystem.add(productToBeAdded);
                System.out.println("Product successfully added to the system");
            }
        } else {
            System.out.println("System can only have 50 types of products!");
        }
    }

    @Override
    public void removeProduct() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the Id of the product that you want to remove:");

        String productId = scanner.nextLine();

        for (Product product : productListSystem) {

            if (productId.equals(product.getProductId())) {
                System.out.println(product);

                if (productListSystem.remove(product)) {

                    System.out.println("Product removed successfully");
                } else {
                    System.out.println("Product removed failed");
                }
                break;
            }
        }
    }

    @Override
    public void printProducts() {

        // Sorting based on the productId attribute using a custom comparator
        productListSystem.sort(Comparator.comparing(Product::getProductId));

        // Printing the sorted list
        for (Product product : productListSystem) {
            System.out.println(product);
        }

    }

    @Override
    public void saveProducts() {
        try {
            File file = new File("productObject.ser");

            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            // write object to file
            for (Product product : productListSystem) {
                oos.writeObject(product);
            }

            System.out.println("Saved");
        } catch (IOException e) {
            System.out.println("Products not saved");
        }
    }

    //implementation of load products method
    public void loadProducts() {
        try {
            File file = new File("productObject.ser");

            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);

                // Clear the existing list before loading new objects
                productListSystem.clear();

                // read objects from file and add them to the list
                while (true) {
                    try {
                        Product product = (Product) ois.readObject();
                        productListSystem.add(product);
                    } catch (ClassNotFoundException | IOException e) {

                        break;
                    }
                }

                System.out.println("Products loaded");
            } else {
                System.out.println("File does not exist. No products loaded.");
            }
        } catch (IOException e) {
            System.out.println("Error loading products");
        }

    }

    public void manageUser(Scanner scanner) {

        boolean optionValidate;

        do {
            optionValidate = true;
            try {
                System.out.println("Choose Option 1 for Login or Option 2 for Register");

                int option = scanner.nextInt();
                scanner.nextLine();//consume the line

                if (option == 1) {
                    System.out.println("Please enter the username");
                    String userName = scanner.next();
                    scanner.nextLine();
                    System.out.println("Please enter the password(must contain (lower case letter, upper case letter,special character))");
                    String password = scanner.next();
                    scanner.nextLine();


                    // Check whether the given user object is available by using a for-each loop
                    for (User user : userObjects) {

                        if (user.getPassword().equals(password) && user.getUserName().equals(userName)) {
                            ShoppingCart userCart = ShoppingCart.getInstance();
                            HomeFrame homeFrame = new HomeFrame(this.getProductList(), userCart, user);

                            optionValidate = false;
                        }

                    }
                    //display message no such account by tracking using optionValidate
                    if (optionValidate) {
                        System.out.println("NO SUCH ACCOUNT!");
                    }

                } else {

                    boolean passwordStatus;//used to track status of the password

                    do {

                        System.out.println("Please enter the username");
                        String userName = scanner.next();
                        scanner.nextLine();
                        System.out.println("Please enter the password(numbers and characters)");
                        String password = scanner.next();
                        scanner.nextLine();

                        //check whether the details are correct to make the profile
                        if (Validator.isValidPassword(password)) {
                            User user = new User(userName, password);//creation of the user object
                            userObjects.add(user);// add created user to the list
                            passwordStatus = false;//set too false to stop the loop as correct password is inputted
                            saveUsers();
                        } else {
                            System.out.println("Invalid password. Please try again.");
                            passwordStatus = true; // Set to true to continue the loop if the password is invalid
                        }

                    } while (passwordStatus);

                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid Input!");
                scanner.nextLine();
                optionValidate = true;

            }
        } while (optionValidate);

    }

    public void saveUsers() {
        try {
            File file = new File("userObject.ser");

            try (FileOutputStream fos = new FileOutputStream(file); ObjectOutputStream oos = new ObjectOutputStream(fos)) {

                // write user objects to file
                for (User user : userObjects) {
                    oos.writeObject(user);
                }

                System.out.println("Users saved");
            }
        } catch (IOException e) {
            System.out.println("Users not saved");
        }
    }

    public void loadUsers() {
        try {
            File file = new File("userObject.ser");

            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);

                // Clear the existing user list before loading new objects
                userObjects.clear();

                // read user objects from file and add them to the list
                while (true) {
                    try {
                        User user = (User) ois.readObject();
                        userObjects.add(user);
                    } catch (ClassNotFoundException | IOException e) {
                        break;
                    }
                }

                System.out.println("Users loaded");
            } else {
                System.out.println("User file does not exist. No users loaded.");
            }
        } catch (IOException e) {
            System.out.println("Error loading users");
        }
    }

    public List<Product> getProductList() {
        return productListSystem;
    }
}
