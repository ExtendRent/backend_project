package src.service.user.model;

public enum DefaultUserStatus {
    PENDING_VERIFYING("Onay Bekleniyor"),
    VERIFIED("Onaylandı"),
    BLOCKED("Engellendi");
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
