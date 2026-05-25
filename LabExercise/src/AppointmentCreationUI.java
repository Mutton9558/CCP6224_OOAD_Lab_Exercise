import java.awt.*;
import javax.swing.*;
import java.util.Calendar;
import java.util.Date;

public class AppointmentCreationUI extends JFrame{
    private AppointmentController appointmentController;
    private JTextField patientTextField;
    private JTextField doctorTextField;
    private JSpinner appointmentDateField;
    private JSpinner appointmentTimeField;
    private JTextField locationTextField;
    
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
        formBoxLabel.setFont(new Font("Sans-Serif", Font.BOLD, 16));
        formElementBox.add(formBoxLabel, formBoxConstraints);
        
        formBoxConstraints.gridy = 1;
        patientTextField = new JTextField("Enter Patient ID", 48);
        formElementBox.add(patientTextField, formBoxConstraints);
        
        formBoxConstraints.gridy = 2;
        doctorTextField = new JTextField("Enter Doctor ID", 48);
        formElementBox.add(doctorTextField, formBoxConstraints);
        
        formBoxConstraints.gridwidth = 1;
        formBoxConstraints.gridy = 3;
        SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        appointmentDateField = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(appointmentDateField, "yyyy-MM-dd");
        appointmentDateField.setEditor(dateEditor);
        formElementBox.add(appointmentDateField, formBoxConstraints);
        
        formBoxConstraints.gridwidth = 1;
        formBoxConstraints.gridx = 1;
        formBoxConstraints.gridy = 3;
        SpinnerDateModel timeModel = new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY);
        appointmentTimeField = new JSpinner(timeModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(appointmentTimeField, "hh:mm a");
        appointmentTimeField.setEditor(timeEditor);
        formElementBox.add(appointmentTimeField, formBoxConstraints);
        
        formBoxConstraints.gridwidth = 2;
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 4;
        locationTextField = new JTextField("Enter Appointment Location", 48);
        formElementBox.add(locationTextField, formBoxConstraints);

        creationForm.add(formElementBox);
        
        this.add(creationForm);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void submitApproval(){
        
    }
}
