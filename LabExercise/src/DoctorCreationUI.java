import java.awt.*;
import javax.swing.*;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class DoctorCreationUI extends JDialog implements ActionListener{
    private TextFieldWithPlaceholder doctorNameTextField;
    private TextFieldWithPlaceholder doctorAgeTextField;
    private TextFieldWithPlaceholder doctorGenderTextField;
    private TextFieldWithPlaceholder officeTextField;
    private TextFieldWithPlaceholder specialisationTextField;
    private JButton submitButton;
    private Window parent;
    
    public DoctorCreationUI(Window parent){
        super(parent, "Create Doctor", Dialog.ModalityType.APPLICATION_MODAL);
        this.parent = parent;
        JPanel content = new JPanel();
        content.setLayout(new GridBagLayout());
        content.setBackground(UIConstants.Azure);
        GridBagConstraints formBoxConstraints = new GridBagConstraints();
        formBoxConstraints.gridx = 0;
        formBoxConstraints.insets = new Insets(20, 0, 20, 0);
        formBoxConstraints.weightx = 1.0;
        formBoxConstraints.gridwidth = 2;
        
//        make it so when text field on focus remove text (listener)
        formBoxConstraints.gridy = 0;
        JLabel formBoxLabel = new JLabel("Create new Doctor at Mao Mao Hospital!");
        formBoxLabel.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        content.add(formBoxLabel, formBoxConstraints);
        
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 1;
        formBoxConstraints.gridwidth = 1;
        doctorNameTextField = new TextFieldWithPlaceholder("Enter Doctor Name", 24);
        content.add(doctorNameTextField.returnTextField(), formBoxConstraints);
        
        formBoxConstraints.gridx = 1;
        formBoxConstraints.gridy = 1;
        doctorAgeTextField = new TextFieldWithPlaceholder("Enter Doctor Age", 24);
        content.add(doctorAgeTextField.returnTextField(), formBoxConstraints);
        
        formBoxConstraints.gridx = 2;
        formBoxConstraints.gridy = 1;
        doctorGenderTextField = new TextFieldWithPlaceholder("Enter Doctor Gender", 24);
        content.add(doctorGenderTextField.returnTextField(), formBoxConstraints);
        
        formBoxConstraints.gridwidth = 2;
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 3;
        officeTextField = new TextFieldWithPlaceholder("Enter Doctor Office", 48);
        content.add(officeTextField.returnTextField(), formBoxConstraints);

        formBoxConstraints.gridwidth = 2;
        formBoxConstraints.gridx = 1;
        formBoxConstraints.gridy = 3;
        specialisationTextField = new TextFieldWithPlaceholder("Enter Doctor Specialisation", 48);
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
        String name, gender, office, specialisation;
        int age;
        
        try{            
            name = doctorNameTextField.returnTextField().getText();
            age = Integer.parseInt(doctorAgeTextField.returnTextField().getText());
            gender = doctorGenderTextField.returnTextField().getText();
            specialisation = specialisationTextField.returnTextField().getText();

            office = officeTextField.returnTextField().getText();
            
            if(office.equals("") || office.equals(" ") || office.equals(officeTextField.returnPlaceholderText())){
                JOptionPane.showMessageDialog(this,
                            "Location Cannot be Found", "Invalid Input",
                            JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            doctorNameTextField.returnTextField().setEnabled(false);
            doctorAgeTextField.returnTextField().setEnabled(false);
            doctorGenderTextField.returnTextField().setEnabled(false);
            officeTextField.returnTextField().setEnabled(false);
            specialisationTextField.returnTextField().setEnabled(false);
        } catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(this,
                            "Age must be a number", "Invalid Input",
                            JOptionPane.WARNING_MESSAGE);
            ex.printStackTrace();
            return;
        }
        
        submitApproval(name, age, gender, office, specialisation);
    }
    
    public void submitApproval(String name, int age, String gender, String office, String specialisation){
        System.out.println("Submit");
        JOptionPane.showMessageDialog(this,
                            "Successfully created Doctor", "Invalid Input",
                            JOptionPane.INFORMATION_MESSAGE);
    }
}
