import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.function.Consumer;

public class ButtonEditor extends DefaultCellEditor{
    
    private JButton butt;
    private JTable table;
    private Consumer<Integer> click;
    private int currentRow;
    
    public ButtonEditor(JTable table, Consumer<Integer> func){
        super(new JCheckBox());
        
        this.table = table;
        click = func;
        butt = new JButton();
        butt.setFocusPainted(false);
        
        butt.addActionListener(e->{fireEditingStopped();}); 
    }
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column){
        butt.setText(value.toString());
        return butt;
    }
    @Override
    public Object getCellEditorValue(){
        currentRow = table.getSelectedRow();
        click.accept(currentRow);
        return butt.getText();
    }
}
