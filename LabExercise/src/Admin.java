public class Admin extends User{
    public Admin(){}
    
    public Admin(int id, String name, String password, String gender, int age){
        super(id, name, password, gender, age);
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
    public boolean canEditAppointments(){
        return true;
    }
    
    @Override
    public boolean canSearchRecords(){
        return true;
    }
    
    @Override
    public boolean canEditUserProfileInfo(){
        return true;
    };
    
    @Override
    public boolean canEditUserProfile(){
        return true;
    };
    
    @Override
    public boolean canEditDiagnosis(){
        return false;
    };
    
    @Override
    public boolean canEditPrescription(){
        return false;
    };
    
    @Override
    public boolean hasMedicalRecords(){
        return false;
    };
}


