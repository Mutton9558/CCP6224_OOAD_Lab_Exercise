import java.awt.*;
import javax.swing.*;

//this is the side panel UI that is ONLY for the admin
//this UI shows up when the user clicks on the button called "Receptionist Records" at the side panel 
public class ReceptionistRecordsUI extends JPanel {

    // THE FOLLOWING CONTENT IS SIMPLY TO TEST FUNCTIONALITY
    JLabel lb1;
    public ReceptionistRecordsUI() {

        //placeHOLDER for zaki!!
        setBackground(Color.RED);
        lb1 = new JLabel("RECEPTIONIST RECORDS BUTTON WAS PRESSED");
        add(lb1);
        setVisible(true);
    }
}
