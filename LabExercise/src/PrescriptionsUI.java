import java.awt.*;
import javax.swing.*;

//this is the side panel UI that is ONLY for the patient
//this UI shows up when the user clicks on the button called "Prescriptions" at the side panel 
public class PrescriptionsUI extends JPanel {

    // THE FOLLOWING CONTENT IS SIMPLY TO TEST FUNCTIONALITY
    JLabel lb1;
    public PrescriptionsUI() {

        //table of presciptions for the user
        //placeHOLDER for me(els)
        setBackground(Color.PINK);
        lb1 = new JLabel("PRESCRIPTIONS BUTTON WAS PRESSED");
        add(lb1);
        setVisible(true);
    }
}
