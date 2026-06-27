public interface ProfilePermission {
    boolean canViewPatientProfile();
    boolean canViewDoctorProfile();
    boolean canViewReceptionistProfile();
    boolean canEditProfile();
    boolean canAddPatient();
}
