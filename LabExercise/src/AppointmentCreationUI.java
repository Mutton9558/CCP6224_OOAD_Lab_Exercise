import java.awt.*;
import javax.swing.*;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

public class AppointmentCreationUI extends JFrame implements ActionListener{
    private AppointmentController appointmentController;
    private TextFieldWithPlaceholder patientTextField;
    private TextFieldWithPlaceholder doctorTextField;
    private JSpinner appointmentDateField;
    private JSpinner appointmentTimeField;
    private TextFieldWithPlaceholder locationTextField;
    private JButton submitButton;
    
    public AppointmentCreationUI(AppointmentController controller){
        super("Appointment Creation Screen");
        this.setLayout(new GridBagLayout());
        this.getContentPane().setBackground(Color.GRAY);
        JPanel creationForm = new JPanel();
        
        setSize(1280, 720);
        
        creationForm.setBackground(Color.LIGHT_GRAY);
        creationForm.setPreferredSize(new Dimension(600, 400)); 
        creationForm.setLayout(new GridBagLayout());
        
        JPanel formElementBox = new JPanel();
        formElementBox.setPreferredSize(new Dimension(520, 320));
        formElementBox.setLayout(new GridBagLayout());
        GridBagConstraints formBoxConstraints = new GridBagConstraints();
        formBoxConstraints.gridx = 0;
        formBoxConstraints.insets = new Insets(20, 0, 20, 0);
        formBoxConstraints.weightx = 1.0;
        formBoxConstraints.gridwidth = 2;
        formElementBox.setBackground(Color.LIGHT_GRAY);
        
//        make it so when text field on focus remove text (listener)
        formBoxConstraints.gridy = 0;
        JLabel formBoxLabel = new JLabel("Create new Appointment at Mao Mao Hospital!");
        formBoxLabel.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        formElementBox.add(formBoxLabel, formBoxConstraints);
        
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 1;
        formBoxConstraints.gridwidth = 1;
        patientTextField = new TextFieldWithPlaceholder("Enter Patient ID", 24);
        formElementBox.add(patientTextField.returnTextField(), formBoxConstraints);
        
        formBoxConstraints.gridx = 1;
        formBoxConstraints.gridy = 1;
        doctorTextField = new TextFieldWithPlaceholder("Enter Doctor ID", 24);
        formElementBox.add(doctorTextField.returnTextField(), formBoxConstraints);
        
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 2;
        SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        appointmentDateField = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(appointmentDateField, "yyyy-MM-dd");
        appointmentDateField.setEditor(dateEditor);
        formElementBox.add(appointmentDateField, formBoxConstraints);
        
        
        formBoxConstraints.gridx = 1;
        formBoxConstraints.gridwidth = 1;
        formBoxConstraints.gridy = 2;
        SpinnerDateModel timeModel = new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY);
        appointmentTimeField = new JSpinner(timeModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(appointmentTimeField, "hh:mm a");
        appointmentTimeField.setEditor(timeEditor);
        formElementBox.add(appointmentTimeField, formBoxConstraints);
        
        formBoxConstraints.gridwidth = 2;
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 3;
        locationTextField = new TextFieldWithPlaceholder("Enter Appointment Location", 48);
        formElementBox.add(locationTextField.returnTextField(), formBoxConstraints);
        
        formBoxConstraints.gridy = 4;
        submitButton = new JButton("Create Appointment");
        submitButton.addActionListener(this);
        formElementBox.add(submitButton, formBoxConstraints);
        
        creationForm.add(formElementBox);
        
        this.add(creationForm);
        setVisible(true);
        this.getContentPane().requestFocusInWindow();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        int patientID;
        int doctorID;
        LocalDate appointmentDateValue;
        LocalTime appointmentTimeValue;
        String location;
        
        Dialog errorMessage = new JDialog(this, "Error", true);
        errorMessage.setLayout(new GridBagLayout());
        errorMessage.setLocationRelativeTo(this);
        errorMessage.setSize(300, 100);
        
        try{            
            patientID = Integer.parseInt(patientTextField.returnTextField().getText());
            doctorID = Integer.parseInt(doctorTextField.returnTextField().getText());
            java.util.Date dateValue = (java.util.Date) appointmentDateField.getValue();
            java.util.Date timeValue = (java.util.Date) appointmentTimeField.getValue();
            
            appointmentDateValue = dateValue.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

            appointmentTimeValue = timeValue.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime();
            location = locationTextField.returnTextField().getText();
            
            if(location.equals("") || location.equals(" ") || location.equals(locationTextField.returnPlaceholderText())){
                JLabel label = new JLabel("Location cannot be empty");
                errorMessage.add(label);
                errorMessage.setVisible(true);
                return;
            }
            
            patientTextField.returnTextField().setEnabled(false);
            doctorTextField.returnTextField().setEnabled(false);
            appointmentDateField.setEnabled(false);
            appointmentTimeField.setEnabled(false);
            locationTextField.returnTextField().setEnabled(false);
        } catch (NumberFormatException ex){
            JLabel label = new JLabel("Patient or DoctorID must be a number");
            errorMessage.add(label);
            errorMessage.setVisible(true);
            ex.printStackTrace();
            return;
        } catch (java.time.format.DateTimeParseException ex){
            JLabel label = new JLabel("Date or Time cannot be converted");
            errorMessage.add(label);
            errorMessage.setVisible(true);
            ex.printStackTrace();
            return;
        }
        
        submitApproval(patientID, doctorID, appointmentDateValue, appointmentTimeValue, location);
    }
    
    public void submitApproval(int patient, int doctor, LocalDate appointmentDate, LocalTime appointmentTime, String appointmentLocation){
        System.out.println("Submit");
    }
}
