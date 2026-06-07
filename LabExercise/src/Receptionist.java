public class Receptionist extends User{
    public Receptionist(){}
    
    public Receptionist(int id, String username, String password, String gender, int age){
        super(id, username, password, gender, age);
    }
    
    @Override
    public String returnRole(){
        return "Receptionist";
    }
    
    @Override
    public boolean canViewPrescriptions(){
        return false;
    };
//    patient records
    @Override
    public boolean canViewSelfRecords(){
        return false;
    };
    
    public boolean canViewActiveAppointments(){
        return true;
    }
    
    @Override
    public boolean canViewPatientRecords(){
        return true;
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
        return true;
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
        return true;
    };
    
    @Override
    public boolean canEditProfile(){
        return true;
    };
    
    @Override
    public boolean canAddAppointments(){
        return true;
    };
    
    @Override
    public boolean canGenerateReport(){
        return false;
    };
    
    @Override
    public boolean canSearchAppointments(){
        return true;
    }
    
    @Override
    public boolean canEditAppointments(){
        return true;
    }

    @Override
    public boolean canSearchRecords(){
        return true;
    }
}
