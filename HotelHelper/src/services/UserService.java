package services;

import db.FileHandler;
import entities.Payment;
import entities.ReservedRoom;
import entities.Room;
import entities.User;
import util.CommonUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import static util.CommonUtils.acceptNewInput;
import static validations.UserValidations.validateUserAge;

public class UserService {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Vector<User> userList;
    Vector<Room> roomList;
    Vector<Payment> paymentList;
    Vector<ReservedRoom> roomReservationList;



    public User addUser(String aadhar) throws IOException {
        User user = new User();

        System.out.println("Enter the name of the user");
        user.setName(acceptNewInput());
        System.out.println("Enter the age of the user");
        user.setAge(validateUserAge(acceptNewInput()));
        user.setAadharNo(aadhar);
        System.out.println("Enter the room number of the user");
        user.setLocation(acceptNewInput());
        user.setStatus("");
        user.setId(CommonUtils.generatePrimaryKey());
        FileHandler.insertSingleUserIntoFile(user);
        return user;
    }

    public User enterModificationDetails(User user) throws IOException {

        System.out.println("Enter 1 to modify the user name");
        System.out.println("Enter 2 to modify the age of the user");
        System.out.println("Enter 3 to modify the aadhar number of the user");
        System.out.println("Enter 4 to modify the room number of the user");
        System.out.println("Enter 5 to modify the status of the user");
        String a = br.readLine();
        int choice = 0;
        while (true) {

            if (this.menuInputValidator(a, 1, 5)) {
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
                user.setName(val);
                break;
            case 2:
                user.setAge(validateUserAge(val));
                break;
            case 3:
                user.setAadharNo(val);
                break;
            case 4:
                user.setLocation(val);
                break;
            case 5:
                user.setStatus(val);
                break;
        }
        return user;
    }

    public Boolean menuInputValidator(String input, int begin, int end) {
        int inputInteger = 0;
        try {
            inputInteger = Integer.parseInt(input);
            if (inputInteger >= begin && inputInteger <= end) {
                return true;
            }
        } catch (NumberFormatException e) {
            System.out.println("Sorry this input is invalid");
        }
        return false;
    }



    public void viewAllUsers() {

        Vector<User> users = FileHandler.retrieveAllUsers();
        for(User userPirnting: users){
            System.out.println(userPirnting.toString());
            System.out.println("******\t*******\t********");
        }
    }


    public User deleteUser(String id, Vector<User> user) {
        for (int i = 0; i < user.size(); i++) {
            if (user.get(i).getAadharNo().equalsIgnoreCase(id)) {
                User userOld = user.get(i);
                user.remove(i);
                return userOld;
            }
        }
        return new User();
    }

    public User getUserById(String id, Vector<User> userList) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getAadharNo().equalsIgnoreCase(id)) {
                return userList.get(i);
            }
        }
        return new User();

    }

}
