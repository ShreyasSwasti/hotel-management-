# 🏨 Hotel Reservation System

> **A simple, console-based hotel reservation system built in Java — manage customers, explore rooms, search by price, and make reservations without the paperwork.**

![Java](https://img.shields.io/badge/Java-17%2B-orange?style=for-the-badge&logo=openjdk)
![Console App](https://img.shields.io/badge/Application-Console%20Based-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-In%20Development-yellow?style=for-the-badge)

---

## ✨ What is this project?

Imagine walking into a hotel and having to manage **customers, rooms, prices, room types, and reservations** manually.

Messy, right? 😵‍💫

This project turns that process into a simple **Java-based hotel reservation system**.

The application provides a menu-driven interface where users can:

- 👤 Add and manage customers
- 🛏️ View available rooms
- 🔎 Filter rooms by type
- 💰 Search rooms by price
- 📋 Make room reservations
- 🧾 View reservation summaries

It is designed to demonstrate how a real-world hotel workflow can be implemented using **Java, object-oriented programming, services, and basic data management**.

---

## 🚀 Features

### 👤 Customer Management
Add customer information such as:

- Customer ID
- Name
- Phone number

### 🛏️ Room Management

Explore available rooms and their details.

You can search rooms based on:

- Room type
- Availability
- Price

### 💰 Search Rooms by Price

Looking for something within a budget?

The system allows users to search for rooms according to their price range.

Example:

```text
Enter maximum price: 3000

Available Rooms:
Room 101 | Single | ₹2000
Room 103 | Double | ₹2500
Room 105 | Single | ₹2800
```

### 🏷️ Filter by Room Type

Need a particular type of room?

Choose from available room categories and quickly find matching rooms.

### 📅 Room Reservation

Once you find the right room, you can reserve it for a customer.

The system keeps track of the reservation and room availability.

### 🧾 Reservation Summary

View important reservation details in one place, making it easier to keep track of bookings.

---

## 🖥️ How It Looks

When the application starts, you'll see a simple menu:

```text
========================================
       🏨 HOTEL RESERVATION SYSTEM
========================================

1. Add Customer
2. View Available Rooms
3. View Rooms By Type
4. Search Rooms By Price
5. Reserve Room
6. View Reservation Summary
7. Exit

Enter Choice:
```

Simple. Fast. No complicated interface.

Just choose an option and get things done. ⚡

---

## 🏗️ Project Structure

```text
hotel-management/
│
├── hotel/
│   ├── main/
│   │   └── HotelReservationSystem.java
│   │
│   ├── model/
│   │   ├── Customer.java
│   │   ├── Room.java
│   │   └── Reservation.java
│   │
│   └── service/
│       └── ReservationService.java
│
└── README.md
```

The project follows a simple separation of responsibilities:

```text
        ┌─────────────────────────┐
        │  HotelReservationSystem │
        │          Main            │
        └────────────┬────────────┘
                     │
                     ▼
        ┌─────────────────────────┐
        │   ReservationService    │
        │  Business Logic / Flow  │
        └────────────┬────────────┘
                     │
          ┌──────────┼──────────┐
          ▼          ▼          ▼
      Customer     Room     Reservation
```

---

## 🧠 Concepts Demonstrated

This project is also a practical way to understand important Java concepts:

- ☕ Object-Oriented Programming
- 📦 Classes and Objects
- 🔐 Encapsulation
- 🧩 Separation of responsibilities
- 🔄 Loops and conditional statements
- 📋 Collections
- 🛠️ Service-layer design
- ⌨️ Console input handling
- ⚠️ Exception handling
- 🔍 Searching and filtering data

---

## ▶️ How to Run

### 1️⃣ Clone the repository

```bash
git clone <your-repository-url>
```

### 2️⃣ Navigate into the project

```bash
cd hotel-management
```

### 3️⃣ Compile the project

```bash
javac hotel/main/HotelReservationSystem.java
```

If your project contains multiple Java files, compile the required source files together.

### 4️⃣ Run the application

```bash
java hotel.main.HotelReservationSystem
```

---

## 🎯 Example Workflow

A typical session might look like this:

```text
=== HOTEL RESERVATION SYSTEM ===

1. Add Customer
2. View Available Rooms
3. View Rooms By Type
4. Search Rooms By Price
5. Reserve Room
6. View Reservation Summary
7. Exit

Enter Choice: 1

Enter Customer ID: 101
Enter Customer Name: Shreyas
Enter Customer Phone: 523665423

Customer added successfully! ✅
```

Then:

```text
Enter Choice: 4

Enter maximum price: 3000

Rooms within your budget:

Room 101 → Single → ₹2000
Room 103 → Double → ₹2500
Room 105 → Single → ₹2800
```

Finally:

```text
Enter Choice: 5

Enter Customer ID: 101
Enter Room Number: 103

Reservation successful! 🎉
```

---

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| ☕ Java | Core application |
| 🧱 OOP | Application design |
| 📦 Java Collections | Data management |
| ⌨️ Scanner | User input |
| 🖥️ Console | User interface |
| 🌳 Git | Version control |
| 🐙 GitHub | Project hosting |

---

## 🔮 Future Improvements

This project can grow far beyond a console application.

Possible future upgrades:

- 🖥️ Graphical User Interface
- 🌐 Web-based hotel booking
- 🗄️ MySQL database integration
- 🔐 User authentication
- 💳 Payment processing
- 📧 Booking confirmation emails
- 📅 Check-in / check-out dates
- 🧾 Automatic invoice generation
- 👨‍💼 Admin dashboard
- 📊 Reservation analytics
- 🔎 Advanced room search and filtering

---

##
