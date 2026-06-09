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
    private JComboBox<String> statusDropDown;
    private UIConstants uiconst = new UIConstants();
    private Window parent;
    private Appointment oldAppointment;
    
    public EditAppointmentUI(Window parent, Appointment target){
        super(parent, "Edit Appointment", Dialog.ModalityType.APPLICATION_MODAL);
        this.parent = parent;
        this.oldAppointment = target;
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
        JLabel formBoxLabel = new JLabel("Edit Appointment");
        formBoxLabel.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        content.add(formBoxLabel, formBoxConstraints);
        
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 1;
        formBoxConstraints.gridwidth = 1;
        this.patientTextField = new JTextField(this.oldAppointment.getPatientName(), 24);
        this.patientTextField.setEnabled(false);
        content.add(this.patientTextField, formBoxConstraints);        
        
        formBoxConstraints.gridx = 1;
        formBoxConstraints.gridy = 1;
        this.doctorTextField = new JTextField(this.oldAppointment.getPatientName(), 24);
        this.doctorTextField.setEnabled(false);
        content.add(this.doctorTextField, formBoxConstraints);
        
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 2;
        Date appointmentDate = Date.from(this.oldAppointment.getAppointmentDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        SpinnerDateModel dateModel = new SpinnerDateModel(appointmentDate, null, null, Calendar.DAY_OF_MONTH);
        this.appointmentDateField = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(this.appointmentDateField, "yyyy-MM-dd");
        this.appointmentDateField.setEditor(dateEditor);
        content.add(this.appointmentDateField, formBoxConstraints);
        
        
        formBoxConstraints.gridx = 1;
        formBoxConstraints.gridwidth = 1;
        formBoxConstraints.gridy = 2;
        java.time.Instant instant = this.oldAppointment.getAppointmentTime().atDate(LocalDate.now())
                                             .atZone(ZoneId.systemDefault())
                                             .toInstant();
        Date date = Date.from(instant);
        SpinnerDateModel timeModel = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
        this.appointmentTimeField = new JSpinner(timeModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(this.appointmentTimeField, "hh:mm a");
        this.appointmentTimeField.setEditor(timeEditor);
        content.add(this.appointmentTimeField, formBoxConstraints);
        
        formBoxConstraints.gridwidth = GridBagConstraints.REMAINDER;
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 3;
        this.locationTextField = new JTextField(this.oldAppointment.getLocation(), 48);
        content.add(this.locationTextField, formBoxConstraints);
        
        formBoxConstraints.gridwidth = GridBagConstraints.REMAINDER;
        formBoxConstraints.gridy = 4;
        String[] choices = {"Scheduled", "Completed", "Cancelled"};
        this.statusDropDown = new JComboBox<>(choices);
        this.statusDropDown.setSelectedItem(oldAppointment.getStatus());
        content.add(this.statusDropDown, formBoxConstraints);
        
        formBoxConstraints.gridy = 5;
        this.submitButton = new JButton("Edit Appointment");
        this.submitButton.addActionListener(this);
        content.add(this.submitButton, formBoxConstraints);

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
            java.util.Date dateValue = (java.util.Date) this.appointmentDateField.getValue();
            java.util.Date timeValue = (java.util.Date) this.appointmentTimeField.getValue();
            
            appointmentDateValue = dateValue.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

            appointmentTimeValue = timeValue.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime();
            location = this.locationTextField.getText();
            
            if(location.equals("") || location.equals(" ")){
                JOptionPane.showMessageDialog(this,
                            "Location cannot be empty", "Invalid Input",
                            JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            this.appointmentDateField.setEnabled(false);
            this.appointmentTimeField.setEnabled(false);
            this.locationTextField.setEnabled(false);
            statusDropDown.setEnabled(false);
            
        } catch (java.time.format.DateTimeParseException ex){
            JOptionPane.showMessageDialog(this,
                            "Date and time cannot be saved", "Invalid Input",
                            JOptionPane.WARNING_MESSAGE);
            ex.printStackTrace();
            return;
        }
        
        submitApproval(appointmentDateValue, appointmentTimeValue, location, (String) this.statusDropDown.getSelectedItem());
    }
    
    public void submitApproval(LocalDate appointmentDate, LocalTime appointmentTime, String appointmentLocation, String newStatus){
        System.out.println("Submit");
        JOptionPane.showMessageDialog(this,
                            "Successfully edited Appointment with ID " + this.oldAppointment.getAppointmentID(), "Invalid Input",
                            JOptionPane.INFORMATION_MESSAGE);
    }
}
