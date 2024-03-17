package src.service.paperwork.discount.model;

public enum DefaultDiscount {

    NONE(0),
    DEFAULT(5),
    PAIR5(20),
    HOSGELDIN(10);

    private final int percentage;

    DefaultDiscount(int percentage) {
        this.percentage = percentage;
    }

    public static DefaultDiscount[] getAll() {
        return values();
    }

    public int getPercentage() {
        return percentage;
    }
}
