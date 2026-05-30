public class Admin extends User{
    private int admin_ID, user_Age;
    private String user_Name, password, user_Gender;
    public Admin(String username, String password){
        super(username, password);
    }

    @Override
    public int returnRole(){
        return admin_ID;
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

    public boolean canViewActiveAppointments(){
        return true;
    }
    
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
        return true;
    }

    @Override
    public boolean canSearchRecords(){
        return true;
    }
}


