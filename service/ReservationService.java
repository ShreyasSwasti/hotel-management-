package hotel.service;

import hotel.exception.InvalidInputException;
import hotel.model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReservationService {
    private List<Room> rooms;
    private List<Customer> customers;
    private List<Reservation> reservations;

    public ReservationService() {
        rooms = new ArrayList<>();
        customers = new ArrayList<>();
        reservations = new ArrayList<>();
        initializeRooms();
    }

    private void initializeRooms() {
        rooms.add(new Room(101, RoomType.STANDARD, 1000.0));
        rooms.add(new Room(102, RoomType.STANDARD, 1000.0));
        rooms.add(new Room(201, RoomType.DELUXE, 2500.0));
        rooms.add(new Room(202, RoomType.DELUXE, 2500.0));
        rooms.add(new Room(301, RoomType.SUITE, 5000.0));
    }

    public void addCustomer(Scanner scanner) {
        try {
            Customer customer = new Customer();
            customer.inputCustomerDetails(scanner);
            customers.add(customer);
            System.out.println("Customer added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding customer: " + e.getMessage());
            scanner.nextLine(); // clear buffer if needed
        }
    }

    public void viewAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        boolean found = false;
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println(room);
                found = true;
            }
        }
        if (!found) System.out.println("No rooms available.");
    }

    public void reserveRoom(Scanner scanner) throws InvalidInputException {
        System.out.print("Enter Customer ID: ");
        if (!scanner.hasNextInt()) {
            String invalid = scanner.next();
            throw new InvalidInputException("Customer ID must be numeric (You entered: " + invalid + ")");
        }
        int customerId = scanner.nextInt();
        
        Customer customer = findCustomer(customerId);
        if (customer == null) {
            throw new InvalidInputException("Customer not found with ID: " + customerId);
        }

        viewAvailableRooms();
        System.out.print("Enter Room Number to Reserve: ");
        if (!scanner.hasNextInt()) {
            String invalid = scanner.next();
            throw new InvalidInputException("Room Number must be numeric (You entered: " + invalid + ")");
        }
        int roomNumber = scanner.nextInt();

        Room room = findRoom(roomNumber);
        if (room == null) {
            throw new InvalidInputException("Room not found.");
        }
        if (!room.isAvailable()) {
            throw new InvalidInputException("Room is not available.");
        }

        System.out.print("Enter Number of Days: ");
        if (!scanner.hasNextInt()) {
            String invalid = scanner.next();
            throw new InvalidInputException("Days must be numeric (You entered: " + invalid + ")");
        }
        int days = scanner.nextInt();
        if (days <= 0) throw new InvalidInputException("Days must be positive.");

        Reservation reservation = new Reservation(customer, room, days);
        reservations.add(reservation);

        // Multithreading
        ReservationThread bookingThread = new ReservationThread(reservation);
        InvoiceThread invoiceTask = new InvoiceThread(reservation);
        Thread invoiceThread = new Thread(invoiceTask, "InvoiceThread");

        bookingThread.start();
        invoiceThread.start();
        
        try {
            bookingThread.join();
            invoiceThread.join();
        } catch (InterruptedException e) {
            System.out.println("Process Interrupted.");
        }
    }
    
    public void viewReservationSummary() {
        System.out.println("\n--- All Reservations ---");
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            for (Reservation res : reservations) {
                System.out.println(res);
                System.out.println("-----------------");
            }
        }
    }

    private Customer findCustomer(int id) {
        for (Customer c : customers) {
            if (c.getId() == id) return c;
        }
        return null;
    }

    private Room findRoom(int roomNumber) {
        for (Room r : rooms) {
            if (r.getRoomNumber() == roomNumber) return r;
        }
        return null;
    }
}
