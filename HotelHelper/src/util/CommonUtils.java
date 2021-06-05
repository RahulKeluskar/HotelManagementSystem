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
