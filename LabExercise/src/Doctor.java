import java.util.*;

public class Doctor extends User{
    private int doctor_ID, user_Age;
    private String user_Name, password, office, specialisation, user_Gender;
    private Map<Integer, Patient> patientList = new HashMap<>(); //Key is patient_ID, Value is Patient
    private Map<Integer, Appointment> AppointmentList = new HashMap(); //Key is appointment_ID, Value is Appointment

    public Doctor(String username, String password, String gender, int age, String office, String specialisation){
        super(username, password, gender, age);
        this.doctor_ID = this.setUserID("DOCTOR");
        this.office = office;
        this.specialisation = specialisation;
    }

    public Doctor(){}

    @Override
    public int getUserID(){
        return this.doctor_ID;
    }

    public String getOffice(){
        return this.office;
    }

    public String getSpecialisation(){
        return this.specialisation;
    }

    public Map<Integer, Patient> getPatientList(){
        return this.patientList;
    }

    public Map<Integer, Appointment> getAppointmentList(){
        return this.AppointmentList;
    }

    //Setters
    public void setOffice(String office){
        this.office = office;
    }

    public void setSpecialisation(String specialisation){
        this.specialisation = specialisation;
    }
}
