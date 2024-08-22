package Hotel_source;

public enum RoomType {
    SINGLE("Single", 50.0, "A room assigned to one person with a single bed."),
    DOUBLE("Double", 80.0, "A room assigned to two people with one double bed."),
    SUITE("Suite", 150.0, "A luxurious room with separate living area, often includes extra amenities."),
    FAMILY("Family", 120.0, "A room with multiple beds for families or groups.");

    private final String name;
    private final double basePrice;
    private final String description;

    RoomType(String name, double basePrice, String description) {
        this.name = name;
        this.basePrice = basePrice;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name + " - " + description + " (Base price: $" + basePrice + ")";
    }
}
