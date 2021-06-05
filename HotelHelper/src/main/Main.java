package main;

import db.FileHandler;
import entities.Payment;
import entities.ReservedRoom;
import entities.Room;
import entities.User;
import services.ReservationService;
import services.RoomService;
import services.UserService;
import util.CommonUtils;
import util.Constants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Vector;
import java.util.*;

public class Main {
    Vector<User> userList;
    Vector<Room> roomList;
    Vector<Payment> paymentList;
    Vector<ReservedRoom> roomReservationList;

    public static void main(String args[]) throws IOException {
        Main menuObject = new Main();
        menuObject.userList = new Vector<>();

        menuObject.paymentList = new Vector<>();
        menuObject.run();
    }



    BufferedReader br = new BufferedReader(new java.io.InputStreamReader((System.in)));

    public Main() {
        FileHandler fh = new FileHandler();
        userList = new Vector<User>(fh.retrieveAllUsers());
        roomList = new Vector<Room>(fh.retrieveAllRooms());
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
        stringBuffer.append("\n2. Check in using reservation ID");
        stringBuffer.append("\n3. Check out");
        stringBuffer.append("\n4. View reservation");
        stringBuffer.append("\n5. Update reservation Information");
        stringBuffer.append("\n6. Delete a reservation");
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
                this.userMainMenu(choice, userList, roomList, paymentList, roomReservationList);
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
                this.roomServiceMainMenu(choice);
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

                this.reservationMainMenu(choice);
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
                System.out.println("Enter 1 for Deluxe");
                System.out.println("Enter 2 for Suite");
                System.out.println("Enter 3 for NONAC");
                System.out.println("Enter 4 for Double Deluxe");
                String roomType = br.readLine();
                System.out.println("roomNumber: " + roomNumber);
                System.out.println("roomPrice: " + roomPrice);
                System.out.println("roomType: " + roomType);

                System.out.println("No reservations added for the room as of now.");
                Vector<String> reservationId = new Vector<String>();

                roomList.add(rs.addRoomwithAllDetails(roomNumber, roomPrice, roomType, "", reservationId));
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



    public Vector<User> userMainMenu(int choice, Vector<User> user, Vector<Room> rooms, Vector<Payment> payment,
                                     Vector<ReservedRoom> roomReservations) throws IOException {
        userList = user;
        roomList = rooms;
        paymentList = payment;
        roomReservationList = roomReservations;

        UserService us = new UserService();
        switch (choice) {
            case 1:
                System.out.println("Enter aadhar number");
                String ad = br.readLine();
                userList.add(us.addUser(ad));
                System.out.println("User Added");
                break;
            case 2:
                us.viewAllUsers(userList);
                break;
            case 3:
                System.out.println("Enter the aadhar number of the user:");
                String aadhar = br.readLine();
                User userOld = us.deleteUser(aadhar, userList);
                if (!userOld.getName().equals(null)) {
                    userList.add(us.enterModificationDetails(userOld));
                    System.out.println("User updated");
                    break;
                }
                System.out.println("Sorry, user does not exist");

                break;
            case 4:
                System.out.println("Enter the aadhar number of the user you want to delete");
                String aadharNo = br.readLine();
                User userO = us.deleteUser(aadharNo, userList);
                if (!userO.getName().equals(null)) {

                    System.out.println("User deleted");
                }
                System.out.println("User does not exist");
                break;
        }
        return userList;

    }

    public void checkout() throws IOException {
        System.out.println("Enter the room number for which you want to checkout");
        String a = br.readLine();
        for (int i = 0; i < roomList.size(); i++) {
            if (roomList.get(i).getRoomNumber().equalsIgnoreCase(a)) {
                roomList.get(i).setCurrentReservationId("");
                break;
            }
        }
    }

    public void createReservation()throws IOException{
        int choice;
        User user;
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader((System.in)));
        ReservationService resSer = new ReservationService();
        UserService userSer = new UserService();
        RoomService roomSer= new RoomService();
        Vector<String> ReservedList;
        Date startDate,endDate;
        System.out.print("1.Deluxe \n 2.Suite \n 3.NonAC \n 4.DoubleDeluxe");
        System.out.println("Select a type of room:  ");
        String input = br.readLine();
        while (true) 
        {
            if (menuInputValidator(input, 1, 4)) 
            {
                choice = Integer.parseInt(input);
                break;
            }
            System.out.println("Please enter valid input");
            input = br.readLine();
        }
        String d1,m1,y1,d2,m2,y2;
        CommonUtils common = new CommonUtils();
        
        while(true)
        {
            System.out.println("Enter the start date: ");
            d1 = br.readLine();
            System.out.println("Enter the start month: ");
            m1 = br.readLine();
            System.out.println("Enter the start year: ");
            y1 = br.readLine();
            if(common.validateJavaDate(d1+"/"+m1+"/"+y1))
                break;
            else
                System.out.println("Enter a valid date ");
        
        }
    
        while(true)
        {
            System.out.println("Enter the end date: ");
            d2 = br.readLine();
            System.out.println("Enter the end month: ");
            m2 = br.readLine();
            System.out.println("Enter the end year: ");
            y2 = br.readLine();
            if(common.validateJavaDate(d2+"/"+m2+"/"+y2))
            {
                Calendar cal = Calendar.getInstance();
                cal.set(Integer.parseInt(y1),Integer.parseInt(m1),Integer.parseInt(d1)); // Year, month and day of month
                startDate = (Date) cal.getTime();
                cal.set(Integer.parseInt(y2),Integer.parseInt(m2),Integer.parseInt(d2)); // Year, month and day of month
                endDate = (Date) cal.getTime();
                if(startDate.compareTo(endDate)>0)
                {
                    System.out.println("Start date cannot be greater than end date. Please enter a valid end date.");
                }
                else
                {
                    break;
                }
            }
            else
                System.out.println("Enter a valid date ");
    
        }
        Room room = new Room();
        ReservedList = resSer.getReservedIds( roomReservationList,startDate, endDate);
        for(int i=0;i<roomList.size();i++)
        {
            Vector<String> temp = roomList.get(i).getReservationId();
            boolean flag = true;
            for(int j=0;j<temp.size();j++)
            {
                if(ReservedList.contains(temp.get(i)))
                {
                    flag = false;
                    break;
                }
            }
            if(flag == true)
            {
                //room is empty
                room= roomList.get(i);
                break;
            }
        }
        System.out.println("Enter aadhar number");
        String ad = br.readLine();
        user = userSer.addUser(ad);
        System.out.println(" Do you want to confirm reservation?");
        System.out.println("1.Enter 1. for yes, 2. for no");
        input = br.readLine();
        while (true) 
        {
            if (menuInputValidator(input, 1, 2))
            {
                choice = Integer.parseInt(input);
                break;
            }
            System.out.println("Please enter valid input");
            input = br.readLine();
        }
        if(choice == 1)
        {
            userList.add(user);
            Vector<String> s = new Vector<String>();
            s.add(user.getAadharNo());
            String random = UUID.randomUUID().toString();
            Vector<String> ran = room.getReservationId();
            resSer.addReservation(random,room.getId(), s, startDate, endDate);
            roomSer.deleteRoom(room.getRoomNumber(), roomList, roomReservationList, false);
            ran.add(random);
            room.setReservationId(ran);
            roomList.add(room);
        }
        else
        {
            System.out.println("Thank you for considering us");
        }
    }

    public void checkIn() throws IOException {
        System.out.println("Enter the reservation Id to checkin");
        String a = br.readLine();
        String reservationId = "";
        String roomNo = "";
        for (int i = 0; i < roomReservationList.size(); i++) {
            if (roomReservationList.get(i).getId().equalsIgnoreCase(a)) {
                System.out.println("Enter the user details for all the people checking in");
                Vector<String> usersForReservation = roomReservationList.get(i).getUserId();

                while (true) {
                    System.out.println("Enter the aadhar number");
                    String aadhar = br.readLine();
                    UserService userSr = new UserService();
                    User newUser = userSr.getUserById(aadhar, userList);
                    if (!(newUser.getAadharNo().equals(null))) {
                        userSr.deleteUser(aadhar, userList);
                        newUser.setLocation(roomReservationList.get(i).getRoomId());
                    } else {
                        newUser = userSr.addUser(aadhar);
                        userList.add(newUser);
                        usersForReservation.add(newUser.getAadharNo());
                        newUser.setLocation(roomReservationList.get(i).getRoomId());
                        System.out.println("User Added");
                    }
                    System.out.println("Enter 1 to add another user");
                    String p = br.readLine();
                    if (p.equalsIgnoreCase("1")) {
                        break;
                    }
                }

                roomReservationList.get(i).setUserId(usersForReservation);
                reservationId = roomReservationList.get(i).getId();
                roomNo = roomReservationList.get(i).getRoomId();
                break;
            }

            if (!reservationId.equals(null)) {
                for (int k = 0; k < roomList.size(); k++) {
                    if (roomList.get(k).getRoomNumber().equalsIgnoreCase(roomNo)) {

                        roomList.get(k).setCurrentReservationId(reservationId);
                    }
                }
            }
        }

    }

    public void reservationMainMenu(int choice) throws IOException {
        ReservationService rs = new ReservationService();
        switch (choice) {
            case 1:
                this.createReservation();
                break;
            case 2:
                this.checkIn();

                break;
            case 3:
                this.checkout();

                break;
            case 4:// View

                break;
            case 5:
                System.out.println("Enter the reservation id for the reservation to be deleted");
                String reservat = br.readLine();
                ReservedRoom reserv = rs.deleteReservation(reservat, roomReservationList);
                if (!reserv.getId().equals(null)) {
//                    roomReservationList.add(rs.enterModificationDetails(reserv));
                    System.out.println("User updated");
                    break;
                }
                System.out.println("Sorry, user does not exist");

                break;
            case 6:
                System.out.println("Enter the reservation id for the reservation to be deleted");
                String reservation = br.readLine();
                ReservedRoom reser = rs.deleteReservation(reservation, roomReservationList);
                if (!reser.getId().equals(null)) {

                    System.out.println("Reservation deleted");
                }
                System.out.println("Reservation does not exist");
                break;

        }

    }


}
