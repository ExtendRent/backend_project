package source_files.data.enums.defaultDataEnums.Status;

public enum DefaultRentalStatus {
    WAITING("Beklemede"),
    ACTIVE("Aktif"),
    FINISHED("Tamamlandı");
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
