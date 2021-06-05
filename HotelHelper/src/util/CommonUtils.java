package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.*;

public class CommonUtils {

    public static String acceptNewInput() throws IOException {
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }

    public static String generatePrimaryKey(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
    public static String randomRoomName() {
        String ALPHA_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String NUMERIC_STRING = "0123456789";
        StringBuilder builder = new StringBuilder();
        int count = 3;
        int character = (int)(Math.random()*ALPHA_STRING.length());
        builder.append(ALPHA_STRING.charAt(character));
        while (count-- != 0) {
        int charac = (int)(Math.random()*NUMERIC_STRING.length());
        builder.append(NUMERIC_STRING.charAt(charac));
        }
        return builder.toString();
        }
    public boolean validateJavaDate(String strDate) {
        /* Check if date is 'null' */
        if (strDate.trim().equals("")) {
            return true;
        }
        /* Date is not 'null' */
        else {
            /*
             * Set preferred date format, For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.
             */
            SimpleDateFormat sdfrmt = new SimpleDateFormat("MM/dd/yyyy");
            sdfrmt.setLenient(false);
            /*
             * Create Date object parse the string into date
             */
            try {
                Date javaDate = sdfrmt.parse(strDate);
                // System.out.println(strDate+" is valid date format");
            }
            /* Date format is invalid */
            catch (ParseException e) {
                // System.out.println(strDate+" is Invalid Date format");
                return false;
            }
            /* Return true if date format is valid */
            return true;
        }
    }
}
