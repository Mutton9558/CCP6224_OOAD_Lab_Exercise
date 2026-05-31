import java.awt.*;
import javax.swing.*;

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
        DashboardUI dashboard = new DashboardUI(client);

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
        User tempUser = new Receptionist("Shawn", "1");
        MainUI test1 = new MainUI(tempUser);
    }
}
