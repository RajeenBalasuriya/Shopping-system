package graphicalUserInterface;

import com.mycompany.shopping.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class selectProductPanel extends JPanel {

    private final List<Product> productListSystem;
    private final productInformationTable tableInfo;

    public selectProductPanel(List<Product> productListSystem) {
        this.productListSystem = productListSystem;

        // Set the layout manager to BorderLayout for the main panel
        this.setLayout(new BorderLayout());
        
        this.setBackground(Color.red);

       

        // Add a label to the inner panel
        JLabel selectedCategoryLabel = new JLabel("Selected category: ");

        // Add the label to the inner panel
        this.add(selectedCategoryLabel,BorderLayout.NORTH);

        // Array of strings containing menu items
        String menuItems[] = {"All","Electronics", "Clothing"};

        // Create combo box
        JComboBox<String> dropMenu = new JComboBox<>(menuItems);

       
        // Add the inner panel to the main panel at BorderLayoutNORTH
        this.add(dropMenu, BorderLayout.NORTH);

        // Creating the information table object
        tableInfo = new productInformationTable(productListSystem);
        
        JScrollPane jScrollPane = new JScrollPane(tableInfo.table);
        jScrollPane.setPreferredSize(new Dimension(1500, 400));
        
        
        
        // Add JScrollPane to an inner panel with FlowLayout.CENTER
        JPanel scrollPaneCenterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        scrollPaneCenterPanel.add(jScrollPane);

        // Add the inner panel with JScrollPane to the main panel at BorderLayout CENTER
        this.add(scrollPaneCenterPanel, BorderLayout.SOUTH);
        
        
        // Add an ActionListener to the combo box
        dropMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Filter products based on the selected type
                String selectedType = (String) dropMenu.getSelectedItem();
                List<Product> filteredProducts = filterProducts(selectedType);

                // Update the table with the filtered products
                tableInfo.updateTable(filteredProducts);
            }
        });
    }

    private List<Product> filterProducts(String selectedType) {
        if ("All".equals(selectedType)) {
            return productListSystem;
        } else {
            return productListSystem.stream()
                    .filter(product -> selectedType.equals(product.getProductType()))
                    .collect(Collectors.toList());
        }
    }
}
