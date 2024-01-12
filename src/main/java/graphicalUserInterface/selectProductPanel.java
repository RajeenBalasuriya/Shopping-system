package graphicalUserInterface;

import com.mycompany.shopping.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SelectProductPanel extends JPanel {

    private final List<Product> productListSystem;
    private final ProductInformationTable tableInfo;

    public SelectProductPanel(List<Product> productListSystem) {
        
        this.productListSystem = productListSystem;
        Collections.sort(productListSystem, Comparator.comparing(Product::getProductName));//sorting the list before adding to the table
        
        this.setLayout(new BorderLayout());

        
        JLabel selectedCategoryLabel = new JLabel("Selected category: ");
        String menuItems[] = {"All", "Electronics", "Clothing"};
        JComboBox<String> dropMenu = new JComboBox<>(menuItems);


        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p2.add(selectedCategoryLabel);
        p2.add(dropMenu);
        this.add(p2, BorderLayout.NORTH);
        
        // Creating the information table object
        tableInfo = new ProductInformationTable(productListSystem);
        JScrollPane jScrollPane = new JScrollPane(tableInfo.table);
        jScrollPane.setPreferredSize(new Dimension(500, 300));


        
      JPanel p5=tableInfo.getPanel();
      p5.setBackground(Color.GREEN);
      this.add(p5,BorderLayout.SOUTH);
        
        JPanel scrollPaneCenterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        scrollPaneCenterPanel.add(jScrollPane);

        // Add the inner panel with JScrollPane to the main panel at BorderLayout CENTER
        this.add(scrollPaneCenterPanel, BorderLayout.CENTER);

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
