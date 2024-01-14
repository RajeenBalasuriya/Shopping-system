package graphicalUserInterface;

import com.mycompany.shopping.Product;
import com.mycompany.shopping.ShoppingCart;
import com.mycompany.shopping.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.stream.Collectors;

public class HomeFrame extends JFrame {

    private JTable table;
    private Product selectedProduct; //track the selected product of the user
    private final List<Product> productListSystem;

    private List<Product> filteredProducts ;

    private boolean isFilterd=false;


    JLabel productID = new JLabel();

    JLabel productName = new JLabel();
    JLabel itemsAvailable = new JLabel();
    JLabel productType = new JLabel();
    JLabel infoName = new JLabel();
    JLabel infoType = new JLabel();
    JLabel infoID = new JLabel();
    JLabel infoItemsAvailable = new JLabel();
    JLabel additionalInfo1 = new JLabel();
    JLabel additionalInfo2 = new JLabel();
    JLabel infoAdditional1 = new JLabel();
    JLabel infoAdditional2 = new JLabel();



    // constructor for the HomeFrame
    public HomeFrame(List<Product> productListSystem, User user, ShoppingCart userCart) {


        this.productListSystem = productListSystem;

        // components of home frame
        JButton shoppingCartButton = new JButton("Shopping Cart");
        JButton addToCartButton = new JButton("Add TO Cart");

        // set attributes of home frame

        this.setSize(1000, 650);// set the frame size
        this.setLocationRelativeTo(null);// Center the frame on the screen

        /* HomeFrame Layout setting */

        // setting layout manager of home frame
        this.setLayout(new BorderLayout());

        // creating a top panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(shoppingCartButton);
        this.add(topPanel, BorderLayout.NORTH);// adding top panel to NORTH of homeFrame

        // creating of productTablePanel(Center panel of homeFrame)
        JPanel productInformationPanel = new JPanel(new BorderLayout());

        JPanel ComboPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel SelectCategory = new JLabel("Select Category");

        //selections for the combo box
        String[] menuItems = {"All", "Electronics", "Clothing"};
        JComboBox<String> ComboBox = new JComboBox<>(menuItems);
        //add combobox to comboPanel
        ComboPanel.add(SelectCategory);
        ComboPanel.add(ComboBox);

        // Add an empty border with some space to move the contents down
        ComboPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        // Add combo panel to productInformationPanel
        productInformationPanel.add(ComboPanel, BorderLayout.NORTH);

        // Use the class member table instead of creating a new JTable
        createTable(productListSystem, userCart);
        JScrollPane scrollPane = new JScrollPane(table);

        // Set the preferred size for the scroll pane
        scrollPane.setPreferredSize(new Dimension(800, 300));

        // creating table panel and adding empty border with space on both sides
        JPanel tablePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tablePanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20)); // Adjust the values as needed
        tablePanel.add(scrollPane);

        //creating a detailPanel to show details
        JPanel detailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //creating a panel with two colums add  inside of detail panel which is placed left
        JPanel infoDetailPanel = new JPanel(new GridLayout(6,2));

        //add infoDetailPanel to the detailPanel
        detailPanel.add(infoDetailPanel);


        productInformationPanel.add(tablePanel, BorderLayout.CENTER);
        productInformationPanel.add(detailPanel,BorderLayout.SOUTH);



        /*
        Section below is for showing selected product details which is also in productInformation panel

         */

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(addToCartButton);
        this.add(productInformationPanel, BorderLayout.CENTER);
        this.add(bottomPanel,BorderLayout.SOUTH);

        //setting the events listeners for components in the home frame

        // Add a mouse listener to the table to capture clicks on rows
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int selectedRow = table.getSelectedRow();


                // Get the selected product from the data model
                if(isFilterd){
                    selectedProduct = filteredProducts.get(selectedRow);
                    setDetails(infoDetailPanel,selectedProduct);
                    System.out.println("Selected Type: " + selectedProduct.getProductId());

                }else{
                    selectedProduct = productListSystem.get(selectedRow);
                    setDetails(infoDetailPanel,selectedProduct);
                    System.out.println("Selected Type: " + selectedProduct.getProductId());
                }




            }
        });

        // Add an ActionListener to the combo box
        ComboBox.addActionListener(e -> {
            // Filter products based on the selected type
            String selectedType = (String) ComboBox.getSelectedItem();
            filterProducts(selectedType);
            updateTable(filteredProducts);


        });

        this.setVisible(true);
    }

    public void createTable(List<Product> productListSystem, ShoppingCart userCart) {

        // Define column names
        String[] columnNames = {" Product ID", "NAME", "Category", "Price", "Info"};

        // initialize model for the table
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make all cells uneditable
                return false;
            }
        };

        // Populate the model with data from the ArrayList
        for (Product product : productListSystem) {
            Object[] rowData = {product.getProductId(), product.getProductName(), product.getProductType(), product.getMarketPrice(), product.getProductInfo()};
            model.addRow(rowData);
        }

        // Create JTable with the model
        table = new JTable(model);

        // Set custom font for column headings
        Font headerFont = new Font("SansSerif", Font.BOLD, 14);
        table.getTableHeader().setFont(headerFont);

        // Set custom row height
        table.setRowHeight(30);
    }

    //method to update table when select a product category from combo box
    private void updateTable(List<Product> filteredProducts) {

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        // Populate the model with data from the filtered products
        for (Product product : filteredProducts) {
            Object[] rowData = {product.getProductId(), product.getProductName(), product.getProductType(), product.getMarketPrice(), product.getProductInfo()};
            model.addRow(rowData);
        }
    }

    //method to  filter products when category is selected from combo box
    private void filterProducts(String selectedType) {
        if ("All".equals(selectedType)) {
            isFilterd=false;
            this.filteredProducts=productListSystem;

        } else {
            isFilterd=true;
            filteredProducts=(productListSystem.stream()
                    .filter(product -> selectedType.equals(product.getProductType()))
                    .collect(Collectors.toList()));

        }
    }

    //method to show info in detail panel
    private void setDetails(JPanel infoDetailPanel, Product selectedProduct) {

        // Clear existing labels from infoDetailPanel
        infoDetailPanel.removeAll();

        productID.setText("Product Id:");
        infoDetailPanel.add(productID);
        infoID.setText(selectedProduct.getProductId());
        infoDetailPanel.add(infoID);

        productType.setText("Category:");
        infoDetailPanel.add(productType);
        infoType.setText(selectedProduct.getProductType());
        infoDetailPanel.add(infoType);

        productName.setText("Name:");
        infoDetailPanel.add(productName);
        infoName.setText(selectedProduct.getProductName());
        infoDetailPanel.add(infoName);

        if (selectedProduct.getProductType().equals("Clothing")) {
            additionalInfo1.setText("Size");
            infoDetailPanel.add(additionalInfo1);
            infoAdditional1.setText(selectedProduct.additionalInfo1());
            infoDetailPanel.add(infoAdditional1);

            additionalInfo2.setText("Color");
            infoDetailPanel.add(additionalInfo2);
            infoAdditional2.setText(selectedProduct.additionalInfo2());
            infoDetailPanel.add(infoAdditional2);
        } else if(selectedProduct.getProductType().equals("Electronics")) {
            additionalInfo1.setText("Brand");
            infoDetailPanel.add(additionalInfo1);
            infoAdditional1.setText(selectedProduct.additionalInfo1());
            infoDetailPanel.add(infoAdditional1);

            additionalInfo2.setText("Warranty Period");
            infoDetailPanel.add(additionalInfo2);
            infoAdditional2.setText(selectedProduct.additionalInfo2());
            infoDetailPanel.add(infoAdditional2);
        }

        itemsAvailable.setText("Items Available");
        infoDetailPanel.add(itemsAvailable);
        infoItemsAvailable.setText(String.valueOf(selectedProduct.getNoOfAvailableItems()));
        infoDetailPanel.add(infoItemsAvailable);

        // Force UI update
        infoDetailPanel.revalidate();
        infoDetailPanel.repaint();
    }

}
