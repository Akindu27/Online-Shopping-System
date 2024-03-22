import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GUILogin extends JFrame implements ActionListener{
    //GUI components
    private JTextField usernameInputField;
    private JPasswordField passwordInputField;
    private JLabel messageToDisplay;

    //Data from the main program
    private ArrayList<User> userList;
    private ArrayList<Product> productList;

    //Constructor for the login GUI
    public GUILogin(ArrayList<User> userList,ArrayList<Product> productList){
        this.userList = userList;
        this.productList = productList;

        Font headerFont = new Font("Arial", Font.BOLD, 13);
        // Set up JFrame properties
        setTitle("Login or Register");
        setSize(500, 500);
        setResizable(false);
        setVisible(true);
        setLayout(new BorderLayout());

        //Create a JPanel to hold the top section of the GUILogin with a 2x2 grid layout and specific padding
        JPanel topPanel = new JPanel(new GridLayout(2,2,25,20));
        topPanel.setBorder(new EmptyBorder(110,0,40,95));

        //Create and configure JLabels for username and password
        JLabel username = new JLabel("Username :");
        username.setFont(headerFont);
        username.setHorizontalAlignment(SwingConstants.RIGHT);

        JLabel password = new JLabel("Password :");
        password.setFont(headerFont);
        password.setHorizontalAlignment(SwingConstants.RIGHT);

        //Create text fields for user input (username and password)
        usernameInputField = new JTextField();
        passwordInputField = new JPasswordField();
        usernameInputField.setFont(headerFont);
        passwordInputField.setFont(headerFont);
        usernameInputField.setHorizontalAlignment(SwingConstants.CENTER);
        passwordInputField.setHorizontalAlignment(SwingConstants.CENTER);

        //Add labels and text fields to the top panel in a specific order
        topPanel.add(username);
        topPanel.add(usernameInputField);
        topPanel.add(password);
        topPanel.add(passwordInputField);

        //Create JButton components for login and register actions
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        //Create a JLabel for displaying messages to the user
        messageToDisplay = new JLabel("    ");
        messageToDisplay.setBorder(new EmptyBorder(20,0,0,0));
        messageToDisplay.setFont(headerFont);
        messageToDisplay.setHorizontalAlignment(SwingConstants.CENTER);

        //Create a JPanel to hold the login and register buttons in a horizontal layout
        JPanel buttonPanel = new JPanel(new GridLayout(1,2,20,20));
        buttonPanel.setBorder(new EmptyBorder(0,150,0,150));
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        //Create a JPanel to hold the button panel and the message display label
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(new EmptyBorder(0,0,150,0));
        bottomPanel.add(buttonPanel,BorderLayout.CENTER);
        bottomPanel.add(messageToDisplay,BorderLayout.SOUTH);

        // Add action listeners for the login and register buttons by registering the current instance (this)
        loginButton.addActionListener( this);
        registerButton.addActionListener( this);

        // Add the top and bottom panels to the JFrame with specific layout positions
        add(topPanel,BorderLayout.CENTER);
        add(bottomPanel,BorderLayout.SOUTH);
    }

    //ActionPerformed method to handle button clicks
    public void actionPerformed(ActionEvent e) {
        //Get user input
        String username = usernameInputField.getText();
        String password = new String(passwordInputField.getPassword()); // Convert char[] to String for password

        boolean firstPurchase ;

        //Handle login button action
        if (e.getActionCommand().equals("Login")) {
            //Check if the provided username and password match any user in the userList
            for(User user: userList){
                if(user.getUserName().equals(username) && user.getPassword().equals(password)){
                    //Check if it's the user's first purchase
                    firstPurchase = !user.isHasMadePurchase();

                    if(firstPurchase){
                        user.setHasMadePurchase(true);
                    }

                    //Save user data to file, dispose of the login frame and open the main shopping GUI
                    User.saveUsersToFile(userList);
                    this.dispose();
                    ArrayList<Product> productsOnCart = new ArrayList<>(50);
                    Map<Product, Integer> quantityOnCart = new HashMap<>();
                    ShoppingCart shoppingCart = new ShoppingCart(productsOnCart,quantityOnCart);
                    ShoppingCenterGUI frame = new ShoppingCenterGUI(productList,shoppingCart,firstPurchase);
                    frame.setTitle("Westminster Shopping Center");
                    frame.setSize(700,650);
                    frame.setMinimumSize(new Dimension(600, 400));
                    frame.setVisible(true);
                    return;
                }
                else if(user.getUserName().equals(username)){
                    //Display an error message if the password is incorrect
                    messageToDisplay.setText("Password is incorrect");
                    passwordInputField.setText("");
                    passwordInputField.setBackground(new Color(255,100,100));
                    this.repaint();
                    return;
                }
            }

            //Clear fields, set background color and display an error message for incorrect username and password
            usernameInputField.setText("");
            passwordInputField.setText("");
            usernameInputField.setBackground(new Color(255, 100, 100));
            passwordInputField.setBackground(new Color(255,100,100));
            messageToDisplay.setText("Incorrect username and password");

            this.repaint();

        } else if (e.getActionCommand().equals("Register")) {
            //Handle register button action
            boolean newUser = true;

            //Check if the username is already taken
            for(User user: userList){
                if(user.getUserName().equals(username)){
                    newUser = false;
                    break;
                }
            }

            //Register a new user if the provided username and password are valid
            if(!username.isEmpty() && !password.isEmpty() && newUser){
                userList.add(new User(username,password,false));
                usernameInputField.setBackground(new Color(100, 200, 200));
                passwordInputField.setBackground(new Color(100,200,200 ));
                messageToDisplay.setText("Registered");
            } else if (!newUser) {
                //Display an error message if the username is already taken
                usernameInputField.setBackground(new Color(255, 100, 100));
                messageToDisplay.setText("This username is already given");
            }
            else if(username.isEmpty() && password.isEmpty()){
                //Display an error message  if both username and password are empty
                messageToDisplay.setText("Please enter the username and password");
                usernameInputField.setBackground(new Color(255, 100, 100));
                passwordInputField.setBackground(new Color(255,100,100));

            } else if (password.isEmpty()) {
                //Display an error message if the password is empty
                messageToDisplay.setText("Please enter the password");
                passwordInputField.setBackground(new Color(255, 100, 100));

            }
            else {
                //Display an error message if the username is empty
                messageToDisplay.setText("Please enter the username");
                usernameInputField.setBackground(new Color(255, 100, 100));
            }
        }
    }

}