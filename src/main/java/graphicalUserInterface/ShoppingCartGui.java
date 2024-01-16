package graphicalUserInterface;

import com.mycompany.shopping.Product;
import com.mycompany.shopping.ShoppingCart;
import com.mycompany.shopping.User;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ShoppingCartGui extends JFrame {


    private final DefaultTableModel tableModel;

    private final ShoppingCart userCart;


    private final JLabel infoTotal;
    private final JLabel infoFirstDiscount;
    private final JLabel infoCategoryDiscount;
    private final JLabel infoNewTotal;


    public ShoppingCartGui(ShoppingCart userCart, User user) {
        // Code for creating the table
        JPanel topCartPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        String[] columnNames = {"Product", "Quantity", "Price"};

        this.tableModel = new DefaultTableModel(columnNames, 0);

        // Create the table with the model
        JTable cartTable = new JTable(tableModel);

        // Style the table
        styleTable(cartTable);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(cartTable);
        this.userCart = userCart;

        // Add top border to the table
        TitledBorder border = BorderFactory.createTitledBorder("Shopping Cart");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(40, 10, 10, 10), border));

        scrollPane.setPreferredSize(new Dimension(500, 300));

        // Add the scroll pane to the topCartPanel
        topCartPanel.add(scrollPane);

        // Set values of the shopping cart gui frame
        this.setSize(1000, 650);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);

        // Add topCartPanel to the ShoppingCartGui frame (moved to this position)
        this.add(topCartPanel, BorderLayout.NORTH);



        /* THIS IS THE DISCOUNT SECTION */


        //setting the labels
        //JLabels for the discount section
        JLabel total = new JLabel();
        JLabel firstDiscount = new JLabel();
        JLabel categoryDiscount = new JLabel();
        JLabel newTotal = new JLabel();
        this.infoTotal = new JLabel();
        this.infoFirstDiscount = new JLabel();
        this.infoCategoryDiscount = new JLabel();
        this.infoNewTotal = new JLabel();

        total.setText("Total(£)");
        firstDiscount.setText("first discount(10%)");
        categoryDiscount.setText("Category discount (20%)");
        newTotal.setText("New total");


        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
        JPanel bottomLeftPanel = new JPanel();
        bottomPanel.add(bottomLeftPanel);
        JPanel bottomRightPanel = new JPanel(new GridLayout(1, 2));


        JPanel labelPanel = new JPanel();
        JPanel infoPanel = new JPanel();

        labelPanel.setLayout(new GridLayout(4, 1));
        infoPanel.setLayout(new GridLayout(4, 1));

        labelPanel.add(total);
        labelPanel.add(firstDiscount);
        labelPanel.add(categoryDiscount);
        labelPanel.add(newTotal);

        infoPanel.add(infoTotal);
        infoPanel.add(infoFirstDiscount);
        infoPanel.add(infoCategoryDiscount);
        infoPanel.add(infoNewTotal);


        updateInfo(tableModel, user);

        bottomRightPanel.add(labelPanel);
        bottomRightPanel.add(infoPanel);


        bottomPanel.add(bottomRightPanel);


        this.add(bottomPanel, BorderLayout.CENTER);


        this.setVisible(true);

    }

    public void setInfoDisocunt(User user, ShoppingCart userCart) {
        infoTotal.setText(userCart.calculateTotalCost() + "£");

        // setting the first purchase discount
        if (user.getNoOfPurchases() == 1) {
            infoFirstDiscount.setText(userCart.calculateFirstPurchaseDiscount(userCart.calculateTotalCost()) + "£");
        }

        // for loop to retrieve products from product map
        for (Product product : userCart.getProductMap().values()) {
            if (product.getCartCount() > 3) {
                infoCategoryDiscount.setText(userCart.calculateCategoryDiscount(userCart.calculateTotalCost()) + "£");
            }
        }

        // setting the new total
        double newTotal = userCart.calculateTotalCost() - userCart.getTotalDiscount();
        infoNewTotal.setText(String.valueOf(newTotal));


    }


    public void updateInfo(DefaultTableModel tableModel, User user) {
        // Clear existing data in the table
        tableModel.setRowCount(0);

        for (Product product : userCart.getProductMap().values()) {
            tableModel.addRow(new Object[]{product.getProductId() + "," + product.getProductName() + "," + product.additionalInfo1() + "," + product.additionalInfo2(), product.getCartCount(), product.getMarketPrice()});
        }

        setInfoDisocunt(user, userCart);


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


    public DefaultTableModel getTableModel() {
        return tableModel;
    }


}
