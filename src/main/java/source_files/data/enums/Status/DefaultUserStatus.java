package source_files.data.enums.Status;

public enum DefaultUserStatus {
    PENDING_VERIFYING("Onay Bekleniyor"),
    VERIFIED("Onaylandı"),
    BLOCKED("Engelli"),
    TIME_BLOCKED("Süre Engelli"),
    TIME_RENTAL_BLOCKED("Süre Kiralama Engelli");

    private final String label;

    DefaultUserStatus(String label) {
        this.label = label;
    }

    public static DefaultUserStatus[] getAll() {
        return values();
    }

    public String getLabel() {
        return label;
    }
}
