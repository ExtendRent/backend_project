package src.service.rental.status.model;

public enum DefaultRentalStatus {
    WAITING("Beklemede"),
    STARTED("Başladı"),
    FINISHED("Tamamlandı"),
    CANCELED("İptal");
    private final String label;

    DefaultRentalStatus(String label) {
        this.label = label;
    }

    public static DefaultRentalStatus[] getAll() {
        return values();
    }

    public String getLabel() {
        return label;
    }

}
