package graphicalUserInterface;

import com.mycompany.shopping.Product;
import com.mycompany.shopping.ShoppingCart;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;

public class ShoppingCartGui extends JFrame {
    private Map<String, Product> productMap;

    public ShoppingCartGui(ShoppingCart userCart, Product selectedProduct) {
        // Set values of the shopping cart gui frame
        this.setSize(1000, 650);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.productMap = userCart.getProductMap();

        // Create the table and add it to the NORTH of the frame
        createTable(userCart, selectedProduct);

        this.setVisible(true);
    }

    private void createTable(ShoppingCart userCart, Product selectedProduct) {
        // Code for creating the table
        JPanel topCartPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        String[] columnNames = {"Product", "Quantity", "Price"};

        // Create the table model with default data
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);



        // Create the table with the model
        JTable cartTable = new JTable(tableModel);

        // Style the table
        styleTable(cartTable);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(cartTable);

        // Add top border to the table
        TitledBorder border = BorderFactory.createTitledBorder("Shopping Cart");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(40, 10, 10, 10), border));


        scrollPane.setPreferredSize(new Dimension(500, 300));

        // Add the scroll pane to the topCartPanel
        topCartPanel.add(scrollPane);

        productMap = userCart.getProductMap();

        // Populate cart table
        for (Product product : productMap.values()) {
            tableModel.addRow(new Object[]{product.getProductId() + "," + product.getProductName() + "," + product.additionalInfo1() + "," + product.additionalInfo2(), product.getCartCount(), product.getMarketPrice()});
        }

        // Add topCartPanel to the ShoppingCartGui frame
        this.add(topCartPanel, BorderLayout.NORTH);
    }

    private void styleTable(JTable table) {
        // Set custom cell renderer to center text
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        // Set row height for better visibility
        table.setRowHeight(30);

        // Set font for the table
        Font tableFont = new Font("Arial", Font.PLAIN, 14);
        table.setFont(tableFont);


    }

}
