import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;

public class ShoppingCenterGUI extends JFrame{

    public ShoppingCenterGUI(ArrayList<Product> productList,ShoppingCart shoppingCart,boolean firstPurchase){
        //Initialization of Swing components and layout
        JPanel selectProductPanel,selectProductAndCartBtn,topPanel,bottomPanel;
        JLabel selectProductCategoryLbl;
        String[] categories = {"All","Electronics","Clothing"};
        JComboBox<String> selectProductCategoryComboBox;
        JButton cartBtn,addToCartBtn,sortItemBtn;
        Font headerFont = new Font("Calibre", Font.BOLD, 12);
        Font bodyFont = new Font("Arial", Font.PLAIN, 12);

        //Set up the main frame layout
        this.setLayout(new BorderLayout());
        //Create and configure the product selection panel
        selectProductPanel = new JPanel();
        selectProductPanel.setLayout(new FlowLayout(FlowLayout.LEFT,20,30));

        // components for the product selection panel
        selectProductCategoryLbl = new JLabel("  Select Product Category");
        selectProductCategoryComboBox = new JComboBox<>(categories);
        selectProductCategoryComboBox.setPreferredSize(new Dimension(120,20));
        sortItemBtn = new JButton("Sort Items");

        // add components to product selection panel
        selectProductPanel.add(selectProductCategoryLbl);
        selectProductPanel.add(selectProductCategoryComboBox);
        selectProductPanel.add(sortItemBtn);

        //Create and configure the product selection and cart button panel
        selectProductAndCartBtn = new JPanel();
        selectProductAndCartBtn.setLayout(new BorderLayout());

        // components for product selection and cart button panel
        JPanel cartButtonPanel = new JPanel();
        cartButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        cartBtn = new JButton("Shopping Cart");
        cartBtn.setPreferredSize(new Dimension(120,30));
        cartButtonPanel.add(cartBtn);

        // add components to product selection and cart button panel
        selectProductAndCartBtn.add(selectProductPanel,BorderLayout.CENTER);
        selectProductAndCartBtn.add(cartButtonPanel,BorderLayout.EAST);

        //Create and configure topPanel
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        // components for top panel
        ProductTableModel tableModel = new ProductTableModel(productList);
        JTable productsTable = new JTable(tableModel);

        // set attributes for table header
        JTableHeader tableHeader = productsTable.getTableHeader();
        tableHeader.setReorderingAllowed(false); // Disable reordering
        tableHeader.setFont(headerFont); // Set font to bold
        tableHeader.setPreferredSize(new Dimension(tableHeader.getWidth(), 35));

        // custom renderer for each column of the table
        for (int i = 0; i < productsTable.getColumnCount(); i++) {
            productsTable.getColumnModel().getColumn(i).setCellRenderer(new CustomRowRenderer());
        }

        productsTable.setRowHeight(30);
        productsTable.getColumnModel().getColumn(4).setMinWidth(150);

        //Create and configure the table panel
        JPanel tablePanel = new JPanel();
        tablePanel.setBorder(new EmptyBorder(40,0,10,0));
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(new JScrollPane(productsTable),BorderLayout.CENTER);

        // add components to top panel
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        topPanel.add(selectProductAndCartBtn,BorderLayout.NORTH);
        topPanel.add(tablePanel,BorderLayout.CENTER);

        //Create and configure bottomPanel
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        // components for bottom panel
        JPanel productDetails = new JPanel();
        productDetails.setLayout(new GridLayout(7,1,10,10));
        productDetails.setBorder(new EmptyBorder(0, 30, 30, 10));

        //JLabel for displaying the header of the selected product details
        JLabel productDetailsHeader = new JLabel("Selected Product - Details");

        //Individual JLabels for displaying specific details of the selected product
        JLabel idLbl,categoryLbl,nameLbl,brandLbl,warrantyPeriodLbl,availableItemsLbl;
        idLbl = new JLabel("");
        categoryLbl = new JLabel("");
        nameLbl = new JLabel("");
        brandLbl = new JLabel("");
        warrantyPeriodLbl = new JLabel("");
        availableItemsLbl = new JLabel("");

        //Set font for the individual JLabels to the bodyFont
        idLbl.setFont(bodyFont);
        categoryLbl.setFont(bodyFont);
        nameLbl.setFont(bodyFont);
        brandLbl.setFont(bodyFont);
        warrantyPeriodLbl.setFont(bodyFont);
        availableItemsLbl.setFont(bodyFont);

        //Add the individual JLabels to the productDetails JPanel
        productDetails.add(productDetailsHeader);
        productDetails.add(idLbl);
        productDetails.add(categoryLbl);
        productDetails.add(nameLbl);
        productDetails.add(brandLbl);
        productDetails.add(warrantyPeriodLbl);
        productDetails.add(availableItemsLbl);

        //JPanel for the "Add to shopping Cart" button
        JPanel addToCartPanel = new JPanel();
        addToCartPanel.setLayout(new FlowLayout());

        //JButton for adding the selected product to the shopping cart
        addToCartBtn = new JButton("Add to Shopping Cart");

        //Add the Add to shopping cart button to the addTOCartPanel
        addToCartPanel.add(addToCartBtn);

        // add components to bottom panel
        bottomPanel.add(productDetails,BorderLayout.CENTER);
        bottomPanel.add(addToCartPanel,BorderLayout.SOUTH);

        //add panels to the main frame
        this.add(topPanel,BorderLayout.CENTER);
        this.add(bottomPanel,BorderLayout.SOUTH);

        // product table selection model
        productsTable.getSelectionModel().addListSelectionListener(new ProductTableListener(productsTable,
                productList,idLbl,categoryLbl,nameLbl,brandLbl,warrantyPeriodLbl,availableItemsLbl));

        // sorting instance to sort table columns
        TableRowSorter<ProductTableModel> sorter = new TableRowSorter<>(tableModel);
        productsTable.setRowSorter(sorter);

        // Action Listener to filtering products on products table
        selectProductCategoryComboBox.addActionListener(new ProductCategoryHandler
                (selectProductCategoryComboBox,productsTable,sorter));

        //Creating a TableModel Cart for managing the shopping cart data
        TableModelCart cartTable = new TableModelCart(shoppingCart);

        //Creating the GUI for displaying the shopping cart
        ShoppingCartGUI shoppingCartGUI = new ShoppingCartGUI(cartTable);
        shoppingCartGUI.setTitle("Shopping Cart");
        shoppingCartGUI.setSize(700, 450);
        shoppingCartGUI.setVisible(false);

        //Adding an action listener to addToCart button
        addToCartBtn.addActionListener(new AddToCartBtnHandler(productsTable,productList,
                cartTable,shoppingCartGUI,firstPurchase));

        //Adding action listener to cart button
        cartBtn.addActionListener(new CartBtnHandler(shoppingCartGUI));

        //Adding an action listener to the sort items button
        sortItemBtn.addActionListener(new SortBtnHandler(tableModel,productsTable,selectProductCategoryComboBox));

    }
}
