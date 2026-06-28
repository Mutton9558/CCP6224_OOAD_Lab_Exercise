import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Date;
import java.time.LocalDate;

public class PatientCreationUI extends JDialog implements ActionListener{
    private TextFieldWithPlaceholder nameTextField;
    private TextFieldWithPlaceholder passwordTextField;
    private JSpinner dateOfBirthField;
    private TextFieldWithPlaceholder genderTextField;
    private JButton submitButton;
    private UIConstants uiconst = new UIConstants();
    private Window parent;
    private final UserController controller;
    
    public PatientCreationUI(Window parent, UserController controller){
        super(parent, "Create Receptionist", Dialog.ModalityType.APPLICATION_MODAL);
        this.parent = parent;
        this.controller = controller;
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
        JLabel formBoxLabel = new JLabel("Register a new Patient at Mao Mao Hospital!");
        formBoxLabel.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        content.add(formBoxLabel, formBoxConstraints);
        
        // Name Text Box
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 1;
        formBoxConstraints.gridwidth = 1;
        nameTextField = new TextFieldWithPlaceholder("Enter Patient Name", 24);
        content.add(nameTextField.returnTextField(), formBoxConstraints);
        
        //Password Text Box
        formBoxConstraints.gridx = 1;
        formBoxConstraints.gridy = 1;
        passwordTextField = new TextFieldWithPlaceholder("Enter Account Password", 24);
        content.add(passwordTextField.returnTextField(), formBoxConstraints);
        
        formBoxConstraints.gridx = 1;
        formBoxConstraints.gridy = 2;
        SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        dateOfBirthField = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateOfBirthField, "yyyy-MM-dd");
        dateOfBirthField.setEditor(dateEditor);
        content.add(dateOfBirthField, formBoxConstraints);
        
        //Gender Text Box
         formBoxConstraints.gridx = 1;
        formBoxConstraints.gridwidth = 1;
        formBoxConstraints.gridy = 3;
        genderTextField = new TextFieldWithPlaceholder("Enter Patient's Gender", 24);
        content.add(genderTextField.returnTextField(), formBoxConstraints);
        
        formBoxConstraints.gridy = 4;
        submitButton = new JButton("Create Patient");
        submitButton.addActionListener(this);
        content.add(submitButton, formBoxConstraints);

        setContentPane(content);
        content.setFocusable(true);
        pack();
        setLocationRelativeTo(parent);
        setResizable(false);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        String PatientName, PatientPassword, PatientGender;
        LocalDate dob;
        
        try{            
            PatientName = nameTextField.returnTextField().getText();
            PatientPassword = passwordTextField.returnTextField().getText();
            java.util.Date dateValue = (java.util.Date) dateOfBirthField.getValue();
            
            dob = dateValue.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            PatientGender = genderTextField.returnTextField().getText();
            
            nameTextField.returnTextField().setEnabled(false);
            passwordTextField.returnTextField().setEnabled(false);
            dateOfBirthField.setEnabled(false);
            
            if(dob.isAfter(LocalDate.now())){
                JOptionPane.showMessageDialog(this,
                            "Invalid Date of Birth", "Invalid Input",
                            JOptionPane.WARNING_MESSAGE);
                nameTextField.returnTextField().setEnabled(true);
                passwordTextField.returnTextField().setEnabled(true);
                dateOfBirthField.setEnabled(true);
                return;
            }
        } catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(this,
                            "Error creating Patient", "Invalid Input",
                            JOptionPane.WARNING_MESSAGE);
            ex.printStackTrace();
            return;
        }
        registerPatient(PatientName, PatientPassword, PatientGender, dob, "Patient");
    }
    
    public void registerPatient(String name, String password, String gender, LocalDate dob, String role){
        controller.registerUser(name, password, gender, dob, role);
        JOptionPane.showMessageDialog(this,
                            "Successfully created Patient", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
        
    }
}
