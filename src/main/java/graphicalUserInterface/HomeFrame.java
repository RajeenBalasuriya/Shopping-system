/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphicalUserInterface;

import com.mycompany.shopping.Product;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
        
       
        
        
        
        
        this.setBackground(Color.yellow);
        
        JPanel p2= new JPanel(new FlowLayout(FlowLayout.TRAILING));
        JButton b = new JButton("Shopping Cart");
        p2.add(b);
        this.add(p2,BorderLayout.NORTH);
        
        
        selectProductPanel p1=new selectProductPanel(productListSystem);
        this.add(p1,BorderLayout.CENTER);
        
        
        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton c = new JButton("ADD TO SHOPPING CART");
        p3.add(c);
        this.add(p3,BorderLayout.SOUTH);
        
        
        
        
        
     
        
       
        
    }
    
}
