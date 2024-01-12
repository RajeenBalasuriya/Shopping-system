/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphicalUserInterface;

import com.mycompany.shopping.Product;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rajeen
 */
public class ProductInformationTable {

    JTable table;
    ShowDetailsPanel s;

    public ProductInformationTable(List<Product> productListSystem) {

        // Define column names
        String[] columnNames = {" Product ID", "NAME", "Category", "Price", "Info"};
        

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
        
         // Set preferred size of the table
        table.setPreferredSize(new Dimension(1000, 300));
        
       // Set custom font for column headings 
        Font headerFont = new Font("SansSerif", Font.BOLD, 14); 
        table.getTableHeader().setFont(headerFont);

        // Set custom row height 
        table.setRowHeight(30);
        

         s= new ShowDetailsPanel();
         // Add a mouse listener to the table to capture clicks on rows
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int selectedRow = table.getSelectedRow();

                if (selectedRow != -1) {
                    // Get the selected product from the data model
                    Product selectedProduct = productListSystem.get(selectedRow);

                    // Pass the selected product to the ShowProductDetailsPanel
                    
                   s.setInfo(selectedProduct);
                }
            }
        });

    }
    
    public JPanel getPanel(){
        return s;
    }
    
    
    
    

    public void updateTable(List<Product> filteredProducts) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        // Populate the model with data from the filtered products
        for (Product product : filteredProducts) {
            Object[] rowData = {product.getProductId(), product.getProductName(), product.getProductType(), product.getMarketPrice(), product.getProductInfo()};
            model.addRow(rowData);
        }
    }

}
