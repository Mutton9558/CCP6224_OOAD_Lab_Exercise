import java.util.HashMap;
import java.util.Map;

public class Patient extends User {
    private int patient_ID, user_Age;
    private String user_Name, password, user_Gender;
    private Map<Integer, Prescription> PrescriptionList = new HashMap<>(); //Key is patient_ID, Value is Patient
    private Map<Integer, Appointment> AppointmentList = new HashMap(); //Key is appointment_ID, Value is Appointment

    public Patient(String username, String password, String gender, int age){
        super(username, password, gender, age);
        this.patient_ID = this.setUserID("PATIENT");
    }
    
    public Patient(String username, String password){
        super(username, password);
        this.patient_ID = this.setUserID("PATIENT");
    }

    public Patient(){}

    @Override
    public int getUserID(){
        return this.patient_ID;
    }

    public Map<Integer, Prescription> getPrescriptionList(){
        return this.PrescriptionList;
    }

    public Map<Integer, Appointment> getAppointmentList(){
        return this.AppointmentList;
    }

    //Setters
    public void setPrescriptionList(Map<Integer, Prescription> prescriptionList){
        this.PrescriptionList = prescriptionList;
    }

    public void setAppointmentList(Map<Integer, Appointment> appointmentList){
        this.AppointmentList = appointmentList;
    }
<<<<<<< HEAD

    @Override
    public String returnRole(){
        return "Admin";
=======
    
    @Override
    public int returnRole(){
        return patient_ID;
>>>>>>> main
    }
    
    @Override
    public boolean canViewPrescriptions(){
<<<<<<< HEAD
        return false;
=======
        return true;
>>>>>>> main
    };
//    patient records
    @Override
    public boolean canViewSelfRecords(){
<<<<<<< HEAD
=======
        return true;
    };
    
    public boolean canViewActiveAppointments(){
        return true;
    }
    
    @Override
    public boolean canViewPatientRecords(){
>>>>>>> main
        return false;
    };
    
    @Override
<<<<<<< HEAD
    public boolean canViewPatientRecords(){
        return true;
    };
    
    @Override
    public boolean canViewDoctorRecords(){
        return true;
=======
    public boolean canViewDoctorRecords(){
        return false;
>>>>>>> main
    };
    
    @Override
    public boolean canViewReceptionistRecords(){
<<<<<<< HEAD
        return true;
=======
        return false;
>>>>>>> main
    };
    
    @Override
    public boolean canUpdateAppointments(){
        return false;
    };
    
    @Override
    public boolean canAddPrescriptions(){
        return false;
    };
    
    @Override
    public boolean canAddDiagnosis(){
        return false;
    };
    
//    doctor records
    @Override
    public boolean canViewMedicalRecords(){
        return false;
    };
    
    @Override
    public boolean canEditProfile(){
<<<<<<< HEAD
        return true;
=======
        return false;
>>>>>>> main
    };
    
    @Override
    public boolean canAddAppointments(){
        return false;
    };
    
    @Override
    public boolean canGenerateReport(){
<<<<<<< HEAD
        return true;
=======
        return false;
>>>>>>> main
    };
    
    @Override
    public boolean canSearchAppointments(){
<<<<<<< HEAD
        return true;
    }

    @Override
    public boolean canSearchRecords(){
        return true;
    }

=======
        return false;
    }
>>>>>>> main
}

