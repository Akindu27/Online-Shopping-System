//Abstract class representing a generic product
public abstract class Product {

    //private fields to store product information
    private String productID;
    private String productName;
    private int numberOfAvailableItems;
    private double price;

    //Constructor to initialize product attributes
    public Product(String productID,String productName,int numberOfAvailableItems,double price){
        this.productID =productID;
        this.productName=productName;
        this.numberOfAvailableItems=numberOfAvailableItems;
        this.price=price;
    }

    //Getter method to retrieve the product ID
    public String getProductID() {
        return productID;
    }

    //Getter method to retrieve the product name
    public String getProductName() {
        return productName;
    }

    //Getter method to retrieve the number of available items
    public int getNumberOfAvailableItems() {
        return numberOfAvailableItems;
    }

    //Getter method to retrieve the product price
    public double getPrice() {
        return price;
    }

    //Abstract method to be implemented by subclasses, representing the type of product
    public abstract String getProductType();
}
