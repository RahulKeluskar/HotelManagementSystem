package services;

import entities.Room;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class RoomService {
    public List<Room> getAllRooms() {
        return null;
    }

    public Room addRoomwithIdOnly(){
        Room newroom = new Room(UUID.randomUUID().toString());
        return newroom;
    }

    public Room addRoomwithAllDetails(String roomNumber, Float roomPrice, String roomType, String currentReservationIds){
        Room newroom = new Room(UUID.randomUUID().toString(),roomNumber,roomPrice,roomType,currentReservationIds);
        return newroom;
    }

    public Room deleteRoom(){

        //Will implement once DB is setup properly
        return null;
    }

    public Boolean updateRoom(Room room){
        int selection;
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\n\t\tWelcome to Room Update Menu\n\n");
        System.out.println("Choose the option you want to update\n1.Room Price\n2.Reservation Ids\n");
        System.out.print("\nYour choice : ");
        selection = sc.nextInt();
        switch (selection)
        {
            case 1 :
                System.out.println("\n\nCurrent price of the Room is : "+ room.getRoomPrice());
                System.out.print("\n\nEnter the new price of the Room : ");
                sc = new Scanner(System.in);
                Float price = sc.nextFloat();
                room.setRoomPrice(price);
                System.out.println("\n\nRoom Price is updated.\n\n");
                return true;
            case 2 :
                System.out.println("\n\nCurrent Reservation Id's of the Room is : "+ room.getCurrentReservationIds());
                System.out.print("\n\nEnter the new Reservation Id's of the Room : ");
                sc = new Scanner(System.in);
                String ids = sc.nextLine();
                room.setCurrentReservationIds(ids);
                System.out.println("\n\nReservation Id's are updated.\n\n");
                return true;
            default:
                System.out.println("You seemed to have entered wrong option.");
                return false;
        }
    }

    public static void main(String args[])
    {

        //Main method is just to test and understand the function written for Room Service class.
        //Will remove the main method once the entire flow of project is finished.
        RoomService roomService = new RoomService();
        Room newroom1 = roomService.addRoomwithIdOnly();
        Room newroom2 = roomService.addRoomwithAllDetails("1",(float)100,"Deluxe","abc");


        newroom1.printRoomDetails();
        newroom2.printRoomDetails();


        Room newroom3 = roomService.addRoomwithAllDetails("1",(float)100,"Deluxe","abc");
        Boolean status = roomService.updateRoom(newroom3);
        if(status)
        {
            System.out.println("\n\n\t\tUpdated Room Details\n\n");
            newroom3.printRoomDetails();
        }
        else
        {
            System.out.println("\n\nUpdate operation could not be performed.\n\n");
            newroom3.printRoomDetails();
        }
    }
}
