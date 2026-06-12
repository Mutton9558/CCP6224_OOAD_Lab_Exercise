import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class UserProfileUI extends JPanel {

    UIConstants uiConstants = new UIConstants();
    GridBagConstraints gbc = new GridBagConstraints();
    GridBagConstraints gbc2 = new GridBagConstraints();
    
    private User userProfile;
    private User viewer;
    private UserController usrController;
    private ArrayList<Appointment> appList;

    public UserProfileUI(User userProfile, User viewer, ArrayList<Appointment> appList, UserController usrController) {

        this.userProfile = userProfile;
        this.viewer = viewer;
        this.usrController = usrController;
        this.appList = appList; 
        
        //permissions
        boolean hasMedicalRecords = userProfile.hasMedicalRecords();
        boolean canEditProfileInfo = viewer.canEditUserProfileInfo();
        boolean canEditProfile = viewer.canEditUserProfile();
        boolean canEditDiag = viewer.canEditDiagnosis();
        boolean canEditPresc = viewer.canEditPrescription();

       
        setBackground(uiConstants.DarkBlue);
        setLayout(new BorderLayout());

        // northPanel settings
        JPanel northPanel = new JPanel(new GridBagLayout());
        northPanel.setBackground(uiConstants.DarkBlue);
        JButton backButton = new JButton("back");
        backButton.setBackground(uiConstants.DarkBlue);
        backButton.setForeground(Color.WHITE);
        JButton ViewMedicalRecordsButton = new JButton("View Medical Records");
        ViewMedicalRecordsButton.setBackground(uiConstants.DarkBlue);
        ViewMedicalRecordsButton.setForeground(Color.WHITE);
        ViewMedicalRecordsButton.setVisible(hasMedicalRecords);

        // Patient profile in the doctor's view and receptionist's view
        JButton UpdateButton = new JButton("Update");
        UpdateButton.setBackground(uiConstants.VeryDarkBlue);
        UpdateButton.setForeground(Color.WHITE);
        UpdateButton.setVisible(canEditProfile);

        JLabel mmLogo = new JLabel(uiConstants.MMlogo);
        gbc2.gridy = 0;
        gbc2.gridx = 0;
        northPanel.add(backButton, gbc2);
        gbc2.gridx = 1;
        gbc2.weightx = 1;
        northPanel.add(mmLogo, gbc2);
        gbc2.gridx = 2;
        northPanel.add(ViewMedicalRecordsButton, gbc2);
        gbc2.gridx = 3;
        gbc2.gridwidth = 2;
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        northPanel.add(UpdateButton, gbc2);
        // northPanel settings

        // two split panels
        JPanel infoPanel = new JPanel(new GridBagLayout());
        JPanel tablePanel = new JPanel(new GridBagLayout());


        // setting for infoPanel
        infoPanel.setBackground(uiConstants.Azure);

        JLabel UserProfileLabel = new JLabel(userProfile.returnRole()+ "Profile");
        UserProfileLabel.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        infoPanel.add(UserProfileLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel pfpLabel = new JLabel(uiConstants.pfpIcon);
        infoPanel.add(pfpLabel, gbc);

        JLabel GeneralInfoLabel = new JLabel("General Information");
        JPanel infoSubPanel = new JPanel(new BorderLayout());
        infoSubPanel.setPreferredSize(new Dimension(415, 20));
        infoSubPanel.setOpaque(false);
        GeneralInfoLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        // receptionist/admin edit profile
        JButton EditPatientProfile = new JButton("Edit");
        EditPatientProfile.setBackground(uiConstants.VeryDarkBlue);
        EditPatientProfile.setForeground(Color.WHITE);
        EditPatientProfile.setVisible(canEditProfileInfo);
        infoSubPanel.add(GeneralInfoLabel, BorderLayout.WEST);
        infoSubPanel.add(EditPatientProfile, BorderLayout.EAST);
        infoPanel.add(infoSubPanel, gbc);

        ProfileInfoPanel profileInfoPanel = new ProfileInfoPanel(userProfile);
        gbc.gridx = 0;
        gbc.gridy = 3;
        infoPanel.add(profileInfoPanel, gbc);
        // setting for infoPanel


        
        
        // TABLE PANEL
        // setting for tablePanel
        tablePanel.setBackground(uiConstants.DarkBlue);
        JLabel ActiveAppointmentJLabel = new JLabel("Active Appointments");
        ActiveAppointmentJLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        tablePanel.add(ActiveAppointmentJLabel, gbc);

        // TABLES ACTIVE APPOINTMENTS //
        //userProfile 
        String[] columnNames1 = { "ID", "Patient ID", "Patient Name", "Doctor ID", "Doctor Name", "Date", "Time",
                "Location", "Status" };
        DefaultTableModel model1 = new DefaultTableModel(columnNames1, 0);
        for(Appointment a : appList){
            
            model1.addRow(new Object[] { a.getAppointmentID(), a.getPatientID(), usrController.searchUser(a.getPatientID(), "patient").getUserName(), a.getDoctorID(), usrController.searchUser(a.getDoctorID(), "doctor").getUserName(), a.getAppointmentDate().toString(), a.getAppointmentTime().toString(), a.getLocation(),
                a.getStatus()});
        }
        
        JTable AppointmentTable = new JTable(model1);
        JScrollPane scrollPane1 = new JScrollPane(AppointmentTable);
        scrollPane1.setPreferredSize(new Dimension(600, 110));
        gbc.gridx = 0;
        gbc.gridy = 1;
        tablePanel.add(scrollPane1, gbc);

        JPanel PrescSubPanel = new JPanel(new BorderLayout());
        PrescSubPanel.setOpaque(false);
        PrescSubPanel.setPreferredSize(new Dimension(150, 20));

        
        // PRESCRIPTION -- right now it is non-functional as getting prescription function is not implemeted + diagnosis
        JLabel PrescriptionsLabel = new JLabel("Prescriptions");
        PrescriptionsLabel.setForeground(Color.WHITE);
        JButton AddPresc = new JButton("Add");
        AddPresc.setBackground(uiConstants.VeryDarkBlue);
        AddPresc.setForeground(Color.WHITE);
        AddPresc.setVisible(canEditPresc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        PrescSubPanel.add(AddPresc, BorderLayout.EAST);
        PrescSubPanel.add(PrescriptionsLabel, BorderLayout.WEST);
        tablePanel.add(PrescSubPanel, gbc);

        // TABLES PRESCRIPTION //
        String[] columnNames2 = { "Medication Name", "Dose", "Frequency", "Condition" };
        DefaultTableModel model2 = new DefaultTableModel(columnNames2, 0);
        model2.addRow(new Object[] { "Aspirine", "2 puffs", "1 daily", "Diabetes" });
        JTable PrescriptionsTable = new JTable(model2);
        JScrollPane scrollPane2 = new JScrollPane(PrescriptionsTable);
        scrollPane2.setPreferredSize(new Dimension(600, 110));
        tablePanel.add(scrollPane2);
        gbc.gridx = 0;
        gbc.gridy = 3;
        tablePanel.add(scrollPane2, gbc);


        // DIAGNOSIS
        JPanel DiagSubPanel = new JPanel(new BorderLayout());
        DiagSubPanel.setOpaque(false);
        DiagSubPanel.setPreferredSize(new Dimension(150, 20));
        JLabel DiagnosisLabel = new JLabel("Diagnosis");
        DiagnosisLabel.setForeground(Color.WHITE);
        JButton AddDiag = new JButton("Add");
        AddDiag.setBackground(uiConstants.VeryDarkBlue);
        AddDiag.setForeground(Color.WHITE);
        AddDiag.setVisible(canEditDiag);
        gbc.gridx = 0;
        gbc.gridy = 4;
        DiagSubPanel.add(DiagnosisLabel, BorderLayout.WEST);
        DiagSubPanel.add(AddDiag, BorderLayout.EAST);
        tablePanel.add(DiagSubPanel, gbc);

        // TABLES DIAGNOSIS //
        String[] columnNames3 = { "Condition Name", "Date" };
        DefaultTableModel model3 = new DefaultTableModel(columnNames3, 0);
        model3.addRow(new Object[] { "Diabetes", "2026-07-06" });
        JTable DiagnosisTable = new JTable(model3);
        JScrollPane scrollPane3 = new JScrollPane(DiagnosisTable);
        scrollPane3.setPreferredSize(new Dimension(600, 110));
        gbc.gridx = 0;
        gbc.gridy = 5;
        tablePanel.add(scrollPane3, gbc);

        

        //adding main panels 
        JSplitPane splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, infoPanel, tablePanel);
        splitPanel.setDividerLocation(550);
        add(northPanel, BorderLayout.NORTH);
        add(splitPanel, BorderLayout.CENTER);
    }

}

