import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

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
    
    public Appointment getAppointment(int id){
        Appointment temp = new Appointment(id, 1, 1, LocalDate.now(), LocalTime.now(), "s", "temp");
        return temp;
    }
}
