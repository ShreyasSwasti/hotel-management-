package hotel.service;

import hotel.model.Reservation;

public class InvoiceThread implements Runnable {
    private Reservation reservation;

    public InvoiceThread(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public void run() {
        System.out.println(">> " + Thread.currentThread().getName() + " preparing invoice...");
        try {
            // Wait for reservation to be confirmed
            int retries = 0;
            while (!"CONFIRMED".equals(reservation.getStatus()) && !"FAILED".equals(reservation.getStatus())) {
                if (retries > 5) break; 
                Thread.sleep(500);
                retries++;
            }

            if ("CONFIRMED".equals(reservation.getStatus())) {
                System.out.println("\n-----------------------------");
                System.out.println("       INVOICE SUMMARY       ");
                System.out.println("-----------------------------");
                System.out.println(reservation);
                System.out.println("-----------------------------");
            }
        } catch (InterruptedException e) {
            System.out.println("Invoice generation interrupted.");
        }
    }
}
