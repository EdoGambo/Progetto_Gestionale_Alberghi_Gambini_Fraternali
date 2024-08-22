package Hotel_source;

public enum ExpenseCategory {
    MAINTENANCE("Maintenance"),
    UTILITIES("Utilities"),
    SALARIES("Salaries"),
    MARKETING("Marketing"),
    SUPPLIES("Supplies"),
    FOOD_AND_BEVERAGE("Food & Beverage"),
    FURNITURE_AND_EQUIPMENT("Furniture & Equipment"),
    LEGAL_FEES("Legal Fees"),
    TAXES("Taxes"),
    OTHER("Other");

    private final String displayName;

    ExpenseCategory(String displayName) {
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