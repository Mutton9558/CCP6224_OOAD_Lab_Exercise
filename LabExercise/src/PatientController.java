import java.util.HashMap;
import java.util.Map;

public class PatientController extends UserController {

    private final Map<Integer, Patient> patientList = new HashMap<>();

    public void addPrescription(Prescription prescription, int patient_ID) {
        if(patientList.containsKey(patient_ID)){
            Patient patientPrescription = patientList.get(patient_ID);
            Map<Integer, Prescription> prescriptionList = patientPrescription.getPrescriptionList();
            prescriptionList.put(patientPrescription.getUserID(), prescription);
            patientPrescription.setPrescriptionList(prescriptionList);
        }
    }

    public void addMedicalAppointment(Appointment appointment, int patient_ID) {
        if(patientList.containsKey(patient_ID)){
            Patient patientAppointment = patientList.get(patient_ID);
            Map<Integer, Appointment> appointmentList = patientAppointment.getAppointmentList();
            appointmentList.put(appointment.getAppointmentID(), appointment);
            patientAppointment.setAppointmentList(appointmentList);
        }
    }

    public Prescription getPrescription(int prescription_ID, int patient_ID) {
        Patient wantedPatient = null;
        
        if(patientList.containsKey(patient_ID)){
            Patient patientpatient = patientList.get(patient_ID);
            Map<Integer, Patient> patientList = patientpatient.getPatientList();
            if(patientList.containsKey(patient_ID)){
                wantedPatient = patientList.get(patient_ID);
            }
            else{
                System.out.println("Appointment not found in patient list");
                wantedPatient = patientList.get(patient_ID);
            }
        }
        return wantedPatient;
    }

    public Appointment getMedicalAppointment(int appointment_ID, int patient_ID) {
        Appointment wantedAppointment = null;
        
        if(patientList.containsKey(patient_ID)){
            patient patientAppointment = patientList.get(patient_ID);
            Map<Integer, Appointment> appointmentList = patientAppointment.getAppointmentList();
            if(appointmentList.containsKey(appointment_ID)){
                wantedAppointment = appointmentList.get(appointment_ID);
            }
            else{
                System.out.println("Appointment not found in patient list");
                wantedAppointment = appointmentList.get(appointment_ID);
            }
        }
        return wantedAppointment;
    }

}
