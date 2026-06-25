public interface UserPermission {
    //    dashboard
    public boolean canViewPrescriptions();
    public boolean canViewActiveAppointments();
    public boolean canViewPatientRecords();
    public boolean canViewPatientProfile();
    public boolean canViewDoctorProfile();
    public boolean canViewReceptionistProfile();
    public boolean canViewDoctorRecords();
    public boolean canViewReceptionistRecords();
    public boolean canUpdateAppointments();
    public boolean canAddPrescriptions();
    public boolean canAddDiagnosis();
    public boolean canSearchRecords();
    public boolean canEditProfile();
    public boolean canAddAppointments();
    public boolean canGenerateReport();
    public boolean canEditAppointments();
    public boolean canSearchAppointments();
    public boolean canAddPatient();
    public boolean canAddDoctors();
    public boolean canAddReceptionist();
    
//     Profile Viewing 
    public boolean canEditDiagnosis();
    public boolean canEditPrescription();
}
