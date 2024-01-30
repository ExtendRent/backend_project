package source_files.data.types.itemTypes;

public enum PaymentType {
    CREDIT_CARD("Kredi Kartı"),
    CASH("Ofiste Ödeme"),
    BANK_MONEY_TRANSFER("Havale");

    private final String label;

    PaymentType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
