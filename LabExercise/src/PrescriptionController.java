import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionController {

    private List<Prescription> prescList = new ArrayList<Prescription>();
    private static PrescriptionController instance;
    
    private PrescriptionController(){}
    
    public static PrescriptionController getInstance(){
        if(instance == null){
            instance = new PrescriptionController();
        }
        
        return instance;
    }

    public Prescription getPrescription(int prescID){
        Prescription temp = new Prescription();
        return temp;
    }

    public void createPrescription(String prescription_Name, String dose, String condition, String frequency, LocalDate date){
        Prescription presc = new Prescription(prescription_Name, dose, condition, frequency, date);
        prescList.add(presc);
    }
    
    

    // public Prescription[] getPatientPrescriptions(int patient_ID){

    // }
}
