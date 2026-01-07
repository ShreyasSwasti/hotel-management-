package hotel.model;

public class Reservation {
    private Customer customer;
    private Room room;
    private Integer days;       // Wrapper as per requirement
    private Double totalAmount; // Wrapper as per requirement
    private String status;

    public Reservation(Customer customer, Room room, Integer days) {
        this.customer = customer;
        this.room = room;
        this.days = days;
        this.totalAmount = calculateTotalAmount();
        this.status = "PENDING";
    }

    private Double calculateTotalAmount() {
        return days * room.getPricePerDay();
    }

    public void confirmBooking() {
        this.status = "CONFIRMED";
        this.room.setAvailable(false);
    }

    public void cancelBooking() {
        this.status = "FAILED";
    }

    public Customer getCustomer() {
        return customer;
    }

    public Room getRoom() {
        return room;
    }

    public Integer getDays() {
        return days;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Reservation Details:\n" +
               "Customer: " + customer.getName() + "\n" +
               "Room: " + room.getRoomNumber() + " (" + room.getRoomType() + ")\n" +
               "Days: " + days + "\n" +
               "Total: $" + totalAmount + "\n" +
               "Status: " + status;
    }
}
