package db;

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
}

