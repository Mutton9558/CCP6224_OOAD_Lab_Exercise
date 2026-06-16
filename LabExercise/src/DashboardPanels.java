import javax.swing.JPanel;
import java.util.ArrayList;

public class DashboardPanels{
    private final SystemController system;
    private User client;
    private ArrayList<DashboardPanel> panels = new ArrayList<>();

    public class ActiveAppointmentPanel implements DashboardPanel{
        
        private final AppointmentController appointmentController;
        private final ActiveAppointmentsUI panelUI;
        
        public ActiveAppointmentPanel(){
            this.appointmentController = system.getAppointmentControllerInstance();
            this.panelUI = new ActiveAppointmentsUI(client, appointmentController);
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

        private final PrescriptionController prescriptionController;
        private final PrescriptionsUI panelUI;
        
        public PrescriptionPanel(){
            this.prescriptionController = system.getPrescriptionControllerInstance();
            this.panelUI = new PrescriptionsUI(client, prescriptionController);
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
            this.controller = system.getUserControllerInstance();
            this.panelUI = new DoctorRecordsUI(client, controller);
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
            this.controller = system.getUserControllerInstance();
            this.panelUI = new ReceptionistRecordsUI(client, controller);
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
            this.panelUI = new GenerateReportUI(system.getUserControllerInstance(), system.getAppointmentControllerInstance());
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
        
    public DashboardPanels(SystemController system){
        this.system = system;
        this.client = system.getUserControllerInstance().getCurrentUser();
        
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