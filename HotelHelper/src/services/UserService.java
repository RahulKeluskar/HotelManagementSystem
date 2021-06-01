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

    public void userMainMenu(int choice, Vector<User> user, Vector<Room> rooms, Vector<Payment> payment,
            Vector<ReservedRoom> roomReservations) throws IOException {
        switch (choice) {
            case 1:
                this.addUser();
                break;
            case 2:
                this.getAllUsers();
                break;
            case 3:
                System.out.println("Enter the aadhar number of the user:");
                String aadhar = br.readLine();
                this.modifyUser(aadhar, user);
                break;
            case 4:// Delete
                break;
        }

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

        return user;
    }



    public User modifyUser(String id, Vector<User> user) {

        User userToModify = this.getUserById(id, user);

        if(!user.getAadharNo().isEmpty())
            userToModify.setAadharNo(user.getAadharNo());
        if(!user.getAge().isBlank())
            userToModify.setAge(user.getAge());
        if(!user.getStatus().isBlank())
            userToModify.setStatus(user.getStatus());
        if(!user.getAadharNo().isBlank())
            userToModify.setLocation(user.getLocation());
        return userToModify;
    }

    public List<User> getAllUsers(){
        return FileHandler.retrieveAllUsers();
    }

    public User deleteUser(String id){
        return null;
    }

    public User getUserById(String id){
        return null;
    }

}
