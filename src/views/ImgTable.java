package views;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ImgTable extends DefaultTableCellRenderer {


    private static final String NO_BLOCKED = "src/resources/no_blocked.png";
    private static final String BLOCKED = "src/resources/blocked.png";

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if(value instanceof Boolean){
            return new SymbolPanel((boolean) value);
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
