package source_files.data.enums.defaultDataEnums;

public enum DefaultCarImageUrl {

    A4("https://res.cloudinary.com/dtnjuaxaa/image/upload/v1708046184/extendRent/defaultImages/audi-a4.jpg"),
    M3("https://res.cloudinary.com/dtnjuaxaa/image/upload/v1708046184/extendRent/defaultImages/BMW-M3.jpg"),
    MODEL3("https://res.cloudinary.com/dtnjuaxaa/image/upload/v1708046184/extendRent/defaultImages/Tesla-Model3.jpg"),
    AMG_C43("https://res.cloudinary.com/dtnjuaxaa/image/upload/v1708046184/extendRent/defaultImages/mercedes-AMG_C43.jpg");
    private final String label;

    DefaultCarImageUrl(String label) {
        this.label = label;
    }

    public static DefaultCarImageUrl[] getAll() {
        return values();
    }

    public String getLabel() {
        return label;
    }
}
