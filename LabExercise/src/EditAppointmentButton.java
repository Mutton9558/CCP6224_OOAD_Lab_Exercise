import java.awt.*;
import javax.swing.*;
import java.util.function.Consumer;

public class EditAppointmentButton extends JButton{
    public EditAppointmentButton(Consumer<Appointment> editAppointmentFunction, Appointment target){
        super("Edit Appointment");
        this.setPreferredSize(new Dimension(50, 30));
        this.addActionListener(e -> {
            editAppointmentFunction.accept(target);
        });
        this.setVisible(true);
    }
}
