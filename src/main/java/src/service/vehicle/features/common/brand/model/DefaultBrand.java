package src.service.vehicle.features.common.brand.model;

public enum DefaultBrand {

    AUDI("Audi", "https://www.carlogos.org/car-logos/audi-logo-2016-640.png"),

    BMW("BMW", "https://www.carlogos.org/car-logos/bmw-logo-2020-gray.png"),

    TESLA("Tesla", "https://www.carlogos.org/car-logos/tesla-logo-2007-full-640.png"),

    MERCEDES("Mercedes-Benz", "https://www.carlogos.org/logo/Mercedes-Benz-logo-2011-1920x1080.png"),

    TOYOTA("Toyota", "https://www.carlogos.org/car-logos/toyota-logo-2020-europe-640.png");

    private final String label;
    private final String imageUrl;

    DefaultBrand(String label, String imageUrl) {
        this.label = label;
        this.imageUrl = imageUrl;
    }


    public static DefaultBrand[] getAll() {
        return values();
    }

    public String getLabel() {
        return label;
    }

    public String getImageUrl() {
        return imageUrl;
    }


}
