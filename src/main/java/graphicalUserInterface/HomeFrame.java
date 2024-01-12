package graphicalUserInterface;

import com.mycompany.shopping.Product;
import com.mycompany.shopping.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class HomeFrame extends JFrame {

    public HomeFrame(List<Product> productListSystem,User user) {
        SwingUtilities.invokeLater(() -> {
            // Set the layout manager for the frame
            this.setLayout(new BorderLayout());

            // Set the default close operation
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Center the frame on the screen
            this.setLocationRelativeTo(null);

           // set the frame size
            this.setSize(1000,650);

            // Set the frame to be visible after packing
            this.setVisible(true);

            JPanel p2 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
            JButton cartButton = new JButton("Shopping Cart");
            p2.add(cartButton);
            this.add(p2, BorderLayout.NORTH);
            
            // Add ActionListener to the "Shopping Cart" button
            cartButton.addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Create and show the ShoppingCartGui when the button is clicked
                    ShoppingCartGuiFrame shoppingCartFrame = new ShoppingCartGuiFrame(user);
                    
         
                    
                }
            });


            SelectProductPanel p1 = new SelectProductPanel(productListSystem);
            this.add(p1, BorderLayout.CENTER);

            JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JButton addCartButton = new JButton("ADD TO SHOPPING CART");
            p3.add(addCartButton);
            this.add(p3, BorderLayout.SOUTH);
            
            
            


        });
    }
}
