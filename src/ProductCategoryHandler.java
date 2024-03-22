import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//ActionListener implementation for handling changes in the selected product category
public class ProductCategoryHandler implements ActionListener{

    JComboBox<String> selectProductCategoryComboBox;
    JTable productsTable;
    TableRowSorter<ProductTableModel> sorter;

    //Constructor for ProductCategoryHandler
    public ProductCategoryHandler(JComboBox<String> selectProductCategoryComboBox,JTable productsTable,TableRowSorter<ProductTableModel> sorter){
        this.selectProductCategoryComboBox = selectProductCategoryComboBox;
        this.productsTable = productsTable;
        this.sorter = sorter;
    }

    //Handles the action event triggered by a change in the selected product category
    @Override
    public void actionPerformed(ActionEvent event) {
        int selectedIndex = selectProductCategoryComboBox.getSelectedIndex();
        //Apply row filter based on the selected product category
        if(selectedIndex == 1){
            sorter.setRowFilter(RowFilter.regexFilter("^[E]", 2)); // Hide clothing items
        } else if (selectedIndex == 2) {
            sorter.setRowFilter(RowFilter.regexFilter("^[C]",2)); // Hide electronics items
        }
        else sorter.setRowFilter(null); //Show all items when "All" is selected
    }
}
