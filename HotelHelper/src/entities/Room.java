package entities;

import java.io.Serializable;
import java.util.Vector;

public class Room implements Serializable {
    private String id;
    private String roomNumber;
    private Float roomPrice;
    private String roomType;
    private String currentReservationId;
    private Vector<String> reservationId;


    public Room(){}
    public Room(String id) {
        this.id = id;
    }

    public Room(String id, String roomNumber, Float roomPrice, String roomType, String currentReservationId,Vector<String> reservationId) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.roomPrice = roomPrice;
        this.roomType = roomType;
        this.currentReservationId = currentReservationId;
        this.reservationId=reservationId;
    }

    public void printRoomDetails() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", roomPrice=" + roomPrice +
                ", roomType='" + roomType + '\'' +
                ", currentReservationId='" + currentReservationId + '\'' +
                ", reservationId=" + reservationId +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public Float getRoomPrice() {
        return roomPrice;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getCurrentReservationId() {
        return currentReservationId;
    }

    public Vector<String> getReservationId() {
        return reservationId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setRoomPrice(Float roomPrice) {
        this.roomPrice = roomPrice;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setCurrentReservationId(String currentReservationId) {
        this.currentReservationId = currentReservationId;
    }

    public void setReservationId(Vector<String> reservationId) {
        this.reservationId = reservationId;
    }
}
