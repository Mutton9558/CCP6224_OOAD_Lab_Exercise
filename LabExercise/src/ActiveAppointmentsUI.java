import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableColumnModel;

public class ActiveAppointmentsUI extends JPanel {
    User activeClient;
    UIConstants uiConstant = new UIConstants();

    public ActiveAppointmentsUI(User client) {
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
        JLabel panelTitle = new JLabel("Search for Active Appointments");
        panelTitle.setFont(new Font("Sans-Serif", Font.BOLD, 24));
        panelTitle.setForeground(Color.WHITE);
        this.add(panelTitle, adj);
        
        adj.fill = GridBagConstraints.HORIZONTAL; 
        adj.gridy = 1;
        adj.gridwidth = 1;
        adj.insets = new Insets(0, 40, 15, 15); 
        TextFieldWithPlaceholder searchField = new TextFieldWithPlaceholder("Search for Appointments", 24);
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
        JLabel tableTitle = new JLabel("Appointment List (" + recordsFound + " found)");
        tableTitle.setFont(new Font("Sans-Serif", Font.BOLD, 14));
        tableTitle.setForeground(Color.WHITE);
        this.add(tableTitle, adj);
        
        String[] columns = {"ID", "Patient", "Doctor", "Date", "Time", "Status"};
        Object[][] data = {
            {1, "Shawn Huang Qi Yang", "Imran", "1/6/2026", "09:30", "Scheduled"},    
            {2, "Wan Wei Siang", "Imran", "27/5/2026", "16.30", "Concluded"},
            {3, "Syed Zaki Husain Wafa bin Syed Riyad Reza", "Imran", "26/5/2026", "20:30", "Cancelled"},
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
        adj.weightx = 1.0;
        adj.weighty = 1.0; 
        adj.fill = GridBagConstraints.BOTH; 
        adj.insets = new Insets(0, 40, 25, 40);
        this.add(scrollPane, adj);
        this.setFocusable(true);
    }
}
