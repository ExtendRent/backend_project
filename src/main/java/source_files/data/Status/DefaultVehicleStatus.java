package source_files.data.Status;

public enum DefaultVehicleStatus {
    AVAILABLE("Kullanılabilir"),
    IN_USE("Kullanımda"),
    MAINTENANCE("Bakımda"),
    UNAVAILABLE("Kullanılamaz"),
    BOOKED("Rezerve"),
    DELETED("Sistem Dışı");

    private final String label;

    DefaultVehicleStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
