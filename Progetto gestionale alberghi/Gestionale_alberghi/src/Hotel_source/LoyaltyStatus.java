package Hotel_source;

public enum LoyaltyStatus {
    STANDARD("Standard"),
    SILVER("Silver"),
    GOLD("Gold"),
    PLATINUM("Platinum");

    private final String displayName;

    LoyaltyStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return name().toUpperCase();
    }
}
