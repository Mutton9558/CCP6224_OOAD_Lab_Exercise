import java.awt.*;
import javax.swing.*;

public class UIConstants {

    // constant images 
    public static ImageIcon windowIcon = new ImageIcon(UIConstants.class.getResource("/images/imgIcon.png"));

    // MMlogo rectangle
    public static ImageIcon logoImg = new ImageIcon(UIConstants.class.getResource("/images/meowmeowlogo.png"));
    public static Image sizedMMIcon = logoImg.getImage().getScaledInstance(343, 104, Image.SCALE_SMOOTH);
    public static ImageIcon MMlogo = new ImageIcon(sizedMMIcon);

    //MMlogo square 
    public static ImageIcon MMImg_square = new ImageIcon(UIConstants.class.getResource("/images/squareMMlogo.png"));
    public static Image sizedMMlogo_square = MMImg_square.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
    public static ImageIcon MMlogo_square = new ImageIcon(sizedMMlogo_square);

    // main background image(readily scaled) thats used everywhere
    public static ImageIcon bgImage = new ImageIcon(UIConstants.class.getResource("/images/mainbg.jpg"));
    public static Image sizedBgImage = bgImage.getImage().getScaledInstance(1366, 768, Image.SCALE_SMOOTH);
    public static ImageIcon mainBg = new ImageIcon(sizedBgImage);

    // COLOR VARIABLES
    public static Color DarkBlue = new Color(0x3b91fe);
    public static Color LightBlue = new Color(0xe2f8fd);
    public static Color Azure = new Color(0x71bcff);
    // COLOR VARIABLES

    // DIMENSIONS
    Dimension sidePanelDimension = new Dimension(200, 100);
    Dimension contentPanelDimension = new Dimension(200, 600);
    Dimension topPanelDimension = new Dimension(0, 110);

}
