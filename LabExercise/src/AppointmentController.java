import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                LocalTime recordedStartTime = LocalTime.parse(rs.getString("appointment_time"));
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
        
    public boolean createAppointment(int patientID, int doctorID, LocalDate date,
            LocalTime time, String location){
        
        UserController userController = UserController.getInstance();
        User patient = userController.searchUser(patientID, "Patient");
        User doctor = userController.searchUser(doctorID, "Doctor");
        
        LocalDateTime curDateTime = LocalDateTime.now();
        if(LocalDateTime.of(date, time).isBefore(curDateTime)){
//            user try booking a date that has past
            return false;
        }
        
        if(patient == null || doctor == null){
//            fail
            return false;
        }
        
        LocalTime windowStart = time.minusMinutes(30);
        LocalTime windowEnd = time.plusMinutes(30);

        String query = """
            SELECT COUNT(*) FROM Appointments
            WHERE appointment_date = ?
              AND appointment_status != 'Cancelled'
              AND appointment_time >= ?
              AND appointment_time < ?
              AND (patient_id = ? OR doctor_id = ?)
            """;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, date.toString());
            statement.setString(2, windowStart.toString());
            statement.setString(3, windowEnd.toString());
            statement.setInt(4, patientID);
            statement.setInt(5, doctorID);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                if(rs.getInt(1) > 0){
                    System.out.println("Conflict found");
                    return false;
                } else {
                    String patientName = patient.getUserName();
                    String doctorName = doctor.getUserName();
                    String request = "INSERT INTO Appointments (patient_id, doctor_id, appointment_date, appointment_time, appointment_location, appointment_status) VALUES (?,?,?,?,?,?)";
            //        automatically closes when exit
                    try(Connection conn2 = DatabaseConfig.getConnection();
                            PreparedStatement statement2 = conn2.prepareStatement(request, Statement.RETURN_GENERATED_KEYS)){
                        statement2.setInt(1, patientID);
                        statement2.setInt(2, doctorID);
                        statement2.setString(3, date.toString());
                        statement2.setString(4, time.toString());
                        statement2.setString(5, location);
                        statement2.setString(6, "Scheduled");
                        statement2.executeUpdate();

                        ResultSet result = statement.getGeneratedKeys();

                        if (result.next()) {
                           int appointmentID = result.getInt(1);
                           Appointment newAppointment = new Appointment(appointmentID, patientID, doctorID, patientName, doctorName, date, time, location, "Scheduled");
                           this.appointmentMap.put(appointmentID, newAppointment);
                       } else {
                           System.out.println("Fail to add user into the database");
                           return false;
                       }
                    } catch (SQLException e){
                        System.out.println(e);
                        return false;
                    }   
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
    
    public boolean editAppointment(int appointmentID, LocalDate newDate, LocalTime newTime,
            String newLocation, String newStatus){
        LocalDateTime curDateTime = LocalDateTime.now();
//        if user attempts to set a new date and time before the present
        if(LocalDateTime.of(newDate, newTime).isBefore(curDateTime)){
            return false;
        }
        
        String updateRequest = "UPDATE Appointments SET appointment_date=?, appointment_time=?, appointment_location=?, appointment_status=? WHERE appointment_id=?";
        try(Connection conn = DatabaseConfig.getConnection();
                PreparedStatement statement = conn.prepareStatement(updateRequest)
        ){
            statement.setString(1, newDate.toString());
            statement.setString(2, newTime.toString());
            statement.setString(3, newLocation);
            statement.setString(4, newStatus);
            statement.setInt(5, appointmentID);
            
            statement.executeUpdate();
        } catch (SQLException e){
            System.out.println(e);
            return false;
        }
        return true;
    }
    
    public Appointment getAppointment(int id){
        return this.appointmentMap.get(id);
    }
    
    public ArrayList<Appointment> getUserAppointments(int id){
        ArrayList<Appointment> temp = new ArrayList<>();
        
        this.appointmentMap.forEach((key, val) -> {
            if(val.getPatientID() == id || val.getDoctorID() == id){
                temp.add(val);
            }
        });
        
        return temp;
    }
    
    public ArrayList<Appointment> getActiveAppointments(){
        LocalDateTime curDateTime = LocalDateTime.now();
        ArrayList<Appointment> activeAppointments = new ArrayList<>();
        
        this.appointmentMap.forEach((key, val) -> {
            LocalDateTime appointmentDateTime = LocalDateTime.of(val.getAppointmentDate(), val.getAppointmentTime());
            if(!appointmentDateTime.isBefore(curDateTime)){
                activeAppointments.add(val);
            }
        });
        
        return activeAppointments;
    }
    
    public ArrayList<Appointment> getActiveAppointmentsByID(int userID){
        LocalDateTime curDateTime = LocalDateTime.now();
        ArrayList<Appointment> activeAppointments = new ArrayList<>();
        
        this.appointmentMap.forEach((key, val) -> {
            LocalDateTime appointmentDateTime = LocalDateTime.of(val.getAppointmentDate(), val.getAppointmentTime());
            if(!appointmentDateTime.isBefore(curDateTime) && (val.getPatientID() == userID || val.getDoctorID() == userID) ){
                activeAppointments.add(val);
            }
        });
        
        return activeAppointments;
    }
    
    public ArrayList<Appointment> getActiveAppointmentsByRole(int userID, String role){
        if(role.equals("Receptionist")){
            return getActiveAppointments();
        } else {
            return getActiveAppointmentsByID(userID);
        }
    }
    
    public ArrayList<Appointment> getPastAppointments(int userId){
        ArrayList<Appointment> pastAppointments = new ArrayList<>();
        this.appointmentMap.forEach((key, val) -> {
            if(val.getPatientID() == userId || val.getDoctorID() == userId){
                pastAppointments.add(val);
            }
        });
        
        return pastAppointments;
    }
    
    public ArrayList<Appointment> getAllAppointments(){
        ArrayList<Appointment> temp = new ArrayList<>();
        this.appointmentMap.forEach((key, val) -> {
            temp.add(val);
        });
        
        return temp;
    }
}
