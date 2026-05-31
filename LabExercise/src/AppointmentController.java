import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

public class AppointmentController {
    private Appointment appointmentList[];
    private User client;
    private final JComponent parent;
    
    public AppointmentController(JComponent parent, User client){
        this.parent = parent;
        this.client = client;
    }
    
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
    
    public Appointment getAppointment(int id){
        Appointment temp = new Appointment(id, 1, 1, LocalDate.now(), LocalTime.now(), "s", "temp");
        return temp;
    }
    
    public void loadAppointmentCreation() {
        Window window = SwingUtilities.getWindowAncestor(parent);
        AppointmentCreationUI dialog = new AppointmentCreationUI(window);
        dialog.setVisible(true);
    }
    
    public void loadEditAppointment(Appointment target) {
        Window window = SwingUtilities.getWindowAncestor(parent);
        EditAppointmentUI dialog = new EditAppointmentUI(window, target);
        dialog.setVisible(true);
    }
    
    public ActiveAppointmentsUI loadActiveAppointments(){
        ActiveAppointmentsUI activeAppointmentsUI = new ActiveAppointmentsUI(client, () -> loadAppointmentCreation(), this::loadEditAppointment, this::getAppointment);
        return activeAppointmentsUI;
    }
}
