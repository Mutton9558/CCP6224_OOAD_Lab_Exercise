import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class MainUI extends JFrame{

    public MainUI(User client){
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
        
        DashboardPanels panelList = new DashboardPanels(client);
        ArrayList<DashboardPanel> panels = panelList.returnDashboardPanels();
        DashboardUI dashboard = new DashboardUI(panels);

        mainPanel.add(homepage, "HOME");
        mainPanel.add(dashboard, "DASHBOARD");

        //show home page first 
        cardLayout.show(mainPanel, "HOME");

        //add Action listeners to the buttons that exist in those pages 
        homepage.butt5.addActionListener(event -> { cardLayout.show(mainPanel, "DASHBOARD");});
        //dashboard.btn2_m.addActionListener(event -> { cardLayout.show(mainPanel, "HOME");});

        // add the mainPanel to this frame 
        mainPanel.setOpaque(false);
        bgImage.add(mainPanel, BorderLayout.CENTER);
        setContentPane(bgImage);
        setVisible(true);

    }
        
    public static void main(String[] args) {
//        Just to test pls remove later
        UserController controller = new UserController();
        controller.loginUser(1004, "test04");
        User tempUser = controller.getCurrentUser();
        System.out.println(tempUser.getClass().getSimpleName());
        System.out.println(tempUser.returnRole());
        MainUI test1 = new MainUI(tempUser);
    }
}
