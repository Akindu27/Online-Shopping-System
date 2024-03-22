import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.ArrayList;

//Listener for handling product table selection events and updating product details
public class ProductTableListener implements ListSelectionListener{

    private JTable productsTable;
    private ArrayList<Product> productList;
    private JLabel idLbl, categoryLbl, nameLbl, brandLbl, warrantyPeriodLbl, availabileItemsLbl;

    //Constructs a ProductTableListener with the necessary components
    public ProductTableListener(JTable productsTable, ArrayList<Product> productList, JLabel idLbl, JLabel categoryLbl,
                                JLabel nameLbl, JLabel brandLbl, JLabel warrantyPeriodLbl, JLabel availabileItemsLbl){
        this.productsTable = productsTable;
        this.productList = productList;
        this.idLbl = idLbl;
        this.categoryLbl = categoryLbl;
        this.nameLbl = nameLbl;
        this.brandLbl = brandLbl;
        this.warrantyPeriodLbl = warrantyPeriodLbl;
        this.availabileItemsLbl = availabileItemsLbl;
    }

    //Invoked when the selection of the productsTable changes
    @Override
    public void valueChanged(ListSelectionEvent event) {
        if (!event.getValueIsAdjusting()) { // Check if the selection process has finished
            int selectedRow = productsTable.getSelectedRow();
            if (selectedRow != -1) { // Check if any row is selected
                String selectedProductId = productsTable.getValueAt(selectedRow,0).toString();
                Product selectedProduct = null;
                boolean selectedItemIsNull = true;

                //Find the selected product in the productList
                for(Product checker : productList){
                    if (checker.getProductID().equals(selectedProductId)) {
                        selectedProduct = checker;
                        selectedItemIsNull = false;
                        break;
                    }
                }
                if(!selectedItemIsNull) {
                    //Update the product details labels with information from the selected product
                    idLbl.setText("Product Id: "+ selectedProduct.getProductID());
                    nameLbl.setText("Name: "+ selectedProduct.getProductName());
                    availabileItemsLbl.setText("Items Available: "+ selectedProduct.getNumberOfAvailableItems());

                    //Check the type of the selected product
                    if (selectedProduct.getProductType().equals("Clothing")) {
                        Clothing selectedClothe = (Clothing)selectedProduct;
                        categoryLbl.setText("Category: Clothing");
                        brandLbl.setText("Size: "+ selectedClothe.getSize());
                        warrantyPeriodLbl.setText("Colour: "+ selectedClothe.getColour());
                    } else {
                        Electronics selectedElectronic = (Electronics)selectedProduct;
                        categoryLbl.setText("Category: Electronics");
                        brandLbl.setText("Brand: "+selectedElectronic.getBrand());
                        warrantyPeriodLbl.setText("Warranty Period: "+selectedElectronic.getWarrantyPeriod().replace("m"," months"));

                    }
                }
            }

        }
    }
}
