public class Patient extends User {
//    Both AppointmentController and PrescriptionController can just pull individual list from DB
//    private Map<Integer, Prescription> PrescriptionList = new HashMap<>(); //Key is patient_ID, Value is Patient
//    private Map<Integer, Appointment> AppointmentList = new HashMap(); //Key is appointment_ID, Value is Appointment

    public Patient(){}
    
    //Patient will need an extra Prescriptions Array to store their prescriptions
    public Patient(int id, String username, String password, String gender, int age){
        super(id, username, password, gender, age);
    }

//    @Override
//    public int getUserID(){
//        return this.patient_ID;
//    }

//    PrescriptionController should handle
//    public Map<Integer, Prescription> getPrescriptionList(){
//        return this.PrescriptionList;
//    }
//
//    public Map<Integer, Appointment> getAppointmentList(){
//        return this.AppointmentList;
//    }
//
//    //Setters
//    public void setPrescriptionList(Map<Integer, Prescription> prescriptionList){
//        this.PrescriptionList = prescriptionList;
//    }
//
//    public void setAppointmentList(Map<Integer, Appointment> appointmentList){
//        this.AppointmentList = appointmentList;
//    }
    
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
    public boolean canViewSelfRecords(){
        return true;
    };
    
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
}

