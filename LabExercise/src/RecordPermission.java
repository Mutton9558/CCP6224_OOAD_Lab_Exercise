public interface RecordPermission {
    boolean canViewPatientRecords();
    boolean canViewDoctorRecords();
    boolean canViewReceptionistRecords();
    boolean canSearchRecords();
}