package Hotel_view;

import Hotel_source.RoomType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ReservationView extends JFrame {
    private JTextField roomNumberField = new JTextField(20);
    private JComboBox<RoomType> roomTypeComboBoxForCreation = new JComboBox<>(RoomType.values());
    private JButton createRoomButton = new JButton("Create Room");
    private JTextArea roomCreationStatusArea = new JTextArea(5, 30);

    private JTextField customerIdField = new JTextField(20);
    private JTextField roomIdField = new JTextField(20);
    private JTextField checkInDateField = new JTextField(20);
    private JTextField checkOutDateField = new JTextField(20);
    private JButton createReservationButton = new JButton("Create Reservation");

    private JTextField reservationIdForDetailsField = new JTextField(20);
    private JButton getReservationDetailsButton = new JButton("Get Reservation Details");
    private JTextArea reservationDetailsArea = new JTextArea(10, 30);

    private JTextField reservationIdForModificationField = new JTextField(20);
    private JTextField newCheckInDateField = new JTextField(20);
    private JTextField newCheckOutDateField = new JTextField(20);
    private JButton modifyReservationButton = new JButton("Modify Reservation");

    private JTextField reservationIdForCancellationField = new JTextField(20);
    private JButton cancelReservationButton = new JButton("Cancel Reservation");

    private JTextField startDateField = new JTextField(20);
    private JTextField endDateField = new JTextField(20);
    private JComboBox<RoomType> roomTypeComboBox = new JComboBox<>(RoomType.values());
    private JButton listAvailableRoomsButton = new JButton("List Available Rooms");
    private JTextArea availableRoomsArea = new JTextArea(10, 30);

    public ReservationView() {
        setTitle("Hotel Reservation System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        JPanel createRoomPanel = createPanel("Create Room", new Color(135, 206, 250));
        createRoomPanel.add(new JLabel("Room Number:"));
        createRoomPanel.add(roomNumberField);
        createRoomPanel.add(new JLabel("Room Type:"));
        createRoomPanel.add(roomTypeComboBoxForCreation);
        createRoomPanel.add(createRoomButton);
        createRoomPanel.add(new JScrollPane(roomCreationStatusArea));

        JPanel createReservationPanel = createPanel("Create Reservation", new Color(144, 238, 144));
        createReservationPanel.add(new JLabel("Customer ID:"));
        createReservationPanel.add(customerIdField);
        createReservationPanel.add(new JLabel("Room ID:"));
        createReservationPanel.add(roomIdField);
        createReservationPanel.add(new JLabel("Check-In Date (YYYY-MM-DD):"));
        createReservationPanel.add(checkInDateField);
        createReservationPanel.add(new JLabel("Check-Out Date (YYYY-MM-DD):"));
        createReservationPanel.add(checkOutDateField);
        createReservationPanel.add(createReservationButton);

        JPanel getReservationDetailsPanel = createPanel("Get Reservation Details", new Color(255, 182, 193));
        getReservationDetailsPanel.add(new JLabel("Reservation ID:"));
        getReservationDetailsPanel.add(reservationIdForDetailsField);
        getReservationDetailsPanel.add(getReservationDetailsButton);
        getReservationDetailsPanel.add(new JScrollPane(reservationDetailsArea));

        JPanel modifyReservationPanel = createPanel("Modify Reservation", new Color(255, 160, 122));
        modifyReservationPanel.add(new JLabel("Reservation ID:"));
        modifyReservationPanel.add(reservationIdForModificationField);
        modifyReservationPanel.add(new JLabel("New Check-In Date (YYYY-MM-DD):"));
        modifyReservationPanel.add(newCheckInDateField);
        modifyReservationPanel.add(new JLabel("New Check-Out Date (YYYY-MM-DD):"));
        modifyReservationPanel.add(newCheckOutDateField);
        modifyReservationPanel.add(modifyReservationButton);

        JPanel cancelReservationPanel = createPanel("Cancel Reservation", new Color(238, 232, 170));
        cancelReservationPanel.add(new JLabel("Reservation ID:"));
        cancelReservationPanel.add(reservationIdForCancellationField);
        cancelReservationPanel.add(cancelReservationButton);

        JPanel listAvailableRoomsPanel = createPanel("List Available Rooms", new Color(175, 238, 238));
        listAvailableRoomsPanel.add(new JLabel("Start Date (YYYY-MM-DD):"));
        listAvailableRoomsPanel.add(startDateField);
        listAvailableRoomsPanel.add(new JLabel("End Date (YYYY-MM-DD):"));
        listAvailableRoomsPanel.add(endDateField);
        listAvailableRoomsPanel.add(new JLabel("Room Type:"));
        listAvailableRoomsPanel.add(roomTypeComboBox);
        listAvailableRoomsPanel.add(listAvailableRoomsButton);
        listAvailableRoomsPanel.add(new JScrollPane(availableRoomsArea));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.add(createRoomPanel);
        mainPanel.add(createReservationPanel);
        mainPanel.add(getReservationDetailsPanel);
        mainPanel.add(modifyReservationPanel);
        mainPanel.add(cancelReservationPanel);
        mainPanel.add(listAvailableRoomsPanel);

        add(new JScrollPane(mainPanel));
        this.setVisible(true);
    }

    private JPanel createPanel(String title, Color color) {
        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.setBorder(createTitledBorder(title));
        panel.setBackground(color);
        return panel;
    }

    private TitledBorder createTitledBorder(String title) {
        TitledBorder border = BorderFactory.createTitledBorder(title);
        border.setTitleFont(new Font("Arial", Font.BOLD, 14));
        border.setTitleColor(Color.DARK_GRAY);
        border.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        return border;
    }

    public int getRoomNumber() {
        return Integer.parseInt(roomNumberField.getText());
    }

    public RoomType getRoomTypeForCreation() {
        return (RoomType) roomTypeComboBoxForCreation.getSelectedItem();
    }

    public void setRoomCreationStatus(String status) {
        roomCreationStatusArea.setText(status);
    }

    public int getCustomerId() {
        return Integer.parseInt(customerIdField.getText());
    }

    public int getRoomId() {
        return Integer.parseInt(roomIdField.getText());
    }

    public LocalDate getCheckInDate() {
        return LocalDate.parse(checkInDateField.getText());
    }

    public LocalDate getCheckOutDate() {
        return LocalDate.parse(checkOutDateField.getText());
    }

    public void setReservationDetails(String details) {
        reservationDetailsArea.setText(details);
    }

    public LocalDate getNewCheckInDate() {
        return LocalDate.parse(newCheckInDateField.getText());
    }

    public LocalDate getNewCheckOutDate() {
        return LocalDate.parse(newCheckOutDateField.getText());
    }

    public LocalDate getStartDate() {
        return LocalDate.parse(startDateField.getText());
    }

    public LocalDate getEndDate() {
        return LocalDate.parse(endDateField.getText());
    }

    public RoomType getRoomType() {
        return (RoomType) roomTypeComboBox.getSelectedItem();
    }

    public void setAvailableRooms(String rooms) {
        availableRoomsArea.setText(rooms);
    }

    public int getReservationIdForDetails() {
        return Integer.parseInt(reservationIdForDetailsField.getText());
    }

    public int getReservationIdForModification() {
        return Integer.parseInt(reservationIdForModificationField.getText());
    }

    public int getReservationIdForCancellation() {
        return Integer.parseInt(reservationIdForCancellationField.getText());
    }

    public void addCreateRoomListener(ActionListener listener) {
        createRoomButton.addActionListener(listener);
    }

    public void addCreateReservationListener(ActionListener listener) {
        createReservationButton.addActionListener(listener);
    }

    public void addGetReservationDetailsListener(ActionListener listener) {
        getReservationDetailsButton.addActionListener(listener);
    }

    public void addModifyReservationListener(ActionListener listener) {
        modifyReservationButton.addActionListener(listener);
    }

    public void addCancelReservationListener(ActionListener listener) {
        cancelReservationButton.addActionListener(listener);
    }

    public void addListAvailableRoomsListener(ActionListener listener) {
        listAvailableRoomsButton.addActionListener(listener);
    }
}
