import java.time.LocalDate;

public class Receptionist extends User{
    public Receptionist(){}
    
    public Receptionist(int id, String username, String password, String gender, LocalDate dob){
        super(id, username, password, gender, dob);
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
        return true;
    };
    
    @Override
    public boolean canAddDoctors(){
        return false;
    };
    
    @Override
    public boolean canAddReceptionist(){
        return false;
    };
}
