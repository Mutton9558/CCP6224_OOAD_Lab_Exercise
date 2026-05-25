public class User {
    protected int user_ID;
    protected String user_Name;
    protected String password;
    protected int user_Age;
    protected String user_Gender;
    
    public User(String username, String pass){
        this.user_Name = username;
        this.password = pass;
    }
}