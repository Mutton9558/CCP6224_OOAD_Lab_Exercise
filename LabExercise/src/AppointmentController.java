import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;

public class AppointmentController {
    private Map<Integer, Appointment> appointmentMap = new HashMap();  
    private static AppointmentController instance;
    
    private AppointmentController(){
        String query = "SELECT * FROM Appointments";
         try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            
             ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                LocalDate recordedDate = LocalDate.parse(rs.getString("appointment_date"));
                LocalTime recordedStartTime = LocalTime.parse(rs.getString("appointment_start_time"));
                int appointmentID = rs.getInt("appointment_id");
                UserController tempUC = UserController.getInstance();
//                Appointment recordedAppointment = new Appointment(rs.getInt("appointment_id"), tempUC.searchUser(rs.getInt("patient_id"), "Patient").getUserName(), tempUC.searchUser(rs.getInt("doctor_id"), "Doctor").getUserName(), recordedDate, recordedStartTime, rs.getString("appointment_location"), rs.getString("appointment_status"));
                Appointment recordedAppointment = new Appointment(
                        appointmentID, 
                        rs.getInt("patient_id"),  
                        rs.getInt("doctor_id"), 
                        tempUC.searchUser(rs.getInt("patient_id")).getUserName(),
                        tempUC.searchUser(rs.getInt("doctor_id")).getUserName(),
                        recordedDate, recordedStartTime, 
                        rs.getString("appointment_location"), 
                        rs.getString("appointment_status")
                );
                appointmentMap.put(appointmentID, recordedAppointment);
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
        return this.appointmentMap.get(id);
    }
    
    public ArrayList<Appointment> getAllAppointments(){
        ArrayList<Appointment> temp = new ArrayList<>();
        this.appointmentMap.forEach((key, val) -> {
            temp.add(val);
        });
        
        return temp;
    }
}
