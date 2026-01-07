package hotel.service;

import hotel.model.Reservation;

public class ReservationThread extends Thread {
    private Reservation reservation;

    public ReservationThread(Reservation reservation) {
        this.reservation = reservation;
        this.setName("ReservationThread");
    }

    @Override
    public void run() {
        System.out.println(">> " + getName() + " started...");
        try {
            // Validate availability doubles check (already done in service, but processing simulation)
            if (reservation.getRoom().isAvailable()) {
                Thread.sleep(1000); // Simulate delay
                reservation.confirmBooking();
                System.out.println(">> Booking Status: " + reservation.getStatus());
            } else {
                reservation.cancelBooking();
                System.out.println(">> Booking Failed: Room not available.");
            }
        } catch (InterruptedException e) {
            System.out.println("Reservation interrupted.");
        }
    }
}
