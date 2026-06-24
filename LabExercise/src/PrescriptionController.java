import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class PrescriptionController {

    private Map<Integer, Prescription> prescMap = new HashMap();
    private static PrescriptionController instance;
    
    private PrescriptionController(){
        String query = "SELECT * FROM Prescriptions";
        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement statement = conn.prepareStatement(query)
        ) {
            
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                UserController uc = UserController.getInstance();
                int prescriptionId = rs.getInt("prescription_id");
                int patientId = rs.getInt("prescription_target");
                Prescription recordedPrescriptions = new Prescription(
                        prescriptionId, 
                        rs.getString("prescription_name"),  
                        rs.getString("prescription_dosage"),
                        rs.getString("prescription_condition"),
                        rs.getString("prescription_frequency"),
                        patientId,
                        LocalDate.parse(rs.getString("prescription_start_date")),
                        LocalDate.parse(rs.getString("prescription_end_date")),
                        rs.getInt("prescription_author")
                );
                this.prescMap.put(prescriptionId, recordedPrescriptions);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static PrescriptionController getInstance(){
        if(instance == null){
            instance = new PrescriptionController();
        }
        
        return instance;
    }

    public Prescription getPrescription(int prescID){
        return this.prescMap.get(prescID);
    }
    
//
    public ArrayList<Prescription> getActivePrescription(int userID){
        ArrayList<Prescription> prescriptions = new ArrayList<>();
        prescMap.forEach((key, val) -> {
           if(val.getPrescriptionPatient() == userID || val.getPrescriptionDoctor() == userID){
               if(val.getPrescriptionEnd().isAfter(LocalDate.now())){
                    prescriptions.add(val);
               }
           } 
        });
        return prescriptions;
    }
    
    public ArrayList<Prescription> getPastPrescription(int userID){
        ArrayList<Prescription> prescriptions = new ArrayList<>();
        prescMap.forEach((key, val) -> {
           if(val.getPrescriptionPatient() == userID || val.getPrescriptionDoctor() == userID){
               if(val.getPrescriptionEnd().isBefore(LocalDate.now())){
                    prescriptions.add(val);
               }
           } 
        });
        return prescriptions;
    }

    public boolean createPrescription(String prescription_Name, String dose, String condition, String frequency, int patient_ID, int doctor_ID, LocalDate date, LocalDate end){
        
        User patient = UserController.getInstance().searchUser(patient_ID, "Patient");
        if(patient == null){
//            fail bcs user doesnt exist
            return false;
        }
        
        String createPrescriptionRequest = "INSERT INTO Prescriptions (prescription_name, prescription_dosage, prescription_start_date, prescription_end_date, prescription_frequency, prescription_condition, prescription_target, prescription_author) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement statement = conn.prepareStatement(createPrescriptionRequest, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, prescription_Name);
            statement.setString(2, dose);
            statement.setString(3, date.toString());
            statement.setString(4, end.toString());
            statement.setString(5, frequency);
            statement.setString(6, condition);
            statement.setInt(7, patient_ID);
            statement.setInt(8, doctor_ID);
            statement.executeUpdate();
            
            ResultSet result = statement.getGeneratedKeys();
           if (result.next()) {
               int newPrescriptionID = result.getInt(1);
               Prescription newPrescription = new Prescription(newPrescriptionID,prescription_Name,dose,condition,frequency,patient_ID,date, end, doctor_ID);
               prescMap.put(newPrescriptionID, newPrescription);
               return true;
           } else {
               System.out.println("Fail to add user into the database");
               return false;
           }
       } catch (SQLException e) {
           e.printStackTrace();
           return false;
       }
    }
}
