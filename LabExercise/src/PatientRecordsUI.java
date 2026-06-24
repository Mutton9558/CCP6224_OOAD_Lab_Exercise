//import java.awt.*;
//import javax.swing.*;
//
////this is the side panel UI that is shared between receptionist and doctor
////this UI shows up when the user clicks on the button called "Patient Records" at the side panel
//public class PatientRecordsUI extends JPanel {
//
//    // THE FOLLOWING CONTENT IS SIMPLY TO TEST FUNCTIONALITY
//    JLabel lb1;
//    public PatientRecordsUI() {
//
//        //table of patient records
//        //placeHOLDER for me(els)
//        setBackground(Color.RED);
//        lb1 = new JLabel("PATIENT RECORDS BUTTON WAS PRESSED");
//        add(lb1);
//        setVisible(true);
//    }
//}
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.ArrayList;
import java.util.List;

public class PatientRecordsUI extends JPanel{
    User activeClient;
    UIConstants uiConstant = new UIConstants();

    // Store Patients separately so the button editor can reference them by row
    private List<User> PatientList = new ArrayList<>();
    private DefaultTableModel tableModel;
    private JTable table;

    public PatientRecordsUI(User client, UserController controller) {

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
        JLabel panelTitle = new JLabel("Search for Patients");
        panelTitle.setFont(new Font("Sans-Serif", Font.BOLD, 24));
        panelTitle.setForeground(Color.WHITE);
        this.add(panelTitle, adj);

        adj.fill = GridBagConstraints.HORIZONTAL;
        adj.gridy = 1;
        adj.gridwidth = 1;
        adj.insets = new Insets(0, 40, 15, 15);
        TextFieldWithPlaceholder searchField =
                new TextFieldWithPlaceholder("Search for Patients", 24);
        boolean canSearch = client.canSearchRecords();
        searchField.returnTextField().setVisible(canSearch);
        this.add(searchField.returnTextField(), adj);

        String[] columns = new String[4];
        columns[0] = "ID";
        columns[1] = "Name";
        columns[2] = "Age";
        columns[3] = "Gender";

        tableModel = new DefaultTableModel(columns, 0) {

            @Override
            public Class<?> getColumnClass(int col) {
                return col == 6 ? JButton.class : Object.class;
            }
        };

        table = new JTable(tableModel);
        table.setRowHeight(32);

        adj.gridy = 2;
        adj.gridwidth = 1;
        JButton searchButton = new JButton("Search");
        searchButton.setVisible(canSearch);
        searchButton.setPreferredSize(new Dimension(30, 20));
        searchButton.addActionListener(e -> {
            try {
                int targetID = Integer.parseInt(
                        searchField.returnTextField().getText().trim());
                User target = controller.searchUser(targetID, "Patient");
                if (target == null) {
                    JOptionPane.showMessageDialog(this,
                            "Patient not found.", "Search",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                tableModel.setRowCount(0);
                PatientList.clear();
                PatientList.add(target);
                loadPatients();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Please enter a valid numeric ID.", "Invalid Input",
                        JOptionPane.WARNING_MESSAGE);
            }
        });
        this.add(searchButton, adj);

        int recordsFound = 30; // replace with real count from data source
        adj.gridwidth = 1;
        adj.gridy = 2;
        adj.insets = new Insets(10, 0, 10, 0);
        adj.fill = GridBagConstraints.NONE;
        JLabel tableTitle = new JLabel(
                "Patient List (" + recordsFound + " found)");
        tableTitle.setFont(new Font("Sans-Serif", Font.BOLD, 14));
        tableTitle.setForeground(Color.WHITE);
        this.add(tableTitle, adj);

        adj.gridwidth = 2;
        adj.gridy = 2;
        adj.insets = new Insets(0, 0, 10, 0);
        adj.fill = GridBagConstraints.NONE;
        JButton createPatient = new JButton("Register Patient");
        createPatient.addActionListener(e -> {
            Window window = SwingUtilities.getWindowAncestor(this);
//            ReceptionistCreationUI dialog = new ReceptionistCreationUI(window);
//            dialog.setVisible(true);
        });
        this.add(createPatient, adj);

        adj.gridwidth = GridBagConstraints.REMAINDER;
        adj.gridy = 3;
        adj.weightx = 1.0;
        adj.weighty = 1.0;
        adj.fill = GridBagConstraints.BOTH;
        adj.insets = new Insets(0, 40, 25, 40);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

        // Auto-size columns based on content
        autoSizeColumns(table);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(550, 300));
        
        adj.gridy = 3;
        adj.weightx = 3.0;
        adj.weighty = 1.0; 
        adj.fill = GridBagConstraints.BOTH; 
        adj.insets = new Insets(0, 40, 25, 40);
        this.add(scrollPane, adj);

        this.PatientList = controller.getUsersByRole("Patient");
        loadPatients();
        this.setFocusable(true);
    }

    public void loadPatients() {
        tableModel.setRowCount(0);
        for (User a : PatientList) {
            addRow(a);
        }
        autoSizeColumns(table);
    }

    private void addRow(User a) {
        Object[] row = new Object[4];
        row[0] = a.getUserID();
        row[1] = a.getUserName();
        row[2] = a.getUserAge();
        row[3] = a.getUserGender();
        tableModel.addRow(row);
    }

    private void autoSizeColumns(JTable tbl) {
        TableColumnModel cm = tbl.getColumnModel();
        for (int col = 0; col < tbl.getColumnCount(); col++) {
            int maxWidth = 50;
            Object header = cm.getColumn(col).getHeaderValue();
            if (header != null) {
                maxWidth = Math.max(maxWidth,
                        tbl.getTableHeader()
                           .getFontMetrics(tbl.getTableHeader().getFont())
                           .stringWidth(header.toString()) + 25);
            }
            for (int row = 0; row < tbl.getRowCount(); row++) {
                Object val = tbl.getValueAt(row, col);
                if (val != null) {
                    maxWidth = Math.max(maxWidth,
                            tbl.getFontMetrics(tbl.getFont())
                               .stringWidth(val.toString()) + 25);
                }
            }
            cm.getColumn(col).setMinWidth(maxWidth);
            cm.getColumn(col).setPreferredWidth(maxWidth);
        }
    }

    }