package db;

import entities.Room;
import entities.User;
import util.Constants;
import entities.Payment;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import entities.ReservedRoom;
import java.util.*;
import java.io.*;

import entities.ReservedRoom;

public class FileHandler implements java.io.Serializable{
	
	public void writeReservationFile(Vector<ReservedRoom> reservations, String fileName)
	{
		
		 try {
             File tmpDir = new File(".\\" + fileName);
             boolean exists = tmpDir.exists();
			 File file = new File(".\\"+fileName);
			 if (!file.exists()) {
			     file.createNewFile();
			  }
		      FileOutputStream fos = new FileOutputStream(file);
		      ObjectOutputStream oos = new ObjectOutputStream(fos);
		      // Write objects to file
		      for (int i = 0;i< reservations.size();i++)
				{
		    	  oos.writeObject(reservations.get(i));
				}
		      oos.flush();
		      oos.close();
		      fos.close();
		    }
		    catch (IOException e){
		      e.printStackTrace();
		    }
	}
    public void writeRoomFile(Vector<Room> rooms, String fileName)
    {

        try {
            File file = new File(".\\"+"Rooms.txt");
            if (!file.exists()) {
                file.createNewFile();
            }else{
                file.delete();
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // Write objects to file
            for (int i = 0;i< rooms.size();i++)
            {
                oos.writeObject(rooms.get(i));
            }
            oos.flush();
            oos.close();
            fos.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
	public Vector<ReservedRoom> readReservationFile(String file)
	{
		String fileName= "reservations";
        Vector<ReservedRoom>  res= new Vector<ReservedRoom>();
		try{
		      FileInputStream fis = new FileInputStream(new File(".\\"+fileName));
		      ObjectInputStream ois = new ObjectInputStream(fis);
		      while(true)
		      {
		    	  ReservedRoom ob  = (ReservedRoom)ois.readObject();
		    	  if(ob!=null)
                    res.add(ob);
		    		//   System.out.println(ob.toString());
		    	  else
		    		  break;
			      
		      }
              fis.close();
		      ois.close();
              return res;
		     
		    }catch(Exception ex){
		      
		    }
            return res;
	}
    public void readRoomFile(String fileName)
    {

        try{
            FileInputStream fis = new FileInputStream(new File(".\\"+fileName));
            ObjectInputStream ois = new ObjectInputStream(fis);
            while(true)
            {
                Room ob  = (Room)ois.readObject();
                if(ob!=null)
                    System.out.println(ob.toString());
                else
                    break;

            }

            fis.close();
            ois.close();
        }catch(Exception ex){

        }
    }

	public void appendReservationToFile(ReservedRoom rs , String fileName)
	{
	    /*
	    The file must exist for this method to work that is the only catch
	    File output stream class adds stream headers every time there is a new entry added to file
	    That's why we are using our overridden class so that so that new stream headers aren't added everytime and we can read
	    the content of the file
	     */
        try(AppendableObjectOutputStream oos =
                    new AppendableObjectOutputStream(new FileOutputStream(fileName, true))){
            oos.writeObject(rs);
            System.out.println("Successfully Inserted");
            oos.flush();
            oos.reset();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

  
   public static void insertUsersIntoFile(List<User> users){
        try(AppendableObjectOutputStream oos =
                new AppendableObjectOutputStream(new FileOutputStream("Users", true))) {
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

    public void writeSingleReservation(ReservedRoom reservation) throws IOException {
        File tmpDir = new File(".\\reservations");
        boolean exists = tmpDir.exists();

        if(!exists) {
            tmpDir.createNewFile();
            ObjectOutputStream fos = new ObjectOutputStream(new FileOutputStream(tmpDir));
            fos.writeObject(reservation);
            System.out.println("Created file and inserted entry");
            fos.flush();
            fos.close();
        }
        try(AppendableObjectOutputStream oos =
                    new AppendableObjectOutputStream(new FileOutputStream(".\\reservations", true))) {
            oos.writeObject(reservation);
            System.out.println("Successfully Inserted");
            oos.flush();
            oos.reset();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String retrieveAllReservations(){
	    StringBuffer output = new StringBuffer();

        Vector<ReservedRoom> reservationList = new Vector<>();
        try {
            FileInputStream fis = new FileInputStream(new File(".\\reservations"));
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                ReservedRoom ob = (ReservedRoom) ois.readObject();
                if (ob != null) {
                    output.append(ob.toString());
                } else
                    break;

            }
            fis.close();
            ois.close();
            return output.toString();
        } catch (Exception ex) {

        }


	    return output.toString();
    }
    public static void insertSingleUserIntoFile(User user) throws IOException {
        File tmpDir = new File("Users");
        boolean exists = tmpDir.exists();

        if(!exists) {
            tmpDir.createNewFile();
            ObjectOutputStream fos = new ObjectOutputStream(new FileOutputStream(tmpDir));
            fos.writeObject(user);
            System.out.println("Created file and inserted entry");
            fos.flush();
            fos.close();
        }
        try(AppendableObjectOutputStream oos =
                new AppendableObjectOutputStream(new FileOutputStream("Users", true))) {
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
    public static void insertSingleRoomIntoFile(Room room) throws IOException {
        File tmpDir = new File("Rooms.txt");
        boolean exists = tmpDir.exists();

        if(!exists) {
            tmpDir.createNewFile();
            ObjectOutputStream fos = new ObjectOutputStream(new FileOutputStream(tmpDir));
            fos.writeObject(room);
            System.out.println("Created file and inserted entry");
            fos.flush();
            fos.close();
        }
        try(AppendableObjectOutputStream oos =
                    new AppendableObjectOutputStream(new FileOutputStream("Rooms.txt", true))) {
            oos.writeObject(room);
            System.out.println("Successfully Inserted");
            oos.flush();
            oos.reset();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // public static List<User> retrieveAllUsers(){
    // List<User> allUsers = new ArrayList<>();
    // boolean cont = true;
    // try(ObjectInputStream ois =
    // new ObjectInputStream(new FileInputStream("Users.txt"))) {
    // Object obj= null;
    // while((obj = (User) ois.readObject()) != null){
    // if(obj instanceof User){
    // allUsers.add((User)obj);
    // }
    // }

    // } catch (FileNotFoundException e) {
    // e.printStackTrace();
    // } catch (IOException e) {
    // // Will throw the end of file exception suggesting file read is over
    // System.out.println("Reached the end of file");
    // } catch (ClassNotFoundException e) {
    // e.printStackTrace();
    // }
    // return allUsers;
    // }

    public static Vector<User> retrieveAllUsers() {
        Vector<User> userList = new Vector<User>();
        try {
            FileInputStream fis = new FileInputStream(new File("Users"));
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                User ob = (User) ois.readObject();
                if (ob != null) {
                    userList.add(ob);
                } else
                    break;

            }
            fis.close();
            ois.close();
            return userList;
        } catch (Exception ex) {

        }
        return userList;
    }
    public static Vector<Room> retrieveAllRooms() {
        Vector<Room> roomList = new Vector<Room>();
        try {
            FileInputStream fis = new FileInputStream(new File("Rooms.txt"));
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                Room ob = (Room) ois.readObject();
                if (ob != null) {
                    roomList.add(ob);
                } else
                    break;

            }
            fis.close();
            ois.close();
            return roomList;
        } catch (Exception ex) {

        }
        return roomList;
    }


    public Room findSingleRoomById(String id){
        try {
            FileInputStream fis = new FileInputStream(new File("Rooms.txt"));
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                Room ob = (Room) ois.readObject();
                if (ob != null) {
                    if(ob.getId().equals(id)){
                        return ob;
                    }
                } else
                    break;

            }
            fis.close();
            ois.close();
            return null;
        } catch (Exception ex) {

        }
        return null;
    }
    public ReservedRoom findSingleReservationById(String id){
        try {
            FileInputStream fis = new FileInputStream(new File(".\\reservations"));
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                ReservedRoom ob = (ReservedRoom) ois.readObject();
                if (ob != null) {
                    if(ob.getId().equals(id)){
                        return ob;
                    }
                } else
                    break;

            }
            fis.close();
            ois.close();
            return null;
        } catch (Exception ex) {

        }
        return null;
    }
    public void insertPaymentInfo(){

    }

    public static User updateUser(String id){
        return null;
    }

    public static User deleteUser(String id){
        return null;
    }

    public void insertPayment(Payment payment) throws IOException {
        File tmpDir = new File("Payment.txt");
        boolean exists = tmpDir.exists();

        if(!exists) {
            tmpDir.createNewFile();
            ObjectOutputStream fos = new ObjectOutputStream(new FileOutputStream(tmpDir));
            fos.writeObject(payment);
            System.out.println("Created file and inserted entry");
            fos.flush();
            fos.close();
        }
        try(AppendableObjectOutputStream oos =
                    new AppendableObjectOutputStream(new FileOutputStream("Payment.txt", true))) {
            oos.writeObject(payment);
            System.out.println("Successfully Inserted");
            oos.flush();
            oos.reset();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

