import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class MainUI extends JFrame{
    
    private DashboardUI dashboard;
    private UserProfileUI profile;

    public MainUI(SystemController system){
        super("MeowMeow Hospital Main Page!");
        CardLayout cardLayout = new CardLayout();
        UIConstants UIconst = new UIConstants();

        JLabel bgImage = new JLabel(UIConstants.mainBg);
        bgImage.setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1366, 768);
        setResizable(false);
        
        // set the image icon of the window
        setIconImage(UIconst.windowIcon.getImage());
        JPanel mainPanel = new JPanel(cardLayout);

        // create instance of the main UIs
        HomePageUI homepage = new HomePageUI();
        mainPanel.add(homepage, "HOME");
        //show home page first 
        cardLayout.show(mainPanel, "HOME");

        loggedOutButtonState(homepage);
//        homepage.butt3.setVisible(false);
//        homepage.butt4.setVisible(true);
//        homepage.butt5.setVisible(false);
//        homepage.butt6.setVisible(false);

        homepage.butt4.addActionListener(event -> {
            LoginUI loginDialog = new LoginUI(this, system, loggedInSystem -> {
                System.out.println("Callback fired!");
                SwingUtilities.invokeLater(() -> {  // ADD THIS
                    System.out.println("Inside invokeLater");
                    DashboardPanels panelList = new DashboardPanels(system);
                    ArrayList<DashboardPanel> panels = panelList.returnDashboardPanels();
                    dashboard = new DashboardUI(panels);
                    profile = new UserProfileUI(system.getUserControllerInstance().getCurrentUser(), system);
                    dashboard.backButton.addActionListener(backEvent -> {
                    cardLayout.show(mainPanel, "HOME");
                    });
                
                     profile.backButton.addActionListener(backEvent -> {
                    cardLayout.show(mainPanel, "HOME");
                    });
                
                    mainPanel.add(dashboard, "DASHBOARD");
                    mainPanel.add(profile, "PROFILE");
                    mainPanel.revalidate();
                    mainPanel.repaint();
                
                    loggedInButtonState(homepage);
//                homepage.butt3.setVisible(true);
//                homepage.butt4.setVisible(false);
//                homepage.butt5.setVisible(true);
//                homepage.butt6.setVisible(true);
                 });
            });
            loginDialog.setModal(true);
            loginDialog.setVisible(true);
        });

        //add Action listeners to the buttons that exist in those pages 
        homepage.butt5.addActionListener(event -> { cardLayout.show(mainPanel, "DASHBOARD");});
        homepage.butt6.addActionListener(event -> { cardLayout.show(mainPanel, "PROFILE"); });

        //action listener for Logout
        homepage.butt3.addActionListener(event ->{
            system.getUserControllerInstance().userLogout();
            loggedOutButtonState(homepage);            
            if(dashboard != null){
                mainPanel.remove(dashboard);
                dashboard = null;
            }
            if(profile != null){
                mainPanel.remove(profile);
                profile = null;
            }
            cardLayout.show(mainPanel, "HOME");
        }
        );
 
        // add the mainPanel to this frame 
        mainPanel.setOpaque(false);
        bgImage.add(mainPanel, BorderLayout.CENTER);
        setContentPane(bgImage);
        setVisible(true);
    }
        void loggedInButtonState(HomePageUI homepage){
            homepage.butt3.setVisible(true);
            homepage.butt4.setVisible(false);
            homepage.butt5.setVisible(true);
            homepage.butt6.setVisible(true);
        }
        
        void loggedOutButtonState(HomePageUI homepage){
            homepage.butt3.setVisible(false);
            homepage.butt4.setVisible(true);
            homepage.butt5.setVisible(false);
            homepage.butt6.setVisible(false);
        }
        
    public static void main(String[] args) {
//        Just to test pls remove later
//        UserController controller = new UserController();
//        controller.loginUser(1004, "test04");
//        User tempUser = controller.getCurrentUser();
//        MainUI test1 = new MainUI(tempUser);
        DatabaseInitialiser.initialiseDatabase();
        SystemController system = new SystemController();
        MainUI mainScreen = new MainUI(system);
    }
}
