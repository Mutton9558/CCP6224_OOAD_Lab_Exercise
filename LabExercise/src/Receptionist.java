public class Receptionist extends User{
    public Receptionist(String username, String password){
        super(username, password);
    }
    
    @Override
    public String returnRole(){
        return "Receptionist";
    }
    
    @Override
    public boolean canViewPrescriptions(){
        return true;
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
}
