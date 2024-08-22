package Hotel_source;

public class Service {
    private static int idCounter = 0;
    private int id;
    private String name;
    private double price;
    private boolean isAvailable;

    public Service(String name, double price, boolean isAvailable) {
        this.id = idCounter++;
        this.name = name;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public int getServiceId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "Service{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", price=" + price +
               ", isAvailable=" + isAvailable +
               '}';
    }
}