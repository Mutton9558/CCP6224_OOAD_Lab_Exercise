public abstract class User implements UserPermission{

    private int user_ID, user_Age;
    private String password, user_Gender;

    // username is made my the receotionist and it is the USER ID !!!!
    public User(int username, String password) {

        this.user_ID = username;
        this.password = password;
    }

    // Getters
    public int getUserID() {
        return user_ID;
    }

    public String getUserName() {
        return user_Name;
    }

    public int getUserAge() {
        return user_Age;
    }

    public String getUserGender() {
        return user_Gender;
    }

    // do we need getUserPassword?

    // Setters
    public void setUserID(int userID) {
        user_ID = userID;
    }

    public void setUserName(String userName) {
        user_Name = userName;
    }

    public void setUserAge(int userAge) {
        user_Age = userAge;
    }

    public void setUserGender(String userGender) {
        user_Gender = userGender;
    }
    
    public abstract String returnRole();
    
    public abstract boolean canViewPrescriptions();
//    patient records
    public abstract boolean canViewSelfRecords();
    
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
}
