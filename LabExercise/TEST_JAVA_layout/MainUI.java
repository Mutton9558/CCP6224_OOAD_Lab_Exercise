import java.awt.*;
import javax.swing.*;

public class MainUI extends JFrame {

    JPanel mainPanel;
    CardLayout cardLayout = new CardLayout();

    public MainUI() {

        super("Testing The Layout");
        setBackground(Color.RED);
        setSize(1366, 768);
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);

        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(Color.RED);
        mainPanel.setVisible(true);

        //create objects of the UI 
        HomePageUI homePage = new HomePageUI();
        DashBoardUI dashBoard = new DashBoardUI();

        mainPanel.add(homePage, "HOME");
        mainPanel.add(dashBoard, "DASHBOARD");

        //show home page first 
        cardLayout.show(mainPanel, "HOME");

        //add Action listeners to the buttons that exist in those pages 
        homePage.btn1_m.addActionListener(event -> { cardLayout.show(mainPanel, "DASHBOARD");});
        dashBoard.btn2_m.addActionListener(event -> { cardLayout.show(mainPanel, "HOME");});

        this.add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        MainUI test1 = new MainUI();
    }
}