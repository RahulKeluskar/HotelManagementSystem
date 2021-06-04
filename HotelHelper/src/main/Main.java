package main;

import db.FileHandler;
import entities.Payment;
import entities.ReservedRoom;
import entities.Room;
import entities.User;
import services.ReservationService;
import services.RoomService;
import services.UserService;
import util.Constants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String args[]) throws IOException {
        Main menuObject = new Main();

        menuObject.run();
    }

    Vector<User> userList;
    Vector<Room> roomList;
    Vector<Payment> paymentmentList;
    Vector<ReservedRoom> roomReservationList;
    BufferedReader br = new BufferedReader(new java.io.InputStreamReader((System.in)));

    public Main() {
        FileHandler fh = new FileHandler();
        userList = new Vector<User>(fh.retrieveAllUsers());
    }

    public String getMainMenuString(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Welcome to the hotel management portal:\nPlease select from one of the options given below\n");

        stringBuffer.append("1. User Management ((Update, Delete or View User Information)");
        stringBuffer.append("\n2. Room Management (Update, Delete or View Room (Occupied/Free) Information)");
        stringBuffer.append("\n3.  Reservation Management(Add/Cancel/Update/View)");
        stringBuffer.append("\n4.  Payment Management");
        stringBuffer.append("\n  Any other number to exit");

        return stringBuffer.toString();
    }

    public String getUserMenuString(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Select an option to proceed further\n");
        stringBuffer.append("\n1. Add a new user");
        stringBuffer.append("\n2. View all users");
        stringBuffer.append("\n3. Update User Information");
        stringBuffer.append("\n4. Delete a user");

        return stringBuffer.toString();
    }

    public String getRoomMenuString(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Select an option to proceed further\n");
        stringBuffer.append("\n1. Add a new room");
        stringBuffer.append("\n2. View rooms");
        stringBuffer.append("\n3. Update room Information");
        stringBuffer.append("\n4. Delete a room");

        return stringBuffer.toString();
    }

    public String getReservationMenuString(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Select an option to proceed further\n");
        stringBuffer.append("\n1. Add a new reservation");
        stringBuffer.append("\n2. View reservation");
        stringBuffer.append("\n3. Update reservation Information");
        stringBuffer.append("\n4. Delete a reservation");
        return stringBuffer.toString();
    }

    public String getPaymentMenuString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Select an option to proceed further\n");
        stringBuffer.append("\n1. Make a payement");
        stringBuffer.append("\n2. View payment history");
        return stringBuffer.toString();
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

    public void run() throws IOException, NumberFormatException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        while (true) {
        System.out.println(this.getMainMenuString());
        String input = br.readLine();
        int choice = 0;
        while (true) {
            if (menuInputValidator(input, -9999, 9999)) {
                choice = Integer.parseInt(input);
                break;
            }
            System.out.println("Please enter valid input");
            input = br.readLine();
        }
        if (choice < 1 || choice >= 5) {
            System.out.println("Logging out...");
            break;
        }
        // insertNewUser();
        switch (choice) {
            case 1:
                System.out.println(this.getUserMenuString());
                input = br.readLine();
                choice = 0;
                while (true) {
                    if (menuInputValidator(input, Constants.Menu.optionOne, Constants.Menu.optionFour)) {
                        choice = Integer.parseInt(input);
                        break;
                    }
                }
                UserService ob = new UserService();
                ob.userMainMenu(choice, userList, roomList, paymentmentList, roomReservationList);
                // User Service menu call
                break;
            case 2: // Room Management
                System.out.println(this.getRoomMenuString());
                input = br.readLine();

                while (true) {
                    if (menuInputValidator(input, Constants.Menu.optionOne, Constants.Menu.optionFour)) {
                        choice = Integer.parseInt(input);
                        break;
                    }
                }
                // RoomService ob1 = new RoomService();

                // Room Service menu call
                break;
            case 3:// Reservation Management
                System.out.println(this.getReservationMenuString());
                input = br.readLine();

                while (true) {
                    if (menuInputValidator(input, Constants.Menu.optionOne, Constants.Menu.optionFour)) {
                        choice = Integer.parseInt(input);
                        break;
                    }
                }
                ReservationService res = new ReservationService();
                res.reservationMainMenu(choice);
                // Reservation Service menu call
                break;
            case 4:// Payment Management
                System.out.println(this.getPaymentMenuString());
                input = br.readLine();

                while (true) {
                    if (menuInputValidator(input, Constants.Menu.optionOne, Constants.Menu.optionFour)) {
                        choice = Integer.parseInt(input);
                        break;
                    }
                }
                // Payment Service menu call
                break;
            default:

        }
    }

    }

    public void roomServiceMainMenu(int choice) throws IOException {

        RoomService rs = new RoomService();
        Scanner sc = new Scanner(System.in);
        switch (choice) {
            case 1:// Add Room
                //paramateres to be added

                System.out.println("Enter the room number : " );
                String roomNumber = br.readLine();

                System.out.println("Enter the Room Price : ");
                sc = new Scanner(System.in);
                while(!sc.hasNextFloat())
                {
                    System.out.print("\n\nPlease enter a valid value : ");
                    sc.next();
                }
                Float roomPrice = sc.nextFloat();

                System.out.println("Enter the room type : " );
                String roomType = br.readLine();

                System.out.println("Enter the current reservation Id's : " );
                String currentReservationIds = br.readLine();


                System.out.println("Currently the Reservation Id is null as the room.");
                Vector<String> reservationId = new Vector<String>();

                roomList.add(rs.addRoomwithAllDetails(roomNumber,roomPrice,roomType,currentReservationIds,reservationId));
                break;
            case 2:// View
                rs.viewAllRooms(roomList);
                break;
            case 3:// Update
                // find room object with a specific id and pass it as parameters

                System.out.println("Enter the room number : ");
                String oldroomNumber = br.readLine();
                Room roomOld = rs.deleteRoom(oldroomNumber, roomList,roomReservationList,false);
                if (!roomOld.getId().equals(null)) {
                    roomList.add(rs.updateRoom(roomOld));
                    System.out.println("Room updated");
                    break;
                }
                System.out.println("Sorry, room does not exist");
                break;
            case 4:// Delete
                System.out.println("Enter the room number : ");
                String roomnumber = br.readLine();
                rs.deleteRoom(roomnumber,roomList,roomReservationList,true);
                break;
        }
    }

    public void insertNewUser() throws IOException {
        UserService userService = new UserService();
        FileHandler.insertSingleUserIntoFile(userService.addUser());
    }

//    public void getAllUsers(){
//        UserService userService = new UserService();
//        for(User user: userService.getAllUsers() ){
//            System.out.println(user);
//        }
//    }
}
