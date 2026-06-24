import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class HomePageUI extends JPanel{

    public JButton butt1, butt2, butt3, butt4, butt5;
    private JDialog aboutDialog, contactDialog;

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

        butt4 = new JButton("Login");
        butt4.setBackground(UIConst.DarkBlue);
        butt4.setForeground(Color.WHITE);

        butt5 = new JButton("Dashboard");
        butt5.setBackground(UIConst.DarkBlue);
        butt5.setForeground(Color.WHITE);
        
        butt6 = new JButton("My Profile");
        butt6.setBackground(UIConst.DarkBlue);
        butt6.setForeground(Color.WHITE);
        // buttons
        
        JPanel upperPanel_2 = new JPanel(new FlowLayout());
        upperPanel_2.setBackground(UIConst.DarkBlue);
        upperPanel_2.setPreferredSize(new Dimension(100, 40));
        upperPanel_2.add(butt1);
        upperPanel_2.add(butt2);
        upperPanel_2.add(butt3);
        upperPanel_2.add(butt4);
        upperPanel_2.add(butt5);
        upperPanel_2.add(butt6);
        upperPanel.add(upperPanel_2, BorderLayout.SOUTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        JLabel greetingLbl = new JLabel("Warm greetings! How may we assist you today?");
        greetingLbl.setForeground(UIConstants.VeryDarkBlue);
        greetingLbl.setFont(new Font("Arial", Font.PLAIN, 18));
        JLabel adLbl = new JLabel(UIConstants.adImg);
        
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20,40,20,40));
        greetingLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        adLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
 
        
        // JDIALOGS
        //get the parent frame 
        Frame parent = (Frame)SwingUtilities.getWindowAncestor(this);
        aboutDialog = new JDialog(parent, "About Us", true);
        aboutDialog.setSize(1200, 200);
        aboutDialog.setLocationRelativeTo(parent);
        aboutDialog.setResizable(false);
        JLabel abtLbl = new JLabel(UIConstants.aboutUsImg);
        aboutDialog.add(abtLbl);
        aboutDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        contactDialog = new JDialog(parent, "About Us", true);
        contactDialog.setSize(1200, 200);
        contactDialog.setLocationRelativeTo(parent);
        contactDialog.setResizable(false);
        JLabel contactLbl = new JLabel(UIConstants.contactImg);
        contactDialog.add(contactLbl);
        contactDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        // JDIALOGS
        
        // ABT AND CONTACT US listeners
        butt2.addActionListener(e->{aboutDialog.setVisible(true);});
        butt1.addActionListener(e->{contactDialog.setVisible(true);});
        // ABT AND CONTACT US listeners

        centerPanel.setBackground(UIConst.LightBlue);
        centerPanel.add(greetingLbl);
        centerPanel.add(adLbl);
        add(upperPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }
}
