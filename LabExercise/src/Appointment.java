import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private int appointmentID;
//    private String patient;
//    private String doctor;
    private int patient_ID;
    private int doctor_ID;
    private String patientName;
    private String doctorName;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String location;
    private String status;
    
    public Appointment(int appointmentID, int patientID, int doctorID, String patientName, String doctorName, 
            LocalDate date, LocalTime time, String location, String status){
        this.appointmentID = appointmentID;
        this.patient_ID = patientID;
        this.doctor_ID = doctorID;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.appointmentDate = date;
        this.appointmentTime = time;
        this.location = location;
        this.status = status;
    }
    
    public String getPatientName(){
        return this.patientName;
    }
    
    public String getDoctorName(){
        return this.doctorName;
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
}
