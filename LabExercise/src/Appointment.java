import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private int patient_ID;
    private int doctor_ID;
    private int appointment_ID;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String location;
    private String status;
    
    public Appointment(int patient, int doctor, LocalDate date, LocalTime time, String location, String status){
        this.patient_ID = patient;
        this.doctor_ID = doctor;
        this.appointmentDate = date;
        this.appointmentTime = time;
        this.location = location;
        this.status = status;
    }
    
    public User getPatientData(){
        User appointmentPatient = new Patient();
//        db stuff here, call user controller
        return appointmentPatient;
    }
    
    public User getDoctorData(){
        User assignedDoctor = new Doctor();
//        db stuff here, call user controller
        return assignedDoctor;
    }
    
    public int getAppointmentID(){
        return this.appointment_ID;
    }

    public LocalDate getAppointmentDate(){
        return this.appointmentDate;
    }
    
    public LocalTime getAppointmentTime(){
        return this.appointmentTime;
    }
    
    public String getLocation(){
        return this.location;
    }
    
    public String getStatus(){
        return this.status;
    }
    
    public void editAppointment(LocalDate newDate, LocalTime newTime, String newStatus){
        this.appointmentDate = newDate;
        this.appointmentTime = newTime;
        this.status = newStatus;
//        db update pls use AppointmentController
    }
}
