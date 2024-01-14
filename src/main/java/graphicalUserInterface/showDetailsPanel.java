

package graphicalUserInterface;

import com.mycompany.shopping.Product;
import com.mycompany.shopping.ShoppingCart;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;

/**
 * Represents a panel for displaying product details.
 * 
 * @author rajeen
 */
public class ShowDetailsPanel extends JPanel {
    
    private final JLabel productID;
    private final JLabel productInfoLabel;
    private final JLabel productName;
    private final JLabel itemsAvailable;
    private final JLabel productType;
    private final JLabel infoName;
    private final JLabel infoPrice;
    private final JLabel infoType;
    private final JLabel infoID;
    private final JLabel infoItemsAvailable;
    private final JLabel additionalInfo1;
    private final JLabel additionalInfo2;
    private final JLabel infoAdditional1;
    private final JLabel infoAdditional2;
    private final JButton addCartButton; 

    /**
     * Creates a new instance of ShowDetailsPanel.
     */
    public ShowDetailsPanel(){
        this.setLayout(new BorderLayout());
        this.setBackground(Color.red);

        // Initialize the label to display product information
        productInfoLabel = new JLabel("Product Information");
        // Set font size for the productInfoLabel
        Font productInfoFont = new Font("SansSerif", Font.BOLD, 20); 
        productInfoLabel.setFont(productInfoFont);
        JPanel topicPanel = new JPanel(new BorderLayout());
        topicPanel.add(productInfoLabel,BorderLayout.NORTH);
        this.add(topicPanel,BorderLayout.NORTH);
        
       //JLabels to display relavant setions
         productID = new JLabel("product ID:");
         productType = new JLabel("Product type:");
         productName= new JLabel("product name:");
         
         additionalInfo1=new JLabel();//will dynamically set value depending on product type
         additionalInfo2=new JLabel();//will dynamically set value depending on product type
         
         itemsAvailable = new JLabel("Items Available:");
         
         
        // Initialize the label for the content of the above initialized labels
         infoName = new JLabel();
         infoPrice = new JLabel();
         infoType = new JLabel();
         infoID= new JLabel();
         infoAdditional1=new JLabel();//will dynamically set value depending on product type
         infoAdditional2=new JLabel();//will dynamically set value depending on product type
         infoItemsAvailable = new JLabel();
         
         
        


        
        JPanel panelInfo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panelInfoComponents = new JPanel(new GridLayout(6,2)); 
        
        panelInfoComponents.add(productID);
        panelInfoComponents.add(infoID);
        
        panelInfoComponents.add(productType);
        panelInfoComponents.add(infoType);
        
        panelInfoComponents.add(productName);
        panelInfoComponents.add(infoName);
        
                
        panelInfoComponents.add(additionalInfo1);
        panelInfoComponents.add(infoAdditional1);
        
                
        panelInfoComponents.add(additionalInfo2);
        panelInfoComponents.add(infoAdditional2);
        
        
        
        panelInfoComponents.add(itemsAvailable);
        panelInfoComponents.add(infoItemsAvailable);
        

        
        panelInfo.add(panelInfoComponents);
        this.add(panelInfo,BorderLayout.CENTER);
        
         JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
         addCartButton = new JButton("ADD TO SHOPPING CART");
         p3.add(addCartButton);
         
         this.add(p3,BorderLayout.SOUTH);
    }
    
    /**
     * Sets the product information to be displayed on the panel.
     * 
     * @param product The Product object containing information to be displayed.
     */
    public void setInfo(Product product){
        // Update the label in SOUTH with the new product information
        infoID.setText(product.getProductId());
        infoType.setText(product.getProductType());
        infoName.setText(product.getProductName());
        //set text fields depending on the product type
        if(product.getProductType().equals("Clothing")){
            additionalInfo1.setText("Size");
            infoAdditional1.setText(product.additionalInfo1());
            additionalInfo2.setText("Color");
            infoAdditional2.setText(product.additionalInfo2());
        }
        else{
            additionalInfo1.setText("Brand");
            infoAdditional1.setText(product.additionalInfo1());
            additionalInfo2.setText("Warranty Period");
            infoAdditional2.setText(product.additionalInfo2());
        
        }
        infoItemsAvailable.setText(String.valueOf(product.getNoOfAvailableItems()));
        
        
        
        // Repaint the panel to reflect the changes
        this.revalidate();
        this.repaint();
    }
    
    public void addToCart(ShoppingCart userCart,Product product){
        
        
        addCartButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            int noOfAvailableItems=product.getNoOfAvailableItems();
            
            if(noOfAvailableItems==0){
                System.out.println(product.getProductName()+":"+product.getNoOfAvailableItems());
            }
            else{
                
                 userCart.addProduct(product);//adding product to the cart
                 product.incrementCartCount();//increment cartCount by1 after adding to cart
                 product.setNoOfAvailableItems(product.getNoOfAvailableItems()-1);
                 infoItemsAvailable.setText(String.valueOf(product.getNoOfAvailableItems()));
                 
            }
            
            
        }
    });
    }
}


