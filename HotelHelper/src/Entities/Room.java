package Entities;

public class Room {
    private String id;
    private String roomNumber;
    private Float roomPrice;
    private String roomType;
    private String currentReservationIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Float getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Float roomPrice) {
        this.roomPrice = roomPrice;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getCurrentReservationIds() {
        return currentReservationIds;
    }

    public void setCurrentReservationIds(String currentReservationIds) {
        this.currentReservationIds = currentReservationIds;
    }
}
