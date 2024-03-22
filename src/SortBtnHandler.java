import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//ActionListener implementation for handling sorting of products
public class SortBtnHandler implements ActionListener {
    ProductTableModel tableModel;
    JTable productsTable;
    JComboBox<String> selectProductCategoryComboBox;

    //Constructor for SortBtnHandler
    public SortBtnHandler(ProductTableModel tableModel,JTable productsTable,JComboBox<String> selectProductCategoryComboBox){
        this.tableModel=tableModel;
        this.productsTable =productsTable;
        this.selectProductCategoryComboBox =selectProductCategoryComboBox;
    }

    //Handles the action event triggered by clicking the sort button
    @Override
    public void actionPerformed(ActionEvent event) {
        // sorting instance to sort table columns
        TableRowSorter<ProductTableModel> sorter = new TableRowSorter<>(tableModel);
        productsTable.setRowSorter(sorter);

        // Action Listener to filtering products on products table
        selectProductCategoryComboBox.addActionListener(new ProductCategoryHandler
                (selectProductCategoryComboBox,productsTable,sorter));

        //Set default sorting by product ID in ascending order
        sorter.setSortKeys(java.util.List.of(new RowSorter.SortKey(0,SortOrder.ASCENDING)));
    }
}
