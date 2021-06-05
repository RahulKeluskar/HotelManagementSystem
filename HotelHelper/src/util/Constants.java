package util;

import java.util.Vector;

public class Constants {

    public interface User{
        String relativePathToFile = "C:/Users/I513949/Documents/c";
    }

    interface Room{
        interface RoomType{
            Integer Deluxe = 1;
            Integer Suite = 2;
            Integer NonAC = 3;
            Integer DoubleDeluxe = 4;

            String DELUXE = "Deluxe";
            String SUITE = "Suite";
            String NONAC = "NONAC";
            String DOUBLEDELUXE = "Double Deluxe";
        }
    }

    public interface Menu{
        Integer optionOne = 1;
        Integer optionFour = 4;
    }


    public interface Testing{
        String DUMMY_USER_LOCATION = "Dummy location";
        int DUMMY_USER_AGE = 21;
        int LENGTH_OF_AADHAR = 12;
        int DUMMY_STATUS = 1;
        Float DUMMY_ROOM_PRICE=1500.00f;
        String DUMMY_ROOM_TYPE="DELUXE";
        String DUMMY_R_ID=null;
        Vector<String> DUMMY_RIV_LIST = null;
    }

    // Add function to modify details for reservation service
    // add function to view reservation details
    // find user is in which room

}
