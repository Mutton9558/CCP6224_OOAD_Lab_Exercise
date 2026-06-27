public interface AdminPermission {
    boolean canAddDoctors();
    boolean canAddReceptionist();
    boolean canGenerateReport();
}