import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    
    public ArrayList<Appointment> getAllAppointments(){
        ArrayList<Appointment> temp = new ArrayList<>();
        String query = "SELECT * FROM Appointments";
         try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            
             ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                LocalDate recordedDate = LocalDate.parse(rs.getString("appointment_date"));
                LocalTime recordedStartTime = LocalTime.parse(rs.getString("appointment_start_time"));
                Appointment recordedAppointment = new Appointment(rs.getInt("appointment_id"), rs.getInt("patient_id"), rs.getInt("doctor_id"), recordedDate, recordedStartTime, rs.getString("appointment_location"), rs.getString("appointment_status"));
                temp.add(recordedAppointment);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }
}
