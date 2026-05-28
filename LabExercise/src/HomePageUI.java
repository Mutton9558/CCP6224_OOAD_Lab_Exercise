import java.awt.*;
import javax.swing.*;

public class HomePageUI extends JPanel {

    public JButton butt1, butt2, butt3, butt4, butt5;

    public HomePageUI() {

        UIConstants UIConst = new UIConstants();
        setLayout(new BorderLayout(0, 48));
        setOpaque(false);

        JPanel upperPanel = new JPanel(new BorderLayout());
        upperPanel.setBackground(UIConst.DarkBlue);
        upperPanel.setPreferredSize(new Dimension(100, 140));

        JPanel upperPanel_1 = new JPanel(new FlowLayout());
        upperPanel_1.setBackground(UIConst.DarkBlue);

        // create the logo button, place at the west
        //JLabel MeowMeowLogo = new JLabel(uiConstants.MMlogo);
        JButton MeowMeowLogo = new JButton(UIConst.MMlogo);
        MeowMeowLogo.setBorderPainted(false);
        MeowMeowLogo.setContentAreaFilled(false);
        MeowMeowLogo.setFocusPainted(false);
        MeowMeowLogo.setOpaque(false);

        upperPanel_1.add(MeowMeowLogo);
        upperPanel.add(upperPanel_1, BorderLayout.WEST);

        // buttons
        butt1 = new JButton("Contact Us");
        butt1.setBackground(UIConst.DarkBlue);
        butt1.setForeground(Color.WHITE);

        butt2 = new JButton("About Us");
        butt2.setBackground(UIConst.DarkBlue);
        butt2.setForeground(Color.WHITE);

        butt3 = new JButton("Find a Doctor");
        butt3.setBackground(UIConst.DarkBlue);
        butt3.setForeground(Color.WHITE);

        // shawn pls hide this once user is logged in
        butt4 = new JButton("Login");
        butt4.setBackground(UIConst.DarkBlue);
        butt4.setForeground(Color.WHITE);

        // shawn pls make this appear once user is logged in and hidden when just starting
        butt5 = new JButton("Dashboard");
        butt5.setBackground(UIConst.DarkBlue);
        butt5.setForeground(Color.WHITE);
        // buttons
        
        JPanel upperPanel_2 = new JPanel(new FlowLayout());
        upperPanel_2.setBackground(UIConst.DarkBlue);
        upperPanel_2.setPreferredSize(new Dimension(100, 40));
        upperPanel_2.add(butt1);
        upperPanel_2.add(butt2);
        upperPanel_2.add(butt3);
        upperPanel_2.add(butt4);
        upperPanel_2.add(butt5);
        upperPanel.add(upperPanel_2, BorderLayout.SOUTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(UIConst.LightBlue);

        add(upperPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }
}
