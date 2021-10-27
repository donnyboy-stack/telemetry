package Panel.Error;

import Data.Type.DataTypeInterface;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;

public class ErrorPanel extends AbstractErrorPanel {
    final public static int ROW_HEIGHT = 30;

    protected JTable table;
    protected DefaultTableModel model;

    public ErrorPanel () {
        TitledBorder border = BorderFactory.createTitledBorder(" Error Data ");

        setBorder(border);

        /*
         * Need a layout for the tables to show up
         */
        setLayout(new BorderLayout());

        /*
         * Create the table to display the data
         */
        createTable();
    }

    public void refresh () {
        int row = model.getRowCount() - 1;

        for (; row >= 0; row--) {
            model.removeRow(row);
        }

        addRows();

        model.fireTableDataChanged();
    }

    protected void createTable(){
        removeAll();

        model = new DefaultTableModel(new String[] {
                "Error", "Value"
        }, 0);

        table = new JTable(model);

        table.setRowHeight(ROW_HEIGHT);
        TableColumn errors  = table.getColumnModel().getColumn(1);

        errors.setCellRenderer(new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
                Component c = super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
                c.setBackground(value.equals(0.0) ? Color.GREEN : Color.RED);
                return c;
            }
        });

        addRows();

        /*
         * Create the scroll pane without a border
         */
        JScrollPane scrollable = new JScrollPane(table);

        scrollable.setBorder(BorderFactory.createEmptyBorder());

        /*
         * Add the scroll pane with the table to the panel
         */
        add(scrollable, BorderLayout.CENTER);
    }

    protected void addRows () {
        if (types == null)
            return;

        for (DataTypeInterface type : types.values()) {
            if (type.isEnabled()) {
                model.addRow(new Object[] {
                        type.getDisplayName(),
                        type.getCurrentValue()
                });
            }
        }
    }
}
