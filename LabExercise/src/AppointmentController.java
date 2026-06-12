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
    private ArrayList<Appointment> appointmentList = new ArrayList<>();  
    private static AppointmentController instance;
    
    private AppointmentController(){
        String query = "SELECT * FROM Appointments";
         try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            
             ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                LocalDate recordedDate = LocalDate.parse(rs.getString("appointment_date"));
                LocalTime recordedStartTime = LocalTime.parse(rs.getString("appointment_start_time"));
                UserController tempUC = UserController.getInstance();
                Appointment recordedAppointment = new Appointment(rs.getInt("appointment_id"), tempUC.searchUser(rs.getInt("patient_id"), "Patient").getUserName(), tempUC.searchUser(rs.getInt("doctor_id"), "Doctor").getUserName(), recordedDate, recordedStartTime, rs.getString("appointment_location"), rs.getString("appointment_status"));
                appointmentList.add(recordedAppointment);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
         
    }
    
    public static AppointmentController getInstance(){
        if(instance == null){
            instance = new AppointmentController();
        }
        
        return instance;
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
        Appointment temp = new Appointment(id, "Sjae", "yus", LocalDate.now(), LocalTime.now(), "s", "temp");
        return temp;
    }
    
    public ArrayList<Appointment> getAllAppointments(){
        return this.appointmentList;
    }
}
