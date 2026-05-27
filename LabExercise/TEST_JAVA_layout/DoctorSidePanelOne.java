import java.awt.Color;
import javax.swing.*;

public class DoctorSidePanelOne extends JPanel{
   //This is the center Panel that shows up when btn 1 is pressed
   JLabel lb1;

   public DoctorSidePanelOne(){

      setBackground(Color.YELLOW);
      lb1 = new JLabel("BUTTON 3 WAS PRESSED, THIS IS THE FIRST PANEL ");
      add(lb1);
      setVisible(true);
   }
}
