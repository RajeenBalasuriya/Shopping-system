package graphicalUserInterface;

import com.mycompany.shopping.Product;
import com.mycompany.shopping.ShoppingCart;
import com.mycompany.shopping.User;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ShoppingCartGuiFrame extends JFrame {

    public ShoppingCartGuiFrame(User user,ShoppingCart userCart) {
        // Set the layout manager for the frame
        this.setLayout(new BorderLayout());

        // Set the default close operation
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Center the frame on the screen
        this.setLocationRelativeTo(null);

        // Set the frame size based on the preferred sizes of its components
        this.setSize(1000, 650);

        //line 26-45 , table creation and centering
        
        
        // Create table model with columns
        String[] columnNames = {"Product", "Quantity", "Price"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Create JTable with the model
        JTable tableCart = new JTable(model);

        // Create JScrollPane to make the table scrollable
        JScrollPane scrollPane = new JScrollPane(tableCart);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel tablePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        tablePanel.add(scrollPane);

        // Add tablePanle to the frame
        this.add(tablePanel, BorderLayout.CENTER);
        updateTable(model,userCart);


        
        
        

        // Set the frame to be visible after packing
        this.setVisible(true);
    }
    
    
        // Method to update the table with data from the shopping cart
    public void updateTable(DefaultTableModel model,ShoppingCart userCart) {
        model.setRowCount(0); // Clear existing rows

        Map<String, Product> cartItems = userCart.getProductMap();
        for (Product product : cartItems.values()) {
            // Add each product to the table model
            Object[] rowData = {
                product.additionalInfo1(),
                product.getCartCount(), 
                product.getMarketPrice()
            };
            model.addRow(rowData);
        }
        this.revalidate();
        this.repaint();
    }
}


