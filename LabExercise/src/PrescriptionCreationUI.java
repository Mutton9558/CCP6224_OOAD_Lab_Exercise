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
    private JSpinner prescDateField;
    private JButton submitButton;
    private UIConstants uiconst = new UIConstants();
    private Window parent;
    
    public PrescriptionCreationUI(Window parent){
        super(parent, "Create Prescription", Dialog.ModalityType.APPLICATION_MODAL);
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
        formBoxConstraints.gridy = 1;
        formBoxConstraints.gridwidth = 1;
        doseTextField = new TextFieldWithPlaceholder("Enter Prescription Dose", 24);
        content.add(doseTextField.returnTextField(), formBoxConstraints);

        // Condition Text Box
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 2;
        conditionTextField = new TextFieldWithPlaceholder("Enter Prescription Condition", 24);
        content.add(conditionTextField.returnTextField(), formBoxConstraints);
        
        // Frequency Text Box
        formBoxConstraints.gridx = 1;
        formBoxConstraints.gridy = 2;
        frequencyTextField = new TextFieldWithPlaceholder("Enter Prescription Frequency", 24);
        content.add(frequencyTextField.returnTextField(), formBoxConstraints);
        
        // Date Prescribed Prescription
        formBoxConstraints.gridx = 0;
        formBoxConstraints.gridy = 3;
        SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        prescDateField = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(prescDateField, "yyyy-MM-dd");
        prescDateField.setEditor(dateEditor);
        content.add(prescDateField, formBoxConstraints);

        formBoxConstraints.gridy = 4;
        submitButton = new JButton("Create Prescription");
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
        String prescName, prescDose, prescCondition, prescFrequency;
        LocalDate prescDate;
        
        try{            
            prescName = nameTextField.returnTextField().getText();
            prescDose = doseTextField.returnTextField().getText();
            prescCondition = conditionTextField.returnTextField().getText();
            prescFrequency = frequencyTextField.returnTextField().getText();
            java.util.Date dateValue = (java.util.Date) prescDateField.getValue();

            prescDate = dateValue.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            
            nameTextField.returnTextField().setEnabled(false);
            doseTextField.returnTextField().setEnabled(false);
            conditionTextField.returnTextField().setEnabled(false);
            frequencyTextField.returnTextField().setEnabled(false);
            prescDateField.setEnabled(false);

        } catch (java.time.format.DateTimeParseException ex){
            JOptionPane.showMessageDialog(this,
                            "Cannot save date and time", "Invalid Input",
                            JOptionPane.WARNING_MESSAGE);
            ex.printStackTrace();
            return;
        }
        registerPrescription(prescName, prescDose, prescCondition, prescFrequency, prescDate);
    }
    
    public void registerPrescription(String name, String dose, String condition, String frequency, LocalDate date){
        PrescriptionController pController = new PrescriptionController();
        pController.createPrescription(name, dose, condition, frequency, date);
        System.out.println("Submit");
        JOptionPane.showMessageDialog(this,
                            "Successfully created doctor", "Invalid Input",
                            JOptionPane.INFORMATION_MESSAGE);
        
    }
}
