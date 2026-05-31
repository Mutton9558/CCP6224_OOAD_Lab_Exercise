import java.awt.*;
import javax.swing.*;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

public class EditAppointmentUI extends JDialog implements ActionListener{
    private JTextField patientTextField;
    private JTextField doctorTextField;
    private JSpinner appointmentDateField;
    private JSpinner appointmentTimeField;
    private JTextField locationTextField;
    private JButton submitButton;
    private UIConstants uiconst = new UIConstants();
    private Window parent;
    private Appointment oldAppointment;
    
    public EditAppointmentUI(Window parent, Appointment target){
        super(parent, "Edit Appointment", Dialog.ModalityType.APPLICATION_MODAL);
        this.parent = parent;
        this.oldAppointment = target;
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
        JLabel formBoxLabel = new JLabel("Edit Appointment");
        formBoxLabel.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        content.add(formBoxLabel, formBoxConstraints);
        
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 1;
        formBoxConstraints.gridwidth = 1;
        patientTextField = new JTextField(oldAppointment.getPatientData().getUserName(), 24);
        patientTextField.setEnabled(false);
        content.add(patientTextField, formBoxConstraints);        
        
        formBoxConstraints.gridx = 1;
        formBoxConstraints.gridy = 1;
        doctorTextField = new JTextField(oldAppointment.getDoctorData().getUserName(), 24);
        doctorTextField.setEnabled(false);
        content.add(doctorTextField, formBoxConstraints);
        
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 2;
        Date appointmentDate = Date.from(oldAppointment.getAppointmentDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        SpinnerDateModel dateModel = new SpinnerDateModel(appointmentDate, null, null, Calendar.DAY_OF_MONTH);
        appointmentDateField = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(appointmentDateField, "yyyy-MM-dd");
        appointmentDateField.setEditor(dateEditor);
        content.add(appointmentDateField, formBoxConstraints);
        
        
        formBoxConstraints.gridx = 1;
        formBoxConstraints.gridwidth = 1;
        formBoxConstraints.gridy = 2;
        java.time.Instant instant = oldAppointment.getAppointmentTime().atDate(LocalDate.now())
                                             .atZone(ZoneId.systemDefault())
                                             .toInstant();
        Date date = Date.from(instant);
        SpinnerDateModel timeModel = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
        appointmentTimeField = new JSpinner(timeModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(appointmentTimeField, "hh:mm a");
        appointmentTimeField.setEditor(timeEditor);
        content.add(appointmentTimeField, formBoxConstraints);
        
        formBoxConstraints.gridwidth = 2;
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 3;
        locationTextField = new JTextField(oldAppointment.getLocation(), 48);
        content.add(locationTextField, formBoxConstraints);
        
        formBoxConstraints.gridy = 4;
        submitButton = new JButton("Edit Appointment");
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
        LocalDate appointmentDateValue;
        LocalTime appointmentTimeValue;
        String location;
        
        try{            
            java.util.Date dateValue = (java.util.Date) appointmentDateField.getValue();
            java.util.Date timeValue = (java.util.Date) appointmentTimeField.getValue();
            
            appointmentDateValue = dateValue.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

            appointmentTimeValue = timeValue.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime();
            location = locationTextField.getText();
            
            if(location.equals("") || location.equals(" ")){
                JOptionPane.showMessageDialog(this,
                            "Location cannot be empty", "Invalid Input",
                            JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            appointmentDateField.setEnabled(false);
            appointmentTimeField.setEnabled(false);
            locationTextField.setEnabled(false);
        } catch (java.time.format.DateTimeParseException ex){
            JOptionPane.showMessageDialog(this,
                            "Date and time cannot be saved", "Invalid Input",
                            JOptionPane.WARNING_MESSAGE);
            ex.printStackTrace();
            return;
        }
        
        submitApproval(appointmentDateValue, appointmentTimeValue, location);
    }
    
    public void submitApproval(LocalDate appointmentDate, LocalTime appointmentTime, String appointmentLocation){
        System.out.println("Submit");
        JOptionPane.showMessageDialog(this,
                            "Successfully edited Appointment with ID " + oldAppointment.getAppointmentID(), "Invalid Input",
                            JOptionPane.INFORMATION_MESSAGE);
    }
}
