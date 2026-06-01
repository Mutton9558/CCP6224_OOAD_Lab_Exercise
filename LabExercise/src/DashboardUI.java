import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.*;
import java.util.List;

public class DashboardUI extends JPanel {

    CardLayout cardLayout1 = new CardLayout();
    CardLayout cardLayout2 = new CardLayout();
    JPanel westPanel, contentPanel, mainPanel, northPanel;
    JButton butt1, butt2, butt3, butt4, butt5;

    // this is the main dashboard UI shared accross patient, receptionists, doctors and admin
    public DashboardUI(List<DashboardPanel> dashboardPanels) {
        UIConstants UIConst = new UIConstants();
        setLayout(new BorderLayout());

        westPanel = new JPanel(cardLayout1); //side panel
        contentPanel = new JPanel(cardLayout2); //content panel
        mainPanel = new JPanel(new BorderLayout());

        //note: northPanel is simply an invisible space to see the bg 
        northPanel = new JPanel();
        northPanel.setOpaque(false);
        northPanel.setPreferredSize(UIConst.topPanelDimension);

        //westPanel.setBackground(UIConst.DarkBlue);
        westPanel.setOpaque(false);
        contentPanel.setBackground(UIConst.Azure);
        mainPanel.setBackground(Color.BLUE);

        westPanel.setPreferredSize(UIConst.sidePanelDimension);
        //southPanel.setPreferredSize(UIConst.contentPanelDimension);

        setOpaque(false);

        // add the subpanels into the mainPanel
        mainPanel.add(contentPanel, BorderLayout.CENTER); 
        mainPanel.add(westPanel, BorderLayout.WEST);
        
        JLabel MMlogo_square1 = new JLabel(UIConst.MMlogo_square);
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBackground(UIConst.DarkBlue);
        sidePanel.add(MMlogo_square1);
        
//        Iterates over dashboard panels and creates the appropriate side button and content panel
        for(DashboardPanel i : dashboardPanels){
           JButton sidebarButton = new JButton(i.getName());
           sidebarButton.setVisible(i.isVisible());
           contentPanel.add(i.getPanel(), i.getName());
           sidebarButton.addActionListener(event -> {
                cardLayout2.show(contentPanel, i.getName());
            });
           sidePanel.add(sidebarButton);
        }
        
//        
        westPanel.add(sidePanel, "sidePanel");
        cardLayout1.show(westPanel, "sidePanel");

        add(northPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }
}

