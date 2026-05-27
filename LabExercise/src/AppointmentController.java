import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class AppointmentController {
    private Appointment appointmentList[];
    
    public AppointmentController(){}
    
    public void createAppointment(int patientID, int doctorID, LocalDate date, LocalTime time, String location){
        
    }
    
    public void editAppointment(Appointment appointment){
        
    }
    
    public ArrayList<Appointment> getPatientAppointments(int patientID){
        ArrayList<Appointment> temp = new ArrayList<>();
        return temp;
    }
    
    public ArrayList<Appointment> getDoctorAppointments(int doctorID){
        ArrayList<Appointment> temp = new ArrayList<>();
        return temp;
    }
    
    public void loadAppointmentCreation(){
        
    }
    
     public static void main(String[] args){
         AppointmentController controller = new AppointmentController();
//        this is a test for me to test out the ui
        new AppointmentCreationUI(controller);
    }
}
