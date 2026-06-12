import java.awt.*;
import javax.swing.*;

public class ProfileInfoPanel extends JPanel {
    
    public ProfileInfoPanel(User user) {

        setBackground(Color.WHITE);
        //setPreferredSize(new Dimension(300, 500));
        setLayout(new GridLayout(7, 2));

        add(new JLabel("Full Name:"));
        add(new JLabel(user.getUserName()));
        add(new JLabel("Sex:"));
        add(new JLabel(user.getUserGender()));
        add(new JLabel("Nationality:"));
        add(new JLabel(user.getUserNationality()));
        add(new JLabel("Date Of Birth:"));
        add(new JLabel(user.getUserDateOfBirth().toString()));
        add(new JLabel("Address:"));
        add(new JLabel(user.getUserAddress()));
        add(new JLabel("Email:"));
        add(new JLabel(user.getUserEmail()));
        add(new JLabel("Phone Number:"));
        add(new JLabel(user.getUserPhoneNumber()));
    }
}
