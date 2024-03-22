import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//ActionListener implementation for handling the "Shopping Cart" button
public class CartBtnHandler implements ActionListener {
    private ShoppingCartGUI shoppingCartGUI;

    //Constructor for CartBtnHandler
    public CartBtnHandler(ShoppingCartGUI shoppingCartGUI){
        this.shoppingCartGUI = shoppingCartGUI;
    }

    //Handles the button click event by making the shopping cart GUI visible if it is not already active
    @Override
    public void actionPerformed(ActionEvent event) {
        //Check if the shopping cart GUI is not already active and make it visible if not
        if (!shoppingCartGUI.isActive()){
            shoppingCartGUI.setVisible(true);
        }
    }

}
