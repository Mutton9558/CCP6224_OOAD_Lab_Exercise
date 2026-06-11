public class SystemController {
//    orchestrate and manage different controllers cuz we have too many lol
    private final UserController userControllerInstance;
    private final PrescriptionController prescriptionControllerInstance;
    private final AppointmentController appointmentControllerInstance;
    
    public SystemController(){
        this.userControllerInstance = UserController.getInstance();
        this.prescriptionControllerInstance = PrescriptionController.getInstance();
        this.appointmentControllerInstance = AppointmentController.getInstance();
    }
    
    public UserController getUserControllerInstance(){
        return userControllerInstance;
    }
    
    public PrescriptionController getPrescriptionControllerInstance(){
        return prescriptionControllerInstance;
    }
    
    public AppointmentController getAppointmentControllerInstance(){
        return appointmentControllerInstance;
    }
}
