package source_files.data.enums.defaultDataEnums;

public enum DefaultCarBodyType {

    SEDAN("Sedan"),
    HATCHBACK("Hatchback"),
    COUPE("Coupe"),
    SUV("SUV"),
    PICKUP("Pickup");
    private final String label;

    DefaultCarBodyType(String label) {
        this.label = label;
    }

    public static DefaultCarBodyType[] getAll() {
        return values();
    }

    public String getLabel() {
        return label;
    }
}
