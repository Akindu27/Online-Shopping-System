import java.util.Scanner;

//Interface defining the contract for managing shopping operations
public interface ShoppingManager {

    //Method to add a product to the shopping manager
    void addProduct(Product product);

    //Method to delete a product from the shopping manager using user input (Scanner parameter)
    void deleteProduct(Scanner input);

    //Method to print the list int the shopping manager
    void printList();

    //Method to save the current state of the shopping manager in a file
    void saveToFile();

    //Method to read the state of the shopping manager from a file
    void readFromFile();

    //Method to run the main menu and return a boolean indicating whether to exit the program
    boolean runMenu();

    //Method to open the graphical user interface (GUI) for the shopping manager
    void openGUI();

    //Method to save user-related information to a file
    void saveUserToFile();
}
