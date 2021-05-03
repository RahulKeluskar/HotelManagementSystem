package entities;

public class Room {
    private String id;
    private String roomNumber;
    private Float roomPrice;
    private String roomType;
    private String currentReservationIds;

    public Room(){}
    public Room(String id) {
        this.id = id;
    }


    public Room(String id, String roomNumber, Float roomPrice, String roomType, String currentReservationIds) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.roomPrice = roomPrice;
        this.roomType = roomType;
        this.currentReservationIds = currentReservationIds;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", roomPrice=" + roomPrice +
                ", roomType='" + roomType + '\'' +
                ", currentReservationIds='" + currentReservationIds + '\'' +
                '}';
    }

    public void printRoomDetails() {
        System.out.println(this.toString());
    }

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
