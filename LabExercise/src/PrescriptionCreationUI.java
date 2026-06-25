import java.awt.*;
import javax.swing.*;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.*;
import java.time.LocalDate;

public class PrescriptionCreationUI extends JDialog implements ActionListener{
    private TextFieldWithPlaceholder nameTextField;
    private TextFieldWithPlaceholder doseTextField;
    private TextFieldWithPlaceholder conditionTextField;
    private TextFieldWithPlaceholder frequencyTextField;
    private TextFieldWithPlaceholder patientTextField;
    private JSpinner prescDateField;
    private JSpinner prescEndField;
    private JButton submitButton;
    private UIConstants uiconst = new UIConstants();
    private Window parent;
    private final PrescriptionController controller;
    private User client;
    
    public PrescriptionCreationUI(Window parent, User client, PrescriptionController controller){
        super(parent, "Create Prescription", Dialog.ModalityType.APPLICATION_MODAL);
        this.controller = controller;
        this.parent = parent;
        this.client = client;
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
        JLabel formBoxLabel = new JLabel("Create a new Prescription at Mao Mao Hospital!");
        formBoxLabel.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        content.add(formBoxLabel, formBoxConstraints);
        
        // Name Text Box
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 1;
        formBoxConstraints.gridwidth = 1;
        nameTextField = new TextFieldWithPlaceholder("Enter Prescription Name", 24);
        content.add(nameTextField.returnTextField(), formBoxConstraints);
        
         // Dose Text Box
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 2;
        formBoxConstraints.gridwidth = 1;
        doseTextField = new TextFieldWithPlaceholder("Enter Prescription Dose", 24);
        content.add(doseTextField.returnTextField(), formBoxConstraints);

        // Condition Text Box
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 3;
        conditionTextField = new TextFieldWithPlaceholder("Enter Prescription Condition", 24);
        content.add(conditionTextField.returnTextField(), formBoxConstraints);
        
        // Frequency Text Box
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 4;
        frequencyTextField = new TextFieldWithPlaceholder("Enter Prescription Frequency", 24);
        content.add(frequencyTextField.returnTextField(), formBoxConstraints);
        
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 5;
        formBoxConstraints.gridwidth = 1;
        JLabel prescStartLabel = new JLabel("Prescription Start: ");
        content.add(prescStartLabel, formBoxConstraints);
        
        // Date Prescribed Prescription
        formBoxConstraints.gridx = 1;
        formBoxConstraints.gridy = 5;
        SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        prescDateField = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(prescDateField, "yyyy-MM-dd");
        prescDateField.setEditor(dateEditor);
        content.add(prescDateField, formBoxConstraints);
        
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 6;
        formBoxConstraints.gridwidth = 1;
        JLabel prescEndLabel = new JLabel("Prescription End: ");
        content.add(prescEndLabel, formBoxConstraints);
        
        // Date Prescribed Prescription
        formBoxConstraints.gridx = 1;
        formBoxConstraints.gridy = 6;
        SpinnerDateModel dateModel2 = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        prescEndField = new JSpinner(dateModel2);
        JSpinner.DateEditor dateEditor2 = new JSpinner.DateEditor(prescEndField, "yyyy-MM-dd");
        prescEndField.setEditor(dateEditor2);
        content.add(prescEndField, formBoxConstraints);
        
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 7;
        formBoxConstraints.gridwidth = 1;
        patientTextField = new TextFieldWithPlaceholder("Enter Patient ID", 24);
        content.add(patientTextField.returnTextField(), formBoxConstraints);
        
        formBoxConstraints.gridy = 9;
        submitButton = new JButton("Create Prescription");
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
        String prescName, prescDose, prescCondition, prescFrequency;
        int patientID, doctorID;
        LocalDate prescDate, prescEnd;
        
        try{            
            prescName = nameTextField.returnTextField().getText();
            prescDose = doseTextField.returnTextField().getText();
            prescCondition = conditionTextField.returnTextField().getText();
            prescFrequency = frequencyTextField.returnTextField().getText();
            patientID = Integer.parseInt(patientTextField.returnTextField().getText());
            java.util.Date dateValue = (java.util.Date) prescDateField.getValue();
            java.util.Date endDateVal = (java.util.Date) prescEndField.getValue();

            prescDate = dateValue.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            prescEnd = endDateVal.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            
            doctorID = this.client.getUserID();
            
            this.nameTextField.returnTextField().setEnabled(false);
            this.doseTextField.returnTextField().setEnabled(false);
            this.conditionTextField.returnTextField().setEnabled(false);
            this.frequencyTextField.returnTextField().setEnabled(false);
            this.patientTextField.returnTextField().setEnabled(false);
            this.prescDateField.setEnabled(false);
            this.prescEndField.setEnabled(false);

        }catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(this,
                            "Patient or Doctor ID must be a number", "Invalid Input",
                            JOptionPane.WARNING_MESSAGE);
            ex.printStackTrace();
            return;
        
        }catch (java.time.format.DateTimeParseException ex){
            JOptionPane.showMessageDialog(this,
                            "Cannot save date and time", "Invalid Input",
                            JOptionPane.WARNING_MESSAGE);
            ex.printStackTrace();
            return;
        }
        registerPrescription(prescName, prescDose, prescCondition, prescFrequency, patientID, doctorID, prescDate, prescEnd);
    }
    
    public void registerPrescription(String name, String dose, String condition, String frequency, int patient_ID, int doctorID, LocalDate date, LocalDate end){
        boolean success = controller.createPrescription(name, dose, condition, frequency, patient_ID, doctorID, date, end);
        if(success){
            JOptionPane.showMessageDialog(this,
                            "Successfully created prescription", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                            "Could not add prescription. Please check your inputs", "Invalid Input",
                            JOptionPane.INFORMATION_MESSAGE);
            this.nameTextField.returnTextField().setEnabled(true);
            this.doseTextField.returnTextField().setEnabled(true);
            this.conditionTextField.returnTextField().setEnabled(true);
            this.frequencyTextField.returnTextField().setEnabled(true);
            this.patientTextField.returnTextField().setEnabled(true);
            this.prescDateField.setEnabled(true);
            this.prescEndField.setEnabled(false);
        }
    }
}
