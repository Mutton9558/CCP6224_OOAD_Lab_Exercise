import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private int appointmentID;
    private String patient;
    private String doctor;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String location;
    private String status;
    
    public Appointment(int appointmentID, String patient, String doctor, LocalDate date, LocalTime time, String location, String status){
        this.appointmentID = appointmentID;
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentDate = date;
        this.appointmentTime = time;
        this.location = location;
        this.status = status;
    }
    
    public String getPatientName(){
        return this.patient;
    }
    
    public String getDoctorName(){
        return this.doctor;
    }
    
    public int getPatientID(){
        return this.patient_ID;
    }
    
    public int getDoctorID(){
        return this.doctor_ID;
    }
    
    public int getAppointmentID(){
        return this.appointmentID;
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
