public class LoginController {
//  interface so LoginUI can hand the SystemController back to MainUI on success
    public interface LoginCallback {
        void onLoginSuccess(SystemController system);
    }

    private static LoginController instance;
    private SystemController system;

    private LoginController() {
        this.system = new SystemController();
    }

    public static LoginController getInstance() {
        if (instance == null) {
            instance = new LoginController();
        }
        return instance;
    }

    // returns true if login succeeded, false if not
    public boolean validateLogin(int userID, String password) {
        system.getUserControllerInstance().checkCredentials(userID, password);
        return system.getUserControllerInstance().getCurrentUser() != null;
    }

    public SystemController getSystem() {
        return system;
    }
}
