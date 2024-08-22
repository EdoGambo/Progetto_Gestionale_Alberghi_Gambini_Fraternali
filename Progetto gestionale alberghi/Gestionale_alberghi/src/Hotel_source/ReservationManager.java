package Hotel_source;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ReservationManager {
    private List<Reservation> reservations;
    private List<Room> rooms;
    private int nextReservationId = 1;  

    public ReservationManager() {
        reservations = new ArrayList<>();
        rooms = new ArrayList<>();
        loadRoomsFromFile("rooms.txt");
        loadReservationsFromFile("reservations.txt");
    }

    public Reservation createReservation(int customerId, int roomId, LocalDate checkInDate, LocalDate checkOutDate) {
        if (!isRoomAvailable(roomId, checkInDate, checkOutDate)) {
            throw new IllegalArgumentException("Room is not available for the selected dates.");
        }

        Reservation newReservation = new Reservation(nextReservationId++, customerId, roomId, checkInDate, checkOutDate);  
        reservations.add(newReservation);
        saveReservationToFile(newReservation, "reservations.txt");
        return newReservation;
    }

    public Reservation getReservationDetails(int reservationId) {
        return reservations.stream()
                .filter(reservation -> reservation.getId() == reservationId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Reservation ID " + reservationId + " does not exist."));
    }

    public void cancelReservation(int reservationId) {
        reservations.removeIf(reservation -> reservation.getId() == reservationId);
        saveAllReservationsToFile("reservations.txt");
    }

    public void modifyReservation(int reservationId, LocalDate newCheckInDate, LocalDate newCheckOutDate) {
        Reservation reservation = getReservationDetails(reservationId);
        reservation.setCheckInDate(newCheckInDate);
        reservation.setCheckOutDate(newCheckOutDate);
        saveAllReservationsToFile("reservations.txt");
    }

    public List<Room> listAvailableRooms(LocalDate startDate, LocalDate endDate, RoomType roomType) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getRoomType() == roomType && isRoomAvailable(room.getRoomNumber(), startDate, endDate)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public Room createRoom(int roomNumber, RoomType roomType) {
        Room room = new Room(roomNumber, roomType);
        rooms.add(room);
        saveRoomToFile(room, "rooms.txt");
        return room;
    }

    private void loadRoomsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                int roomNumber = Integer.parseInt(parts[0].trim());
                String roomTypeString = parts[1].trim().split(" - ")[0].trim().toUpperCase();
                RoomType roomType = RoomType.valueOf(roomTypeString);
                Room room = new Room(roomNumber, roomType);
                rooms.add(room);
            }
        } catch (IOException e) {
            System.err.println("Error loading rooms from file: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error parsing room type from file: " + e.getMessage());
        }
    }

    private void loadReservationsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int reservationId = Integer.parseInt(parts[0].trim());
                int customerId = Integer.parseInt(parts[1].trim());
                int roomId = Integer.parseInt(parts[2].trim());
                LocalDate checkInDate = null;
                LocalDate checkOutDate = null;

                try {
                    checkInDate = LocalDate.parse(parts[3].trim());
                    checkOutDate = LocalDate.parse(parts[4].trim());
                } catch (DateTimeParseException e) {
                    System.err.println("Error parsing date for reservation: " + line + " - " + e.getMessage());
                    continue;  
                }

                Reservation reservation = new Reservation(reservationId, customerId, roomId, checkInDate, checkOutDate);
                reservations.add(reservation);

                if (reservationId >= nextReservationId) {
                    nextReservationId = reservationId + 1;
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading reservations from file: " + e.getMessage());
        }
    }
    
    private void saveRoomToFile(Room room, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(room.getRoomNumber() + "," + room.getRoomType() + "," + room.getRoomType().getBasePrice() + "\n");
        } catch (IOException e) {
            System.err.println("Error saving room to file: " + e.getMessage());
        }
    }

    private void saveReservationToFile(Reservation reservation, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(reservation.getId() + "," + reservation.getCustomerId() + "," + reservation.getRoomId() + "," + reservation.getCheckInDate() + "," + reservation.getCheckOutDate() + "\n");
        } catch (IOException e) {
            System.err.println("Error saving reservation to file: " + e.getMessage());
        }
    }

    private void saveAllReservationsToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false))) {
            for (Reservation reservation : reservations) {
                writer.write(reservation.getId() + "," + reservation.getCustomerId() + "," + reservation.getRoomId() + "," + reservation.getCheckInDate() + "," + reservation.getCheckOutDate() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error saving reservations to file: " + e.getMessage());
        }
    }

    private boolean isRoomAvailable(int roomNumber, LocalDate startDate, LocalDate endDate) {
        for (Reservation reservation : reservations) {
            if (reservation.getRoomId() == roomNumber) {
                if (!(endDate.isBefore(reservation.getCheckInDate()) || startDate.isAfter(reservation.getCheckOutDate()))) {
                    return false;
                }
            }
        }
        return true;
    }
}

