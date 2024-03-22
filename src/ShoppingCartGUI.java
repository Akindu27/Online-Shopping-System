import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class ShoppingCartGUI extends JFrame{

    //Labels for displaying various prices in the shopping cart
    JLabel totalPriceLbl, firstPurchaseDiscountPriceLbl, threeItemsDiscountPriceLbl, finalPriceLbl;

    public ShoppingCartGUI(TableModelCart cartTableModel){
        //Font settings for the labels
        Font bodyFont = new Font("Arial", Font.PLAIN, 12);
        Font headerFont = new Font("Calibre", Font.BOLD, 12);

        //Set layout for the frame
        this.setLayout(new BorderLayout());

        //Create a JTable for the shopping cart
        JTable cartTable = new JTable(cartTableModel);

        // Add the cartTable to a JScrollPane and add it to the frame
        JScrollPane scrollPane = new JScrollPane(cartTable);

        JPanel tablePanel = new JPanel();
        tablePanel.setBorder(new EmptyBorder(40,20,10,20));
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(scrollPane,BorderLayout.CENTER);

        //Set cell renderer for center alignment of certain columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i=0; i<3; i++){
            cartTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        cartTable.setRowHeight(30);
        cartTable.getColumnModel().getColumn(0).setMinWidth(150);

        // set header attributes for the cart table
        JTableHeader tableHeader = cartTable.getTableHeader();
        tableHeader.setReorderingAllowed(false); // Disable reordering
        tableHeader.setFont(headerFont); // Set font to bold
        tableHeader.setPreferredSize(new Dimension(tableHeader.getWidth(), 35));

        //Create a bottom panel to display prices and checkout button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(4,1,0,10));
        bottomPanel.setBorder(new EmptyBorder(15,20,35,100));

        //Panels for each row in the bottom panel
        JPanel totalPanel,firstPurchasePanel,threeItemsPanel,finalTotalPanel;

        totalPanel = new JPanel(new BorderLayout());
        firstPurchasePanel = new JPanel(new BorderLayout());
        threeItemsPanel = new JPanel(new BorderLayout());
        finalTotalPanel = new JPanel(new BorderLayout());

        // labels for shopping cart purchase section
        JLabel totalLabel,firstPurchaseLabel, threeItemsLabel,finalTotalLabel;

        // labels for prices description
        totalLabel = new JLabel("Total      ");
        firstPurchaseLabel = new JLabel("First Purchase Discount (10%)      ");
        threeItemsLabel = new JLabel("Three Items in same Category (20%)      ");
        finalTotalLabel = new JLabel("Final Total      ");

        totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        firstPurchaseLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        threeItemsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        finalTotalLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // labels for prices values
        totalPriceLbl = new JLabel("0 £");
        firstPurchaseDiscountPriceLbl = new JLabel("-0 £");
        threeItemsDiscountPriceLbl = new JLabel("-0 £");
        finalPriceLbl = new JLabel("0 £");

        //Set fonts for prices values
        totalLabel.setFont(bodyFont);
        firstPurchaseLabel.setFont(bodyFont);
        threeItemsLabel.setFont(bodyFont);
        totalPriceLbl.setFont(bodyFont);
        firstPurchaseDiscountPriceLbl.setFont(bodyFont);
        threeItemsDiscountPriceLbl.setFont(bodyFont);

        //Set preferred size for price labels
        Dimension priceLabelsSize = new Dimension(100,20);
        totalPriceLbl.setPreferredSize(priceLabelsSize);
        firstPurchaseDiscountPriceLbl.setPreferredSize(priceLabelsSize);
        threeItemsDiscountPriceLbl.setPreferredSize(priceLabelsSize);
        finalPriceLbl.setPreferredSize(priceLabelsSize);

        //Add components to the panels
        totalPanel.add(totalLabel,BorderLayout.CENTER);
        totalPanel.add(totalPriceLbl,BorderLayout.EAST);

        firstPurchasePanel.add(firstPurchaseLabel,BorderLayout.CENTER);
        firstPurchasePanel.add(firstPurchaseDiscountPriceLbl,BorderLayout.EAST);
        threeItemsPanel.add(threeItemsLabel,BorderLayout.CENTER);
        threeItemsPanel.add(threeItemsDiscountPriceLbl,BorderLayout.EAST);
        finalTotalPanel.add(finalTotalLabel,BorderLayout.CENTER);
        finalTotalPanel.add(finalPriceLbl,BorderLayout.EAST);

        bottomPanel.add(totalPanel);
        bottomPanel.add(firstPurchasePanel);
        bottomPanel.add(threeItemsPanel);
        bottomPanel.add(finalTotalPanel);

        //Add panels to the frame
        add(tablePanel,BorderLayout.CENTER);
        add(bottomPanel,BorderLayout.SOUTH);
    }

    public void updatePrices(double totalPrice,ShoppingCart shoppingCart,boolean firstPurchase){
        //Initialize discounts
        double discount1 = 0;
        double discount2 = shoppingCart.getThreeItemsDiscount();

        //Set the total price label with rounded total price
        this.totalPriceLbl.setText((Math.round(totalPrice* 100.0) / 100.0) +" £");

        //Apply first purchase discount if applicable
        if(firstPurchase) {
            discount1 = Math.round((totalPrice*0.1) * 100.0) / 100.0;
            this.firstPurchaseDiscountPriceLbl.setText("-" + discount1 + " £");
        }

        //Set three items discount label
        this.threeItemsDiscountPriceLbl.setText("-"+ discount2 +" £");

        //Calculate the final price after applying discounts
        totalPrice = totalPrice-(discount1+discount2);

        //Set the final price label with rounded final price
        this.finalPriceLbl.setText((Math.round(totalPrice* 100.0) / 100.0) +" £");
    }
}
