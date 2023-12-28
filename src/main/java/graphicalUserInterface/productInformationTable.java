/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphicalUserInterface;

import com.mycompany.shopping.Product;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rajeen
 */
public class productInformationTable {

    JTable table;

    public productInformationTable(List<Product> productListSystem) {

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

        // Set custom row height (adjust this value as needed)
        table.setRowHeight(40);

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
