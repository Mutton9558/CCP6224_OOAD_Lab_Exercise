public interface PrescriptionPermission {
    boolean canViewPrescriptions();
    boolean canAddPrescriptions();
    boolean canEditPrescription();
    boolean canAddDiagnosis();
    boolean canEditDiagnosis();
}