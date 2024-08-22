package Hotel_source;

public class Room{
    private int roomNumber;
    private RoomType roomType;
    private double basePrice;

    public Room(int roomNumber, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.basePrice = roomType.getBasePrice();
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public double getBasePrice(){
        return this.basePrice;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", roomType='" + roomType + '\'' +
                '}';
    }
}