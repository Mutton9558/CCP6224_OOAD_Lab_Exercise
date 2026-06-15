import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                        LocalDate.parse(rs.getString("prescription_date")),
                        uc.searchUser(patientId).getUserName()
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

//    public Prescription getPrescription(int prescID){
//        Prescription temp = new Prescription();
//        return temp;
//    }
//
//    public List<Prescription> getPatientPrescription(int patientID){
//        List<Prescription> patientPrescriptions = new ArrayList<Prescription>();
//        for(Prescription presc : prescList){
//            if(presc.getPrescriptionPatient() == patientID){
//                patientPrescriptions.add(presc);
//            }
//        }
//        return patientPrescriptions;
//    }
//
//    public void createPrescription(String prescription_Name, String dose, String condition, String frequency, int patient_ID, LocalDate date){
//        Prescription presc = new Prescription(prescription_Name, dose, condition, frequency, patient_ID, date);
//        prescList.add(presc);
//    }
    

    

    // public Prescription[] getPatientPrescriptions(int patient_ID){

    // }
}
