package hotel.model;

import java.util.Scanner;

public class Customer {
    private int id;
    private String name;
    private String phone;

    public Customer() {
        // Default constructor
    }

    public Customer(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void inputCustomerDetails(Scanner scanner) {
        System.out.print("Enter Customer ID: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid ID. Please enter a number.");
            scanner.next();
        }
        this.id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Customer Name: ");
        this.name = scanner.nextLine();

        System.out.print("Enter Customer Phone: ");
        this.phone = scanner.next();
    }

    public void displayCustomerDetails() {
        System.out.println("Customer Details:");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phone);
    }
}
