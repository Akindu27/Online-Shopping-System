import java.util.ArrayList;
import java.util.Map;

public class ShoppingCart{
    //Fields to store the list of products int the cart and their quantities
    private ArrayList<Product> cartList;

    private Map<Product, Integer> cartQuantity;

    //Constructor to initialize the ShoppingCart object
    public ShoppingCart(ArrayList<Product> cartList, Map<Product, Integer> cartQuantity){
        this.cartList = cartList;
        this.cartQuantity = cartQuantity;
    }

    //Getter method for cartList
    public  ArrayList<Product> getCartList(){
        return cartList;
    }

    //Getter method for cartQuantity
    public Map<Product, Integer> getCartQuantity() {
        return cartQuantity;
    }

    //Method to add a product to the cart
    public void addProductToCart(Product product){
        if(cartList.contains(product)){
            cartQuantity.put(product, cartQuantity.get(product) +1);
        }
        else {
            cartList.add(product);
            cartQuantity.put(product,1);
        }
    }

    //Method to calculate the total cost of items in the cart
    public double calculateTotalCost(){   // change to calculateTotalCost
        double totalCost = 0;
        double itemPrice;
        for(Product product:cartList){
            itemPrice = product.getPrice() * cartQuantity.get(product);
            totalCost += Math.round(itemPrice * 100.0)/100.0;
        }
        return totalCost;
    }

    //Method to calculate the discount for purchasing three or more items
    public double getThreeItemsDiscount (){
        double addedDiscount = 0;
        int clothingCategoryItem =0;
        int electronicsCategoryItem=0;
        boolean sameThreeProducts = false;
        int clothingItemQuantity = 0;
        int electronicsItemQuantity = 0;

        //Checking if three or more items are present in the cart
        for (Product product: cartList) {
            if (getCartQuantity().containsKey(product) && getCartQuantity().get(product) >= 3)
                sameThreeProducts = true;

            //Counting items in clothing and electronics categories
            if (product instanceof Clothing){
                clothingCategoryItem++;
                clothingItemQuantity = clothingItemQuantity + getCartQuantity().get(product);
            }else {
                electronicsCategoryItem++;
                electronicsItemQuantity = electronicsItemQuantity + getCartQuantity().get(product);
            }
        }

        //Applying if conditions are met
        if (clothingCategoryItem>=3 || electronicsCategoryItem>=3 || sameThreeProducts || clothingItemQuantity>=3 || electronicsItemQuantity>=3){
            addedDiscount = calculateTotalCost()*0.2;
        }
        return  Math.round(addedDiscount * 100.0)/100.0;
    }

}
