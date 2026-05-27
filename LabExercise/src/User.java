import java.util.concurrent.atomic.AtomicInteger;

public class User {

    public enum UserRole {
        PATIENT(100),
        DOCTOR(200),
        RECEPTIONIST(300),
        ADMIN(400);

        private final AtomicInteger serial_ID;

        //Initialie the base ID value for each role
        UserRole(int role_ID) {
            this.serial_ID = new AtomicInteger(role_ID);
        }

        // Increments the current role ID and provide it for the user
        public int getRoleID() {
            return this.serial_ID.incrementAndGet();
        }
    }

    //Gonna serialise  
    private int user_ID;
    private int user_Age;
    private UserRole user_Role;
    private String user_Name, password, user_Gender;

    public User() {
        this.user_Name = "";
        this.password = "";
    }

    public User(String username, String password) {
        this.user_Name = username;
        this.password = password;
        this.user_ID = setUserID(" ");
    }

    public User(String username, String password, String gender, int age) {
        this.user_ID = setUserID(" ");
        this.user_Name = username;
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
    public int setUserID(String role){
        switch(role){
            case "PATIENT":
                user_Role = UserRole.PATIENT;
                break;
            case "DOCTOR":
                user_Role = UserRole.DOCTOR;
                break;
            case "RECEPTIONIST":
                user_Role = UserRole.RECEPTIONIST;
                break;
            case "ADMIN":
                user_Role = UserRole.DOCTOR;
                break;
            default:
                user_Role = UserRole.PATIENT;
                break;
        }
        return user_Role.getRoleID();
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
}
