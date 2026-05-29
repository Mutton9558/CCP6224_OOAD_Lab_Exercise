import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.TableColumnModel;

public class DoctorRecordsUI extends JPanel implements FocusListener, ActionListener{
    User activeClient;

    private DoctorController doctorController;
    private JTextField searchDoctorTextField;
    private JButton searchButton;
    UIConstants UIConst = new UIConstants();
    private HashMap<JTextField, String> placeholderText = new HashMap<>();

    public DoctorRecordsUI(User client) {
        this.activeClient = client;
        UIConstants UIConst = new UIConstants();
        
        this.setLayout(new CardLayout());
        this.setSize(600, 400);
        this.setBackground(UIConst.Azure);
        
        JLabel panelTitle = new JLabel("Search for Doctor");
        panelTitle.setFont(new Font("Sans-Serif", Font.BOLD, 24));
        panelTitle.setForeground(Color.WHITE);
        this.add(panelTitle);

        JTextField searchField = new JTextField("Search", 24);
        searchField.setVisible(activeClient.canSearchRecords());
        this.add(searchField);
        searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        this.add(searchButton);
        
        int recordsFound = 30;
        JLabel tableTitle = new JLabel("Appointment List (" + recordsFound + " found)");
        tableTitle.setFont(new Font("Sans-Serif", Font.BOLD, 14));
        tableTitle.setForeground(Color.WHITE);
        this.add(tableTitle);
        
        String[] columns = {"ID", "Patient", "Doctor", "Date", "Time", "Status"};
        Object[][] data = {
            {1, "Shawn Huang Qi Yang", "Imran", "1/6/2026", "09:30", "Scheduled"},    
            {2, "Wan Wei Siang", "Imran", "27/5/2026", "16.30", "Concluded"},
            {3, "Syed Zaki Husain Wafa bin Syed Riyad Reza", "Imran", "26/5/2026", "20:30", "Cancelled"},
        };
        
        JTable table = new JTable(data, columns);
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
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
        scrollPane.setPreferredSize(new Dimension(500, 220));     
        this.add(scrollPane);   
        this.setVisible(true);
    }

    JLabel lb1;
    public DoctorRecordsUI() {

        //table of active appointments 
        //placeHOLDER for shawn 
        setBackground(Color.YELLOW);
        lb1 = new JLabel("ACTIVE APPOINTMENT BUTTON WAS PRESSED");
        add(lb1);
        setVisible(true);
    }

    @Override
    public void focusGained(FocusEvent e){
        JTextField textField = (JTextField) e.getSource();
        String text = textField.getText();
        for(String p : placeholderText.values()){
            if(text.equals(p)){
                textField.setText("");
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e){
        JTextField textField = (JTextField) e.getSource();
        if(textField.getText().equals("")){
            textField.setText(placeholderText.get(textField));
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
    }
    
    public static void main(String[] args){
        User client = new Doctor("Shawn", "1");
        new DoctorRecordsUI(client);
    }
}