import java.io.File;

//Main class for the WestminsterShoppingManager application
public class Main {
    public static void main(String[] args)
    {
        //Creating an instance of WestminsterShoppingManager
        WestminsterShoppingManager system = new WestminsterShoppingManager();

        //Flag to control the main loop and exit the program
        boolean exit =false;

        //Creating a File object to represent the data file
        File dataFile = new File("data.txt");

        //Checking if the data file already exists
        if(dataFile.exists()){
            //If the file exists, read data from it to initialize the system
            system.readFromFile();
        }

        //Main program loop
        while (!exit){
            //Running the menu and updating the exit flag based on user input
            exit = system.runMenu();
        }
    }
}