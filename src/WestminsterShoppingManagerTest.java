import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WestminsterShoppingManagerTest {

    @Test
    void addProduct() {
        // Create an instance of WestminsterShoppingManager
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

        // Create a sample product for testing
        Product sampleProduct = new Electronics("E001", "Test Product", 10, 99.99, "Test Brand", "12m");

        // Add the product to the shopping manager
        shoppingManager.addProduct(sampleProduct);

        // Check if the product was added successfully
        assertTrue(shoppingManager.getProductList().contains(sampleProduct));
    }

    @Test
    void deleteProduct() {
        // Create an instance of WestminsterShoppingManager
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

        // Create a sample product for testing
        Product sampleProduct = new Electronics("E001", "Test Product", 10, 99.99, "Test Brand", "12m");

        // Add the product to the shopping manager
        shoppingManager.addProduct(sampleProduct);

        // Delete the product
        shoppingManager.deleteProduct(new java.util.Scanner("E001"));

        // Check if the product was deleted successfully
        assertFalse(shoppingManager.getProductList().contains(sampleProduct));
    }

    @Test
    void printList() {
        // Create an instance of WestminsterShoppingManager
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

        // Create a sample product for testing
        Product sampleProduct = new Electronics("E001", "Test Product", 10, 99.99, "Test Brand", "12m");

        // Add the product to the shopping manager
        shoppingManager.addProduct(sampleProduct);

        // Redirect System.out to capture printed output
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        // Print the list
        shoppingManager.printList();

        // Reset System.out
        System.setOut(System.out);

        // Check if the printed output contains expected information
        assertTrue(outContent.toString().contains("Product ID: E001"));
    }

    @Test
    void saveToFile() {
        // Create an instance of WestminsterShoppingManager
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

        // Create a sample product for testing
        Product sampleProduct = new Electronics("E001", "Laptop", 10, 999.99, "Dell", "12m");

        // Add the product to the shopping manager
        shoppingManager.addProduct(sampleProduct);

        // Redirect System.out to capture printed output
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        // Save to file
        shoppingManager.saveToFile();

        // Reset System.out
        System.setOut(System.out);

        // Check if the printed output contains expected information
        assertTrue(outContent.toString().contains("Successfully saved to the file."));
    }

    @Test
    void readFromFile() {
        // Create an instance of WestminsterShoppingManager
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

        // Redirect System.out to capture printed output
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        // Read from file
        shoppingManager.readFromFile();

        // Reset System.out
        System.setOut(System.out);

        // Check if the printed output contains expected information
        assertTrue(outContent.toString().contains("File loaded successfully."));
    }

}