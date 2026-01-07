package hotel.model;

public class Room {
    private int roomNumber;
    private RoomType roomType;
    private double pricePerDay;
    private boolean isAvailable;

    public Room(int roomNumber, RoomType roomType, double pricePerDay) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerDay = pricePerDay;
        this.isAvailable = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " [" + roomType + "] - Price: $" + pricePerDay + " - Available: " + isAvailable;
    }
}
