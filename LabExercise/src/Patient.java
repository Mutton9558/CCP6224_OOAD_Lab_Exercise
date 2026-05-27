import java.util.HashMap;
import java.util.Map;

public class Patient extends User {
    
    //Edit Later to suit Patient
    private int doctor_ID, user_Age;
    private String user_Name, password, office, specialisation, user_Gender;
    private Map<Integer, Patient> patientList = new HashMap<>(); //Key is patient_ID, Value is Patient
    private Map<Integer, Appointment> AppointmentList = new HashMap(); //Key is appointment_ID, Value is Appointment

}
