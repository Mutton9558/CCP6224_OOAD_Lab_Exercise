import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;

//this is the side panel UI that is ONLY for the admin
//this UI shows up when the user clicks on the button called "Generate Report" at the side panel 
public class GenerateReportUI extends JPanel {

    UIConstants uiConstant = new UIConstants();

    // THE FOLLOWING CONTENT IS SIMPLY TO TEST FUNCTIONALITY
    JLabel lb1;
    public GenerateReportUI(UserController userController, AppointmentController appointmentController) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints adj = new GridBagConstraints();
        this.setBackground(uiConstant.Azure);

        // title
        adj.gridwidth = GridBagConstraints.REMAINDER;
        adj.anchor = GridBagConstraints.CENTER;
        adj.insets = new Insets(20, 0, 15, 0);
        adj.weightx = 1.0;
        adj.weighty = 0.0;
        adj.fill = GridBagConstraints.NONE;
        adj.gridy = 0;
        JLabel panelTitle = new JLabel("Generate Report");
        panelTitle.setFont(new Font("Sans-Serif", Font.BOLD, 24));
        panelTitle.setForeground(Color.WHITE);
        this.add(panelTitle, adj);

        // summary cards row
        adj.gridy = 1;
        adj.insets = new Insets(10, 40, 10, 40);
        adj.fill = GridBagConstraints.HORIZONTAL;
        JPanel summaryPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        summaryPanel.setOpaque(false);
        int totalPatients = userController.getUsersByRole("Patient").size();
        int totalAppointments = appointmentController.getAllAppointments().size();
        summaryPanel.add(makeSummaryCard("Total Patients", String.valueOf(totalPatients)));
        summaryPanel.add(makeSummaryCard("Total Appointments", String.valueOf(totalAppointments)));
        this.add(summaryPanel, adj);

        // doctor schedule section label
        adj.gridy = 2;
        adj.insets = new Insets(20, 40, 5, 40);
        adj.fill = GridBagConstraints.NONE;
        JLabel tableTitle = new JLabel("Doctor Schedules");
        tableTitle.setFont(new Font("Sans-Serif", Font.BOLD, 16));
        tableTitle.setForeground(Color.WHITE);
        this.add(tableTitle, adj);

        // doctor schedule table
        String[] columns = {"Doctor Name", "Specialisation", "Office", "Total Appointments"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        ArrayList<User> doctors = userController.getUsersByRole("Doctor");
        for (User doc : doctors) {
            int docAppointments = appointmentController.getDoctorAppointments(doc.getUserID()).size();
            tableModel.addRow(new Object[]{
                doc.getUserName(),
                doc.getSpecialisation(),
                doc.getOffice(),
                docAppointments
            });
        }

        JTable table = new JTable(tableModel);
        table.setRowHeight(32);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        autoSizeColumns(table);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(550, 250));

        adj.gridy = 3;
        adj.weightx = 1.0;
        adj.weighty = 1.0;
        adj.fill = GridBagConstraints.BOTH;
        adj.insets = new Insets(0, 40, 25, 40);
        this.add(scrollPane, adj);

        this.setFocusable(true);
        setVisible(true);
    }

    // little stat card showing a big number with a label underneath
    private JPanel makeSummaryCard(String label, String value) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(UIConstants.DarkBlue);
        card.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Sans-Serif", Font.BOLD, 36));
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel(label);
        nameLabel.setFont(new Font("Sans-Serif", Font.PLAIN, 14));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(valueLabel);
        card.add(nameLabel);
        return card;
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
