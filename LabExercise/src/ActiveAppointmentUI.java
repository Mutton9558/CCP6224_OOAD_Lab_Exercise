import java.awt.*;
import javax.swing.*;

//this is the side panel UI that is shared between patient and doctor
//this UI shows up when the user clicks on the button called "Active Appointment" at the side panel 
public class ActiveAppointmentUI extends JPanel {

    // THE FOLLOWING CONTENT IS SIMPLY TO TEST FUNCTIONALITY
    JLabel lb1;
    public ActiveAppointmentUI() {

        //table of active appointments 
        //placeHOLDER for shawn 
        setBackground(Color.YELLOW);
        lb1 = new JLabel("ACTIVE APPOINTMENT BUTTON WAS PRESSED");
        add(lb1);
        setVisible(true);
    }
}
