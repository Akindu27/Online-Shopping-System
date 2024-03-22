import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;

//Custom cell renderer for rendering table cells based on product availability
public class CustomRowRenderer extends DefaultTableCellRenderer{

    //Retrieve the custom rendering component for a table cell
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellRenderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        //Convert view row index to model row index
        TableModel model = table.getModel();
        int modelRow = table.convertRowIndexToModel(row);

        // Retrieve the product from the table model based on the row index
        ProductTableModel productModel = (ProductTableModel) model;
        Product product = productModel.getRowObject(modelRow);

        // Check if the product's availability is less than 3
        if (product != null && product.getNumberOfAvailableItems() < 3) {
            cellRenderer.setForeground(Color.RED); // Set background color to red
        } else {
            cellRenderer.setForeground(Color.BLACK);
        }

        //Center align the text in the cell
        this.setHorizontalAlignment(JLabel.CENTER);

        return cellRenderer;
    }

}
