import javax.swing.JPanel;
import java.util.ArrayList;

public class DashboardPanels{
    private final User client;
    private ArrayList<DashboardPanel> panels = new ArrayList<>();

    public class ActiveAppointmentPanel implements DashboardPanel{
        
        private AppointmentController controller;
        private ActiveAppointmentsUI panelUI;
        
        public ActiveAppointmentPanel(){
            this.controller = new AppointmentController();
            ArrayList<Appointment> list = controller.getAllAppointments();
            this.panelUI = new ActiveAppointmentsUI(client, list, controller::getAppointment);
        }
        
        @Override
        public String getName(){
            return "Active Appointment";
        }
        
        @Override
        public boolean isVisible(){
            return client.canViewActiveAppointments();
        }
        
        @Override
        public JPanel getPanel(){
            return panelUI;
        }
    }
    
    public class PrescriptionPanel implements DashboardPanel{

        private PrescriptionController controller;
        private PrescriptionsUI panelUI;
        
        public PrescriptionPanel(){
            this.controller = new PrescriptionController();
            this.panelUI = new PrescriptionsUI(client, controller::getPrescription);
        }
        
        @Override
        public String getName(){
            return "Prescriptions";
        }
        
        @Override
        public boolean isVisible(){
            return client.canViewPrescriptions();
        }
        
        @Override
        public JPanel getPanel(){
            return panelUI;
        }
    }
    
    public class MedicalRecordsPanel implements DashboardPanel{

        private MedicalRecordsUI panelUI;
        
        public MedicalRecordsPanel(){
            this.panelUI = new MedicalRecordsUI();
        }
        
        @Override
        public String getName(){
            return "Medical Records";
        }
        
        @Override
        public boolean isVisible(){
            return client.canViewSelfRecords();
        }
        
        @Override
        public JPanel getPanel(){
            return panelUI;
        }
    }
    
    public class PatientRecordsPanel implements DashboardPanel{

        private PatientRecordsUI panelUI;
        
        public PatientRecordsPanel(){
            this.panelUI = new PatientRecordsUI();
        }
        
        @Override
        public String getName(){
            return "Patient Records";
        }
        
        @Override
        public boolean isVisible(){
            return client.canViewPatientRecords();
        }
        
        @Override
        public JPanel getPanel(){
            return panelUI;
        }
    }
    
    public class DoctorRecordsPanel implements DashboardPanel{

        private UserController controller;
        private DoctorRecordsUI panelUI;
        
        public DoctorRecordsPanel(){
            this.controller = new UserController();
            this.panelUI = new DoctorRecordsUI(client, controller::searchUser, controller::getDoctors);
        }
        
        @Override
        public String getName(){
            return "Doctor Records";
        }
        
        @Override
        public boolean isVisible(){
            return client.canViewDoctorRecords();
        }
        
        @Override
        public JPanel getPanel(){
            return panelUI;
        }
    }
    
    public class ReceptionistRecordsPanel implements DashboardPanel{

        private ReceptionistRecordsUI panelUI;
        private UserController controller;
        
        public ReceptionistRecordsPanel(){
            this.controller = new UserController();
            this.panelUI = new ReceptionistRecordsUI(client, controller::searchUser);
        }
        
        @Override
        public String getName(){
            return "Receptionist Records";
        }
        
        @Override
        public boolean isVisible(){
            return client.canViewReceptionistRecords();
        }
        
        @Override
        public JPanel getPanel(){
            return panelUI;
        }
    }
    
    public class GenerateReportPanel implements DashboardPanel{

        private GenerateReportUI panelUI;
        
        public GenerateReportPanel(){
            this.panelUI = new GenerateReportUI();
        }
        
        @Override
        public String getName(){
            return "Generate Report";
        }
        
        @Override
        public boolean isVisible(){
            return client.canGenerateReport();
        }
        
        @Override
        public JPanel getPanel(){
            return panelUI;
        }
    }
        
    public DashboardPanels(User client){
        this.client = client;
        
        this.panels.add(new ActiveAppointmentPanel());
        this.panels.add(new PrescriptionPanel());
        this.panels.add(new MedicalRecordsPanel());
        this.panels.add(new PatientRecordsPanel());
        this.panels.add(new DoctorRecordsPanel());
        this.panels.add(new ReceptionistRecordsPanel());
        this.panels.add(new GenerateReportPanel());
    }
    
    public ArrayList<DashboardPanel> returnDashboardPanels(){
        return this.panels;
    }
}