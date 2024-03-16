package src.data.enums.default_data_enums;

public enum DefaultPaymentType {
    CREDIT_CARD("Kredi Kartı"),
    CASH("Ofiste Ödeme"),
    BANK_MONEY_TRANSFER("Havale");

    private final String label;

    DefaultPaymentType(String label) {
        this.label = label;
    }

    // Yeni eklenen method
    public static DefaultPaymentType[] getAll() {
        return values();
    }

    public String getLabel() {
        return label;
    }

}
