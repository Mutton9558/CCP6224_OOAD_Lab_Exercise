import java.awt.*;
import javax.swing.*;

public class HomePageUI extends JFrame {

    public HomePageUI() {

        super("Mao Mao Hospital Main Page!");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1366, 768);

        ImageIcon bgImage = new ImageIcon(getClass().getResource("/images/mainbg.jpg"));

        Image sizedBgImage = bgImage.getImage().getScaledInstance(1366, 768, Image.SCALE_SMOOTH);
        ImageIcon mainBg = new ImageIcon(sizedBgImage);
        JLabel mainBackground = new JLabel(mainBg);
        setContentPane(mainBackground);
        mainBackground.setLayout(new BorderLayout());

        // COLOR VARIABLES
        Color DarkBlue = new Color(0x3b91fe);
        //

        JPanel upperPanel = new JPanel(new BorderLayout());
        upperPanel.setBackground(DarkBlue);
        upperPanel.setPreferredSize(new Dimension(100, 140));

        JPanel upperPanel_1 = new JPanel(new FlowLayout());
        upperPanel_1.setBackground(DarkBlue);

        ImageIcon MMIcon = new ImageIcon(getClass().getResource("/images/meowmeowlogo.png"));
        Image sizedMMIcon = MMIcon.getImage().getScaledInstance(343, 104, Image.SCALE_SMOOTH);
        ImageIcon MMlogo = new ImageIcon(sizedMMIcon);       
        JLabel MeowMeowLogo = new JLabel(MMlogo);
        upperPanel_1.add(MeowMeowLogo);
        upperPanel.add(upperPanel_1, BorderLayout.WEST);

        // all the buttons
        JButton butt1 = new JButton("Contact Us");
        butt1.setBackground(DarkBlue);
        butt1.setForeground(Color.WHITE);

        JButton butt2 = new JButton("About Us");
        butt2.setBackground(DarkBlue);
        butt2.setForeground(Color.WHITE);

        JButton butt3 = new JButton("Find a Doctor");
        butt3.setBackground(DarkBlue);
        butt3.setForeground(Color.WHITE);

        JButton butt4 = new JButton("Login");
        butt4.setBackground(DarkBlue);
        butt4.setForeground(Color.WHITE);
        //
        
        JPanel upperPanel_2 = new JPanel(new FlowLayout());
        upperPanel_2.setBackground(DarkBlue);
        upperPanel_2.setPreferredSize(new Dimension(100, 40));
        upperPanel_2.add(butt1);
        upperPanel_2.add(butt2);
        upperPanel_2.add(butt3);
        upperPanel_2.add(butt4);
        upperPanel.add(upperPanel_2, BorderLayout.SOUTH);

        mainBackground.add(upperPanel, BorderLayout.NORTH);
        setVisible(true);
    }

    public static void main(String[] args) {

        new HomePageUI();
    }
}
