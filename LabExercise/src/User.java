public class User {

    private int user_ID, user_Age;
    private String user_Name, password, user_Gender;

    public User(String username, String password){

        user_Name = username;
        this.password = password;
    }

    //Getters
    public int getUserID(){
        return user_ID;
    }

    public String getUserName(){
        return user_Name;
    }

    public int getUserAge(){
        return user_Age;
    }
    
    public String getUserGender(){
        return user_Gender;
    }

    //do we need getUserPassword?

    //Setters
    public void setUserID(int userID){
        user_ID = userID;
    }

    public void setUserName(String userName){
        user_Name = userName;
    }

    public void setUserAge(int userAge){
        user_Age = userAge;
    }
    
    public void setUserGender(String userGender){
        user_Gender = userGender;
    }
}
