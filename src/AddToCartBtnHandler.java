import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

//ActionListener implementation for handling the "Add to shopping cart" button
public class AddToCartBtnHandler implements ActionListener{
    private JTable productsTable;
    private ArrayList<Product> productList;
    private TableModelCart cartTableModel;
    private ShoppingCartGUI shoppingCartGUI;
    private boolean firstPurchase;

    //Constructor for AddToCartBtnHandler
    public AddToCartBtnHandler(JTable productsTable,ArrayList<Product> productList,
                               TableModelCart cartTableModel,ShoppingCartGUI shoppingCartGUI,boolean firstPurchase){
        this.productsTable = productsTable;
        this.productList = productList;
        this.cartTableModel = cartTableModel;
        this.shoppingCartGUI = shoppingCartGUI;
        this.firstPurchase = firstPurchase;
    }

    //Handles the button click event by adding the selected product to the shopping cart
    @Override
    public void actionPerformed(ActionEvent event) {
        //Get the selected row in the products table
        int selectedRow = productsTable.getSelectedRow();

        //Check if a row is selected
        if(selectedRow != -1) {
            //Get the product ID of the selected product
            String selectedProductId = productsTable.getValueAt(selectedRow, 0).toString();
            Map<Product, Integer> quantityOnCart = cartTableModel.getShoppingCart().getCartQuantity();

            //Iterate through the available products to find the selected product
            for (Product checker : productList) {
                //Check if the product ID matches and there are available items
                if (checker.getProductID().equals(selectedProductId) && checker.getNumberOfAvailableItems()>0) {
                    int availableItems;

                    //Calculate the available items considering items already in the cart
                    if(quantityOnCart.containsKey(checker)){
                        availableItems = checker.getNumberOfAvailableItems() - quantityOnCart.get(checker);
                    }
                    else availableItems = checker.getNumberOfAvailableItems();

                    //If there are available items, add the product to the cart and update the GUI
                    if (availableItems > 0) {
                        cartTableModel.getShoppingCart().addProductToCart(checker);
                        shoppingCartGUI.updatePrices(cartTableModel.getShoppingCart().calculateTotalCost(),cartTableModel.getShoppingCart(),firstPurchase);
                        cartTableModel.fireTableDataChanged();
                    }

                    //break out of the loop after processing the selected product
                    break;
                }
            }

        }
    }
}
