package src.data.enums.default_data_enums;

public enum DefaultShiftType {

    SEMI_AUTO("Yarı Otomatik"),
    MANUAL("Manuel"),
    AUTOMATIC("Otomatik"),
    TRIPTONIC("Triptonik"),
    NO_GEAR("Vites Yok");
    private final String label;

    DefaultShiftType(String label) {
        this.label = label;
    }

    public static DefaultShiftType[] getAll() {
        return values();
    }

    public String getLabel() {
        return label;
    }
}
