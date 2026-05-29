import java.awt.*;
import javax.swing.*;

//this is the side panel UI that is ONLY for the admin
//this UI shows up when the user clicks on the button called "Doctor Records" at the side panel 
public class DoctorRecordsUI extends JPanel{

    // THE FOLLOWING CONTENT IS SIMPLY TO TEST FUNCTIONALITY
    JLabel lb1;
    public DoctorRecordsUI(){
        UIConstants UIConst = new UIConstants();
        setLayout(new CardLayout());
        
        setBackground(UIConst.Azure);
        lb1 = new JLabel("DOCTOR RECORDS BUTTON WAS PRESSED");
        add(lb1);
        setVisible(true);
    }
}
