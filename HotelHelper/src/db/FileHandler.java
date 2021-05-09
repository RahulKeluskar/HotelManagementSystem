package db;

import entities.User;
import util.Constants;

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
	public void readReservationFile(String fileName)
	{
		
		try{
		      FileInputStream fis = new FileInputStream(new File(".\\"+fileName));
		      ObjectInputStream ois = new ObjectInputStream(fis);
		      while(true)
		      {
		    	  ReservedRoom ob  = (ReservedRoom)ois.readObject();
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
//		try {
//			 File file = new File(".\\"+fileName);
//		      FileOutputStream fos = new FileOutputStream(file,true);
//		      if(!file.exists())
//		      {
//		    	  System.out.println("File doesn't exist");
//		      }
//		      ObjectOutputStream oos = new ObjectOutputStream(fos);
//		      // Write objects to file
//		      oos.writeObject(rs);
//		      oos.flush();
//		      oos.close();
//		      fos.close();
//			
//		      
//		    }
//		    catch (IOException e){
//		      e.printStackTrace();
//		    }
		
		//Correct this method. appending not working
	}
  
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

