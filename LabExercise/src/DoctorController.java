import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.awt.*;

public class DoctorController extends UserController{

    private List<Doctor> doctorList = new ArrayList<Doctor>();

    

    public Patient getPatientData(int patient_ID, int doctor_ID) {
        Patient wantedPatient = null;
        
        if(doctorList.containsKey(doctor_ID)){
            Doctor patientDoctor = doctorList.get(doctor_ID);
            Map<Integer, Patient> patientList = patientDoctor.getPatientList();
            if(patientList.containsKey(patient_ID)){
                wantedPatient = patientList.get(patient_ID);
            }
            else{
                System.out.println("Appointment not found in doctor list");
                wantedPatient = patientList.get(patient_ID);
            }
        }
        return wantedPatient;
    }

    public Appointment getMedicalAppointment(int appointment_ID, int doctor_ID) {
        Appointment wantedAppointment = null;
        
        if(doctorList.containsKey(doctor_ID)){
            Doctor doctorAppointment = doctorList.get(doctor_ID);
            Map<Integer, Appointment> appointmentList = doctorAppointment.getAppointmentList();
            if(appointmentList.containsKey(appointment_ID)){
                wantedAppointment = appointmentList.get(appointment_ID);
            }
            else{
                System.out.println("Appointment not found in doctor list");
                wantedAppointment = appointmentList.get(appointment_ID);
            }
        }
        return wantedAppointment;
    }
}
