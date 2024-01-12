

package graphicalUserInterface;

import com.mycompany.shopping.Product;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Represents a panel for displaying product details.
 * 
 * @author rajeen
 */
public class showDetailsPanel extends JPanel {
    
    private final JLabel productInfoLabel;
    private final JLabel productInfoLabel1;
    private final JLabel productInfoLabel2;
    private final JLabel productInfoLabel3;
    private final JLabel infoLabel;
    private final JLabel infoLabel2;
    private final JLabel infoLabel3;

    /**
     * Creates a new instance of ShowDetailsPanel.
     */
    public showDetailsPanel(){
        this.setLayout(new BorderLayout());
        this.setBackground(Color.red);

        // Initialize the label to display product information
        productInfoLabel = new JLabel("productInfo");
        JPanel topicPanel = new JPanel(new BorderLayout());
        topicPanel.add(productInfoLabel,BorderLayout.NORTH);
        this.add(topicPanel,BorderLayout.NORTH);
       
        
         productInfoLabel1 = new JLabel("product name");
         productInfoLabel2 = new JLabel("product price");
         productInfoLabel3 = new JLabel("product type");
        // Initialize the label for SOUTH
         infoLabel = new JLabel();
         infoLabel2 = new JLabel();
         infoLabel3 = new JLabel();
        


        
        JPanel panelInfo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel f = new JPanel(new GridLayout(3,2)); 
        
        f.add(productInfoLabel1);
        f.add(infoLabel);
        
        f.add(productInfoLabel2);
        f.add(infoLabel2);
        
        f.add(productInfoLabel3);
        
        panelInfo.add(f);
        this.add(panelInfo,BorderLayout.CENTER);
    }
    
    /**
     * Sets the product information to be displayed on the panel.
     * 
     * @param product The Product object containing information to be displayed.
     */
    public void setInfo(Product product){
        // Update the label in SOUTH with the new product information
        infoLabel.setText(product.getProductName());
        infoLabel2.setText(product.getProductType());
        infoLabel3.setText(product.getProductName());
        
        // Repaint the panel to reflect the changes
        this.revalidate();
        this.repaint();
    }
}
