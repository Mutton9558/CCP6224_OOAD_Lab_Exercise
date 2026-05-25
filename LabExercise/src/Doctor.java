public class Doctor extends User{
    protected int doctor_ID;
    protected String location;
    protected Patient[] PatientList;
    protected String specialisation;
    protected Appointment[] AppointmentList;

    public Doctor(int userID, String username, String pass, String location, String specialisation){
        super(username, pass);
        this.doctor_ID = userID;
        this.location = location;
        this.specialisation = specialisation;
    }
}
