package src.service.image.car.model;

public enum DefaultCarImage {

    A4("https://res.cloudinary.com/dtnjuaxaa/image/upload/v1708634794/extendRent/defaultImages/audi-a4.png"),
    M3("https://res.cloudinary.com/dtnjuaxaa/image/upload/v1708634794/extendRent/defaultImages/bmw-m3-2022.png"),
    MODEL3("https://res.cloudinary.com/dtnjuaxaa/image/upload/v1708634795/extendRent/defaultImages/tesla%20model%203.png"),
    GLE("https://res.cloudinary.com/dtnjuaxaa/image/upload/v1708634794/extendRent/defaultImages/mercedes-gle.png");
    private final String url;

    DefaultCarImage(String url) {
        this.url = url;
    }

    public static DefaultCarImage[] getAll() {
        return values();
    }

    public String getUrl() {
        return url;
    }
}
