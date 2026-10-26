package hotel.ui;

import hotel.model.Room;
import hotel.service.ReservationService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HotelDashboard extends JFrame {

    private final ReservationService service;
    private final DefaultTableModel tableModel;
    private final JLabel totalRoomsLabel;
    private final JLabel availableRoomsLabel;

    public HotelDashboard() {
        service = new ReservationService();

        setTitle("Hotel Management System");
        setSize(900, 560);
        setMinimumSize(new Dimension(750, 450));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(new Color(245, 247, 250));
        setLayout(new BorderLayout());

        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(25, 39, 60));
        sidebar.setPreferredSize(new Dimension(190, 0));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        JLabel brand = new JLabel("  GRAND HOTEL");
        brand.setForeground(Color.WHITE);
        brand.setFont(new Font("SansSerif", Font.BOLD, 19));
        brand.setBorder(
                BorderFactory.createEmptyBorder(28, 12, 35, 5)
        );
        sidebar.add(brand);

        JButton roomsButton = createSidebarButton("Room Overview");
        sidebar.add(roomsButton);

        JLabel comingSoon = new JLabel(
                "<html><div style='padding:10px;color:#B9C5D6'>"
                + "Reservations<br><br>Customers"
                + "</div></html>"
        );
        comingSoon.setFont(new Font("SansSerif", Font.PLAIN, 14));
        comingSoon.setAlignmentX(Component.LEFT_ALIGNMENT);
        sidebar.add(comingSoon);

        add(sidebar, BorderLayout.WEST);

        // Main content
        JPanel content = new JPanel(new BorderLayout(15, 20));
        content.setOpaque(false);
        content.setBorder(
                BorderFactory.createEmptyBorder(25, 25, 25, 25)
        );

        JLabel heading = new JLabel("Hotel Dashboard");
        heading.setFont(new Font("SansSerif", Font.BOLD, 27));
        heading.setForeground(new Color(35, 45, 65));

        JLabel subtitle = new JLabel(
                "Manage and monitor your hotel rooms"
        );
        subtitle.setForeground(new Color(110, 120, 135));

        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.add(heading);
        header.add(Box.createVerticalStrut(6));
        header.add(subtitle);

        // Summary cards
        JPanel cards = new JPanel(new GridLayout(1, 2, 15, 0));
        cards.setOpaque(false);

        totalRoomsLabel = new JLabel("0");
        availableRoomsLabel = new JLabel("0");

        cards.add(createCard("TOTAL ROOMS", totalRoomsLabel));
        cards.add(createCard("AVAILABLE ROOMS", availableRoomsLabel));

        JPanel top = new JPanel(new BorderLayout(0, 18));
        top.setOpaque(false);
        top.add(header, BorderLayout.NORTH);
        top.add(cards, BorderLayout.SOUTH);

        content.add(top, BorderLayout.NORTH);

        // Room table
        String[] columns = {
                "Room Number", "Room Type", "Price / Day", "Availability"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable roomTable = new JTable(tableModel);
        roomTable.setRowHeight(32);
        roomTable.setFont(new Font("SansSerif", Font.PLAIN, 13));
        roomTable.getTableHeader().setFont(
                new Font("SansSerif", Font.BOLD, 13)
        );
        roomTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane = new JScrollPane(roomTable);

        JPanel roomPanel = new JPanel(new BorderLayout(0, 10));
        roomPanel.setOpaque(false);

        JLabel roomHeading = new JLabel("Room Overview");
        roomHeading.setFont(new Font("SansSerif", Font.BOLD, 18));

        roomPanel.add(roomHeading, BorderLayout.NORTH);
        roomPanel.add(scrollPane, BorderLayout.CENTER);

        content.add(roomPanel, BorderLayout.CENTER);

        // Footer buttons
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footer.setOpaque(false);

        JButton refreshButton = new JButton("Refresh Rooms");
        JButton exitButton = new JButton("Exit");

        refreshButton.addActionListener(e -> refreshRooms());
        exitButton.addActionListener(e -> dispose());

        footer.add(refreshButton);
        footer.add(exitButton);

        content.add(footer, BorderLayout.SOUTH);
        add(content, BorderLayout.CENTER);

        refreshRooms();
    }

    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setMaximumSize(new Dimension(190, 45));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(47, 65, 90));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        return button;
    }

    private JPanel createCard(String title, JLabel valueLabel) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(225, 230, 237)
                ),
                BorderFactory.createEmptyBorder(15, 18, 15, 18)
        ));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(new Color(105, 115, 130));
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 12));

        valueLabel.setForeground(new Color(35, 75, 125));
        valueLabel.setFont(new Font("SansSerif", Font.BOLD, 27));

        card.add(titleLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(valueLabel);

        return card;
    }

    private void refreshRooms() {
        List<Room> rooms = service.getRooms();
        tableModel.setRowCount(0);

        int availableCount = 0;

        for (Room room : rooms) {
            String availability = room.isAvailable()
                    ? "Available" : "Occupied";

            if (room.isAvailable()) {
                availableCount++;
            }

            tableModel.addRow(new Object[]{
                    room.getRoomNumber(),
                    room.getRoomType(),
                    String.format("₹%.2f", room.getPricePerDay()),
                    availability
            });
        }

        totalRoomsLabel.setText(String.valueOf(rooms.size()));
        availableRoomsLabel.setText(String.valueOf(availableCount));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HotelDashboard dashboard = new HotelDashboard();
            dashboard.setVisible(true);
        });
    }
}
