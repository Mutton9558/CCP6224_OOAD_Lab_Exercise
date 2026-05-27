import java.awt.*;
import javax.swing.*;

public class HomePageUI extends JPanel {
    JButton btn1_m;
    JLabel lb1;
    JPanel subPanel1, subPanel2;

    public HomePageUI() {

        // set the panel defaults
        setBackground(Color.RED);
        setLayout(new BorderLayout());

        // subPanels in this panel
        subPanel1 = new JPanel(new FlowLayout());
        subPanel2 = new JPanel(new BorderLayout());
        subPanel1.setBackground(Color.RED);
        subPanel2.setBackground(Color.WHITE);
        subPanel1.setPreferredSize(new Dimension(100, 50));
        subPanel2.setPreferredSize(new Dimension(100, 50));
        add(subPanel1, BorderLayout.NORTH);
        add(subPanel2, BorderLayout.CENTER);
        //

        // add this label at the main panel (west)
        lb1 = new JLabel("HomePageUI");
        subPanel1.add(lb1);

        // add this button in subPanel1
        btn1_m = new JButton("go DashBoard");
        subPanel1.add(btn1_m);
        setVisible(true);
    }
}
