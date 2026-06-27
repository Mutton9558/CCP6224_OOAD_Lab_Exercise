import java.awt.*;
import javax.swing.*;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentCreationUI extends JDialog implements ActionListener{
    private TextFieldWithPlaceholder patientTextField;
    private TextFieldWithPlaceholder doctorTextField;
    private JSpinner appointmentDateField;
    private JSpinner appointmentTimeField;
    private TextFieldWithPlaceholder locationTextField;
    private JButton submitButton;
    private UIConstants uiconst = new UIConstants();
    private Window parent;
    private AppointmentController appointmentController;
    
    public AppointmentCreationUI(Window parent, AppointmentController appointmentController){
        super(parent, "Create Appointment", Dialog.ModalityType.APPLICATION_MODAL);
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
        JLabel formBoxLabel = new JLabel("Create new Appointment at Mao Mao Hospital!");
        formBoxLabel.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        content.add(formBoxLabel, formBoxConstraints);
        
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 1;
        formBoxConstraints.gridwidth = 1;
        patientTextField = new TextFieldWithPlaceholder("Enter Patient ID", 24);
        content.add(patientTextField.returnTextField(), formBoxConstraints);
        
        formBoxConstraints.gridx = 1;
        formBoxConstraints.gridy = 1;
        doctorTextField = new TextFieldWithPlaceholder("Enter Doctor ID", 24);
        content.add(doctorTextField.returnTextField(), formBoxConstraints);
        
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 2;
        SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        appointmentDateField = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(appointmentDateField, "yyyy-MM-dd");
        appointmentDateField.setEditor(dateEditor);
        content.add(appointmentDateField, formBoxConstraints);
        
        
        formBoxConstraints.gridx = 1;
        formBoxConstraints.gridwidth = 1;
        formBoxConstraints.gridy = 2;
        SpinnerDateModel timeModel = new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY);
        appointmentTimeField = new JSpinner(timeModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(appointmentTimeField, "hh:mm a");
        appointmentTimeField.setEditor(timeEditor);
        content.add(appointmentTimeField, formBoxConstraints);
        
        formBoxConstraints.gridwidth = 2;
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 3;
        locationTextField = new TextFieldWithPlaceholder("Enter Appointment Location", 48);
        content.add(locationTextField.returnTextField(), formBoxConstraints);
        
        formBoxConstraints.gridy = 4;
        submitButton = new JButton("Create Appointment");
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
        int patientID;
        int doctorID;
        LocalDate appointmentDateValue;
        LocalTime appointmentTimeValue;
        String location;
        
        try{            
            patientID = Integer.parseInt(patientTextField.returnTextField().getText());
            doctorID = Integer.parseInt(doctorTextField.returnTextField().getText());
            java.util.Date dateValue = (java.util.Date) appointmentDateField.getValue();
            java.util.Date timeValue = (java.util.Date) appointmentTimeField.getValue();
            
            appointmentDateValue = dateValue.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

            appointmentTimeValue = timeValue.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime();
            location = locationTextField.returnTextField().getText();
            
            if(location.equals("") || location.equals(" ") || location.equals(locationTextField.returnPlaceholderText())){
                JOptionPane.showMessageDialog(this,
                            "Location Cannot be Found", "Invalid Input",
                            JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            this.patientTextField.returnTextField().setEnabled(false);
            this.doctorTextField.returnTextField().setEnabled(false);
            this.appointmentDateField.setEnabled(false);
            this.appointmentTimeField.setEnabled(false);
            this.locationTextField.returnTextField().setEnabled(false);
        } catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(this,
                            "Patient or Doctor ID must be a number.", "Invalid Input",
                            JOptionPane.WARNING_MESSAGE);
            ex.printStackTrace();
            return;
        } catch (java.time.format.DateTimeParseException ex){
            JOptionPane.showMessageDialog(this,
                            "Cannot save date and time.", "Invalid Input",
                            JOptionPane.WARNING_MESSAGE);
            ex.printStackTrace();
            return;
        }
        
        submitApproval(patientID, doctorID, appointmentDateValue, appointmentTimeValue, location);
    }
    
    public void submitApproval(int patient, int doctor, LocalDate appointmentDate, LocalTime appointmentTime, String appointmentLocation){
        boolean success = appointmentController.createAppointment(patient, doctor, appointmentDate, appointmentTime, appointmentLocation);
        if(success){
            JOptionPane.showMessageDialog(this,
                            "Successfully created appointment!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                            "Could not create appointment. Either incorrect details or conflicting time!", "Error Occured",
                            JOptionPane.WARNING_MESSAGE);
            this.patientTextField.returnTextField().setEnabled(true);
            this.doctorTextField.returnTextField().setEnabled(true);
            this.appointmentDateField.setEnabled(true);
            this.appointmentTimeField.setEnabled(true);
            this.locationTextField.returnTextField().setEnabled(true);
        }
    }
}
