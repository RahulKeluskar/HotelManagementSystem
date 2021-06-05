package entities;

import java.util.Date;
import java.io.Serializable;
import java.util.Vector;

public class ReservedRoom implements Serializable{
    private String id;
    private String roomId;
    private Vector<String> userId;
    private Date startDate;
    private Date endDate;

    public ReservedRoom() {

    }
    public ReservedRoom(String id, String roomId, Vector<String> userId, Date startDate, Date endDate) {
        this.id = id;
        this.roomId = roomId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Vector<String> getUserId() {
        return userId;
    }

    public void setUserId(Vector<String> userId) {
        this.userId = userId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    @Override
    public String toString() {
        return new StringBuffer(" \nId: ").append(this.id)
                .append(" \nRoom Id : ").append(this.roomId).append(" \nUsers : ").append(this.userId.toString()).append(" \nStart Date : ").append(this.startDate).append(" \nEnd Date : ").append(this.endDate).toString();
    }
}
