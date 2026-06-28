import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class ActiveAppointmentsUI extends JPanel {

    User activeClient;
    UIConstants uiConstant = new UIConstants();

    // Store appointments separately so the button editor can reference them by row
    private List<Appointment> appointmentList = new ArrayList<>();
    private DefaultTableModel tableModel;
    private JTable table;
    private static AppointmentController appointmentController;
    public ActiveAppointmentsUI(User client, AppointmentController appointmentController) {
        this.activeClient = client;
        this.appointmentController = appointmentController;
        this.appointmentList = appointmentController.getActiveAppointmentsByRole(client.getUserID(), client.returnRole());
        
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
        TextFieldWithPlaceholder searchField =
                new TextFieldWithPlaceholder("Search for Appointments", 24);
        boolean canSearch = client.canSearchAppointments();
        searchField.returnTextField().setVisible(canSearch);
        this.add(searchField.returnTextField(), adj);

        boolean canEdit = client.canEditAppointments();
        int colCount = canEdit ? 7 : 6;
        String[] columns = new String[colCount];
        columns[0] = "ID";
        columns[1] = "Patient";
        columns[2] = "Doctor";
        columns[3] = "Date and Time";
        columns[4] = "Location";
        columns[5] = "Status";
        if (canEdit) columns[6] = "Edit";

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                // Only the Edit column is "editable" (so the button editor fires)
                return canEdit && col == 6;
            }

            @Override
            public Class<?> getColumnClass(int col) {
                return col == 6 ? JButton.class : Object.class;
            }
        };

        table = new JTable(tableModel);
        table.setRowHeight(32);

        // Register button renderer + editor on the Edit column
        if (canEdit) {
            table.getColumn("Edit").setCellRenderer(new EditButtonRenderer());
            table.getColumn("Edit").setCellEditor(
                    new EditButtonEditor(appointmentList, this, ()-> {
                        this.appointmentList.clear();
                        this.appointmentList.addAll(this.appointmentController.getActiveAppointmentsByRole(client.getUserID(), client.returnRole()));
                        loadAppointments();
                    })
            );
        }

        adj.gridy = 2;
        adj.gridwidth = 1;
        JButton searchButton = new JButton("Search");
        searchButton.setVisible(canSearch);
        searchButton.setPreferredSize(new Dimension(30, 20));
        searchButton.addActionListener(e -> {
            try {
                int targetID = Integer.parseInt(
                        searchField.returnTextField().getText().trim());
                Appointment target = appointmentController.getAppointment(targetID);
                if (target == null) {
                    JOptionPane.showMessageDialog(this,
                            "Appointment not found.", "Search",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                tableModel.setRowCount(0);
                appointmentList.clear();
                appointmentList.add(target);
                loadAppointments();
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
                "Appointment List (" + recordsFound + " found)");
        tableTitle.setFont(new Font("Sans-Serif", Font.BOLD, 14));
        tableTitle.setForeground(Color.WHITE);
        this.add(tableTitle, adj);

        adj.gridwidth = 2;
        adj.gridy = 2;
        adj.insets = new Insets(0, 0, 10, 0);
        adj.fill = GridBagConstraints.NONE;
        JButton createAppointment = new JButton("Create Appointment");
        createAppointment.setVisible(client.canAddAppointments());
        createAppointment.addActionListener(e -> {
            Window window = SwingUtilities.getWindowAncestor(this);
            AppointmentCreationUI dialog = new AppointmentCreationUI(window, appointmentController);
            dialog.setModal(true);
            dialog.setVisible(true);
            
            this.appointmentList.clear();
            this.appointmentList.addAll(appointmentController.getActiveAppointmentsByRole(client.getUserID(), client.returnRole()));
            loadAppointments();
        });
        this.add(createAppointment, adj);

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
        this.add(scrollPane, adj);

//        Appointment test = new Appointment(1, 1, 1, LocalDate.now(),LocalTime.now(), "Shawn's Office", "Scheduled");
//        appointmentList.add(test);
        loadAppointments();
        this.setFocusable(true);
    }

    public void loadAppointments() {
        tableModel.setRowCount(0);
        boolean canEdit = activeClient.canEditAppointments();
        for (Appointment a : appointmentList) {
            addRow(a, canEdit);
        }
        autoSizeColumns(table);
    }

    private void addRow(Appointment a, boolean canEdit) {
        Object[] row = new Object[canEdit ? 7 : 6];
        row[0] = a.getAppointmentID();
      //row[1] = a.getPatientName();
        row[1] = a.getPatientName() + "#" + a.getPatientID();
      //row[2] = a.getDoctorName();
        row[2] = a.getDoctorName() + "#" + a.getDoctorID();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        row[3] = a.getAppointmentDate().toString() + " " + a.getAppointmentTime().format(formatter);
        row[4] = a.getLocation();
        row[5] = a.getStatus();
        if (canEdit) row[6] = "Edit"; // display text; real object lives in appointmentList
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

    private static class EditButtonRenderer extends JButton
            implements TableCellRenderer {

        public EditButtonRenderer() {
            setOpaque(true);
            setText("Edit");
        }

        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            return this;
        }
    }

    private static class EditButtonEditor extends DefaultCellEditor {

        private final JButton button;
        private int currentRow;

        public EditButtonEditor(List<Appointment> appointmentList, JPanel parent, Runnable onRefresh) {
            super(new JCheckBox()); // DefaultCellEditor requires a component

            button = new JButton("Edit");
            button.addActionListener(e -> {
                fireEditingStopped();
                if (currentRow >= 0 && currentRow < appointmentList.size()) {
                    Window window = SwingUtilities.getWindowAncestor(parent);
                    EditAppointmentUI dialog = new EditAppointmentUI(window, appointmentList.get(currentRow), appointmentController);
                    dialog.setModal(true);
                    dialog.setVisible(true);
                    
                    onRefresh.run();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table,
                Object value, boolean isSelected, int row, int column) {
            currentRow = row;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return "Edit";
        }
    }
}