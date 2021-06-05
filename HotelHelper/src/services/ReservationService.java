package services;

import entities.ReservedRoom;
import entities.Room;
import util.CommonUtils;

import java.util.Date;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import db.FileHandler;

public class ReservationService {
    BufferedReader br = new BufferedReader(new java.io.InputStreamReader((System.in)));

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
    //     FileHandler fh = new FileHandler();
    //     fh.writeReservationFile(reservations, "reservations.ser");
    //     fh.readReservationFile("reservations.ser");
    //    fh.appendReservationToFile(rs, "reservations.ser");
    //     System.out.println("............................................");
    //     fh.readReservationFile("reservations.ser");

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
    public ReservedRoom addReservation(String id, String roomId, Vector<String> userId, Date startDate, Date endDate) throws IOException {
        // TODO: add it in the file as well
        // Add the reservation Id to the roomId
        // Validation to be done: end date should be after start date.
        ReservedRoom reservation =  new ReservedRoom(id, roomId, userId, startDate, endDate);
        (new FileHandler()).writeSingleReservation(reservation);
        return reservation;

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
    public ReservedRoom getReservation(Vector<ReservedRoom> reservations, String id) {
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getId() == id) 
            {
                return reservations.get(i);
            } 
        }
        return null;
    }
    public void viewAllReservations(Vector<ReservedRoom> reservedRoom) {
        String output = (new FileHandler()).retrieveAllReservations();
        System.out.println(output);
    }
    public Boolean menuInputValidator(String input, int begin, int end){
        int inputInteger = 0;
        try{
            inputInteger = Integer.parseInt(input);
            if(inputInteger >= begin && inputInteger <= end){
                return true;
            }
        }catch(NumberFormatException e){
            System.out.println("Sorry this input is invalid");
        }
        return false;
    }
    public ReservedRoom enterModificationDetailsForReservedRoom(ReservedRoom room) throws IOException {
 
        CommonUtils common = new CommonUtils();
        Date startDate, endDate;
       
        String input = br.readLine();
        String d1,m1,y1,d2,m2,y2;
        System.out.println("Enter 1 to modify the start date");
        System.out.println("Enter 2 to modify the end date");
        String a = br.readLine();
        int choice = 0;
        while (true) {
 
            if (this.menuInputValidator(a, 1, 2)) {
                choice = Integer.parseInt(a);
                break;
            } else
 
                System.out.println("Please enter a valid choice");
            a = br.readLine();
 
        }
        String val = br.readLine();
        System.out.println("Enter the new value: ");
        switch (choice) {
            case 1:
                System.out.println("Enter the start date: ");
                d1 = br.readLine();
                System.out.println("Enter the start month: ");
                m1 = br.readLine();
                System.out.println("Enter the start year: ");
                y1 = br.readLine();
                if(common.validateJavaDate(d1+"/"+m1+"/"+y1))
                {
                    Calendar cal = Calendar.getInstance();
                    cal.set(Integer.parseInt(y1),Integer.parseInt(m1),Integer.parseInt(d1)); // Year, month and day of month
                    startDate = (Date) cal.getTime();
                    if(startDate.compareTo(room.getEndDate())>0)
                    {
                        System.out.println("Start date cannot be greater than end date.");
                        return null;
                    }
                    room.setStartDate(startDate);
                    
                    System.out.println("Start date modified successfully");
                }
                else
                {
                    System.out.println("Enter a valid date ");
                }
                break;
            case 2:
                System.out.println("Enter the end date: ");
                d2 = br.readLine();
                System.out.println("Enter the end month: ");
                m2 = br.readLine();
                System.out.println("Enter the end year: ");
                y2 = br.readLine();
                if(common.validateJavaDate(d2+"/"+m2+"/"+y2))
                {
                    Calendar cal = Calendar.getInstance();
                    cal.set(Integer.parseInt(y2),Integer.parseInt(m2),Integer.parseInt(d2)); // Year, month and day of month
                    endDate = (Date) cal.getTime();
                    if(room.getStartDate().compareTo(endDate)>0)
                    {
                        System.out.println("End date cannot be less than start date. Please enter a valid end date.");
                        return null;
                    }
                    room.setEndDate(endDate);
                    System.out.println("End date modified successfully");
                }
                else
                {
                    System.out.println("Enter a valid date ");
                }
                break;
        }
        return room;
    }
    public ReservedRoom deleteReservation(String id, Vector<ReservedRoom> reservedRoom,Vector<Room> rooms) {
        for (int i = 0; i < reservedRoom.size(); i++) {
            if (reservedRoom.get(i).getId().equalsIgnoreCase(id)) {
                ReservedRoom reserveR = reservedRoom.get(i);
                //removes the reservation details from room 
                for (int j = 0; j < rooms.size(); j++) {
                    if (rooms.get(j).getRoomNumber().equalsIgnoreCase(reserveR.getRoomId())) {
                        rooms.get(j).getReservationId().remove(id);
                    }
                }
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
