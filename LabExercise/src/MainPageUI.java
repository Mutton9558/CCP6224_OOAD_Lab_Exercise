import java.awt.*;
import javax.swing.*;

public class MainPageUI extends JFrame {

    public MainPageUI(){

        super("Mao Mao Hospital Main Page!");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);

        ImageIcon bgImage = new ImageIcon(getClass().getResource("/images/bluebg.webp"));
        JLabel mainBackground = new JLabel(bgImage);
        mainBackground.setLayout(new FlowLayout());

        setContentPane(mainBackground);
        setVisible(true);
    }

    public static void main(String[] args) {
        
        new MainPageUI();
    }
}
