package validations;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static util.CommonUtils.acceptNewInput;

public class UserValidations {

    public static String validateUserAge(String age) throws IOException {
        try{
            int number = Integer.parseInt(age);
            if(number>0){
                return age;
            }else{
                return validateUserAge(age);
            }
        }catch(Exception e){
            System.out.println("Please enter a valid numeric age: ");
            return validateUserAge(acceptNewInput());
        }
    }

    public String validateUserAadhar(String aadhar){
        return aadhar;
    }

    public String validateUserStatus(String status){
        return null;
    }
}
