import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private int patientID;
    private int doctorID;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String location;
    private String status;
    
    public Appointment(int patient, int doctor, LocalDate date, LocalTime time, String location, String status){
        this.patientID = patient;
        this.doctorID = doctor;
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
