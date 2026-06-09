import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ReceptionistCreationUI extends JDialog implements ActionListener{
    private TextFieldWithPlaceholder nameTextField;
    private TextFieldWithPlaceholder passwordTextField;
    private TextFieldWithPlaceholder ageTextField;
    private TextFieldWithPlaceholder genderTextField;
    private JButton submitButton;
    private UIConstants uiconst = new UIConstants();
    private Window parent;
    
    public ReceptionistCreationUI(Window parent){
        super(parent, "Create Receptionist", Dialog.ModalityType.APPLICATION_MODAL);
        this.parent = parent;
        JPanel content = new JPanel();
        content.setLayout(new GridBagLayout());
        content.setBackground(uiconst.Azure);
        GridBagConstraints formBoxConstraints = new GridBagConstraints();
        formBoxConstraints.gridx = 0;
        formBoxConstraints.insets = new Insets(20, 0, 20, 0);
        formBoxConstraints.weightx = 1.0;
        formBoxConstraints.gridwidth = 2;
        
//      make it so when text field on focus remove text (listener)
        formBoxConstraints.gridy = 0;
        JLabel formBoxLabel = new JLabel("Register a new Receptionist at Mao Mao Hospital!");
        formBoxLabel.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        content.add(formBoxLabel, formBoxConstraints);
        
        // Name Text Box
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 1;
        formBoxConstraints.gridwidth = 1;
        nameTextField = new TextFieldWithPlaceholder("Enter Receptionist Name", 24);
        content.add(nameTextField.returnTextField(), formBoxConstraints);
        
        //Password Text Box
        formBoxConstraints.gridx = 1;
        formBoxConstraints.gridy = 1;
        passwordTextField = new TextFieldWithPlaceholder("Enter Account Password", 24);
        content.add(passwordTextField.returnTextField(), formBoxConstraints);
        
        // Age Text Box
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 2;
        ageTextField = new TextFieldWithPlaceholder("Enter Receptionist's Age", 24);
        content.add(ageTextField.returnTextField(), formBoxConstraints);
        
        //Gender Text Box
         formBoxConstraints.gridx = 1;
        formBoxConstraints.gridwidth = 1;
        formBoxConstraints.gridy = 2;
        genderTextField = new TextFieldWithPlaceholder("Enter Receptionist's Gender", 24);
        content.add(genderTextField.returnTextField(), formBoxConstraints);
        
        formBoxConstraints.gridy = 4;
        submitButton = new JButton("Create Receptionist");
        submitButton.addActionListener(this);
        content.add(submitButton, formBoxConstraints);

        setContentPane(content);
        content.setFocusable(true);
        pack();
        setLocationRelativeTo(parent);
        setResizable(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        String ReceptionistName, ReceptionistPassword, ReceptionistGender;
        int ReceptionistAge; 
        
        try{            
            ReceptionistName = nameTextField.returnTextField().getText();
            ReceptionistPassword = passwordTextField.returnTextField().getText();
            ReceptionistAge = Integer.parseInt(passwordTextField.returnTextField().getText());
            ReceptionistGender = genderTextField.returnTextField().getText();
            
            nameTextField.returnTextField().setEnabled(false);
            passwordTextField.returnTextField().setEnabled(false);
            ageTextField.returnTextField().setEnabled(false);
        } catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(this,
                            "Receptionist's Age must be a number", "Invalid Input",
                            JOptionPane.WARNING_MESSAGE);
            ex.printStackTrace();
            return;
        }
        registerReceptionist(ReceptionistName, ReceptionistPassword, ReceptionistGender, ReceptionistAge, "Receptionist");
    }
    
    public void registerReceptionist(String name, String password, String gender, int age, String role){
        UserController uController = new UserController();
        uController.registerUser(name, password, gender, age, role);
        System.out.println("Submit");
        JOptionPane.showMessageDialog(this,
                            "Successfully created Receptionist", "Invalid Input",
                            JOptionPane.INFORMATION_MESSAGE);
        
    }
}
