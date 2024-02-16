package source_files.data.enums.defaultDataEnums;

public enum DefaultCarModel {

    A4("A4"),
    M3("M3"),
    MODEL3("Model3"),
    AMG_C43("AMG C43"),
    COROLLA("Corolla");

    private final String label;

    DefaultCarModel(String label) {
        this.label = label;
    }

    public static DefaultCarModel[] getAll() {
        return values();
    }

    public String getLabel() {
        return label;
    }
}
