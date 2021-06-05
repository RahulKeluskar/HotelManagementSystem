package services;

import entities.ReservedRoom;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import db.FileHandler;

public class ReservationService {

    public static void main(String args[]) {
        //For testing..
    	//Don't forget to delete it
        Vector<ReservedRoom> reservations = new Vector<ReservedRoom>();
        String id = "14";
        String roomId = "1234";
        Vector<String> users = new Vector<String>();

        Calendar cal = Calendar.getInstance();
        cal.set(2013, Calendar.JANUARY, 14); // Year, month and day of month
        Date startDate = (Date) cal.getTime();
        cal.set(2013, Calendar.JANUARY, 19); // Year, month and day of month
        Date endDate = (Date) cal.getTime();
        ReservedRoom rs = new ReservedRoom(id, roomId, users, startDate, endDate);

        reservations.add(rs);

        cal.set(2013, Calendar.JANUARY, 10); // Year, month and day of month
        startDate = (Date) cal.getTime();
        cal.set(2013, Calendar.JANUARY, 20); // Year, month and day of month
        endDate = (Date) cal.getTime();
        rs = new ReservedRoom("13", roomId, users, startDate, endDate);
        reservations.add(rs);

        cal.set(2013, Calendar.JANUARY, 10); // Year, month and day of month
        startDate = (Date) cal.getTime();

        cal.set(2013, Calendar.JANUARY, 16); // Year, month and day of month
        endDate = (Date) cal.getTime();

        ReservationService reservationService = new ReservationService();
        // Vector<String> rd = reservationService.getReservedIds(reservations,
        // startDate, endDate);
        // for (String number : rd) {
        // System.out.println("Number = " + number);
        // }

//        Vector<String> rd = new Vector<String>();
//        rd.add("13");
//        boolean val = reservationService.isRoomFreeForGivenDate(rd, reservations, endDate);
//        System.out.print(val);
        //	rs = new ReservedRoom("15", roomId, users, startDate, endDate);
        //reservations.add(rs);
        FileHandler fh = new FileHandler();
        fh.writeReservationFile(reservations, "reservations.ser");
        fh.readReservationFile("reservations.ser");
       fh.appendReservationToFile(rs, "reservations.ser");
        System.out.println("............................................");
        fh.readReservationFile("reservations.ser");

    }

    // public List<ReservedRoom> getFreeRooms(){
    // return null;
    // }

    // public List<ReservedRoom> getOccupiedRooms(){
    // return null;
    // }

    // public List<ReservedRoom> getAllReservedRooms(){
    // return null;
    // }

    // public ReservedRoom getRoomReservationDetails(){
    // return null;
    // }

    /*
     * This function takes a vector of reservations as input along with the start
     * and end date. It then returns a vector of all the reservations that are
     * clashing with this reservation. This list can then be used to find out both:
     * the list of empty rooms and occupied rooms. To find empty rooms: if none of
     * the reservation ids for the room are in the returned vector, the room is
     * empty, otherwise occupied.
     */
    public Vector<String> getReservedIds(Vector<ReservedRoom> reservations, Date startDate, Date endDate) {
        Vector<String> reservedIds = new Vector<String>();
        for (int i = 0; i < reservations.size(); i++) {
            if (((reservations.get(i).getStartDate()).compareTo(startDate) < 0
                    && (reservations.get(i).getEndDate()).compareTo(startDate) < 0)
                    || (reservations.get(i).getStartDate()).compareTo(endDate) > 0) {

            } else {
                reservedIds.add(reservations.get(i).getId());
            }
        }

        return reservedIds;
    }

    /*
     * Accepts the Vector of reservationIds for a given room, vector for all the
     * current and upcoming reservations and a specific date Loop through all the
     * upcoming reservations and see if the reservation id is there in the given
     * list and that, if the room is free on that date If the room is not free on
     * that date, add it to the list. Return true if room is free (list is empty),
     * else false
     */
    public boolean isRoomFreeForGivenDate(Vector<String> reservationIds, Vector<ReservedRoom> reservations, Date date) {
        List<ReservedRoom> reservationArrayList = new ArrayList<ReservedRoom>(reservations);
       // Vector<ReservedRoom> result = new Vector<ReservedRoom>();
        List<ReservedRoom> result = reservationArrayList.stream()
                .filter(reservation -> reservationIds.contains(reservation.getId())
                        && (!(date.compareTo(reservation.getStartDate()) >= 0
                                && date.compareTo(reservation.getEndDate()) <= 0)))
                .collect(Collectors.toList());
        return (result.size() != 0);

    }

    /*
     * Adds a new reservation to the file and also returns an object of the
     * reservation.
     */
    public ReservedRoom addReservation(String id, String roomId, Vector<String> userId, Date startDate, Date endDate) {
        // TODO: add it in the file as well
        // Add the reservation Id to the roomId
        // Validation to be done: end date should be after start date.
        return new ReservedRoom(id, roomId, userId, startDate, endDate);

    }

    public ReservedRoom deleteReservation(String id, Vector<ReservedRoom> reservedRoom) {
        for (int i = 0; i < reservedRoom.size(); i++) {
            if (reservedRoom.get(i).getId().equalsIgnoreCase(id)) {
                ReservedRoom reserveR = reservedRoom.get(i);
                reservedRoom.remove(i);
                return reserveR;
            }
        }
        return new ReservedRoom();
    }

    public ReservedRoom findReservationById(String id){
        FileHandler fileHandler = new FileHandler();
        return fileHandler.findSingleReservationById(id);
    }
}
