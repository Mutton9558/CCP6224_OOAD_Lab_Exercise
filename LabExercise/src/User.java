import java.util.concurrent.atomic.AtomicInteger;
import java.util.*;
import java.time.LocalDate;
import java.time.Period;

public abstract class User implements AppointmentPermission, PrescriptionPermission,
        RecordPermission, ProfilePermission{
    private int user_ID;
    private LocalDate dateOfBirth;
//    private UserRole user_Role;
    private String user_Name, password, user_Gender;
    
    public User(){}
  
    // username is made my the receotionist and it is the USER ID !!!!
    public User(int user_id, String name, String password, String gender, LocalDate dob) {
        this.user_ID = user_id;
        this.user_Name = name;
        this.password = password;
        this.user_Gender = gender;
        this.dateOfBirth = dob;
    }

    public int getUserID() {
        return this.user_ID;
    }

    public String getUserName() {
        return this.user_Name;
    }
    

    public int getUserAge() {
        LocalDate today = LocalDate.now();
        int age = Period.between(this.dateOfBirth, today).getYears();
        return age;
    }
    
    public LocalDate getDob(){
        return this.dateOfBirth;
    }

    public String getUserGender() {
        return this.user_Gender;
    }

    public String getUserPassword() {
        return this.password;
    }

    public void setUserID(int id){
        this.user_ID = id;
    }
    
    public void setUserName(String userName) {
        this.user_Name = userName;
    }

    public void setDob(LocalDate dob) {
        this.dateOfBirth= dob;
    }

    public void setUserGender(String userGender) {
        this.user_Gender = userGender;
    }

    public void setUserPassword(String password) {
        this.password = password;
    }
    
    public abstract String returnRole();
    
    public String getOffice(){
        return null;
    }
    public String getSpecialisation(){
        return null;
    }
    
    public void setOffice(String office){}
    
    public void setSpecialization(String specialization){}
    
    public abstract boolean canViewPrescriptions();
//    patient records
    public abstract boolean canViewActiveAppointments();
    public abstract boolean canViewPatientRecords();
    public abstract boolean canViewDoctorRecords();
    public abstract boolean canViewReceptionistRecords();
    public abstract boolean canUpdateAppointments();
    public abstract boolean canAddPrescriptions();
    public abstract boolean canAddDiagnosis();
    
//    doctor records    
    public abstract boolean canEditProfile();
    public abstract boolean canAddAppointments();
    public abstract boolean canGenerateReport();  
    public abstract boolean canSearchAppointments();
    public abstract boolean canEditAppointments();
    public abstract boolean canSearchRecords();
    public abstract boolean canEditDiagnosis();
    public abstract boolean canEditPrescription();
    public abstract boolean canAddPatient();
    public abstract boolean canAddDoctors();
    public abstract boolean canAddReceptionist();

}
