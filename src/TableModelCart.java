import javax.swing.table.AbstractTableModel;

public class TableModelCart extends AbstractTableModel {

    private ShoppingCart shoppingCart;
    private String[] namesOfColumns = {"Product","Quantity","Price"};

    //Constructor to initialize the TableModelCart with a shopping cart instance
    public TableModelCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    //Get the number of rows in the table (number of products in the shopping cart)
    @Override
    public int getRowCount() {
        return shoppingCart.getCartList().size();
    }

    //Get the number of columns in the table
    @Override
    public int getColumnCount() {
        return namesOfColumns.length;
    }

    //Retrieve the value at specific cell in the table based on the row and column indices
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //Get the selected product from the shopping cart
        Product selectedProduct = shoppingCart.getCartList().get(rowIndex);

        //Switch statement to determine which column is being requested
        switch (columnIndex) {
            //Column 0: Product details
            case 0 -> {
                String details = selectedProduct.getProductID()+", "+selectedProduct.getProductName()+", ";

                //Additional details based on the product type (Clothing or Electronics)
                if(selectedProduct.getProductType().equals("Clothing")){
                    Clothing selectedClothingProduct = (Clothing)selectedProduct;
                    details += selectedClothingProduct.getSize()+", "+selectedClothingProduct.getColour();
                }
                else {
                    Electronics selectedElectronicsProduct = (Electronics) selectedProduct;
                    details += selectedElectronicsProduct.getBrand()+", "+
                            selectedElectronicsProduct.getWarrantyPeriod().replace("m"," months");
                }
                return details;
            }
            //Column 1: Quantity of the product in the shopping cart
            case 1 -> {return shoppingCart.getCartQuantity().get(selectedProduct);}
            //Column 2: Total price of the product (quantity * price)
            case 2 -> {
                double price = selectedProduct.getPrice()*shoppingCart.getCartQuantity().get(selectedProduct);
                price = Math.round(price * 100.0) / 100.0;
                return price + "£";
            }

            //Default case: return null for any other column index
            default -> {return null;}
        }
    }

    //Get the column name for a specific column index
    @Override
    public String getColumnName(int col) {
        return namesOfColumns[col];
    }

    //Get the associated ShoppingCart instance
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
}
