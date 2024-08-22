package Hotel_controller;

import Hotel_source.ReservationManager;
import Hotel_view.ReservationView;
import Hotel_source.Reservation;
import Hotel_source.Room;
import Hotel_source.RoomType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class ReservationController {
    private ReservationManager reservationManager;
    private ReservationView reservationView;

    public ReservationController(ReservationManager reservationManager, ReservationView reservationView) {
        this.reservationManager = reservationManager;
        this.reservationView = reservationView;

        this.reservationView.addCreateRoomListener(new CreateRoomListener());
        this.reservationView.addCreateReservationListener(new CreateReservationListener());
        this.reservationView.addGetReservationDetailsListener(new GetReservationDetailsListener());
        this.reservationView.addCancelReservationListener(new CancelReservationListener());
        this.reservationView.addModifyReservationListener(new ModifyReservationListener());
        this.reservationView.addListAvailableRoomsListener(new ListAvailableRoomsListener());
    }

    class CreateRoomListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int roomNumber = reservationView.getRoomNumber();
                RoomType roomType = reservationView.getRoomTypeForCreation();
                reservationManager.createRoom(roomNumber, roomType);
                reservationView.setRoomCreationStatus("Room created successfully: " + roomNumber);
            } catch (Exception ex) {
                reservationView.setRoomCreationStatus("Error creating room: " + ex.getMessage());
            }
        }
    }

    class CreateReservationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int customerId = reservationView.getCustomerId();
                int roomId = reservationView.getRoomId();
                LocalDate checkInDate = reservationView.getCheckInDate();
                LocalDate checkOutDate = reservationView.getCheckOutDate();
                Reservation reservation = reservationManager.createReservation(customerId, roomId, checkInDate, checkOutDate);
                reservationView.setReservationDetails("Reservation created: " + reservation.getId());
            } catch (Exception ex) {
                reservationView.setReservationDetails("Error creating reservation: " + ex.getMessage());
            }
        }
    }

    class GetReservationDetailsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int reservationId = reservationView.getReservationIdForDetails();
                Reservation reservation = reservationManager.getReservationDetails(reservationId);
                reservationView.setReservationDetails("Reservation ID: " + reservation.getId() +
                        "\nCustomer ID: " + reservation.getCustomerId() +
                        "\nRoom ID: " + reservation.getRoomId() +
                        "\nCheck-In Date: " + reservation.getCheckInDate() +
                        "\nCheck-Out Date: " + reservation.getCheckOutDate());
            } catch (Exception ex) {
                reservationView.setReservationDetails("Error retrieving reservation details: " + ex.getMessage());
            }
        }
    }

    class CancelReservationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int reservationId = reservationView.getReservationIdForCancellation();
                reservationManager.cancelReservation(reservationId);
                reservationView.setReservationDetails("Reservation cancelled: " + reservationId);
            } catch (Exception ex) {
                reservationView.setReservationDetails("Error cancelling reservation: " + ex.getMessage());
            }
        }
    }

    class ModifyReservationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int reservationId = reservationView.getReservationIdForModification();
                LocalDate newCheckInDate = reservationView.getNewCheckInDate();
                LocalDate newCheckOutDate = reservationView.getNewCheckOutDate();
                reservationManager.modifyReservation(reservationId, newCheckInDate, newCheckOutDate);
                reservationView.setReservationDetails("Reservation modified: " + reservationId);
            } catch (Exception ex) {
                reservationView.setReservationDetails("Error modifying reservation: " + ex.getMessage());
            }
        }
    }

    class ListAvailableRoomsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                LocalDate startDate = reservationView.getStartDate();
                LocalDate endDate = reservationView.getEndDate();
                RoomType roomType = reservationView.getRoomType();
                List<Room> availableRooms = reservationManager.listAvailableRooms(startDate, endDate, roomType);
                StringBuilder roomList = new StringBuilder("Available rooms:\n");
                for (Room room : availableRooms) {
                    roomList.append("Room Number: ").append(room.getRoomNumber())
                            .append(", Type: ").append(room.getRoomType()).append("\n");
                }
                reservationView.setAvailableRooms(roomList.toString());
            } catch (Exception ex) {
                reservationView.setAvailableRooms("Error listing available rooms: " + ex.getMessage());
            }
        }
    }
}
