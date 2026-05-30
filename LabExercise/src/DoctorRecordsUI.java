import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.TableColumnModel;

public class DoctorRecordsUI extends JPanel{
    User activeClient;
    UIConstants uiConstant = new UIConstants();

    public DoctorRecordsUI(User client) {
        this.activeClient = client;
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints adj = new GridBagConstraints();
        this.setBackground(uiConstant.Azure);
        
        adj.gridwidth = GridBagConstraints.REMAINDER;
        adj.anchor = GridBagConstraints.CENTER;
        adj.insets = new Insets(20, 0, 15, 0);
        adj.weightx = 1.0;
        adj.weighty = 0.0;
        adj.fill = GridBagConstraints.NONE;
        adj.gridy = 0;
        JLabel panelTitle = new JLabel("Search for Doctors");
        panelTitle.setFont(new Font("Sans-Serif", Font.BOLD, 24));
        panelTitle.setForeground(Color.WHITE);
        this.add(panelTitle, adj);
        
        adj.fill = GridBagConstraints.HORIZONTAL; 
        adj.gridy = 1;
        adj.gridwidth = 1;
        adj.insets = new Insets(0, 40, 15, 15); 
        TextFieldWithPlaceholder searchField = new TextFieldWithPlaceholder("Search by Doctor Name", 24);
        searchField.returnTextField().setVisible(activeClient.canSearchAppointments());
        this.add(searchField.returnTextField(), adj);
        
        adj.gridy = 1;
        adj.gridwidth = 1;
        JButton searchButton = new JButton("Search");
        searchButton.setVisible(activeClient.canSearchAppointments());
        searchButton.setPreferredSize(new Dimension(30, 20));
        this.add(searchButton, adj);
        
        int recordsFound = 30;
        adj.gridy = 2;
        adj.insets = new Insets(10, 0, 10, 0);
        adj.fill = GridBagConstraints.NONE; 
        JLabel tableTitle = new JLabel("Doctor List: (" + recordsFound + " found)");
        tableTitle.setFont(new Font("Sans-Serif", Font.BOLD, 14));
        tableTitle.setForeground(Color.WHITE);
        this.add(tableTitle, adj);
        
        String[] columns = {"ID", "Username", "Age", "Gender", "Office", "Specialisation"};
        Object[][] data = {
            {1, "Imran", "18", "Male", "Ground Floor, Room 2", "Dermatologist"},    
            {2, "Elsa", "19", "?", "2nd Floor, Room 1", "Radiologist"},
            {3, "Muhammad Yusuf", "20", "Male", "1st Floor, Room 3", "Neurologist"},
        };
        
        JTable table = new JTable(data, columns);
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        
        TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int maxWidth = 50;
            
            Object headerValue = columnModel.getColumn(column).getHeaderValue();
            if (headerValue != null) {
                maxWidth = Math.max(maxWidth, table.getTableHeader().getFontMetrics(table.getTableHeader().getFont()).stringWidth(headerValue.toString()) + 25);
            }
            
            for (int row = 0; row < table.getRowCount(); row++) {
                Object cellValue = table.getValueAt(row, column);
                if (cellValue != null) {
                    int cellWidth = table.getFontMetrics(table.getFont()).stringWidth(cellValue.toString()) + 25;
                    maxWidth = Math.max(maxWidth, cellWidth);
                }
            }
            
            columnModel.getColumn(column).setMinWidth(maxWidth);
            columnModel.getColumn(column).setPreferredWidth(maxWidth);
        }
        
        JScrollPane scrollPane = new JScrollPane(table);
        
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(550, 300));
        
        adj.gridy = 3;
        adj.weightx = 3.0;
        adj.weighty = 1.0; 
        adj.fill = GridBagConstraints.BOTH; 
        adj.insets = new Insets(0, 40, 25, 40);
        this.add(scrollPane, adj);
        this.setFocusable(true);
    }
}