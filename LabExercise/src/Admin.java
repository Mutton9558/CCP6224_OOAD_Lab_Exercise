public class Admin extends User{
    public Admin(String username, String password){
        super(username, password);
    }
    
    @Override
    public String returnRole(){
        return "Admin";
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
    
    @Override
    public boolean canViewPatientRecords(){
        return true;
    };
    
    @Override
    public boolean canViewDoctorRecords(){
        return true;
    };
    
    @Override
    public boolean canViewReceptionistRecords(){
        return true;
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
        return true;
    };
    
    @Override
    public boolean canAddAppointments(){
        return false;
    };
    
    @Override
    public boolean canGenerateReport(){
        return true;
    };
    
    @Override
    public boolean canSearchAppointments(){
        return false;
    }

}


