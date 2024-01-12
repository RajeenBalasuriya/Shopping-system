package graphicalUserInterface;

import com.mycompany.shopping.Product;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class HomeFrame extends JFrame {

    public HomeFrame(List<Product> productListSystem) {
        SwingUtilities.invokeLater(() -> {
            // Set the layout manager for the frame
            this.setLayout(new BorderLayout());

            // Set the default close operation
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Center the frame on the screen
            this.setLocationRelativeTo(null);

            this.setBackground(Color.yellow);

            JPanel p2 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
            JButton b = new JButton("Shopping Cart");
            p2.add(b);
            this.add(p2, BorderLayout.NORTH);

            selectProductPanel p1 = new selectProductPanel(productListSystem);
            this.add(p1, BorderLayout.CENTER);

            JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JButton c = new JButton("ADD TO SHOPPING CART");
            p3.add(c);
            this.add(p3, BorderLayout.SOUTH);

            // Pack the frame to set its size based on the preferred sizes of its components
            this.setSize(1000,650);

            // Set the frame to be visible after packing
            this.setVisible(true);
        });
    }
}
