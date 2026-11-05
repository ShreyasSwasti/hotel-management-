# 🏨 Hotel Reservation System

> **A Java-based hotel reservation system that brings together customer management, room searching, reservations, and a graphical dashboard — making hotel management a little easier.**

![Java](https://img.shields.io/badge/Java-17%2B-orange?style=for-the-badge&logo=openjdk)
![GUI](https://img.shields.io/badge/Interface-Java%20Swing-blue?style=for-the-badge&logo=java)
![Status](https://img.shields.io/badge/Status-In%20Development-yellow?style=for-the-badge)

---

## ✨ What is this project?

Imagine walking into a hotel and having to manage **customers, rooms, prices, room types, and reservations** manually.

Messy, right? 😵‍💫

This project aims to simplify that process through a Java-based Hotel Reservation System.

It started as a simple console-based application and is gradually moving towards a graphical user interface (GUI).

The project focuses on implementing real-world hotel management operations using **Java, Object-Oriented Programming, and a service-based structure**.

---

## 🚀 Features

### 👤 Customer Management

Manage basic customer information, including:

- Customer ID
- Name
- Phone number

### 🛏️ Room Management

Explore rooms and their details.

You can:

- View available rooms
- Filter rooms by type
- Check room prices
- Monitor room availability

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

Find rooms according to their available categories.

### 📅 Room Reservation

Reserve rooms for registered customers.

The system keeps track of reservations and room availability.

### 🧾 Reservation Summary

View important reservation details and keep track of bookings.

---

## 🖥️ Graphical User Interface (GUI)

The project is gradually moving beyond its original console interface.

A Java Swing dashboard has been introduced to make room management easier and more visual.

### Dashboard Features

- 📊 **Total Rooms:** Displays the total number of rooms.
- 🟢 **Available Rooms:** Shows how many rooms are currently available.
- 🔴 **Occupied Rooms:** Displays the number of occupied rooms.
- 🔎 **Room Search:** Search rooms using room numbers or room types.
- 🏷️ **Availability Filter:** Filter rooms by All Rooms, Available, or Occupied.
- 🔄 **Refresh Rooms:** Refresh room information and dashboard statistics.
- 🎨 **Improved Interface:** Sidebar navigation, summary cards, and a structured room table.

The dashboard uses Java Swing components such as `JFrame`, `JPanel`, `JTable`, `JLabel`, and `JButton`.

*More GUI improvements are planned as development continues.*

---

## 🏗️ Project Structure

The project is organized into different packages to separate the responsibilities of the application.

```text
hotel-management/
│
├── UI/
│   └── HotelDashboard.java
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

*Note: The structure above represents the main source files. Actual folders may contain additional files.*

### Application Structure

```text
       ┌──────────────────────────┐
       │       User Interface     │
       │                          │
       │   Console / Swing GUI    │
       └────────────┬─────────────┘
                    │
                    ▼
       ┌──────────────────────────┐
       │    ReservationService    │
       │                          │
       │      Business Logic      │
       └────────────┬─────────────┘
                    │
          ┌─────────┼─────────┐
          ▼         ▼         ▼
       Customer    Room   Reservation
        Model     Model      Model
```

---

## 🧠 Concepts Demonstrated

This project is a practical way to understand important Java concepts:

- ☕ Object-Oriented Programming
- 📦 Classes and Objects
- 🔐 Encapsulation
- 🧩 Separation of Responsibilities
- 🔄 Loops and Conditional Statements
- 📋 Java Collections
- 🛠️ Service-Layer Design
- ⌨️ Console Input Handling
- ⚠️ Exception Handling
- 🔍 Searching and Filtering Data
- 🖥️ Java Swing GUI Development
- 🎨 Event Handling in Swing

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

Compile the required Java source files together, ensuring that package paths and dependencies are correct.

For example:

```bash
javac -d out hotel/model/*.java hotel/service/*.java hotel/main/HotelReservationSystem.java
```

### 4️⃣ Run the console application

```bash
java -cp out hotel.main.HotelReservationSystem
```

### 5️⃣ Run the GUI dashboard

Compile `HotelDashboard.java` along with its dependencies, then run:

```bash
java -cp out UI.HotelDashboard
```

*Note: These commands assume the source files and package declarations match the structure shown above.*

---

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| ☕ Java | Core application |
| 🧱 OOP | Application design |
| 📦 Java Collections | Data management |
| 🖥️ Java Swing | Graphical interface |
| 🎨 AWT | GUI styling and layouts |
| ⌨️ Scanner | Console input |
| 🌳 Git | Version control |
| 🐙 GitHub | Project hosting |

---

## 🔮 Future Improvements

There is still plenty of room to improve this project!

Some ideas for future development:

- 🖥️ Complete GUI integration
- 📅 Reservation management dashboard
- 👤 Customer management interface
- 🗄️ MySQL database integration
- 🔐 User authentication
- 💳 Payment processing
- 📧 Booking confirmation emails
- 📆 Check-in and check-out management
- 🧾 Automatic invoice generation
- 👨‍💼 Admin dashboard
- 📊 Reservation analytics
- 🔎 Advanced room search and filtering

---

## 📌 Project Status

<badge>In Development</badge>

This project is continuously evolving.

It began as a console-based Java application, and the graphical interface is now being developed step by step.

The goal is to gradually turn it into a more complete hotel management application while learning and improving along the way.

---

## 🤝 Contributions

Suggestions, improvements, and feedback are always welcome!

If you find something that can be improved, feel free to open an issue or suggest a feature.

---

**Made with ☕ Java and a lot of learning!**
