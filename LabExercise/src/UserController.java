import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.sql.Statement;

public class UserController{

    private User currentUser = null;
    private Map<Integer, User> userList = new HashMap<>(); //Map (More advanced version of Dictionary) to store the Users (Key is the user ID, value is the User)
                                                          // And username is key for convenience, could use ID but need to implement system for serial 

    private Map<String, User> userSubclassMap = new HashMap<>();
    
    public void UserController(){
        userSubclassMap.put("Patient", new Patient());
        userSubclassMap.put("Doctor", new Doctor());
        userSubclassMap.put("Receptionist", new Receptionist());
        userSubclassMap.put("Admin", new Admin());
        String getAllUserQuery = "SELECT * FROM Users";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement statement = conn.prepareStatement(getAllUserQuery)) {
            
             ResultSet result = statement.executeQuery();
            while (result.next()) {
                User recordedUser = userSubclassMap.get(result.getString("user_role"));
                recordedUser.setUserID(result.getInt("user_id"));
                recordedUser.setUserName(result.getString("user_name"));
                recordedUser.setUserPassword(result.getString("user_password"));
                recordedUser.setUserGender(result.getString("user_gender"));
                recordedUser.setUserAge(result.getInt("user_age"));
                userList.put(result.getInt("user_id"), recordedUser);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //User own registration
    public void registerUser(String name, String password, String gender, int age, String role){ 
        String createUserRequest = "INSERT INTO Users (user_name, user_password, user_age, user_gender, user_role) VALUES (? ? ? ? ?)";
        
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
               User newUser = userSubclassMap.get(role);
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
        }
    }
    
    public User getCurrentUser(){
        return this.currentUser;
    }
}
