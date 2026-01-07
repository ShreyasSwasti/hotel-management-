package hotel.main;

import hotel.service.ReservationService;
import hotel.exception.InvalidInputException;
import java.util.Scanner;

public class HotelReservationSystem {
    private ReservationService service;
    private Scanner scanner;

    public HotelReservationSystem() {
        service = new ReservationService();
        scanner = new Scanner(System.in);
    }

    public void showMenu() {
        System.out.println("\n=== HOTEL ROOM RESERVATION SYSTEM ===");
        System.out.println("1. Add Customer");
        System.out.println("2. View Available Rooms");
        System.out.println("3. Reserve Room");
        System.out.println("4. View Reservation Summary");
        System.out.println("5. Exit");
        System.out.print("Enter Choice: ");
    }

    public void handleMenuChoice(int choice) {
        try {
            switch (choice) {
                case 1:
                    service.addCustomer(scanner);
                    break;
                case 2:
                    service.viewAvailableRooms();
                    break;
                case 3:
                    service.reserveRoom(scanner);
                    break;
                case 4:
                    service.viewReservationSummary();
                    break;
                case 5:
                    System.out.println("Exiting System... Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Choice. Please select 1-5.");
            }
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected Error: " + e.getMessage());
            scanner.nextLine(); // Clear buffer in case of bad state
        }
    }

    public void start() {
        while (true) {
            showMenu();
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                handleMenuChoice(choice);
            } else {
                System.out.println("Invalid Input. Please enter a number.");
                scanner.next(); // Consume invalid input
            }
        }
    }

    public static void main(String[] args) {
        HotelReservationSystem system = new HotelReservationSystem();
        system.start();
    }
}
