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
    public JButton backButton = new JButton("Back");
    
    private User userProfile;
    private User viewer;
    private ArrayList<Appointment> appList;
    
    // We keep track of the profile info panel instance so we can swap it out on reload
    private ProfileInfoPanel profileInfoPanel;

    public UserProfileUI(User userProfile, SystemController system) {

        this.userProfile = userProfile;
        this.viewer = system.getUserControllerInstance().getCurrentUser();
        this.appList = system.getAppointmentControllerInstance().getActiveAppointmentsByID(userProfile.getUserID());
        
        //permissions
        boolean canEditProfileInfo = viewer.canEditProfile();
        boolean canEditProfile = viewer.canEditProfile();
        boolean canEditDiag = viewer.canEditDiagnosis();
        boolean canEditPresc = viewer.canEditPrescription();

       
        setBackground(uiConstants.DarkBlue);
        setLayout(new BorderLayout());

        // northPanel settings
        JPanel northPanel = new JPanel(new GridBagLayout());
        northPanel.setBackground(uiConstants.DarkBlue);
        backButton.setBackground(uiConstants.DarkBlue);
        backButton.setForeground(Color.WHITE);
        JButton ViewMedicalRecordsButton = new JButton("View Medical Records");
        ViewMedicalRecordsButton.setBackground(uiConstants.DarkBlue);
        ViewMedicalRecordsButton.setForeground(Color.WHITE);
        ViewMedicalRecordsButton.setVisible(true);

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
        JButton EditProfileBtn = new JButton("Edit");
        EditProfileBtn.addActionListener(e -> {
            Window window = SwingUtilities.getWindowAncestor(this);
            JDialog editProfileDialog = new EditProfileUI(window, this.userProfile, system.getUserControllerInstance());
            editProfileDialog.setModal(true);
            editProfileDialog.setVisible(true);
            
            // 1. Fetch updated data from database/controller
            this.userProfile = system.getUserControllerInstance().searchUser(this.userProfile.getUserID());
            
            // 2. Remove the outdated component from infoPanel
            infoPanel.remove(profileInfoPanel);
            
            // 3. Create a fresh visual instance with updated user data
            profileInfoPanel = new ProfileInfoPanel(this.userProfile);
            
            // 4. Re-add it to the same GridBagLayout constraints
            GridBagConstraints updatedGbc = new GridBagConstraints();
            updatedGbc.gridx = 0;
            updatedGbc.gridy = 3;
            updatedGbc.anchor = GridBagConstraints.WEST;
            updatedGbc.insets = new Insets(10, 10, 10, 10);
            infoPanel.add(profileInfoPanel, updatedGbc);
            
            // 5. Force Swing to clear cache layout structural properties and repaint
            infoPanel.revalidate();
            infoPanel.repaint();
        });
        
        EditProfileBtn.setBackground(uiConstants.VeryDarkBlue);
        EditProfileBtn.setForeground(Color.WHITE);
        EditProfileBtn.setVisible(canEditProfileInfo);
        infoSubPanel.add(GeneralInfoLabel, BorderLayout.WEST);
        infoSubPanel.add(EditProfileBtn, BorderLayout.EAST);
        infoPanel.add(infoSubPanel, gbc);

        // Initialized component assigned to field reference
        profileInfoPanel = new ProfileInfoPanel(userProfile);
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
        String[] columnNames1 = { "ID", "Patient", "Doctor", "Date And Time", "Location", "Status" };
        DefaultTableModel model1 = new DefaultTableModel(columnNames1, 0);
        for(Appointment a : appList){
            
            model1.addRow(new Object[] { a.getAppointmentID(), a.getPatientID(), system.getUserControllerInstance().searchUser(a.getPatientID()).getUserName(), a.getDoctorID(), system.getUserControllerInstance().searchUser(a.getDoctorID()).getUserName(), a.getAppointmentDate().toString(), a.getAppointmentTime().toString(), a.getLocation(),
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
        // (Note: if these elements ever need to update dynamically, you would use a similar approach or table model updates)
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
        
        

        //adding main panels 
        JSplitPane splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, infoPanel, tablePanel);
        splitPanel.setDividerLocation(550);
        add(northPanel, BorderLayout.NORTH);
        add(splitPanel, BorderLayout.CENTER);
    }
}