import java.util.HashMap;
import java.util.Map;

public class UserController{

    private Map<Integer, User> userList = new HashMap<>(); //Map (More advanced version of Dictionary) to store the Users (Key is the user ID, value is the User)
                                                          // And username is key for convenience, could use ID but need to implement system for serial 

    public void UserController(){}

    //Admin/Receptionist
    public void createUser(String username, String password, String gender, int age){
        User createdUser = new User(username,password,gender, age);
        userList.put(createdUser.getUserID(), createdUser);
    }

    //User own registration
    public void registerUser(String username, String password){
        User createdUser = new User(username,password);        
        userList.put(createdUser.getUserID(), createdUser);
    }

    public void loginUser(int userID, String password){
        if(userList.containsKey(userID)) {
            User attemptLoginUser = userList.get(userID);
            if(attemptLoginUser.getUserPassword().equals(password)){
                System.out.println("Successful Login!");
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
}
