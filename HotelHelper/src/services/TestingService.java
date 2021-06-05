package services;

import db.FileHandler;
import entities.Room;
import entities.User;
import util.CommonUtils;
import util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestingService {
    private UserService users;
    private RoomService rooms;
    private FileHandler fileHandler;
    private final int LIMIT = 500;

    TestingService(){
        users = new UserService();
        rooms = new RoomService();
        fileHandler = new FileHandler();
    }

    public void insertUsersAndRooms() throws IOException {
        List<User> listOfDummyUsers = new ArrayList<>();
        for(int i=0; i < LIMIT; i++){
            FileHandler.insertSingleUserIntoFile(this.returnDummyUserData());
            rooms.addRoomwithAllDetails(Integer.toString(i),
                    Constants.Testing.DUMMY_ROOM_PRICE,
                    Constants.Testing.DUMMY_ROOM_TYPE,
                    Constants.Testing.DUMMY_R_ID,
                    Constants.Testing.DUMMY_RIV_LIST);

        }
    }
    public String createRandomString(){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }
    private String generateRandomAadhar(){
        return this.createRandomString();
    }
    private User returnDummyUserData(){
        User user = new User();
        user.setName(this.createRandomString());
        user.setLocation(Constants.Testing.DUMMY_USER_LOCATION);
        user.setAadharNo(this.generateRandomAadhar());
        user.setAge(Integer.toString(Constants.Testing.DUMMY_USER_AGE));
        user.setStatus(Integer.toString(Constants.Testing.DUMMY_STATUS));
        user.setId(CommonUtils.generatePrimaryKey());
        return user;
    }

    public static void main(String args[]) throws IOException {
        TestingService testingService = new TestingService();
        testingService.insertUsersAndRooms();
    }
}
