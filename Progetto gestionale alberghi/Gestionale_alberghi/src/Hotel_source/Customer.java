package Hotel_source;

public class Customer extends Person{

    private int id;
    private LoyaltyStatus loyaltyStatus;

    public Customer(String name, String email, String phone, LoyaltyStatus loyaltyStatus) {
        super(name, email, phone);
        this.loyaltyStatus = loyaltyStatus;
    }

    public Customer(int id, String name, String email, String phone, LoyaltyStatus loyaltyStatus) {
        super(name, email, phone);
        this.id = id;
        this.loyaltyStatus = loyaltyStatus;
    }

    public Customer(int id, String name, String email, String phone) {
        super(name, email, phone);
        this.id = id;
    }

    public Customer(String name) {
        super(name);
    }

    public int getId() {
        return id;
    }

    public LoyaltyStatus getLoyaltyStatus() {
        return loyaltyStatus;
    }

    public void setLoyaltyStatus(LoyaltyStatus status) {
        this.loyaltyStatus = status;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + email + "," + phone + "," + loyaltyStatus;
    }

    public static Customer fromString(String str) {
        String[] parts = str.split(",");
        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid customer string: " + str);
        }
        int id = Integer.parseInt(parts[0].trim());
        String name = parts[1].trim();
        String email = parts[2].trim();
        String phone = parts[3].trim();
        LoyaltyStatus loyaltyStatus = LoyaltyStatus.valueOf(parts[4].trim().toUpperCase());
        return new Customer(id, name, email, phone, loyaltyStatus);
    }
}
