import java.awt.*;
import javax.swing.*;

public class DashBoardUI extends JPanel {
    JButton btn2_m;
    JLabel lb1;
    JPanel subPanel1, subPanel2, subPanel3;
    CardLayout cardLayout = new CardLayout();
    CardLayout cardLayout2 = new CardLayout();

    public DashBoardUI() {

        // defaults for this panel 
        setBackground(Color.BLUE);
        setLayout(new BorderLayout());

        //subPanels in this panel
        subPanel1 = new JPanel(new FlowLayout());
        subPanel2 = new JPanel(cardLayout2); // this is the content panel that changes depending on the side panel buttons that are pressed 
        subPanel3 = new JPanel(cardLayout); // this panel is the side panel that changes depending on user
        subPanel1.setBackground(Color.PINK);
        subPanel2.setBackground(Color.WHITE);
        subPanel3.setBackground(Color.BLUE);
        subPanel1.setPreferredSize(new Dimension(100,50));
        subPanel2.setPreferredSize(new Dimension(100,50));
        subPanel3.setPreferredSize(new Dimension(200,600));
        add(subPanel1, BorderLayout.NORTH);
        add(subPanel2, BorderLayout.CENTER);  //content panel is center, changing depending on the buttons pressed 
        add(subPanel3, BorderLayout.WEST);
        //

        //add this label at the main panel (west)
        lb1 = new JLabel("DashBoardUI");
        subPanel1.add(lb1);

        //The button that takes them back to home pgage
        btn2_m = new JButton("go HomePage");
        subPanel1.add(btn2_m);







        // since dashboard has a side panel that depends on the user
        // we need to implement another card layout

        // EXAMPLE - sidePanel1 is patient
        JPanel sidePanel1 = new JPanel(new FlowLayout());
        sidePanel1.setBackground(Color.BLUE);
        JButton btn1 = new JButton("btn1");
        JButton btn2 = new JButton("btn2");
        sidePanel1.add(btn1);
        sidePanel1.add(btn2);
        //

        // EXAMPLE - sidePanel2 is doctor
        JPanel sidePanel2 = new JPanel(new FlowLayout());
        sidePanel2.setBackground(Color.BLUE);
        JButton btn3 = new JButton("btn3");
        JButton btn4 = new JButton("btn4");
        sidePanel2.add(btn3);
        sidePanel2.add(btn4);
        //

        //add the sidePanels to the main sidePanel
        subPanel3.add(sidePanel1, "patientPanel");
        subPanel3.add(sidePanel2, "doctorPanel");

        User user = new User("doctor");
        String role = user.getUserRole();

        switch(role){

            case "doctor":
                cardLayout.show(subPanel3, "doctorPanel");
                break;
            case "patient":
                cardLayout.show(subPanel3, "patientPanel");
                break;
        }

        //This is for the panels to be shown in the center region depending on the buttons pressed at the side
        DoctorSidePanelOne panel1 = new DoctorSidePanelOne();
        DoctorSidePanelTwo panel2 = new DoctorSidePanelTwo();

        subPanel2.add(panel1, "panel1");
        subPanel2.add(panel2, "panel2");


        // set the eventlistener to the buttons made 
        //add Action listeners to the buttons that exist in those pages 
        btn3.addActionListener(event -> { cardLayout2.show(subPanel2, "panel1");});
        btn4.addActionListener(event -> { cardLayout2.show(subPanel2, "panel2");});

        setVisible(true);
    }

    public static void main(String[] args){
    }
}
