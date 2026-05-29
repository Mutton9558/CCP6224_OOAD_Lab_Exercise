import java.awt.*;
import javax.swing.*;

public class DashboardUI extends JPanel {

    CardLayout cardLayout1 = new CardLayout();
    CardLayout cardLayout2 = new CardLayout();
    JPanel westPanel, contentPanel, mainPanel, northPanel;
    JButton butt1, butt2, butt3, butt4, butt5;

    // this is the main dashboard UI shared accross patient, receptionists, doctors and admin
    public DashboardUI() {

        UIConstants UIConst = new UIConstants();
        setLayout(new BorderLayout());

        westPanel = new JPanel(cardLayout1); //side panel
        contentPanel = new JPanel(cardLayout2); //content panel
        mainPanel = new JPanel(new BorderLayout());

        //note: northPanel is simply an invisible space to see the bg 
        northPanel = new JPanel();
        northPanel.setOpaque(false);
        northPanel.setPreferredSize(UIConst.topPanelDimension);

        //westPanel.setBackground(UIConst.DarkBlue);
        westPanel.setOpaque(false);
        contentPanel.setBackground(UIConst.Azure);
        mainPanel.setBackground(Color.BLUE);

        westPanel.setPreferredSize(UIConst.sidePanelDimension);
        //southPanel.setPreferredSize(UIConst.contentPanelDimension);

        setOpaque(false);

        // add the subpanels into the mainPanel
        mainPanel.add(contentPanel, BorderLayout.CENTER); 
        mainPanel.add(westPanel, BorderLayout.WEST);
        
        // Patient side panel 
        JLabel MMlogo_square1 = new JLabel(UIConst.MMlogo_square);
        JPanel patient_SidePanel = new JPanel();
        patient_SidePanel.setLayout(new BoxLayout(patient_SidePanel, BoxLayout.Y_AXIS));
        patient_SidePanel.setBackground(UIConst.DarkBlue);
        patient_SidePanel.add(MMlogo_square1);
        JButton ActiveAppointmentsButton = new JButton("Active Appointments");
        JButton PrescriptionsButton = new JButton("Prescriptions");
        JButton MedicalRecordsButton = new JButton("Medical Records");
        patient_SidePanel.add(ActiveAppointmentsButton);
        patient_SidePanel.add(PrescriptionsButton);
        patient_SidePanel.add(MedicalRecordsButton);
        // 


        // Doctor side panel 
        JLabel MMlogo_square2 = new JLabel(UIConst.MMlogo_square);
        JPanel doctor_SidePanel = new JPanel(new FlowLayout());
        doctor_SidePanel.setBackground(UIConst.DarkBlue);
        doctor_SidePanel.add(MMlogo_square2);
        JButton PatientRecordsButton = new JButton("Patient Records");
        doctor_SidePanel.add(ActiveAppointmentsButton); //doctor uses the same button as patient 
        doctor_SidePanel.add(PatientRecordsButton);
        // 
        

        // Receptionist side panel 
        JLabel MMlogo_square3 = new JLabel(UIConst.MMlogo_square);
        JPanel receptionist_SidePanel = new JPanel(new FlowLayout());
        receptionist_SidePanel.setBackground(UIConst.DarkBlue);
        receptionist_SidePanel.add(MMlogo_square3);
        receptionist_SidePanel.add(ActiveAppointmentsButton); //receptionist uses the same button as doctor+patient
        receptionist_SidePanel.add(PatientRecordsButton); //receptionist uses the same button as doctor 
        // 


        // Admin side panel 
        JLabel MMlogo_square4 = new JLabel(UIConst.MMlogo_square);
        JPanel admin_SidePanel = new JPanel(new FlowLayout());
        admin_SidePanel.setBackground(UIConst.DarkBlue);
        admin_SidePanel.add(MMlogo_square4);
        JButton DoctorRecordsButton = new JButton("Doctor Records");
        JButton ReceptionistRecordsButton = new JButton("Receptionist Records");
        JButton GenerateReportButton = new JButton("Generate Report");
        admin_SidePanel.add(DoctorRecordsButton);
        admin_SidePanel.add(ReceptionistRecordsButton);
        admin_SidePanel.add(GenerateReportButton);
        // 


        //add the side panels into the main side panel 
        westPanel.add(patient_SidePanel, "patientPanel");
        westPanel.add(doctor_SidePanel, "doctorPanel");
        westPanel.add(receptionist_SidePanel, "receptionistPanel");
        westPanel.add(admin_SidePanel, "adminPanel");


        // PLACEHOLDER FOR TESTING PURPOSES ONLY
        // shawn please make the user role pass here to get to their right panel
        // for now, patient is going to be the testsubj
        // if other people want to test their UI, kindly change patient into whatever role u want 

        // TO DELETE
        String role = "doctor";

        switch (role) {

            case "doctor":
                cardLayout1.show(westPanel, "doctorPanel");
                break;
            case "patient":
                cardLayout1.show(westPanel, "patientPanel");
                break;
            case "receptionist":
                cardLayout1.show(westPanel, "receptionistPanel");
                break;
            case "admin":
                cardLayout1.show(westPanel, "adminPanel");
                break;
        }
        // TO DELETE


        // create instances of the JPanels for the contentPanel !!
        //Patient 
        ActiveAppointmentUI activeAppointmentUI = new ActiveAppointmentUI(); //shared between patient+doctor+recptionist
        PrescriptionsUI prescriptionsUI = new PrescriptionsUI();
        MedicalRecordsUI medicalRecordsUI = new MedicalRecordsUI();

        //Doctor + Receptionist
        PatientRecordsUI patientRecordsUI = new PatientRecordsUI();

        //Admin only
        DoctorRecordsUI doctorRecordsUI = new DoctorRecordsUI();
        ReceptionistRecordsUI receptionistRecordsUI = new ReceptionistRecordsUI();
        GenerateReportUI generateReportUI = new GenerateReportUI();

        //adding these instances into the content panel 
        contentPanel.add(activeAppointmentUI, "activeAppointmentUI");
        contentPanel.add(prescriptionsUI, "prescriptionsUI");
        contentPanel.add(medicalRecordsUI, "medicalRecordsUI");
        contentPanel.add(patientRecordsUI, "patientRecordsUI");
        contentPanel.add(doctorRecordsUI, "doctorRecordsUI");
        contentPanel.add(receptionistRecordsUI, "receptionistRecordsUI");
        contentPanel.add(generateReportUI, "generateReportUI");

        //adding listeners for the buttons in the side panel to go the the UI needed!
        ActiveAppointmentsButton.addActionListener(event -> {
        cardLayout2.show(contentPanel, "activeAppointmentUI");
        });
        PrescriptionsButton.addActionListener(event -> {
        cardLayout2.show(contentPanel, "prescriptionsUI");
        });
        MedicalRecordsButton.addActionListener(event -> {
        cardLayout2.show(contentPanel, "medicalRecordsUI");
        });
        PatientRecordsButton.addActionListener(event -> {
        cardLayout2.show(contentPanel, "patientRecordsUI");
        });
        DoctorRecordsButton.addActionListener(event -> {
        cardLayout2.show(contentPanel, "doctorRecordsUI");
        });
        ReceptionistRecordsButton.addActionListener(event -> {
        cardLayout2.show(contentPanel, "receptionistRecordsUI");
        });
        GenerateReportButton.addActionListener(event -> {
        cardLayout2.show(contentPanel, "generateReportUI");
        });

        add(northPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }
}

