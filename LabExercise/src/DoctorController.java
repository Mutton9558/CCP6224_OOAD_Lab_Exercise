import java.util.ArrayList;
import java.util.List;

public class DoctorController extends UserController{

    private List<User> doctorList = new ArrayList<>();

    public User getDoctor(int doctorID){
        User temp = new Doctor();
        return temp;
    }

    public void registerDoctor(int id, String username, String password, String gender, int age, String office, String specialisation){
        User createdDoctor = new Doctor(id, username,password,gender,age,office,specialisation);
        doctorList.add(createdDoctor);
    }

    public Appointment getMedicalAppointment(int appointment_ID) {
        Appointment wantedAppointment = null;
        AppointmentController ac = new AppointmentController();

        for(Appointment appointment : ac.getAllAppointments()){
            if(appointment.getAppointmentID() == appointment_ID){
                return wantedAppointment;
            }
        }
        System.out.println("No such Appointment found");
        return null;
    }
}
