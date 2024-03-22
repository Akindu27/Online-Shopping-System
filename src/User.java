import java.util.ArrayList;
import java.io.*;

public class User {
    //Fields to store user information
    private String userName;
    private String password;
    private boolean hasMadePurchase;

    //Constructor to initialize user object
    public User(String userName,String password,boolean hasMadePurchase){
        this.userName=userName;
        this.password=password;
        this.hasMadePurchase=hasMadePurchase;
    }

    //Getter method for userName
    public String getUserName() {
        return userName;
    }

    //Getter method for password
    public String getPassword() {
        return password;
    }

    //Getter method for hasMadePurchase
    public boolean isHasMadePurchase(){
        return hasMadePurchase;
    }

    //Setter method for hasMadePurchase
    public void setHasMadePurchase(boolean hasMadePurchase){
        this.hasMadePurchase=hasMadePurchase;
    }

    //Method to save user information to a file
    public static void saveUsersToFile(ArrayList<User> userList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"))) {
            //Writing each user's information to the file
            for (User user : userList) {
                writer.write(user.getUserName() + ":" + user.getPassword()+ ":" +user.hasMadePurchase);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing user file: " + e.getMessage());
        }
    }

    //Method to read user information from a file
    public static ArrayList<User> readUsersFromFile() {
        ArrayList<User> userList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            //Reading each line from the file
            while ((line = reader.readLine()) != null) {
                //Splitting each line into parts using ":"
                String[] parts = line.split(":");
                //Extracting user information from parts
                String userName = parts[0].trim();
                String password = parts[1].trim();
                boolean hasMadePurchase = Boolean.parseBoolean(parts[2].trim());
                //Creating a new User object and adding it to the userList
                userList.add(new User(userName, password,hasMadePurchase));
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading user file: " + e.getMessage());
        }
        return userList;
    }

}
