import java.util.HashMap;
import java.util.Map;

public class DoctorController{

    protected final Map<Integer, Doctor> doctorList = new HashMap<>(); //Username might be better ngl

    public void registerDoctor(String username, String password, String gender, int age, String office, String specialisation){
        Doctor createdDoctor = new Doctor(username,password,gender,age,office,specialisation);
        doctorList.put(createdDoctor.getUserID(), createdDoctor);
    }

    public void addPatient(Patient patient, int doctor_ID) {
        if(doctorList.containsKey(doctor_ID)){
            Doctor patientDoctor = doctorList.get(doctor_ID);
            Map<Integer, Patient> patientList = patientDoctor.getPatientList();
            patientList.put(patient.getUserID(), patient);
            patientDoctor.setPatientList(patientList);
        }
    }

    public void addMedicalAppointment(Appointment appointment, int doctor_ID) {
        if(doctorList.containsKey(doctor_ID)){
            Doctor doctorAppointment = doctorList.get(doctor_ID);
            Map<Integer, Appointment> appointmentList = doctorAppointment.getAppointmentList();
            appointmentList.put(appointment.getAppointmentID(), appointment);
            doctorAppointment.setAppointmentList(appointmentList);
        }
    }

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
