import java.awt.Color;
import javax.swing.*;

public class DoctorSidePanelTwo extends JPanel{
    //This is the center Panel that shows up when btn 2 is pressed
   JLabel lb1;

   public DoctorSidePanelTwo(){

      setBackground(Color.WHITE);
      lb1 = new JLabel("BUTTON 4 WAS PRESSED, THIS IS THE SECOND PANEL ");
       add(lb1);
      setVisible(true);
   }
}
