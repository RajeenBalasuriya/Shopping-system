/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphicalUserInterface;

import com.mycompany.shopping.Product;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author rajeen
 */
public class HomeFrame extends JFrame {
    
    public HomeFrame(List<Product> productListSystem){
        
       // Set the layout manager for the frame
        this.setLayout(new BorderLayout());

     
        // Set the size of the frame
        this.setSize(800, 600);

        // Set the default close operation
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the frame to be visible
        this.setVisible(true);

        // Center the frame on the screen
        this.setLocationRelativeTo(null);
        
        //creating selelctPRoductPanel object
        selectProductPanel p1=new selectProductPanel(productListSystem);
        
        this.setBackground(Color.yellow);
        
        
        this.add(p1,BorderLayout.NORTH);
        
     
        
       
        
    }
    
}
