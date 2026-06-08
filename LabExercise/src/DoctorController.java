import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class DoctorController{

    private List<Doctor> doctorList = new ArrayList<Doctor>();

    public Doctor getDoctor(int doctorID){
        Doctor temp = new Doctor("","");
        return temp;
    }

    public void registerDoctor(String username, String password, String gender, int age, String office, String specialisation){
        Doctor createdDoctor = new Doctor(username,password,gender,age,office,specialisation);
        doctorList.add(createdDoctor);
    }

    //I'll rework the list thing ltr
    public void addPatient(Patient patient, int doctor_ID) {
        if(doctorList.contains(doctor_ID)){
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
