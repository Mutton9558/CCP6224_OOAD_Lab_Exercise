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
        int patientID;
        int doctorID;
        LocalDate DoctorDateValue;
        LocalTime DoctorTimeValue;
        String location;
        
        try{            
            patientID = String.parse(doctorNameTextField.returnTextField().getText());
            doctorID = Integer.parseInt(doctorAgeTextField.returnTextField().getText());
            java.util.Date dateValue = (java.util.Date) DoctorDateField.getValue();
            java.util.Date timeValue = (java.util.Date) DoctorTimeField.getValue();
            
            DoctorDateValue = dateValue.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

            DoctorTimeValue = timeValue.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime();
            location = locationTextField.returnTextField().getText();
            
            if(location.equals("") || location.equals(" ") || location.equals(locationTextField.returnPlaceholderText())){
                JOptionPane.showMessageDialog(this,
                            "Location Cannot be Found", "Invalid Input",
                            JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            doctorNameTextField.returnTextField().setEnabled(false);
            doctorTextField.returnTextField().setEnabled(false);
            DoctorDateField.setEnabled(false);
            DoctorTimeField.setEnabled(false);
            locationTextField.returnTextField().setEnabled(false);
        } catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(this,
                            "Patient or Doctor ID must be a number", "Invalid Input",
                            JOptionPane.WARNING_MESSAGE);
            ex.printStackTrace();
            return;
        } catch (java.time.format.DateTimeParseException ex){
            JOptionPane.showMessageDialog(this,
                            "Cannot save date and time", "Invalid Input",
                            JOptionPane.WARNING_MESSAGE);
            ex.printStackTrace();
            return;
        }
        
        submitApproval(patientID, doctorID, DoctorDateValue, DoctorTimeValue, location);
    }
    
    public void submitApproval(int patient, int doctor, LocalDate DoctorDate, LocalTime DoctorTime, String DoctorLocation){
        System.out.println("Submit");
        JOptionPane.showMessageDialog(this,
                            "Successfully created Doctor", "Invalid Input",
                            JOptionPane.INFORMATION_MESSAGE);
    }
}