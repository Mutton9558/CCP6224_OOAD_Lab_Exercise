import java.time.LocalDate;

public class Admin extends User{
    public Admin(){}
    
    public Admin(int id, String name, String password, String gender, LocalDate dob){
        super(id, name, password, gender, dob);
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
    public boolean canViewActiveAppointments(){
        return false;
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
        return false;
    }
    
    @Override
    public boolean canSearchRecords(){
        return true;
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
        return true;
    };
    
    @Override
    public boolean canAddReceptionist(){
        return true;
    };
    
    @Override
    public boolean canViewPatientProfile(){
        return false;
    };
    
    @Override
    public boolean canViewDoctorProfile(){
        return true;
    };
    
    @Override
    public boolean canViewReceptionistProfile(){
        return true;
    };
}


