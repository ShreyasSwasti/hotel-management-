package hotel.ui;

import hotel.model.Room;
import hotel.service.ReservationService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.List;

public class HotelDashboard extends JFrame {

    private final ReservationService service;
    private final DefaultTableModel tableModel;

    private final JLabel totalRoomsLabel;
    private final JLabel availableRoomsLabel;
    private final JLabel occupiedRoomsLabel;

    private final JTextField searchField;
    private final JComboBox<String> filterCombo;

    private List<Room> allRooms;

    public HotelDashboard() {
        service = new ReservationService();

        setTitle("Grand Hotel | Management Dashboard");
        setSize(1050, 650);
        setMinimumSize(new Dimension(850, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(new Color(245, 247, 250));
        setLayout(new BorderLayout());

        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(25, 39, 60));
        sidebar.setPreferredSize(new Dimension(200, 0));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        JLabel brand = new JLabel("  GRAND HOTEL");
        brand.setForeground(Color.WHITE);
        brand.setFont(new Font("SansSerif", Font.BOLD, 20));
        brand.setBorder(
                BorderFactory.createEmptyBorder(30, 10, 40, 5)
        );

        sidebar.add(brand);

        JButton roomsButton = createSidebarButton("Room Overview");
        roomsButton.setBackground(new Color(55, 80, 110));
        sidebar.add(roomsButton);

        JLabel menuLabel = new JLabel(
                "<html><div style='padding:15px;color:#B9C5D6'>"
                + "Reservations<br><br>Customers<br><br>Reports"
                + "</div></html>"
        );

        menuLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        menuLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        sidebar.add(menuLabel);

        add(sidebar, BorderLayout.WEST);

        // Main content
        JPanel content = new JPanel(new BorderLayout(15, 20));
        content.setOpaque(false);

        content.setBorder(
                BorderFactory.createEmptyBorder(25, 25, 25, 25)
        );

        // Header
        JLabel heading = new JLabel("Hotel Dashboard");
        heading.setFont(new Font("SansSerif", Font.BOLD, 28));
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
        JPanel cards = new JPanel(new GridLayout(1, 3, 15, 0));
        cards.setOpaque(false);

        totalRoomsLabel = new JLabel("0");
        availableRoomsLabel = new JLabel("0");
        occupiedRoomsLabel = new JLabel("0");

        cards.add(createCard("TOTAL ROOMS", totalRoomsLabel));
        cards.add(createCard("AVAILABLE ROOMS", availableRoomsLabel));
        cards.add(createCard("OCCUPIED ROOMS", occupiedRoomsLabel));

        JPanel top = new JPanel(new BorderLayout(0, 18));
        top.setOpaque(false);

        top.add(header, BorderLayout.NORTH);
        top.add(cards, BorderLayout.SOUTH);

        content.add(top, BorderLayout.NORTH);

        // Search and filter section
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        searchPanel.setOpaque(false);

        searchField = new JTextField(20);
        searchField.setToolTipText("Search by room number or room type");

        filterCombo = new JComboBox<>(
                new String[]{"All Rooms", "Available", "Occupied"}
        );

        JButton searchButton = new JButton("Search");
        JButton clearButton = new JButton("Clear");

        searchButton.addActionListener(e -> filterRooms());

        clearButton.addActionListener(e -> {
            searchField.setText("");
            filterCombo.setSelectedIndex(0);
            filterRooms();
        });

        searchField.addActionListener(e -> filterRooms());

        filterCombo.addActionListener(e -> filterRooms());

        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(new JLabel("Status:"));
        searchPanel.add(filterCombo);
        searchPanel.add(clearButton);

        // Room table
        String[] columns = {
                "Room Number",
                "Room Type",
                "Price / Day",
                "Availability"
        };

        tableModel = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable roomTable = new JTable(tableModel);

        roomTable.setRowHeight(35);
        roomTable.setFont(new Font("SansSerif", Font.PLAIN, 13));

        roomTable.getTableHeader().setFont(
                new Font("SansSerif", Font.BOLD, 13)
        );

        roomTable.getTableHeader().setBackground(
                new Color(230, 235, 243)
        );

        roomTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        // Color availability cells
        roomTable.getColumnModel()
                .getColumn(3)
                .setCellRenderer(new DefaultTableCellRenderer() {

                    @Override
                    public Component getTableCellRendererComponent(
                            JTable table,
                            Object value,
                            boolean selected,
                            boolean focused,
                            int row,
                            int column) {

                        Component component =
                                super.getTableCellRendererComponent(
                                        table, value, selected,
                                        focused, row, column
                                );

                        if (!selected) {
                            if ("Available".equals(value)) {
                                component.setForeground(
                                        new Color(30, 145, 85)
                                );
                            } else {
                                component.setForeground(
                                        new Color(210, 75, 75)
                                );
                            }
                        }

                        return component;
                    }
                });

        JScrollPane scrollPane = new JScrollPane(roomTable);

        JPanel roomPanel = new JPanel(new BorderLayout(0, 10));
        roomPanel.setOpaque(false);

        JLabel roomHeading = new JLabel("Room Overview");
        roomHeading.setFont(
                new Font("SansSerif", Font.BOLD, 19)
        );

        roomPanel.add(roomHeading, BorderLayout.NORTH);
        roomPanel.add(searchPanel, BorderLayout.CENTER);
        roomPanel.add(scrollPane, BorderLayout.SOUTH);

        // Correct table layout
        JPanel tableContainer = new JPanel(new BorderLayout(0, 10));
        tableContainer.setOpaque(false);

        tableContainer.add(roomHeading, BorderLayout.NORTH);
        tableContainer.add(searchPanel, BorderLayout.CENTER);
        tableContainer.add(scrollPane, BorderLayout.SOUTH);

        // Use GridBagLayout to give the table remaining space
        JPanel roomSection = new JPanel(new BorderLayout(0, 10));
        roomSection.setOpaque(false);
        roomSection.add(roomHeading, BorderLayout.NORTH);
        roomSection.add(searchPanel, BorderLayout.CENTER);
        roomSection.add(scrollPane, BorderLayout.SOUTH);

        content.add(roomSection, BorderLayout.CENTER);

        // Footer
        JPanel footer = new JPanel(
                new FlowLayout(FlowLayout.RIGHT)
        );

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
        button.setMaximumSize(new Dimension(200, 45));

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

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(225, 230, 237)
                        ),
                        BorderFactory.createEmptyBorder(
                                15, 18, 15, 18
                        )
                )
        );

        JLabel titleLabel = new JLabel(title);

        titleLabel.setForeground(new Color(105, 115, 130));
        titleLabel.setFont(
                new Font("SansSerif", Font.BOLD, 12)
        );

        valueLabel.setForeground(new Color(35, 75, 125));
        valueLabel.setFont(
                new Font("SansSerif", Font.BOLD, 28)
        );

        card.add(titleLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(valueLabel);

        return card;
    }

    private void refreshRooms() {

        allRooms = service.getRooms();

        int total = allRooms.size();
        int available = 0;

        for (Room room : allRooms) {
            if (room.isAvailable()) {
                available++;
            }
        }

        int occupied = total - available;

        totalRoomsLabel.setText(String.valueOf(total));
        availableRoomsLabel.setText(String.valueOf(available));
        occupiedRoomsLabel.setText(String.valueOf(occupied));

        filterRooms();
    }

    private void filterRooms() {

        if (allRooms == null) {
            return;
        }

        tableModel.setRowCount(0);

        String searchText = searchField.getText()
                .trim()
                .toLowerCase();

        String selectedFilter =
                (String) filterCombo.getSelectedItem();

        for (Room room : allRooms) {

            String roomNumber =
                    String.valueOf(room.getRoomNumber());

            String roomType =
                    String.valueOf(room.getRoomType());

            boolean matchesSearch =
                    roomNumber.toLowerCase().contains(searchText)
                    || roomType.toLowerCase().contains(searchText);

            boolean matchesFilter = true;

            if ("Available".equals(selectedFilter)) {
                matchesFilter = room.isAvailable();
            } else if ("Occupied".equals(selectedFilter)) {
                matchesFilter = !room.isAvailable();
            }

            if (matchesSearch && matchesFilter) {

                String availability = room.isAvailable()
                        ? "Available" : "Occupied";

                tableModel.addRow(new Object[]{
                        room.getRoomNumber(),
                        room.getRoomType(),
                        String.format(
                                "₹%.2f",
                                room.getPricePerDay()
                        ),
                        availability
                });
            }
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            HotelDashboard dashboard = new HotelDashboard();
            dashboard.setVisible(true);

        });
    }
}