package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

public class CommonUtils {

    public static String acceptNewInput() throws IOException {
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }

    public static String generatePrimaryKey(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
