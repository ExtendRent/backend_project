package src.service.vehicle.features.common.fuel.model;

public enum DefaultFuelType {

    PETROL("Benzin"),
    DIESEL("Dizel"),
    ELECTRIC("Elektrik"),
    HYBRID("Hybrid"),
    LPG("Lpg"),
    GASOLINE_LPG("Benzin Lpg"),
    NO_FUEL("Yakıt Yok");
    private final String label;

    DefaultFuelType(String label) {
        this.label = label;
    }

    public static DefaultFuelType[] getAll() {
        return values();
    }

    public String getLabel() {
        return label;
    }
}
