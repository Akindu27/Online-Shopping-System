import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
public class ProductTableModel extends AbstractTableModel{
    private String[] columnNames = {"Product ID","Name","Category","Price(£)","Info"};
    private ArrayList<Product> productList;

    //Constructor to initialize the ProductTableModel with a list of products
    public ProductTableModel(ArrayList<Product> productList){
        this.productList = productList;
    }


    //Return the number of rows in the table
    @Override
    public int getRowCount() {
        return productList.size();
    }

    //Return the number of columns in the table
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    //Return the value at a specific cell in the table based on the row and column indices
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //Get the product at the specified row index
        Product selectedProduct = productList.get(rowIndex);

        //Switch statement to determine which column is being requested
        switch (columnIndex) {
            //Column 0: Product ID
            case 0 -> {return selectedProduct.getProductID();}
            //Column 1: Product Name
            case 1 -> {return selectedProduct.getProductName();}
            //Column 2: Category(Clothing or Electronics)
            case 2 -> {
                if (selectedProduct.getProductType().equals("Clothing")) {
                    return "Clothing";
                } else return "Electronics";
            }
            //Column 3: Price
            case 3 -> {return selectedProduct.getPrice();}
            //Column 4: Additional Information based on the product type
            case 4 -> {
                if(selectedProduct.getProductType().equals("Clothing")){
                    //For Clothing products, display size and color
                    Clothing selectedClothingProduct = (Clothing)selectedProduct;
                    return selectedClothingProduct.getSize()+", "+selectedClothingProduct.getColour();
                }
                else{
                    //For Electronics products, display brand and warranty period
                    Electronics selectedElectronicsProduct = (Electronics) selectedProduct;
                    return selectedElectronicsProduct.getBrand()+ ", " +
                            selectedElectronicsProduct.getWarrantyPeriod().replace("m"," months warranty");
                }
            }
            //Default case: return null for any other column index
            default -> {return null;}
        }
    }

    // needed to show column names at a specific index
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    //Get the corresponding Product object for a given row index
    public Product getRowObject(int Row){
        for(Product product: productList){
            if(this.getValueAt(Row,0).equals(product.getProductID())){
                return product;
            }
        }
        return null;
    }

}
