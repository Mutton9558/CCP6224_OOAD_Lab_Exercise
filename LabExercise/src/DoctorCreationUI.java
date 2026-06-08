import java.awt.*;
import javax.swing.*;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class DoctorCreationUI extends JDialog implements ActionListener{
    private TextFieldWithPlaceholder nameTextField;
    private TextFieldWithPlaceholder passwordTextField;
    private TextFieldWithPlaceholder ageTextField;
    private TextFieldWithPlaceholder genderTextField;
    private TextFieldWithPlaceholder officeTextField;
    private TextFieldWithPlaceholder specialisationTextField;
    private JButton submitButton;
    private UIConstants uiconst = new UIConstants();
    private Window parent;
    
    public DoctorCreationUI(Window parent){
        super(parent, "Create Doctor", Dialog.ModalityType.APPLICATION_MODAL);
        this.parent = parent;
        JPanel content = new JPanel();
        content.setLayout(new GridBagLayout());
        content.setBackground(uiconst.Azure);
        GridBagConstraints formBoxConstraints = new GridBagConstraints();
        formBoxConstraints.gridx = 0;
        formBoxConstraints.insets = new Insets(20, 0, 20, 0);
        formBoxConstraints.weightx = 1.0;
        formBoxConstraints.gridwidth = 2;
        
//        make it so when text field on focus remove text (listener)
        formBoxConstraints.gridy = 0;
        JLabel formBoxLabel = new JLabel("Register a new Doctor at Mao Mao Hospital!");
        formBoxLabel.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        content.add(formBoxLabel, formBoxConstraints);
        
        // Name Text Box
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 1;
        formBoxConstraints.gridwidth = 1;
        nameTextField = new TextFieldWithPlaceholder("Enter Doctor Name", 24);
        content.add(nameTextField.returnTextField(), formBoxConstraints);
        
        //Password Text Box
        formBoxConstraints.gridx = 1;
        formBoxConstraints.gridy = 1;
        passwordTextField = new TextFieldWithPlaceholder("Enter Account Password", 24);
        content.add(passwordTextField.returnTextField(), formBoxConstraints);
        
        // Age Text Box
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 2;
        ageTextField = new TextFieldWithPlaceholder("Enter Doctor's Age", 24);
        content.add(ageTextField.returnTextField(), formBoxConstraints);
        
        //Gender Text Box
         formBoxConstraints.gridx = 1;
        formBoxConstraints.gridwidth = 1;
        formBoxConstraints.gridy = 2;
        genderTextField = new TextFieldWithPlaceholder("Enter Doctor's Gender", 24);
        content.add(genderTextField.returnTextField(), formBoxConstraints);
        
        //Location Text Box
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 3;
        formBoxConstraints.gridwidth = 1;
        officeTextField = new TextFieldWithPlaceholder("Enter Doctor's Office Location", 24);
        content.add(officeTextField.returnTextField(), formBoxConstraints);

        
        formBoxConstraints.gridx = 1;
        formBoxConstraints.gridy = 3;
        formBoxConstraints.gridwidth = 1;
        specialisationTextField = new TextFieldWithPlaceholder("Enter Doctor's Specialisation", 24);
        content.add(specialisationTextField.returnTextField(), formBoxConstraints);
        
        formBoxConstraints.gridy = 4;
        submitButton = new JButton("Create Doctor");
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
        String doctorName, doctorPassword, doctorGender, doctorOffice, doctorSpecialisation;
        int doctorAge; 
        
        try{            
            doctorName = nameTextField.returnTextField().getText();
            doctorPassword = passwordTextField.returnTextField().getText();
            doctorAge = Integer.parseInt(passwordTextField.returnTextField().getText());
            doctorGender = genderTextField.returnTextField().getText();
            doctorSpecialisation = specialisationTextField.returnTextField().getText();
            doctorOffice = officeTextField.returnTextField().getText();
            
            if(doctorOffice.equals("") || doctorOffice.equals(" ") || doctorOffice.equals(officeTextField.returnPlaceholderText())){
                JOptionPane.showMessageDialog(this,
                            "Location Cannot be Found", "Invalid Input",
                            JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            nameTextField.returnTextField().setEnabled(false);
            passwordTextField.returnTextField().setEnabled(false);
            ageTextField.returnTextField().setEnabled(false);
            specialisationTextField.returnTextField().setEnabled(false);
            officeTextField.returnTextField().setEnabled(false);
        } catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(this,
                            "Doctor's Age must be a number", "Invalid Input",
                            JOptionPane.WARNING_MESSAGE);
            ex.printStackTrace();
            return;
        }
        registerDoctor(doctorName, doctorPassword, doctorAge, doctorGender, doctorSpecialisation);
    }
    
    public void registerDoctor(String name, String password, int age, String gender, String specialisation){
        DoctorController dController = new DoctorController();
        dController.registerDoctor(name, password, gender, age, gender, specialisation);
        System.out.println("Submit");
        JOptionPane.showMessageDialog(this,
                            "Successfully created doctor", "Invalid Input",
                            JOptionPane.INFORMATION_MESSAGE);
        
    }
}
