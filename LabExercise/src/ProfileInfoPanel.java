import java.awt.*;
import javax.swing.*;

public class ProfileInfoPanel extends JPanel {
    
    public ProfileInfoPanel(User user) {

        setBackground(Color.WHITE);
//      setPreferredSize(new Dimension(300, 500));
        setLayout(new GridLayout(4, 2));

        add(new JLabel("User ID:"));
        add(new JLabel(String.valueOf(user.getUserID())));
        add(new JLabel("Full Name:"));
        add(new JLabel(user.getUserName()));
        add(new JLabel("Sex:"));
        add(new JLabel(user.getUserGender()));
        add(new JLabel("Age:"));
        add(new JLabel(String.valueOf(user.getUserAge())));
    }
}
