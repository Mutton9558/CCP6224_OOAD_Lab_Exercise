import java.util.*;
import java.time.LocalDate;

public class Doctor extends User{
    private String office, specialisation;
    private int doctorID;

    public Doctor(int id, String username, String password, String gender, LocalDate dob, String office, String specialisation){
        super(id, username, password, gender, dob);
        this.doctorID = id;
        this.office = office;
        this.specialisation = specialisation;
    }
    
//    public Doctor(String username, String password){
//        super(username, password);
//        this.doctor_ID = this.setUserID("Doctor");
//    }

    public Doctor(){}

    @Override
    public String returnRole(){
        return "Doctor";
    }
    
    @Override
    public final String getOffice(){
        return this.office;
    }

    @Override
    public final String getSpecialisation(){
        return this.specialisation;
    }

//    public Map<Integer, Patient> getPatientList(){
//        return this.PatientList;
//    }
//
//    public Map<Integer, Appointment> getAppointmentList(){
//        return this.AppointmentList;
//    }

    //Setters
    @Override
    public void setOffice(String office){
        this.office = office;
    }

    @Override
    public void setSpecialization(String specialisation){
        this.specialisation = specialisation;
    }

//    public void setPatientList(Map<Integer, Patient> patientList){
//        this.PatientList = patientList;
//    }
//
//    public void setAppointmentList(Map<Integer, Appointment> appointmentList){
//        this.AppointmentList = appointmentList;
//    }
//    
//    @Override
//    public int returnRole(){
//        return this.doctor_ID;
//    }
    
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
        return false;
    };
    
    @Override
    public boolean canAddPrescriptions(){
        return true;
    };
    
    @Override
    public boolean canAddDiagnosis(){
        return true;
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
        return true;
    };
    
    @Override
    public boolean canEditPrescription(){
        return true;
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
        return true;
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