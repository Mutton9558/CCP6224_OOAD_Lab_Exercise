import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginUI extends JDialog implements ActionListener {

    JPanel panel;
    JLabel user_label, password_label, message;
    JTextField userID_text;
    JPasswordField password_text;
    JButton submit;

    private UIConstants uiConstant = new UIConstants();
    private LoginController.LoginCallback callback;

    public LoginUI(Window parent, LoginController.LoginCallback callback) {
        super(parent, "Login", Dialog.ModalityType.APPLICATION_MODAL);
        this.callback = callback;

        // main panel - blue background same as other UIs
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(uiConstant.Azure);
        GridBagConstraints adj = new GridBagConstraints();
        adj.gridwidth = GridBagConstraints.REMAINDER;
        adj.insets = new Insets(10, 50, 5, 50);
        adj.weightx = 1.0;

        // hospital logo at top
        adj.gridy = 0;
        adj.anchor = GridBagConstraints.CENTER;
        adj.insets = new Insets(20, 50, 10, 50);
        JLabel logo = new JLabel(UIConstants.MMlogo);
        panel.add(logo, adj);

        // username label
        adj.gridy = 1;
        adj.anchor = GridBagConstraints.WEST;
        adj.insets = new Insets(10, 50, 2, 50);
        user_label = new JLabel("User ID:");
        user_label.setForeground(Color.WHITE);
        user_label.setFont(new Font("Sans-Serif", Font.PLAIN, 14));
        panel.add(user_label, adj);

        // username field
        adj.gridy = 2;
        adj.fill = GridBagConstraints.HORIZONTAL;
        adj.insets = new Insets(0, 50, 10, 50);
        userID_text = new JTextField(24);
        panel.add(userID_text, adj);

        // password label
        adj.gridy = 3;
        adj.fill = GridBagConstraints.NONE;
        adj.insets = new Insets(5, 50, 2, 50);
        password_label = new JLabel("Password:");
        password_label.setForeground(Color.WHITE);
        password_label.setFont(new Font("Sans-Serif", Font.PLAIN, 14));
        panel.add(password_label, adj);

        // password field
        adj.gridy = 4;
        adj.fill = GridBagConstraints.HORIZONTAL;
        adj.insets = new Insets(0, 50, 10, 50);
        password_text = new JPasswordField(24);
        panel.add(password_text, adj);

        // message label for errors
        adj.gridy = 5;
        adj.fill = GridBagConstraints.NONE;
        adj.anchor = GridBagConstraints.CENTER;
        adj.insets = new Insets(0, 50, 5, 50);
        message = new JLabel(" ");
        message.setForeground(Color.RED);
        message.setFont(new Font("Sans-Serif", Font.PLAIN, 12));
        panel.add(message, adj);

        // bottom row - sign in button + "don't have an account?" text side by side
        adj.gridy = 6;
        adj.gridwidth = 1;
        adj.fill = GridBagConstraints.NONE;
        adj.anchor = GridBagConstraints.WEST;
        adj.insets = new Insets(5, 50, 20, 10);
        submit = new JButton("Sign in");
        submit.setBackground(UIConstants.DarkBlue);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        panel.add(submit, adj);

        adj.gridx = 1;
        adj.gridy = 6;
        adj.anchor = GridBagConstraints.EAST;
        adj.insets = new Insets(5, 10, 20, 50);
        JLabel registerText = new JLabel("Don't have an account?");
        registerText.setForeground(Color.WHITE);
        registerText.setFont(new Font("Sans-Serif", Font.PLAIN, 12));
        panel.add(registerText, adj);

        add(panel, BorderLayout.CENTER);
        setTitle("Please Login Here!");
        setSize(500, 420);
        setLocationRelativeTo(parent);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userIDText = userID_text.getText().trim();
        String password = new String(password_text.getPassword()).trim();

        // empty field check
        if (userIDText.isEmpty() || password.isEmpty()) {
            message.setText("Please enter User ID and password.");
            return;
        }

        // ID must be a number
        int userID;
        try {
            userID = Integer.parseInt(userIDText);
        } catch (NumberFormatException ex) {
            message.setText("User ID must be a number.");
            return;
        }

        // validate login
        LoginController controller = LoginController.getInstance();
        boolean success = controller.validateLogin(userID, password);

        if (success) {
            JOptionPane.showMessageDialog(this,
                            "Successfully logged in!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
            dispose();
            callback.onLoginSuccess(controller.getSystem());
        } else {
            message.setText("Invalid User ID or password.");
            password_text.setText("");
        }
    }
}