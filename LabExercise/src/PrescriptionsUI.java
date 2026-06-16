import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class PrescriptionsUI extends JPanel{
    User activeClient;
    UIConstants uiConstant = new UIConstants();

    // Store Doctors separately so the button editor can reference them by row
    private List<Prescription> prescriptionList = new ArrayList<>();
    private DefaultTableModel tableModel;
    private JTable table;

    public PrescriptionsUI(User client, PrescriptionController controller) {

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
        JLabel panelTitle = new JLabel("Search for Prescriptions");
        panelTitle.setFont(new Font("Sans-Serif", Font.BOLD, 24));
        panelTitle.setForeground(Color.WHITE);
        this.add(panelTitle, adj);

        adj.fill = GridBagConstraints.HORIZONTAL;
        adj.gridy = 1;
        adj.gridwidth = 1;
        adj.insets = new Insets(0, 40, 15, 15);
        TextFieldWithPlaceholder searchField =
                new TextFieldWithPlaceholder("Search for Prescriptions", 24);
        boolean canSearch = client.canSearchRecords();
        searchField.returnTextField().setVisible(canSearch);
        this.add(searchField.returnTextField(), adj);

        String[] columns = new String[6];
        columns[0] = "ID";
        columns[1] = "Name";
        columns[2] = "Dose";
        columns[3] = "Frequency";
        columns[4] = "Condition";
        columns[5] = "Date Prescribed";

        tableModel = new DefaultTableModel(columns, 0) {

            @Override
            public Class<?> getColumnClass(int col) {
                return col == 5 ? JButton.class : Object.class;
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
                Prescription target = controller.getPrescription(targetID);
                if (target == null) {
                    JOptionPane.showMessageDialog(this,
                            "Prescription not found.", "Search",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                tableModel.setRowCount(0);
                prescriptionList.clear();
                prescriptionList.add(target);
                loadPrescription();
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
                "Prescription List (" + recordsFound + " found)");
        tableTitle.setFont(new Font("Sans-Serif", Font.BOLD, 14));
        tableTitle.setForeground(Color.WHITE);
        this.add(tableTitle, adj);

        adj.gridwidth = 2;
        adj.gridy = 2;
        adj.insets = new Insets(0, 0, 10, 0);
        adj.fill = GridBagConstraints.NONE;
        JButton createPrescription = new JButton("Create Prescription");
        createPrescription.addActionListener(e -> {
            Window window = SwingUtilities.getWindowAncestor(this);
            PrescriptionCreationUI dialog = new PrescriptionCreationUI(window, controller);
            dialog.setModal(true);
            dialog.setVisible(true);
            
            this.prescriptionList.clear();
            this.prescriptionList = controller.getPatientPrescription(client.getUserID());
            loadPrescription();
        });
        this.add(createPrescription, adj);

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

        this.prescriptionList = controller.getPatientPrescription(client.getUserID());
        loadPrescription();
        this.setFocusable(true);
    }

    public void loadPrescription() {
        tableModel.setRowCount(0);
        for (Prescription a : prescriptionList) {
            addRow(a);
        }
        autoSizeColumns(table);
    }

    private void addRow(Prescription a) {
        Object[] row = new Object[5];
        row[0] = a.getPrescriptionID();
        row[1] = a.getPrescriptionName();
        row[2] = a.getPrescriptionDose();
        row[3] = a.getPrescriptionCondition();
        row[4] = a.getPrescriptionFrequency();
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