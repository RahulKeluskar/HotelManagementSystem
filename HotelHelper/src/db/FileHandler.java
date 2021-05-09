package db;

import entities.User;
import util.Constants;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    public static void insertUsersIntoFile(List<User> users){
        try(ObjectOutputStream oos =
                    new ObjectOutputStream(new FileOutputStream(Constants.User.relativePathToFile))){
            for(User user: users)
            {
                oos.writeObject(user);
                System.out.println("Successfully Inserted");
            }
            oos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void insertSingleUserIntoFile(User user){
        try(AppendableObjectOutputStream oos =
                    new AppendableObjectOutputStream(new FileOutputStream(Constants.User.relativePathToFile, true))){
            oos.writeObject(user);
            System.out.println("Successfully Inserted");
            oos.flush();
            oos.reset();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<User> retrieveAllUsers(){
        List<User> allUsers = new ArrayList<>();
        boolean cont = true;
        try(ObjectInputStream ois =
                    new ObjectInputStream(new FileInputStream(Constants.User.relativePathToFile))){
            Object obj= null;
            while((obj = (User) ois.readObject()) != null){
                if(obj instanceof User){
                    allUsers.add((User)obj);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // Will throw the end of file exception suggesting file read is over
            System.out.println("Reached the end of file");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    public static User updateUser(String id){
        return null;
    }

    public static User deleteUser(String id){
        return null;
    }
}
