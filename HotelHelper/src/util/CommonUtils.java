package util;

import java.util.UUID;

public class CommonUtils {

    public String generatePrimaryKey(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
