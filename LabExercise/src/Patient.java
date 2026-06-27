import java.time.LocalDate;

public class Patient extends User {
//    Both AppointmentController and PrescriptionController can just pull individual list from DB
//    private Map<Integer, Prescription> PrescriptionList = new HashMap<>(); //Key is patient_ID, Value is Patient
//    private Map<Integer, Appointment> AppointmentList = new HashMap(); //Key is appointment_ID, Value is Appointment

    public Patient(){}
    
    //Patient will need an extra Prescriptions Array to store their prescriptions
    public Patient(int id, String username, String password, String gender, LocalDate dob){
        super(id, username, password, gender, dob);
    }

    @Override
    public String returnRole(){
        return "Patient";
    }
    
    @Override
    public boolean canViewPrescriptions(){
        return true;
    };
//    patient records
    @Override
    public boolean canViewActiveAppointments(){
        return true;
    }
    
    @Override
    public boolean canViewPatientRecords(){
        return false;
    };
    
    @Override
    public boolean canViewDoctorRecords(){
        return false;
    };
    
    @Override
    public boolean canViewReceptionistRecords(){
        return false;
    };
    
    @Override
    public boolean canUpdateAppointments(){
        return false;
    };
    
    @Override
    public boolean canAddPrescriptions(){
        return true;
    };
    
    @Override
    public boolean canAddDiagnosis(){
        return false;
    };
    
//    doctor records
    
    @Override
    public boolean canEditProfile(){
        return false;
    };
    
    @Override
    public boolean canAddAppointments(){
        return false;
    };
    
    @Override
    public boolean canGenerateReport(){
        return false;
    };
    
    @Override
    public boolean canSearchAppointments(){
        return false;
    }
    
    @Override
    public boolean canEditAppointments(){
        return false;
    }

    @Override
    public boolean canSearchRecords(){
        return false;
    }
    
    @Override
    public boolean canEditDiagnosis(){
        return false;
    };
    
    @Override
    public boolean canEditPrescription(){
        return false;
    };
    
    @Override
    public boolean canAddPatient(){
        return false;
    };
    
    @Override
    public boolean canAddDoctors(){
        return false;
    };
    
    @Override
    public boolean canAddReceptionist(){
        return false;
    };
         @Override
    public boolean canViewPatientProfile(){
        return false;
    };
    
    @Override
    public boolean canViewDoctorProfile(){
        return false;
    };
    
    @Override
    public boolean canViewReceptionistProfile(){
        return false;
    };
}

