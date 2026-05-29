import java.awt.*;
import javax.swing.*;

//this is the side panel UI that is ONLY for patient 
//this UI shows up when the user clicks on the button called "Medical Records" at the side panel
public class MedicalRecordsUI extends JPanel {

    // THE FOLLOWING CONTENT IS SIMPLY TO TEST FUNCTIONALITY
    JLabel lb1;
    public MedicalRecordsUI() {

        //table of medical records -> medical history PROFILE uneditable
        //placeHOLDER for me(els)
        setBackground(Color.WHITE);
        lb1 = new JLabel("MEDICAL RECORDS BUTTON WAS PRESSED");
        add(lb1);
        setVisible(true);
    }
}

    

