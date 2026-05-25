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
}
