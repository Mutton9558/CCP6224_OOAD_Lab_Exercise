import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.time.LocalDate;
//import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.function.Supplier;

public class UserController{

    private User currentUser = null;
    private Map<Integer, User> userList = new HashMap<>();
    private static UserController instance;
    
    private static final Map<String, Supplier<User>> ROLE_FACTORIES = Map.of(
        "Patient",      Patient::new,
        "Doctor",       Doctor::new,
        "Receptionist", Receptionist::new,
        "Admin",        Admin::new
    );

    private User createUserByRole(String role) {
        Supplier<User> factory = ROLE_FACTORIES.get(role);
        if (factory == null) throw new IllegalArgumentException("Unknown role: " + role);
        return factory.get();
    }
    
    private UserController(){
        String getAllUserQuery = "SELECT * FROM Users";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement statement = conn.prepareStatement(getAllUserQuery)) {
            
             ResultSet result = statement.executeQuery();
            while (result.next()) {
                User recordedUser = createUserByRole(result.getString("user_role"));
                recordedUser.setUserID(result.getInt("user_id"));
                recordedUser.setUserName(result.getString("user_name"));
                recordedUser.setUserPassword(result.getString("user_password"));
                recordedUser.setUserGender(result.getString("user_gender"));
                recordedUser.setUserAge(result.getInt("user_age"));
                recordedUser.setOffice(result.getString("office"));
                recordedUser.setSpecialization(result.getString("specialization"));
                userList.put(result.getInt("user_id"), recordedUser);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static UserController getInstance() {
        if (instance == null) {
            instance = new UserController();
        }
        return instance;
    }
    

    //User own registration
    public void registerUser(String name, String password, String gender, int age, String role){ 
        String createUserRequest = "INSERT INTO Users (user_name, user_password, user_age, user_gender, user_role) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement statement = conn.prepareStatement(createUserRequest, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, name);
            statement.setString(2, password);
            statement.setInt(3, age);
            statement.setString(4, gender);
            statement.setString(5, role);
            statement.executeUpdate();
            
            ResultSet result = statement.getGeneratedKeys();
           if (result.next()) {
               int newUserId = result.getInt(1);
               User newUser = createUserByRole(role);
               newUser.setUserID(newUserId);
               newUser.setUserName(name);
               newUser.setUserPassword(password);
               newUser.setUserAge(age);
               newUser.setUserGender(gender);
               
               userList.put(newUserId, newUser);
           } else {
               System.out.println("Fail to add user into the database");
           }

       } catch (SQLException e) {
           e.printStackTrace();
       }
    }
    
//    overloaded for Doctors
    public void registerUser(String name, String password, String gender, int age, String role, String office, String specialization){ 
        String createUserRequest = "INSERT INTO Users (user_name, user_password, user_age, user_gender, user_role, office, specialization) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement statement = conn.prepareStatement(createUserRequest, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, name);
            statement.setString(2, password);
            statement.setInt(3, age);
            statement.setString(4, gender);
            statement.setString(5, role);
            statement.setString(6, office);
            statement.setString(7, specialization);
            statement.executeUpdate();
            
            ResultSet result = statement.getGeneratedKeys();
           if (result.next()) {
               int newUserId = result.getInt(1);
               User newUser = createUserByRole(role);
               newUser.setUserID(newUserId);
               newUser.setUserName(name);
               newUser.setUserPassword(password);
               newUser.setUserAge(age);
               newUser.setUserGender(gender);
               newUser.setOffice(office);
               newUser.setSpecialization(specialization);
               
               userList.put(newUserId, newUser);
           } else {
               System.out.println("Fail to add user into the database");
           }

       } catch (SQLException e) {
           e.printStackTrace();
       }
    }
    

    public void loginUser(int userID, String password){
        if(userList.containsKey(userID)) {
            User attemptLoginUser = userList.get(userID);
            if(attemptLoginUser.getUserPassword().equals(password)){
                System.out.println("Successful Login!");
                this.currentUser = attemptLoginUser;
                //Send to Login Interface to change to DashboardUI
            }
            else{
                //Loop getting password until get correct password
                while(!attemptLoginUser.getUserPassword().equals(password)){
                    System.out.println("Username or password is incorrect! Please try again");
                    // Send to Login Interface to grab the keyed in username & password
                    loginUser(userID, password);
                }
            }
        } else {
            System.out.println("No such user");
        }
    }
    
    public User getCurrentUser(){
        return this.currentUser;
    }
    
    
    public ArrayList<User> getUsersByRole(String role){
        ArrayList<User> temp = new ArrayList<>();
        userList.forEach((key, val) -> {
            if(val.returnRole().equals(role)){
                temp.add(val);
            }
        });
        return temp;
    }
    
    public User searchUser(int id, String role){
//        temp
        if(userList.containsKey(id)){
            User temp = userList.get(id);
            if(temp.returnRole().equals(role)){
                return userList.get(id);
            }
        } 
        return null;
    }
    
    public User searchUserID(int id){
        return userList.get(id);
    }
    
    
  
}
