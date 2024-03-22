//Class representing a specific type of product: Electronics, extending the Product Class
public class Electronics extends Product{

    //Additional fields specific to Electronics
    private String brand;

    private String warrantyPeriod;

    //Constructor to initialize Electronics attributes and invoke the superclass constructor
    public Electronics(String productID,String productName,int numberOfAvailableItems,double price,String brand,String warrantyPeriod){
        //Invoking the constructor of the superclass (Product) to initialize common attributes
        super(productID, productName, numberOfAvailableItems, price);
        //Initializing Electronics-specific attributes
        this.brand=brand;
        this.warrantyPeriod=warrantyPeriod;
    }

    //Getter method to retrieve the brand of the electronics product
    public String getBrand() {
        return brand;
    }

    //Getter method to retrieve the warranty period of the electronics product
    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }

    //Overriding the abstract method from the superclass to specify the type of product
    @Override
    public String getProductType() {
        return "Electronics";
    }
}
