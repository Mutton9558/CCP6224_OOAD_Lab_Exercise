import java.awt.*;
import javax.swing.*;

//this is the side panel UI that is shared between receptionist and doctor
//this UI shows up when the user clicks on the button called "Patient Records" at the side panel
public class PatientRecordsUI extends JPanel {

    // THE FOLLOWING CONTENT IS SIMPLY TO TEST FUNCTIONALITY
    JLabel lb1;
    public PatientRecordsUI() {

        //table of patient records
        //placeHOLDER for me(els)
        setBackground(Color.RED);
        lb1 = new JLabel("PATIENT RECORDS BUTTON WAS PRESSED");
        add(lb1);
        setVisible(true);
    }
}
