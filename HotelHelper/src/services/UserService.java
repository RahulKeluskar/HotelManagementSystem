package services;

import db.FileHandler;
import entities.Payment;
import entities.ReservedRoom;
import entities.Room;
import entities.User;
import util.CommonUtils;
import validations.UserValidations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Vector;

import static util.CommonUtils.acceptNewInput;
import static validations.UserValidations.validateUserAge;

public class UserService {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Vector<User> userList;
    Vector<Room> roomList;
    Vector<Payment> paymentList;
    Vector<ReservedRoom> roomReservationList;

    public Vector<User> userMainMenu(int choice, Vector<User> user, Vector<Room> rooms, Vector<Payment> payment,
            Vector<ReservedRoom> roomReservations) throws IOException {
        userList = user;
        roomList = rooms;
        paymentList = payment;
        roomReservationList = roomReservations;
        switch (choice) {
            case 1:
                userList.add(this.addUser());
                System.out.println("User Added");
                break;
            case 2:
                this.viewAllUsers(userList);
                break;
            case 3:
                System.out.println("Enter the aadhar number of the user:");
                String aadhar = br.readLine();
                User userOld = this.deleteUser(aadhar, userList);
                if (!userOld.getName().equals(null)) {
                    userList.add(enterModificationDetails(userOld));
                    System.out.println("User updated");
                }
                System.out.println("Sorry, user does not exist");

                break;
            case 4:
                System.out.println("Enter the aadhar number of the user you want to delete");
                String aadharNo = br.readLine();
                User userO = this.deleteUser(aadharNo, userList);
                if (!userO.getName().equals(null)) {

                    System.out.println("User deleted");
                }
                System.out.println("User does not exist");
                break;
        }
        return userList;

    }

    public User addUser() throws IOException {
        User user = new User();

        System.out.println("Enter the name of the user");
        user.setName(acceptNewInput());
        System.out.println("Enter the age of the user");
        user.setAge(validateUserAge(acceptNewInput()));
        System.out.println("Enter the aadhar number of the user");
        user.setAadharNo(acceptNewInput());
        System.out.println("Enter the room number of the user");
        user.setLocation(acceptNewInput());
        System.out.println("Enter the status of the user");
        user.setStatus(acceptNewInput());
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

    // public User modifyUser(String id, Vector<User> user) {

    // // User userToModify = this.getUserById(id, user);

    // // if(!user.getAadharNo().isEmpty())
    // // userToModify.setAadharNo(user.getAadharNo());
    // // if(!user.getAge().isBlank())
    // // userToModify.setAge(user.getAge());
    // // if(!user.getStatus().isBlank())
    // // userToModify.setStatus(user.getStatus());
    // // if(!user.getAadharNo().isBlank())
    // // userToModify.setLocation(user.getLocation());
    // return userToModify;
    // }

    public void viewAllUsers(Vector<User> users) {

        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i).toString());
        }

        // return FileHandler.retrieveAllUsers();

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

    public User getUserById(String id){

        return null;
    }

}
