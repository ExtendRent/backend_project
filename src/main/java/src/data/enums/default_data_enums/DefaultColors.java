package src.data.enums.default_data_enums;

public enum DefaultColors {

    WHITE("Beyaz"),

    BLACK("Siyah"),

    RED("Kırmızı"),

    GREY("Gümüş̧"),

    BLUE("Mavi");
    private final String label;

    DefaultColors(String label) {
        this.label = label;
    }

    public static DefaultColors[] getAll() {
        return values();
    }

    public String getLabel() {
        return label;
    }


}
