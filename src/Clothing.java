//Class representing a specific type of product: Clothing, extending the Product class
public class Clothing extends Product{

    //Additional fields specific to Clothing
    private String size;
    private String colour;

    //Constructor to initialize Clothing attributes and invoke the superclass constructor
    public Clothing(String productID,String productName,int numberOfAvailableItems,double price,String size,String colour){
        //Invoking the constructor of the superclass (Product) to initialize common attributes
        super(productID, productName, numberOfAvailableItems, price);
        //Initializing Clothing-specific attributes
        this.size=size;
        this.colour=colour;
    }

    //Getter method to retrieve the size of the Clothing
    public String getSize() {
        return size;
    }

    //Getter method to retrieve the color of the Clothing
    public String getColour() {
        return colour;
    }

    //Overriding the abstract method from the superclass to specify the type of product
    @Override
    public String getProductType() {
        return "Clothing";
    }
}
