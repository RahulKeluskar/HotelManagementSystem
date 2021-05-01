package util;

public class Constants {

    interface User{

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
}
