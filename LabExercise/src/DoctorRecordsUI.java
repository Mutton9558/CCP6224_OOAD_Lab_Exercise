import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorRecordsUI extends JPanel{
    private final UIConstants uiConstant = new UIConstants();

    // Store Doctors separately so the button editor can reference them by row
    private List<User> doctorList = new ArrayList<>();
    private DefaultTableModel tableModel;
    private JTable table;

    public DoctorRecordsUI(User client, UserController controller, SystemController system) {
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
        JLabel panelTitle = new JLabel("Search for Active Doctors");
        panelTitle.setFont(new Font("Sans-Serif", Font.BOLD, 24));
        panelTitle.setForeground(Color.WHITE);
        this.add(panelTitle, adj);

        adj.fill = GridBagConstraints.HORIZONTAL;
        adj.gridy = 1;
        adj.gridwidth = 1;
        adj.insets = new Insets(0, 40, 15, 15);
        TextFieldWithPlaceholder searchField =
                new TextFieldWithPlaceholder("Search for Doctors", 24);
        boolean canSearch = client.canSearchRecords();
        searchField.returnTextField().setVisible(canSearch);
        this.add(searchField.returnTextField(), adj);

        String[] columns = new String[7];
        columns[0] = "ID";
        columns[1] = "Name";
        columns[2] = "Age";
        columns[3] = "Gender";
        columns[4] = "Office";
        columns[5] = "Specialisation";
        columns[6] = "View Profile";

        tableModel = new DefaultTableModel(columns, 0) {

            @Override
            public Class<?> getColumnClass(int col) {
                return col == 7 ? JButton.class : Object.class;
            }
        };

        table = new JTable(tableModel);
        table.setRowHeight(32);
        
                //render the button called "view"
        table.getColumn("View Profile").setCellRenderer(new ButtonRenderer());
        table.getColumn("View Profile").setCellEditor(new ButtonEditor(table, row->{
            User doctor = doctorList.get(row);
            UserProfileUI profile = new UserProfileUI(doctor, system);
            
            JDialog profileDialog = new JDialog();
            //get the parent frame 
            Frame parent = (Frame)SwingUtilities.getWindowAncestor(this);
            profileDialog = new JDialog(parent, "Doctor Profile", true);
            profileDialog.setSize(1366, 768);
            profileDialog.setLocationRelativeTo(parent);
            profileDialog.setResizable(false);
            profileDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            profileDialog.add(profile);
            profileDialog.setVisible(true);
       }));  

        adj.gridy = 2;
        adj.gridwidth = 1;
        JButton searchButton = new JButton("Search");
        searchButton.setVisible(canSearch);
        searchButton.setPreferredSize(new Dimension(30, 20));
        searchButton.addActionListener(e -> {
            try {
                int targetID = Integer.parseInt(
                        searchField.returnTextField().getText().trim());
                User target = controller.searchUser(targetID, "Doctor");
                if (target == null) {
                    JOptionPane.showMessageDialog(this,
                            "Doctor not found.", "Search",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                
                tableModel.setRowCount(0);
                doctorList.clear();
                doctorList.add(target);
                loadDoctors();
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
                "Doctor List (" + recordsFound + " found)");
        tableTitle.setFont(new Font("Sans-Serif", Font.BOLD, 14));
        tableTitle.setForeground(Color.WHITE);
        this.add(tableTitle, adj);

        adj.gridwidth = 2;
        adj.gridy = 2;
        adj.insets = new Insets(0, 0, 10, 0);
        adj.fill = GridBagConstraints.NONE;
        JButton createDoctor = new JButton("Create Doctor");
        createDoctor.addActionListener(e -> {
            Window window = SwingUtilities.getWindowAncestor(this);
            DoctorCreationUI dialog = new DoctorCreationUI(window, controller);
            dialog.setVisible(true);
        });
        createDoctor.setVisible(client.canAddDoctors());
        this.add(createDoctor, adj);

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

        ArrayList<User> initialData = controller.getUsersByRole("Doctor");
        doctorList = initialData;
        loadDoctors();
        this.setFocusable(true);
    }

    public void loadDoctors() {
        tableModel.setRowCount(0);
        for (User a : doctorList) {
            addRow(a);
        }
        autoSizeColumns(table);
    }

    private void addRow(User a) {
        Object[] row = new Object[7];
        row[0] = a.getUserID();
        row[1] = a.getUserName();
        row[2] = a.getUserAge();
        row[3] = a.getUserGender();
        row[4] = a.getOffice();
        row[5] = a.getSpecialisation();
        row[6] = "View";
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