import java.awt.*;
import javax.swing.*;

//this is the side panel UI that is ONLY for the admin
//this UI shows up when the user clicks on the button called "Generate Report" at the side panel 
public class GenerateReportUI extends JPanel {

    // THE FOLLOWING CONTENT IS SIMPLY TO TEST FUNCTIONALITY
    JLabel lb1;
    public GenerateReportUI() {

        //placeHOLDER for zaki!!
        setBackground(Color.GRAY);
        lb1 = new JLabel("GENERATE REPORT BUTTON WAS PRESSED");
        add(lb1);
        setVisible(true);
    }
}
