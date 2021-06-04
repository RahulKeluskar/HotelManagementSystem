package services;

import db.FileHandler;
import entities.Room;
import entities.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.Vector;

public class RoomService {
    BufferedReader br = new BufferedReader(new java.io.InputStreamReader((System.in)));


//    public List<Room> getAllRooms() {
//        return null;
//    }

    //Prints a list of all the Rooms
    public void viewAllRooms(Vector<Room> rooms) {

        for (int i = 0; i < rooms.size(); i++) {
            System.out.println(rooms.get(i).toString());
        }

    }

    public Room addRoomwithIdOnly(){
        Room newroom = new Room(UUID.randomUUID().toString());
        FileHandler.insertSingleRoomIntoFile(newroom);
        return newroom;
    }

    // public Room addRoomwithAllDetails(String roomNumber, Float roomPrice, String
    // roomType, String currentReservationIds, Vector<String> reservationId){
    // Room newroom = new
    // Room(UUID.randomUUID().toString(),roomNumber,roomPrice,roomType,currentReservationIds,reservationId);
    // return newroom;
    // }


    //Generated UUID for Unique ID for Room and appends the Room to file
    public Room addRoomwithAllDetails(String roomNumber, Float roomPrice, String roomType, String currentReservationIds,
            Vector<String> reservationId) {
        Room newroom = new Room(UUID.randomUUID().toString(),roomNumber,roomPrice,roomType,currentReservationIds,reservationId);
        FileHandler.insertSingleRoomIntoFile(newroom);
        return newroom;
    }

    public Room deleteRoom(String id, Vector<Room> room){
        for (int i = 0; i < room.size(); i++) {
            if (room.get(i).getId().equalsIgnoreCase(id)) {
                Room roomOld = room.get(i);
                room.remove(i);
                return roomOld;
            }
        }
        return new Room();
    }

    public Room updateRoom(Room room){
        int selection;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("\n\n\t\tWelcome to Room Update Menu\n\n");
            System.out.println("Choose the option you want to update\n1. Room Price\n2. Current Reservation Ids\n");
            System.out.print("\nYour choice : ");
            while(!sc.hasNextInt())
            {
                System.out.println("\n\nPlease enter a valid integer value.\n");
                System.out.print("\nYour choice : ");
                sc.next();
            }
            selection = sc.nextInt();
            if(selection==0)
                System.out.println("\n\nPlease select correct option.\n");
        }
        while (selection<=0);

        switch (selection)
        {
            case 1 :
                System.out.println("\n\nCurrent price of the Room is : "+ room.getRoomPrice());
                System.out.print("\n\nEnter the new price of the Room : ");
                sc = new Scanner(System.in);
                while(!sc.hasNextFloat())
                {
                    System.out.print("\n\nPlease enter a valid value : ");
                    sc.next();
                }
                Float price = sc.nextFloat();
                room.setRoomPrice(price);
                System.out.println("\n\nRoom Price is updated.\n\n");
                return room;
            case 2 :
                System.out.println("\n\nCurrent Reservation Id's of the Room is : "+ room.getCurrentReservationId());
                System.out.print("\n\nEnter the new Current Reservation Id's of the Room : ");
                sc = new Scanner(System.in);
                String ids = sc.nextLine();
                room.setCurrentReservationId(ids);
                System.out.println("\n\nReservation Id's are updated.\n\n");
                return room;
            default:
                System.out.println("\n\nYou seemed to have entered wrong option.\n\n");
                return room;
        }
    }



    public static void main(String args[])
    {

        //Main method is just to test and understand the function written for Room Service class.
        //Will remove the main method once the entire flow of project is finished.
        RoomService roomService = new RoomService();
        Room newroom1 = roomService.addRoomwithIdOnly();

        Vector<String> testvector = new Vector<String>();
        testvector.add("123");
        testvector.add("456");
        Room newroom2 = roomService.addRoomwithAllDetails("1",(float)100,"Deluxe","abc",testvector);


        newroom1.printRoomDetails();
        newroom2.printRoomDetails();


        Room newroom3 = roomService.addRoomwithAllDetails("1",(float)100,"Deluxe","abc",testvector);
//        Boolean status = roomService.updateRoom(newroom3);
//        if(status)
//        {
//            System.out.println("\n\n\t\tUpdated Room Details\n\n");
//            newroom3.printRoomDetails();
//        }
//        else
//        {
//            System.out.println("\n\nUpdate operation could not be performed.\n\n");
//            newroom3.printRoomDetails();
//        }
    }
}
