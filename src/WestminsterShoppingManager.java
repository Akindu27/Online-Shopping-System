import java.io.*;
import java.util.*;

//Class implementing the ShoppingManager interface for managing an online shopping system
public class WestminsterShoppingManager implements ShoppingManager{

    //ArrayList to store the product in the shopping system
    private ArrayList<Product> productList;
    //ArrayList to store user information, loaded from file during initialization
    ArrayList<User> userList = User.readUsersFromFile();

    //Constructor to initialize the WestminsterShoppingManager
    public WestminsterShoppingManager(){
        productList = new ArrayList<>();
    }

    //Implementation of the addProduct method from the ShoppingManager Interface
    @Override
    public void addProduct(Product product) {
        int numOfProducts = 50;
        //Checking if the number of products in the system is within the limit
        if(productList.size()< numOfProducts)
            productList.add(product);
        else
            System.out.println("Only 50 products are allowed.");
    }

    //Implementation of the deleteProduct method from the ShoppingManager interface
    @Override
    public void deleteProduct(Scanner input) {

        while (true) {
            System.out.println("Enter the product ID you want to delete: ");
            String productID = input.next();

            //Iterator for safe removal of the product from the productList
            Iterator<Product> iterator = productList.iterator();
            boolean productFound = false;

            while (iterator.hasNext()) {
                int productCount=0;
                Product product = iterator.next();
                if (product.getProductID().equals(productID)) {
                    String productType = product.getProductType();
                    iterator.remove();  // Safe removal using iterator
                    productFound = true;

                    //Counting the remaining products of the same type
                    for(Product product1: productList){
                        if(product1.getProductType().equals(productType))
                            productCount++;
                    }
                    System.out.println("\n"+productType+" product successfully deleted.");
                    System.out.println("There are "+productCount+" "+productType+" product(s) remaining.");
                    break;  // Exit the loop once the product is found and removed
                }
            }

            if (!productFound) {
                System.out.println("There is no product with that product ID.");
                continue;
            }
            break;
        }
    }

    //Implementation of the printList method from the ShoppingManager Interface
    @Override
    public void printList() {
        if(productList.isEmpty()){
            System.out.println("There is no products added. Add products to print.");
        }else {
            //Creating a sorted list based on product ID
            ArrayList<Product> sortedList = new ArrayList<>(productList);
            sortedList.sort(Comparator.comparing(Product::getProductID));       //The syntax ClassName::methodName is known as a method reference.

            //Printing product details based on their type (Electronics or Clothing)
            for(Product product : sortedList)
                if (product instanceof Electronics) {
                    //Printing details for Electronics products
                    System.out.println("Product ID: " + product.getProductID());
                    System.out.println("Product Type: Electronics");
                    System.out.println("Product Name: " + product.getProductName());
                    System.out.println("Available Items: " + product.getNumberOfAvailableItems());
                    System.out.println("Price: " + product.getPrice());
                    System.out.println("Brand : " + ((Electronics) product).getBrand());
                    System.out.println("Warranty Period: "+((Electronics) product).getWarrantyPeriod());
                    System.out.println("\n");
                } else if (product instanceof Clothing) {
                    //Printing details for Clothing products
                    System.out.println("Product ID: " + product.getProductID());
                    System.out.println("Product Type: Clothing");
                    System.out.println("Product Name: " + product.getProductName());
                    System.out.println("Available Items: " + product.getNumberOfAvailableItems());
                    System.out.println("Price: " + product.getPrice());
                    System.out.println("Size: " + ((Clothing) product).getSize());
                    System.out.println("Colour: " + ((Clothing) product).getColour());
                    System.out.println("\n");
                }
        }
    }

    //Implementation of the saveToFile method from the ShoppingManager interface
    @Override
    public void saveToFile() {
        try {
            FileWriter writer = new FileWriter("data.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            //Writing product details to the file based on their type (Electronics or Clothing)
            for (Product product:productList){
                if(product instanceof Electronics){

                    bufferedWriter.write("Electronics "+" | "+product.getProductID()+" | "+product.getProductName()+" | "+product.getNumberOfAvailableItems()
                            +" | "+product.getPrice()+" | "+((Electronics) product).getBrand()+" | "+((Electronics) product).getWarrantyPeriod() + " | ");
                    bufferedWriter.newLine();
                }else if(product instanceof Clothing){
                    bufferedWriter.write("Clothing "+" | "+product.getProductID()+" | "+product.getProductName()+" | "+product.getNumberOfAvailableItems()
                            +"|"+product.getPrice()+" | "+((Clothing) product).getSize()+" | "+((Clothing) product).getColour());
                    bufferedWriter.newLine();
                }
            }

            bufferedWriter.close();
            writer.close();
            System.out.println("Successfully saved to the file.");
        }catch (IOException e){
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    //Implementation of the readFromFile method from the ShoppingManager interface
    @Override
    public void readFromFile() {
        try (FileReader reader = new FileReader("data.txt");
             BufferedReader bufferedReader = new BufferedReader(reader)) {

            //Reading product details from the file and adding them to the productList
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String type = parts[0].trim();

                //Creating Electronics product from file data
                if (type.equals("Electronics")) {
                    String productID = parts[1].trim();
                    String productName = parts[2].trim();
                    int noOfAvailableItems = Integer.parseInt(parts[3].trim());
                    double price = Double.parseDouble(parts[4].trim());
                    String brand = parts[5].trim();
                    String warrantyPeriod = parts[6].trim();
                    Product product = new Electronics(productID, productName, noOfAvailableItems, price, brand, warrantyPeriod);
                    productList.add(product);


                    //Creating Clothing product from file data
                }else if(type.equals("Clothing")){
                    String productID = parts[1].trim();
                    String productName = parts[2].trim();
                    int noOfAvailableItems = Integer.parseInt(parts[3].trim());
                    double price = Double.parseDouble(parts[4].trim());
                    String size = parts[5].trim();
                    String colour = parts[6].trim();
                    Product product = new Clothing(productID,productName,noOfAvailableItems,price,size,colour);
                    productList.add(product);

                } else {
                    System.out.println("Invalid product type: " + type);
                }
            }
            System.out.println("File loaded successfully.");

        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    //Implementation of the openGUI method from the ShoppingManager interface
    @Override
    public void openGUI() {
        //Creating and configuring a GUI for user login or registration
        GUILogin loginGUI = new GUILogin(userList,productList);
        loginGUI.setTitle("Login or Register");
        loginGUI.setSize(500, 500);
        loginGUI.setResizable(false);
        loginGUI.setVisible(true);
    }

    //Implementation of the saveUSerToFile method from the ShoppingManager interface
    @Override
    public void saveUserToFile() {
        //Saving user information to a file using the User class
        User.saveUsersToFile(userList);
    }

    //Implementation of the runMenu from the ShoppingManager interface
    @Override
    public boolean runMenu() {

        boolean exit = false;
        int choice , numberOfAvailableItems;
        double price;
        String type,size,colour,warrantyPeriod ;
        boolean sameProductIDExist = false;
        ArrayList<String> periodArray= new ArrayList<>(Arrays.asList("1m", "2m", "3m", "4m", "6m", "12m", "24m", "36m", "48m"));

        Scanner input = new Scanner(System.in);

        //Displaying the main menu options
        System.out.println("\nWelcome to Online Shopping System!");
        System.out.println("""
                --------------------------------------------------------
                                        Main Menu
                --------------------------------------------------------
                
                    1 : Add a new Product to the System
                    2 : Delete a Product from the System
                    3 : Print the list of the products in the System
                    4 : Save Products to a file
                    5 : Open Graphical User Interface (GUI)
                    6 : Exit from the System
                """);

        while (true) {
            try {
                //Taking user input for menu choice
                System.out.println("Select an option: ");

                choice = input.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wrong Input, Please enter a number from the above menu.");
                input.next();
            }
        }

        //Handling user's choice from the main menu
        switch (choice){
            case 1:
                while (true){
                    //Adding a new product to the system based on user input
                    System.out.println("What type of Product do you want to add (electronics(E)/clothing(C)): ");
                    type = input.next().toLowerCase().trim();
                    if(type.equals("electronics") || type.equals("e")){
                        //Adding an Electronics product

                        System.out.println("Enter Product ID: ");
                        String productID = input.next();
                        sameProductIDExist = false;
                        for (Product existingProduct : productList) {
                            //Checking whether the same product ID exist in the productList
                            if (existingProduct.getProductID().equals(productID)) {
                                sameProductIDExist = true;
                                break;
                            }
                        }
                        if (sameProductIDExist) {
                            System.out.println("Product with the same ID already exists. Please enter a different ID.\n");
                            continue;
                        }

                        input.nextLine();
                        System.out.println("Enter Product Name: ");
                        String productName = input.nextLine();

                        while (true){
                            try{
                                System.out.println("Enter number of Available items: ");
                                numberOfAvailableItems = input.nextInt();
                                break;
                            }catch (Exception e){
                                System.out.println("Invalid Input, Available items should be a number.");
                                input.next();
                            }
                        }

                        while (true){
                            try{
                                System.out.println("Enter price of the Product: ");
                                price = input.nextDouble();
                                break;
                            }catch (Exception e){
                                System.out.println("Invalid Input!, Price should be a number.");
                                input.next();
                            }
                        }

                        System.out.println("Enter the brand of the Product: ");
                        String brand = input.next();

                        while (true){

                            System.out.println("Select warranty Period from these [1m,2m,3m,4m,6m,12m,24m,36m,48m]");
                            System.out.println("Warranty Period: ");
                            warrantyPeriod = input.next().toLowerCase().trim();
                            if(periodArray.contains(warrantyPeriod)){
                                break;
                            }else{
                                System.out.println("Invalid Input entered!, Please enter a valid warranty period");
                            }
                        }

                        Product product = new Electronics(productID,productName,numberOfAvailableItems,price,brand,warrantyPeriod);
                        addProduct(product);
                        break;

                    }else if(type.equals("clothing") || type.equals("c")){
                        //Adding an Clothing product
                        System.out.println("Enter Product ID: ");
                        String productID = input.next();
                        sameProductIDExist = false;

                        for (Product existingProduct : productList) {
                            //Checking whether the same product exist in the productList
                            if (existingProduct.getProductID().equals(productID)) {
                                sameProductIDExist = true;
                                break;
                            }
                        }

                        if (sameProductIDExist) {
                            System.out.println("Product with the same ID already exists. Please enter a different ID.\n");
                            continue;
                        }

                        input.nextLine();
                        System.out.println("Enter Product Name: ");
                        String productName = input.nextLine();

                        while (true){
                            try{
                                System.out.println("Enter number of Available items: ");
                                numberOfAvailableItems = input.nextInt();
                                break;
                            }catch (Exception e){
                                System.out.println("Invalid Input, Available items should be a number.");
                                input.next();
                            }
                        }

                        while (true){
                            try{
                                System.out.println("Enter price of the Product: ");
                                price = input.nextDouble();
                                break;
                            }catch (Exception e){
                                System.out.println("Invalid Input, Price should be a number.");
                                input.next();
                            }
                        }

                        while (true){
                            System.out.println("Enter the size of the Clothing(XS/S/M/L/XL): ");
                            size = input.next().toUpperCase().trim();

                            if(size.equals("XS") || size.equals("S") || size.equals("M") || size.equals("L") || size.equals("XL")){

                                System.out.println("Enter the colour of the Clothing: ");
                                colour = input.next();
                                break;
                            }else {
                                System.out.println("Wrong Input, Please enter your choice from the given sizes.");
                            }
                        }
                        Product product = new Clothing(productID,productName,numberOfAvailableItems,price,size,colour);
                        addProduct(product);
                        break;
                    }else{
                        System.out.println("Wrong input, please enter again.");
                    }
                }
                break;

            case 2:
                //Deleting a product from the system based on user input
                deleteProduct(input);
                break;

            case 3:
                //Printing the list of products in the system
                printList();
                break;

            case 4:
                //Saving the product information to a file
                saveToFile();
                break;

            case 5:
                //Opening the graphical user interface (GUI) for user login or registration
                openGUI();
                break;

            case 6:
                //Saving user information to a file and exiting the system
                saveUserToFile();
                exit = true;
                System.out.println("System termination successful, Thank You! ");
                break;

            default:
                System.out.println("Invalid input , Please enter a number from menu");
        }
        return exit;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }
}
