import javax.swing.*;
import java.awt.event.*;
public class TextFieldWithPlaceholder implements FocusListener{
    JTextField localTextField;
    String placeholderText;
    
    public TextFieldWithPlaceholder(String placeholderText){
        localTextField = new JTextField(placeholderText);
        this.placeholderText = placeholderText;
        localTextField.addFocusListener(this);
    }
    
    public TextFieldWithPlaceholder(String placeholderText, int size){
        localTextField = new JTextField(placeholderText, size);
        this.placeholderText = placeholderText;
        localTextField.addFocusListener(this);
    }
    
    public JTextField returnTextField(){
        return localTextField;
    }
    
    public String returnPlaceholderText(){
        return placeholderText;
    }
    
    @Override
    public void focusGained(FocusEvent e){
        String text = localTextField.getText();
        if(text.equals(placeholderText))
            localTextField.setText("");
    }
    
    @Override
    public void focusLost(FocusEvent e){
        String text = localTextField.getText();
        if(text.equals("")){
            localTextField.setText(placeholderText);
        }
    }
}
