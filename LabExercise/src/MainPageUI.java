import java.awt.*;
import javax.swing.*;

public class MainPageUI extends JFrame {

    public MainPageUI(){

        super("Mao Mao Hospital Main Page!");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(960, 1200);

        ImageIcon bgImage = new ImageIcon(getClass().getResource("/images/mainbg.jpg"));
        JLabel mainBackground = new JLabel(bgImage);

        mainBackground.setLayout(new FlowLayout());

    }

    public static void main(String[] args) {
        
        new MainPageUI();
    }
}
