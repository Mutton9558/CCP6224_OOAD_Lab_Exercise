import java.util.concurrent.atomic.AtomicInteger;

public abstract class User implements UserPermission{
  
//    public enum UserRole {
//        PATIENT(100),
//        DOCTOR(200),
//        RECEPTIONIST(300),
//        ADMIN(400);
//
//        private final AtomicInteger serial_ID;
//
//        //Initialie the base ID value for each role
//        UserRole(int role_ID) {
//            this.serial_ID = new AtomicInteger(role_ID);
//        }
//
//        // Increments the current role ID and provide it for the user
//        public int getRoleID() {
//            return this.serial_ID.incrementAndGet();
//        }
//    }

    private int user_ID;
    private int user_Age;
//    private UserRole user_Role;
    private String user_Name, password, user_Gender;

//    public User() {
//        this.user_Name = "";
//        this.password = "";
//    }
  
//    public User(String username, String password) {
//        this.user_Name = username;
//        this.password = password;
//        this.user_ID = setUserID(" ");
//    }
    
    public User(){}
  
    // username is made my the receotionist and it is the USER ID !!!!
    public User(int user_id, String name, String password, String gender, int age) {
        this.user_ID = user_id;
        this.user_Name = name;
        this.password = password;
        this.user_Gender = gender;
        this.user_Age = age;
    }

    public int getUserID() {
        return this.user_ID;
    }

    public String getUserName() {
        return this.user_Name;
    }

    public int getUserAge() {
        return this.user_Age;
    }

    public String getUserGender() {
        return this.user_Gender;
    }

    public String getUserPassword() {
        return this.password;
    }

    //Setters
//    public int setUserID(String role){
//        switch(role){
//            case "PATIENT":
//                user_Role = UserRole.PATIENT;
//                break;
//            case "DOCTOR":
//                user_Role = UserRole.DOCTOR;
//                break;
//            case "RECEPTIONIST":
//                user_Role = UserRole.RECEPTIONIST;
//                break;
//            case "ADMIN":
//                user_Role = UserRole.DOCTOR;
//                break;
//            default:
//                user_Role = UserRole.PATIENT;
//                break;
//        }
//        return user_Role.getRoleID();
//    }

    public void setUserID(int id){
        this.user_ID = id;
    }
    
    public void setUserName(String userName) {
        this.user_Name = userName;
    }

    public void setUserAge(int userAge) {
        this.user_Age = userAge;
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
    
    public abstract boolean canViewPrescriptions();
//    patient records
    public abstract boolean canViewSelfRecords();
    public abstract boolean canViewActiveAppointments();
    public abstract boolean canViewPatientRecords();
    public abstract boolean canViewDoctorRecords();
    public abstract boolean canViewReceptionistRecords();
    public abstract boolean canUpdateAppointments();
    public abstract boolean canAddPrescriptions();
    public abstract boolean canAddDiagnosis();
    
//    doctor records
    public abstract boolean canViewMedicalRecords();
    
    public abstract boolean canEditProfile();
    public abstract boolean canAddAppointments();
    public abstract boolean canGenerateReport();  
    public abstract boolean canSearchAppointments();
    public abstract boolean canEditAppointments();
    public abstract boolean canSearchRecords();

}
