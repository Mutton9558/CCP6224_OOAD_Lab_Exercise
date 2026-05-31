import java.util.*;

public class Doctor extends User{
    private int doctor_ID, user_Age;
    private String user_Name, password, office, specialisation, user_Gender;
    private Map<Integer, Patient> PatientList = new HashMap<>(); //Key is patient_ID, Value is Patient
    private Map<Integer, Appointment> AppointmentList = new HashMap(); //Key is appointment_ID, Value is Appointment

    public Doctor(String username, String password, String gender, int age, String office, String specialisation){
        super(username, password, gender, age);
        this.doctor_ID = this.setUserID("DOCTOR");
        this.office = office;
        this.specialisation = specialisation;
    }
    
    public Doctor(String username, String password){
        super(username, password);
        this.doctor_ID = this.setUserID("Doctor");
    }

    public Doctor(){}

    @Override
    public int getUserID(){
        return this.doctor_ID;
    }

    public String getOffice(){
        return this.office;
    }

    public String getSpecialisation(){
        return this.specialisation;
    }

    public Map<Integer, Patient> getPatientList(){
        return this.PatientList;
    }

    public Map<Integer, Appointment> getAppointmentList(){
        return this.AppointmentList;
    }

    //Setters
    public void setOffice(String office){
        this.office = office;
    }

    public void setSpecialisation(String specialisation){
        this.specialisation = specialisation;
    }

    public void setPatientList(Map<Integer, Patient> patientList){
        this.PatientList = patientList;
    }

    public void setAppointmentList(Map<Integer, Appointment> appointmentList){
        this.AppointmentList = appointmentList;
    }
    
    @Override
    public int returnRole(){
        return this.doctor_ID;
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
    
//    doctor records
    @Override
    public boolean canViewMedicalRecords(){
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
}
