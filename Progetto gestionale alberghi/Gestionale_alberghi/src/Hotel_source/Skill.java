package Hotel_source;

public enum Skill {
    HOUSEKEEPING("Housekeeping"),
    FRONT_DESK("Front Desk Operations"),
    MAINTENANCE("Maintenance"),
    KITCHEN("Kitchen Operations"),
    MANAGEMENT("Management"),
    CUSTOMER_SERVICE("Customer Service"),
    IT_SUPPORT("IT Support"),
    ACCOUNTING("Accounting"),
    MARKETING("Marketing"),
    SECURITY("Security");

    private final String description;

    Skill(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
