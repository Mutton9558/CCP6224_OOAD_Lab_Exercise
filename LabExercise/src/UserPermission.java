public interface UserPermission {
    //    dashboard
    public boolean canViewPrescriptions();
//    patient records
    public boolean canViewSelfRecords();
    
    public boolean canViewPatientRecords();
    public boolean canViewDoctorRecords();
    public boolean canViewReceptionistRecords();
    public boolean canUpdateAppointments();
    public boolean canAddPrescriptions();
    public boolean canAddDiagnosis();
    
//    doctor records
    public boolean canViewMedicalRecords();
    
    public boolean canEditProfile();
    public boolean canAddAppointments();
    public boolean canGenerateReport();
    
    
    public boolean canSearchAppointments();
}
