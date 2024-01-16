package graphicalUserInterface;

import com.mycompany.shopping.Product;
import com.mycompany.shopping.ShoppingCart;
import com.mycompany.shopping.User;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.stream.Collectors;

public class HomeFrame extends JFrame {

    private JTable table;
    private Product selectedProduct;
    private ShoppingCartGui cartFrame;
    private boolean isCartFormed = false;
    private final List<Product> productListSystem;

    private List<Product> filteredProducts;
    private boolean isFiltered = false;

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

    public HomeFrame(List<Product> productListSystem, ShoppingCart userCart, User user) {
        this.productListSystem = productListSystem;

        JButton shoppingCartButton = new JButton("Shopping Cart");
        JButton addToCartButton = new JButton("Add TO Cart");

        this.setSize(1000, 650);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(shoppingCartButton);
        this.add(topPanel, BorderLayout.NORTH);

        JPanel productInformationPanel = new JPanel(new BorderLayout());

        JPanel ComboPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel SelectCategory = new JLabel("Select Category");

        String[] menuItems = {"All", "Electronics", "Clothing"};
        JComboBox<String> ComboBox = new JComboBox<>(menuItems);
        ComboPanel.add(SelectCategory);
        ComboPanel.add(ComboBox);
        ComboPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        productInformationPanel.add(ComboPanel, BorderLayout.NORTH);

        createTable(productListSystem, userCart);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 300));

        JPanel tablePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tablePanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        tablePanel.add(scrollPane);

        JPanel detailPanel = new JPanel(new BorderLayout());
        JPanel detailPanelTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel productInfoTag = new JLabel("Product Information");
        productInfoTag.setFont(new Font("SansSerif", Font.BOLD, 16)); // Set font to bold
        productInfoTag.setVisible(false);// hidden untill customer selects a product
        detailPanelTop.add(productInfoTag);
        detailPanel.add(detailPanelTop, BorderLayout.NORTH);

        JPanel detailPanelBottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel infoDetailPanel = new JPanel(new GridLayout(6, 2));
        detailPanelBottom.add(infoDetailPanel);
        detailPanel.add(detailPanelBottom, BorderLayout.SOUTH);


        productInformationPanel.add(tablePanel, BorderLayout.CENTER);
        productInformationPanel.add(detailPanel, BorderLayout.SOUTH);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(addToCartButton);
        this.add(productInformationPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);


        // starting from here there will be action listeners fot buttons in home frame

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int selectedRow = table.getSelectedRow();
                if (isFiltered) {
                    selectedProduct = filteredProducts.get(selectedRow);
                    setDetails(infoDetailPanel, selectedProduct);
                    System.out.println("Selected Type: " + selectedProduct.getProductId());
                } else {
                    selectedProduct = productListSystem.get(selectedRow);
                    setDetails(infoDetailPanel, selectedProduct);
                    System.out.println("Selected Type: " + selectedProduct.getProductId());
                }
                productInfoTag.setVisible(true);// make visible after customer selects a product
            }
        });

        ComboBox.addActionListener(e -> {
            String selectedType = (String) ComboBox.getSelectedItem();
            filterProducts(selectedType);
            updateTable(filteredProducts);
        });

        addToCartButton.addActionListener(e -> {
            if (selectedProduct != null && selectedProduct.getNoOfAvailableItems() > 0) {
                //what will happen in the gui after the click
                userCart.addProduct(selectedProduct);
                JOptionPane.showMessageDialog(this, "Product added to cart!");
                selectedProduct.setNoOfAvailableItems(selectedProduct.getNoOfAvailableItems() - 1);
                setDetails(infoDetailPanel, selectedProduct);
                table.repaint();
                //adding the selected item to the cart
                selectedProduct.incrementCartCount();//cartCount is the number of time particular product added to the cart
                userCart.addProduct(selectedProduct);

                //updating the cart when clicking the add to cart button
                if (isCartFormed) {
                    cartFrame.updateInfo(cartFrame.getTableModel(), user);



                }


            } else {
                JOptionPane.showMessageDialog(this, "Please select a product before adding to cart.");
            }
        });

        shoppingCartButton.addActionListener(e -> {
            if (!isCartFormed) {
                user.setNoOfPurchases(user.getNoOfPurchases()+1);
                cartFrame = new ShoppingCartGui(userCart,user);

                isCartFormed = true;
            } else {
                user.setNoOfPurchases(user.getNoOfPurchases()+1);
                cartFrame.setVisible(true);
            }


        });

        this.setVisible(true);


    }

    public void createTable(List<Product> productListSystem, ShoppingCart userCart) {
        String[] columnNames = {" Product ID", "NAME", "Category", "Price", "Info"};

        DefaultTableModel model = getDefaultTableModel(productListSystem, columnNames);

        table = new JTable(model);

        // Set custom renderer for each column
        for (int i = 0; i < model.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new CustomRowRenderer());
        }

        Font headerFont = new Font("SansSerif", Font.BOLD, 14);
        table.getTableHeader().setFont(headerFont);

        table.setRowHeight(30);
    }

    @NotNull
    private static DefaultTableModel getDefaultTableModel(List<Product> productListSystem, String[] columnNames) {
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        // Set custom renderer for each column to center the text
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);


        for (Product product : productListSystem) {
            Object[] rowData = {product.getProductId(), product.getProductName(), product.getProductType(), product.getMarketPrice(), product.getProductInfo()};
            model.addRow(rowData);
        }
        return model;
    }

    private void updateTable(List<Product> filteredProducts) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (Product product : filteredProducts) {
            Object[] rowData = {product.getProductId(), product.getProductName(), product.getProductType(), product.getMarketPrice(), product.getProductInfo()};
            model.addRow(rowData);
        }

        // Force a repaint
        table.repaint();
    }

    private void filterProducts(String selectedType) {
        if ("All".equals(selectedType)) {
            isFiltered = false;
            this.filteredProducts = productListSystem;
        } else {
            isFiltered = true;
            filteredProducts = productListSystem.stream()
                    .filter(product -> selectedType.equals(product.getProductType()))
                    .collect(Collectors.toList());
        }
    }

    private void setDetails(JPanel infoDetailPanel, Product selectedProduct) {
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
        } else if (selectedProduct.getProductType().equals("Electronics")) {
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

        infoDetailPanel.revalidate();
        infoDetailPanel.repaint();
    }

    private class CustomRowRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            int noOfAvailableItems;
            if (isFiltered) {
                noOfAvailableItems = filteredProducts.get(row).getNoOfAvailableItems();
            } else {
                noOfAvailableItems = productListSystem.get(row).getNoOfAvailableItems();
            }

            Color rowColor = (noOfAvailableItems < 4) ? Color.RED : table.getBackground();
            component.setBackground(rowColor);

            return component;
        }
    }
}