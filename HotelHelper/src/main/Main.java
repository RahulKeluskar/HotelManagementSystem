package main;

import util.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String args[]) throws IOException {
        Main menuObject = new Main();
        menuObject.run();
    }

    public String getMainMenuString(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Welcome to the hotel management portal:\nPlease select from one of the options given below\n");
        stringBuffer.append("1. User Management ((Update, Delete or View User Information)");
        stringBuffer.append("2. Room Management (Update, Delete or View Room Information)");
        stringBuffer.append("3. Room Reservation");
        stringBuffer.append("4. Display Payment information");
        return stringBuffer.toString();
    }

    public String getUserMenuString(){
        return null;
    }

    public String getRoomMenuString(){
        return null;
    }

    public String getReservationMenuString(){
        return null;
    }


    public Boolean menuInputValidator(String input, int begin, int end){
        int inputInteger = 0;
        try{
            Integer.parseInt(input);
            if(inputInteger >= begin && inputInteger <= end){
                return true;
            }
        }catch(NumberFormatException e){
            System.out.println("Sorry this input is invalid");
        }
        return false;
    }

    public void run() throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        System.out.println(this.getMainMenuString());
        String input = br.readLine();
        Integer choice;
        while(menuInputValidator(input, Constants.Menu.optionOne, Constants.Menu.optionFour)){
            choice = Integer.parseInt(input);
        }
    }
}
