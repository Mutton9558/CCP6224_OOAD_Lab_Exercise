import java.util.*;

public class Doctor extends User{
    protected int doctor_ID;
    protected String office;
    protected List<Patient> patientList = new ArrayList<>();
    protected String specialisation;
    protected Appointment[] AppointmentList;

    public Doctor(int userID, String username, String pass, String office, String specialisation){
        super(username, pass);
        this.doctor_ID = userID;
        this.office = office;
        this.specialisation = specialisation;
    }
}
