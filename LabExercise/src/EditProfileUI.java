import java.awt.*;
import javax.swing.*;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.ZoneId;

public class EditProfileUI extends JDialog implements ActionListener{
    private JTextField userIdField;
    private JTextField fullNameTextField;
    private JTextField genderTextField;
    private JButton submitButton;
    private UIConstants uiconst = new UIConstants();
    private Window parent;
    private User oldUserInfo;
    private UserController controller;
    private JSpinner dateOfBirthField;
    
    public EditProfileUI(Window parent, User target, UserController c){
        super(parent, "Edit Profile", Dialog.ModalityType.APPLICATION_MODAL);
        this.parent = parent;
        this.oldUserInfo = target;
        this.controller = c;
        JPanel content = new JPanel();
        content.setLayout(new GridBagLayout());
        content.setBackground(this.uiconst.Azure);
        GridBagConstraints formBoxConstraints = new GridBagConstraints();
        formBoxConstraints.gridx = 0;
        formBoxConstraints.insets = new Insets(20, 0, 20, 0);
        formBoxConstraints.weightx = 1.0;
        formBoxConstraints.gridwidth = 2;
        
//        make it so when text field on focus remove text (listener)
        formBoxConstraints.gridy = 0;
        JLabel formBoxLabel = new JLabel("Edit User Profile");
        formBoxLabel.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        content.add(formBoxLabel, formBoxConstraints);
        
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 1;
        formBoxConstraints.gridwidth = 1;
        JLabel userIdLabel = new JLabel("User ID: ");
        content.add(userIdLabel, formBoxConstraints);
        
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 2;
        formBoxConstraints.gridwidth = 1;
        this.userIdField = new JTextField(Integer.toString(this.oldUserInfo.getUserID()), 24);
        this.userIdField.setEnabled(false);
        content.add(this.userIdField, formBoxConstraints);        
        
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 3;
        formBoxConstraints.gridwidth = 1;
        JLabel fullNameLabel = new JLabel("Full Name: ");
        content.add(fullNameLabel, formBoxConstraints);
        
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 4;
        this.fullNameTextField = new JTextField(this.oldUserInfo.getUserName(), 24);
        content.add(this.fullNameTextField, formBoxConstraints);
        
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 5;
        formBoxConstraints.gridwidth = 1;
        JLabel genderLabel = new JLabel("Gender: ");
        content.add(genderLabel, formBoxConstraints);
        
        formBoxConstraints.gridwidth = GridBagConstraints.REMAINDER;
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 6;
        this.genderTextField = new JTextField(this.oldUserInfo.getUserGender(), 48);
        this.genderTextField.setEnabled(false);
        content.add(this.genderTextField, formBoxConstraints);
        
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 7;
        formBoxConstraints.gridwidth = 1;
        JLabel dobLabel = new JLabel("Date Of Birth: ");
        content.add(dobLabel, formBoxConstraints);
        
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 8;
        Date dob = Date.from(this.oldUserInfo.getDob().atStartOfDay(ZoneId.systemDefault()).toInstant());
        SpinnerDateModel dateModel = new SpinnerDateModel(dob, null, null, Calendar.DAY_OF_MONTH);
        this.dateOfBirthField = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(this.dateOfBirthField, "yyyy-MM-dd");
        this.dateOfBirthField.setEditor(dateEditor);
        content.add(this.dateOfBirthField, formBoxConstraints);
        
        formBoxConstraints.gridy = 9;
        this.submitButton = new JButton("Edit Profile");
        this.submitButton.addActionListener(this);
        content.add(this.submitButton, formBoxConstraints);

        setContentPane(content);
        content.setFocusable(true);
        pack();
        setLocationRelativeTo(parent);
        setResizable(false);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        String name, gender;
        LocalDate dob;
        
        try{            
            name = this.fullNameTextField.getText();
            
            if(name.equals("") || name.equals(" ")){
                JOptionPane.showMessageDialog(this,
                            "Name cannot be empty", "Invalid Input",
                            JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            java.util.Date dateValue = (java.util.Date) this.dateOfBirthField.getValue();
            dob = dateValue.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            
            if(dob.isAfter(LocalDate.now())){
                JOptionPane.showMessageDialog(this,
                            "Invalid Date of Birth", "Invalid Input",
                            JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            this.fullNameTextField.setEnabled(false);
            this.dateOfBirthField.setEnabled(false);
            
        } catch (java.time.format.DateTimeParseException ex){
            JOptionPane.showMessageDialog(this,
                            "Date and time cannot be saved", "Invalid Input",
                            JOptionPane.WARNING_MESSAGE);
            ex.printStackTrace();
            return;
        }
        
        submitApproval(name, dob);
    }
    
    public void submitApproval(String name, LocalDate dob){
        boolean success = controller.updateUser(this.oldUserInfo.getUserID(), name, dob);
        if(success){
            JOptionPane.showMessageDialog(this,
                            "Successfully edited User with ID: " + this.oldUserInfo.getUserID(), "Success",
                            JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                            "Failed to edit User of ID:  " + this.oldUserInfo.getUserID() + ". Check your inputs again.", "Invalid Input",
                            JOptionPane.INFORMATION_MESSAGE);
            this.fullNameTextField.setEnabled(true);
            this.dateOfBirthField.setEnabled(true);
        }
    }
}
