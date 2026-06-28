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
        LocalTime time, String location) {

        UserController userController = UserController.getInstance();
        User patient = userController.searchUser(patientID, "Patient");
        User doctor = userController.searchUser(doctorID, "Doctor");

        if (patient == null || doctor == null) return false;

        if (LocalDateTime.of(date, time).isBefore(LocalDateTime.now())) return false;

        LocalTime windowStart = time.minusMinutes(30);
        LocalTime windowEnd   = time.plusMinutes(30);

        String checkQuery = """
            SELECT COUNT(*) FROM Appointments
            WHERE appointment_date = ?
              AND appointment_status != 'Cancelled'
              AND appointment_time >= ?
              AND appointment_time < ?
              AND (patient_id = ? OR doctor_id = ?)
            """;

        String insertQuery = """
            INSERT INTO Appointments
                (patient_id, doctor_id, appointment_date, appointment_time, appointment_location, appointment_status)
            VALUES (?,?,?,?,?,?)
            """;

        try (Connection conn = DatabaseConfig.getConnection()) {
            try (PreparedStatement check = conn.prepareStatement(checkQuery)) {
                check.setString(1, date.toString());
                check.setString(2, windowStart.toString());
                check.setString(3, windowEnd.toString());
                check.setInt(4, patientID);
                check.setInt(5, doctorID);
                ResultSet rs = check.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    System.out.println("Conflict found");
                    return false;
                }
            }

            try (PreparedStatement insert = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                insert.setInt(1, patientID);
                insert.setInt(2, doctorID);
                insert.setString(3, date.toString());
                insert.setString(4, time.toString());
                insert.setString(5, location);
                insert.setString(6, "Scheduled");
                insert.executeUpdate();

                ResultSet keys = insert.getGeneratedKeys(); // ← was wrongly using `statement`
                if (keys.next()) {
                    int appointmentID = keys.getInt(1);
                    Appointment newAppointment = new Appointment(
                        appointmentID, patientID, doctorID,
                        patient.getUserName(), doctor.getUserName(),
                        date, time, location, "Scheduled"
                    );
                    this.appointmentMap.put(appointmentID, newAppointment);
                } else {
                    System.out.println("Failed to retrieve generated key");
                    return false;
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
