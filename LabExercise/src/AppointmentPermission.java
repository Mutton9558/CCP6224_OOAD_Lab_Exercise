public interface AppointmentPermission {
    boolean canViewActiveAppointments();
    boolean canAddAppointments();
    boolean canUpdateAppointments();
    boolean canEditAppointments();
    boolean canSearchAppointments();
}
